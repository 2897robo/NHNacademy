drop database if exists CertificateDB;

create database CertificateDB;
use CertificateDB;

CREATE TABLE Death
(
    deathId       bigint PRIMARY KEY NOT NULL auto_increment,
    deathPersonId bigint             NOT NULL,
    place         int                NOT NULL COMMENT '구분 10개',
    deathDate     datetime           NOT NULL,
    deathAddress  varchar(100)       NOT NULL COMMENT '사망장소'
);

CREATE TABLE Person
(
    personId    BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL,
    birth       DATETIME    NOT NULL,
    idNumber    varbinary(300) NOT NULL unique ,
    gender      REAL        NOT NULL DEFAULT False,
    email       VARCHAR(20),
    phoneNumber VARCHAR(20)
);

CREATE TABLE Address
(
    addressId   BIGINT AUTO_INCREMENT NOT NULL primary key,
    addressName VARCHAR(200) not null
);

create table Marry
(
    marryId BIGINT auto_increment primary key,
    manId   BIGINT,
    womanId BIGINT
);

create table PersonAddress
(
    personAddressId bigint not null primary key auto_increment,
    personId        bigint not null,
    addressId       bigint not null,
    reason          int    not null,
    reportDate      datetime not null
);

# TODO : person birth 옮기기
create TABLE Birth
(
	birthId    bigint primary key auto_increment,
	personId   bigint not null ,
	birthPlace int not null,
	foreign key (personId) REFERENCES Person (personId)
);

create table IdCard(
	idCardId bigint primary key auto_increment,
	ownerId bigint not null ,
	inmateId bigint not null ,
	relationship varchar(50) not null
);

CREATE TABLE Report (
	reportID BIGINT NOT NULL PRIMARY KEY auto_increment,
	reportDATE DATETIME NOT NULL,
	reporterID BIGINT NOT NULL,
	reporterQualified INT NOT NULL,
	reportType int not null
);

create table family (
	familyId bigint primary key auto_increment,
	personId bigint not null ,
	otherPersonId bigint not null ,
	relationship varchar(50) not null ,
	familyGroup int not null ,
	constraint foreign key (personId) references person (personId),
	constraint foreign key (otherPersonId) references person (personId)
);

## 주민등록 등본 테이블
create table ResidentRegister(
	ResidentRegisterNum  BIGINT auto_increment primary key , -- 주민등록등본 형식상 넘버
	issueDate  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP , -- 등본 발행일
	RealResidentResgisterNumber  varchar(16) NOT NULL UNIQUE , -- 증명서 확인번호
	personId  BIGINT , -- 요구사항에 가족관계증명서, 주민등록 발급시 신청한 주민과 증명서확인번호, 증명서 발급일자는 관리해야한다고 했으므로 사용함.
	houseCompositionReason  int , -- PersonAddress table reason과 같은 성질, 세대구성사유
	houseCompositionDate datetime  , -- PersonAddress table reportDate와 같은 성질, 일자
	FOREIGN KEY (personId) REFERENCES Person (personId)
);

## 가족관계증명서 발급기록 테이블
CREATE TABLE familyCertification(
	familyCertificationNum  BIGINT AUTO_INCREMENT PRIMARY KEY, -- 가족관계증명서 형식상 넘버
	issueDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 가족관계증명서 발급일
	RealCertificationNum VARCHAR(16) NOT NULL UNIQUE, -- 증명서 확인번호
	personId BIGINT,
	FOREIGN KEY (personId) REFERENCES Person(personId)
);

alter table PersonAddress
    add foreign key (personId) references Person (personId);
alter table PersonAddress
    add foreign key (addressId) references Address (addressId);
alter table Death
    add foreign key (deathPersonId) references Person (personId);

-- 암호화 키와 초기화 벡터를 직접 전달하여 데이터 삽입
SET @key_str = SHA2('MySecretPassphrase', 256);
SET @init_vector = UNHEX(SHA2(RANDOM_BYTES(16), 256));

