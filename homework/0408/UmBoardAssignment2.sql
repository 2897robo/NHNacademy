drop database if exists ummDatabase2;

create database ummDatabase2;
use ummDatabase2;

CREATE TABLE Member
(
    userid   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '관리번호',
    email    VARCHAR(200) NOT NULL COMMENT '이메일',
    password VARCHAR(20)  NOT NULL COMMENT '비밀번호',
    role     VARCHAR(20)  NOT NULL DEFAULT 'User' COMMENT '회원:User, 관리자:Admin',
    nickname VARCHAR(100) NOT NULL COMMENT '닉네임'
);

CREATE TABLE Category
(
    CategoryID   BIGINT NOT NULL PRIMARY KEY COMMENT '게시판 식별 ID',
    CategoryName VARCHAR(10)
);

CREATE TABLE Post
(
    postid     BIGINT        NOT NULL auto_increment,
    userid     BIGINT        NOT NULL,
    CategoryID BIGINT,
    createAt   DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '게시글 생성시간',
    updatedAt  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정시간',
    Title      VARCHAR(100)  NOT NULL COMMENT '제목',
    content    VARCHAR(1000) NOT NULL COMMENT '본문',
    viewCnt    BIGINT                 DEFAULT 0 COMMENT '조회수',
    PRIMARY KEY (postid),
    FOREIGN KEY (userid) REFERENCES Member (userid) ON DELETE CASCADE,
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID) ON DELETE CASCADE
);

CREATE TABLE Comment
(
    commentId  BIGINT        NOT NULL auto_increment,
    postid     BIGINT        NOT NULL,
    userid     BIGINT        NOT NULL,
    CategoryID BIGINT,
    content    VARCHAR(1000) NOT NULL COMMENT '댓글 본문',
    PRIMARY KEY (commentId),
    FOREIGN KEY (postid) REFERENCES Post (postid) ON DELETE CASCADE,
    FOREIGN KEY (userid) REFERENCES Member (userid) ON DELETE CASCADE,
    FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID) ON DELETE CASCADE
);

#카테고리 정보 삽입
INSERT INTO Category (CategoryID, CategoryName)
VALUES (1, '공지사항'),
       (2, '자유게시판'),
       (3, '가입인사 게시판');

#Insert Dummy Member
insert into Member (email, password, role, nickname)
values ('chaesc1223@naver.com', '1234', 'ADMIN', '채정훈');
insert into Member (email, password, role, nickname)
values ('chaesc1223@nate.com', '1234', 'ADMIN', '관리자2');
insert into Member (email, password, nickname)
values ('KIA@naver.com', '1234', '기아인턴');
insert into Member (email, password, nickname)
values ('HyunDai@naver.com', '1234', '현대인턴');
insert into Member (email, password, nickname)
values ('GIUKGIUK@naver.com', '1234', '김기욱');
insert into Member (email, password, nickname)
values ('hoho123@naver.com', '1234', '김채호');

#Insert Dummy Post
insert into Post (userid, CategoryID, Title, content)
VALUES (1, 1, '공지글', '공지사항 작성글');
insert into Post (userid, CategoryID, Title, content)
VALUES (2, 1, '공지글', '공지사항 작성글');
insert into Post (userid, CategoryID, Title, content)
VALUES (3, 2, '기아자게글', '기아인턴 작성글');
insert into Post (userid, CategoryID, Title, content)
VALUES (3, 2, '현대자게글', '현대인턴 작성글');
insert into Post (userid, CategoryID, Title, content)
VALUES (4, 3, '김기욱소개글', '김기욱 인삿글');
insert into Post (userid, CategoryID, Title, content)
VALUES (5, 3, '김채호소개글', '김채호 인삿글');

delete
FROM Post
where postid = 2;

update Post
set content   = '수정Test',
    updatedAt = CURRENT_TIMESTAMP
where userid = 3;

#게시글 관리자인 경우 삽입 트랜잭션 / 나머지 일반 이용자면 트랜잭션 막힘.
START TRANSACTION;
-- 유저 정보를
SET @role := (SELECT role
              FROM Member
              WHERE userid = 1);
-- 게시물을 삽입합니다.
INSERT INTO Post (userid, CategoryID, Title, content)
VALUES (IF(@role = 'Admin', 1, NULL), -- 관리자인 경우 해당 userid 로 아이디를 설정 아니면 null 처리로 트랜잭션을 막아
        1, -- 공지사항 게시판 식별 ID
        '가입인사 게시판 이용수칙', -- 제목
        '이용수칙' -- 본문
       );
COMMIT;

#일반인 게시물 삽입 경우 트랜잭션
START TRANSACTION;
-- 게시물을 삽입합니다.
INSERT INTO Post (userid, CategoryID, Title, content)
VALUES (5,
        2, -- 게시판 식별 ID
        '자유게시판ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ뭐함', -- 제목
        'ㅈㄱㄴ' -- 본문
       );

COMMIT;

#조회수 증가 트랜잭션 -- postId 3 번 게시물을 본다고 할때
START TRANSACTION;

update Post
set viewCnt = viewCnt + 1
where postid = 3;

commit;

#댓글 Transaction - 공지사항은 댓글기능을 막아놈
Start TRANSACTION;
SET @categoryId := (SELECT CategoryID
                    FROM Post
                    WHERE postid = 4);
-- 댓글 삽입
INSERT INTO Comment (postid, userid, content, CategoryID)
SELECT 4, 3, '뭐야~~~!', @categoryId
WHERE @categoryId != 1;

COMMIT;

# #update 트랜잭션
# START TRANSACTION;
# -- 유저 아이디를 찾아
# SET @userid = (SELECT userid
#                FROM Member
#                WHERE userid = 3
#                  AND role = 'User');
# SET @categoryId = (SELECT CategoryID
#                    FROM Post)
# INSERT INTO Post (userid, CategoryID, Title, content)
# VALUES (@userid,
#         2,
#         '1 자유게시판 제목',
#         '1 게시물 내용입니다.');
# COMMIT;

################################ TEST 0. 기본적인 생성 결과 확인 ################################

################################ TEST 1. 회원 탈퇴 시 글이 사라지는지 테스트 ################################

DELETE
FROM Member
where userid = 1;
DELETE
FROM Member
where userid = 2;
DELETE
FROM Member
where userid = 3;
DELETE
FROM Member
where userid = 4;
DELETE
FROM Member
where userid = 5;


################################ TEST 1. 회원 탈퇴 시 글이 사라지는지 테스트 ################################

################################ TEST 2. 회원이 글을 직접 삭제 시 글이 삭제 되는지 테스트 ################################
DELETE
FROM Post
WHERE userid = 1;

DELETE
FROM Post
WHERE userid = 3;
# 점검 결과 이상 없음
################################ TEST 2. 회원이 글을 직접 삭제 시 글이 삭제 되는지 테스트 ################################

select *
from Member;
select *
from Category;
select *
from Post;

select *
from Comment;
select *
from Post
         left join Comment C on Post.postid = C.postid;
select *
from Category
         left join Post P on Category.CategoryID = P.CategoryID;
select *
from Member
         left join Comment C on Member.userid = C.userid;
select *
from Member
         left join Post P on Member.userid = P.userid;