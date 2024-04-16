CREATE TABLE 파일럿 (
	파일럿번호 int NOT NULL,
    파일럿이름 varchar(10),
	등급 int,
	나이 int,
    CONSTRAINT pk_Pilot PRIMARY KEY(파일럿번호)
);

INSERT INTO 파일럿 VALUES (13, '홍길동', 1, 44);
INSERT INTO 파일럿 VALUES (32, '이순신', 10, 44);
INSERT INTO 파일럿 VALUES (44, '안중근', 7, 32);

CREATE TABLE 비행기 (
	비행기번호 int NOT NULL,
    비행기이름 varchar(20),
    비행기종류 varchar(10),
    CONSTRAINT pk_AirCraft PRIMARY KEY(비행기번호)
);

INSERT INTO 비행기 VALUES (101, '에놀라게이', '폭격기');
INSERT INTO 비행기 VALUES (102, '톰캣', '전투기');
INSERT INTO 비행기 VALUES (103, '블랙버트', '정찰기');

CREATE TABLE 운항 (
	파일럿번호 int,
	비행기번호 int,
    운항일자 date,
    CONSTRAINT fk_PilotNo FOREIGN KEY(파일럿번호) REFERENCES 파일럿(파일럿번호),
    CONSTRAINT fk_AirCraftNo FOREIGN KEY(비행기번호) REFERENCES 비행기(비행기번호)
);

INSERT INTO 운항 VALUES (13, 101, '2022-10-09');
INSERT INTO 운항 VALUES (44, 102, '2022-11-23');

-- Q1. 비행기 101을 운항하는 파일럿의 이름을 구하세요
SELECT 파일럿.파일럿이름
FROM 파일럿
INNER JOIN 운항 ON 파일럿.파일럿번호 = 운항.파일럿번호
INNER JOIN 비행기 ON 운항.비행기번호 = 비행기.비행기번호
WHERE 비행기.비행기번호 = 101;

-- Q3. 전투기를 운항하는 파일럿의 이름을 구하세요
SELECT 파일럿.파일럿이름
FROM 파일럿
JOIN 운항 ON 파일럿.파일럿번호 = 운항.파일럿번호
JOIN 비행기 ON 운항.비행기번호 = 비행기.비행기번호
WHERE 비행기.비행기종류 = '전투기';

-- Q4. 이순신이 조종하는 비행기의 종류를 구하세요
SELECT 비행기.비행기종류
FROM 파일럿
JOIN 운항 ON 파일럿.파일럿번호 = 운항.파일럿번호
JOIN 비행기 ON 운항.비행기번호 = 비행기.비행기번호
WHERE 파일럿.파일럿이름 = '이순신';

-- Q5. 운항 스케줄이 잡혀있는 모든 파일럿의 이름을 구하세요
SELECT DISTINCT 파일럿.파일럿이름
FROM 파일럿
JOIN 운항 ON 파일럿.파일럿번호 = 운항.파일럿번호;

-- Q6. 폭격기 또는 정찰기를 운항하는 파일럿의 이름을 구하세요
SELECT DISTINCT 파일럿.파일럿이름
FROM 파일럿
JOIN 운항 ON 파일럿.파일럿번호 = 운항.파일럿번호
JOIN 비행기 ON 운항.비행기번호 = 비행기.비행기번호
WHERE 비행기.비행기종류 = '폭격기' OR 비행기.비행기종류 = '정찰기';

-- Q7. 전투기와 폭격기를 운영하는 파일럿의 이름을 구하세요
SELECT DISTINCT 파일럿.파일럿이름
FROM 파일럿
JOIN 운항 ON 파일럿.파일럿번호 = 운항.파일럿번호
JOIN 비행기 ON 운항.비행기번호 = 비행기.비행기번호
WHERE 비행기.비행기종류 = '전투기' AND 비행기.비행기종류 = '폭격기';

-- Q8. 폭격기를 운항하지 않는 나이가 40세 이상의 파일럿의 파일럿번호를 구하세요.
SELECT 파일럿번호
FROM 파일럿 
WHERE 나이 >= 40
AND NOT EXISTS (
    SELECT *
    FROM 운항
    JOIN 비행기 ON 운항.비행기번호 = 비행기.비행기번호
    WHERE 비행기.비행기종류 = '폭격기' AND 운항.파일럿번호 = 파일럿.파일럿번호
);