insert into Person (name, birth, idNumber, gender)
values ('남기석', '2012-03-15-14-59', concat(AES_ENCRYPT('120315-1111111', @key_str, @init_vector), random_bytes(284)), true);
insert into Person (name, birth, idNumber, gender, phoneNumber)
values ('남석환', '1954-05-14', concat(AES_ENCRYPT('540514-1111111', @key_str, @init_vector), random_bytes(284)), true, '01023456789');
insert into Person (name, birth, idNumber, gender)
values ('박한나', '1955-10-22', concat(AES_ENCRYPT('551022-1111111', @key_str, @init_vector), random_bytes(284)), false);
insert into Person (name, birth, idNumber, gender)
values ('이주은', '1982-08-21', concat(AES_ENCRYPT('820821-1111111', @key_str, @init_vector), random_bytes(284)), false);
insert into Person (name, birth, idNumber, gender)
values ('이선미', '1985-12-05', concat(AES_ENCRYPT('851205-1111111', @key_str, @init_vector), random_bytes(284)), false);
insert into Person (name, birth, idNumber, gender, email, phoneNumber)
values ('남기준', '1979-04-17', concat(AES_ENCRYPT('790510-1231233', @key_str, @init_vector), random_bytes(284)), true, 'chaesc1223@naver.com', '01023294056');
insert into Person (name, birth, idNumber, gender, email, phoneNumber)
values ('남길동', '1913-04-17', concat(AES_ENCRYPT('130914-321321', @key_str, @init_vector), random_bytes(284)), true, 'chaesc1223@naver.com', '01023294056');

insert into Birth (personId, birthPlace)
values (1, 2);

insert into IdCard (ownerId, inmateId, relationship)
values (6, 6, '본인'),
       (6, 4, '배우자'),
       (6, 1, '자녀'),
       (6, 5, '동거인');

INSERT INTO Report (reporterID, reportDATE, reporterQualified, reportType) VALUES (6, '2012-03-17', 1, 1);
INSERT INTO Report (reporterID, reportDATE, reporterQualified, reportType) VALUES (2, '2020-05-02', 2, 2);

# 주소 추가
insert into Address (addressName) value ('경기도 성남시 분당구 대왕판교로645번길');
insert into Address (addressName) value ('서울시 동작구 상도로 940번길');
insert into Address (addressName) value ('경기도 성남시 분당구 불정로 90번길');

# 사람 주소 추가 (기준)
insert into PersonAddress (personId, addressId, reason, reportDate)
values (6, 1, 1, '2013-03-05');
insert into PersonAddress (personId, addressId, reason, reportDate)
values (6, 2, 2, '2009-10-02');
insert into PersonAddress (personId, addressId, reason, reportDate)
values (6, 3, 3, '2007-10-31');

# 사람 주소 추가 (주은)
insert into PersonAddress (personId, addressId, reason, reportDate)
values (4, 1, 2, '2010-02-15');

# 사람 주소 추가 (기석)
insert into PersonAddress (personId, addressId, reason, reportDate)
values (1, 1, 3, '2012-03-17');

# 사람 주소 추가 (선미)
insert into PersonAddress (personId, addressId, reason, reportDate)
values (5, 1, 2, '2015-11-29');

# 기석
insert into family (personId, otherPersonId, relationship, familyGroup) values(1, 6, '부', 1);
insert into family (personId, otherPersonId, relationship, familyGroup) values(1, 4, '모', 1);
insert into family (personId, otherPersonId, relationship, familyGroup) values(1, 1, '본인', 1);

# 기준
insert into family (personId, otherPersonId, relationship, familyGroup) values(6, 2, '부', 1);
insert into family (personId, otherPersonId, relationship, familyGroup) values(6, 3, '모', 1);
insert into family (personId, otherPersonId, relationship, familyGroup) values(6, 4, '배우자', 1);
insert into family (personId, otherPersonId, relationship, familyGroup) values(6, 1, '자녀', 1);
insert into family (personId, otherPersonId, relationship, familyGroup) values(6, 6, '본인', 1);

# 선미
insert into family (personId, otherPersonId, relationship, familyGroup) values(5, 6, '동거인', 2);

# 사망자
INSERT INTO Death (place, deathDate, deathAddress, deathPersonId)
VALUES (1 , '2021-04-29 09:03:00', '강원도 고성군 금강산로 290번길', 7);

-- RealCertificationNum에 랜덤한 16자리 숫자 할당
INSERT INTO familyCertification (RealCertificationNum, personId, issueDate)
VALUES (CAST(FLOOR(POWER(10, 15) + RAND() * (POWER(10, 16) - POWER(10, 15) - 1)) AS UNSIGNED), 1, '2021-10-25');

insert into ResidentRegister (RealResidentResgisterNumber, personId, houseCompositionReason, houseCompositionDate, issueDate) values
    (CAST(FLOOR(POWER(10, 15) + RAND() * (POWER(10, 16) - POWER(10, 15) - 1)) AS UNSIGNED), 6 ,
     (select reason from PersonAddress where personId=6 order by reportDate desc limit 1) ,
     (select pa.reportDate from PersonAddress pa where personId=6 and pa.reason = 2), '2021-10-25');