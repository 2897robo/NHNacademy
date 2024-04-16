# TODO : 발급일, 증명서 확인번호, 등록기준지 필요
# 가족관계증명서 출력
## 가족관계증명서 발급일, 증명서확인번호, 등록기준지
select issueDate                                      as '발급일',
       concat(substring(RealCertificationNum, 1, 8), '-',
              substring(RealCertificationNum, 9, 16)) as '증명서확인번호',
       (select addressName
        from Address
        where addressId = (select addressId
                           from personAddress
                           where personid = 6
                           order by reportDate
                           limit 1))                  as '등록기준지'
from familyCertification;

## 구분, 성명, 출생연월일, 주민번호, 성별
SELECT f.relationship             as 구분,
       p.name                     as 성명,
       DATE_FORMAT(p.birth, '%Y년 %m월 %d일'),
       CONCAT(
               LEFT(CAST(AES_DECRYPT(SUBSTRING(p.idNumber, 1, 16),
                                     SHA2('MySecretPassphrase', 256),
                                     UNHEX(SHA2(RANDOM_BYTES(16), 256))
                         ) AS CHAR(300)), 6),
               '-*******')        AS 출생연월일,
       IF(p.gender = 1, '남', '여') AS '성별'
FROM family AS f
         JOIN person AS p ON p.PersonID = f.otherPersonId
WHERE f.personId = (SELECT personId FROM person WHERE name = '남기준');

# TODO : 발급일, 증명서 확인번호, 세대주 성명, 세대구성 사유 및 일자 필요
# 주민등록등본 출력
## 발급일, 증명서확인번호, 세대주 성명, 세대구성 사유 및 일자
select DATE_FORMAT(issueDate, '%Y-%m-%d')                    as '발급일',
       concat(substring(RealResidentResgisterNumber, 1, 8), '-',
              substring(RealResidentResgisterNumber, 9, 16)) as '증명서확인번호',
       (select name from person where personId = 6)          as '세대주 성명',
       case
           when houseCompositionReason = 1 then '세대분리'
           when houseCompositionReason = 2 then '전입'
           when houseCompositionReason = 3 then '출생등록'
           end                                               as '세대구성 사유',
       DATE_FORMAT(houseCompositionDate, '%Y-%m-%d')            '일자'
from ResidentRegister;

## 주소, 신고일
select a.addressName                          as 주소,
       DATE_FORMAT(pa.reportDate, '%Y-%m-%d') as 신고일
from PersonAddress pa
         left join Person P on pa.personId = P.personId
         left join Address A on pa.addressId = A.addressId
where p.name = '남기준'
order by pa.reportDate desc;

## 세대주 관계, 성명, 주민번호, 신고일, 변동사유
SELECT ic.relationship                           as '세대주 관계',
       p.name                                    as 성명,
       CONCAT(
               LEFT(CAST(AES_DECRYPT(SUBSTRING(p.idNumber, 1, 16),
                                     SHA2('MySecretPassphrase', 256),
                                     UNHEX(SHA2(RANDOM_BYTES(16), 256))
                         ) AS CHAR(300)), 6),
               '-*******')                       AS 주민등록번호,
       DATE_FORMAT(latestReportDate, '%Y-%m-%d') as 신고일,
       CASE reason
           WHEN 1 THEN '세대분리'
           WHEN 2 THEN '전입'
           WHEN 3 THEN '출생등록'
           END
                                                 AS '변동사유'
FROM (SELECT pa.personId,
             MAX(pa.reportDate) AS latestReportDate
      FROM PersonAddress pa
      GROUP BY pa.personId) AS latest_reports
         INNER JOIN PersonAddress pa ON latest_reports.personId = pa.personId AND
                                        latest_reports.latestReportDate = pa.reportDate
         LEFT JOIN Person p ON p.personId = pa.personId
         LEFT JOIN Address a ON pa.addressId = a.addressId
         left join IdCard ic on ic.inmateId = p.personId;

# 출생신고서 출력
## 신고일
SELECT r.ReportDATE AS '신고일'
FROM Report r
         LEFT JOIN Person p ON p.personId = r.ReporterID
WHERE p.name = '남기준';

## 출생지
select p.name,
       CASE p.gender
           WHEN 1 THEN '남'
           WHEN 0 THEN '여'
           END                                     AS '성별',
       DATE_FORMAT(p.birth, '%Y월 %m월 %d월 %H시 %i분') as '출생일시',
       CASE B.birthPlace
           WHEN 1 THEN '자택'
           WHEN 2 THEN '병원'
           WHEN 3 THEN '기타'
           END                                     AS '출생장소',
       a.addressName                               AS '등록기준지'
from Person p
         left join PersonAddress PA
                   on p.personId = PA.personId
         left join Address a on a.addressId = PA.addressId
         left join Birth B on p.personId = B.personId
         left join family f on p.personId = f.personId
where name = '남기석'
order by PA.reportDate desc
limit 1;

## 부모
select f.relationship as 부모,
       name           as 이름,
       CONCAT(
               LEFT(CAST(AES_DECRYPT(SUBSTRING(p.idNumber, 1, 16),
                                     SHA2('MySecretPassphrase', 256),
                                     UNHEX(SHA2(RANDOM_BYTES(16), 256))
                         ) AS CHAR(300)), 6),
               '-*******'
       )              AS 주민등록번호
from person p
         left join family f on p.personId = f.otherPersonId
where (f.relationship = '부' or f.relationship = '모')
  and f.personId = 1;

## 신고인
SELECT p.name        AS '성명',
       CONCAT(
               LEFT(CAST(AES_DECRYPT(SUBSTRING(p.idNumber, 1, 16),
                                     SHA2('MySecretPassphrase', 256),
                                     UNHEX(SHA2(RANDOM_BYTES(16), 256))
                         ) AS CHAR(300)), 6),
               '-*******'
       )             AS '주민등록번호',
       r.ReportDATE  AS '신고일',
       CASE
           WHEN r.ReporterQualified = 1 THEN '부'
           WHEN r.ReporterQualified = 2 THEN '모'
           WHEN r.ReporterQualified = 3 THEN '동거친족'
           WHEN r.ReporterQualified = 4 THEN '기타'
           END       AS '자격',
       p.email       AS '이메일',
       p.phoneNumber AS '전화번호'
FROM Report r
         JOIN Person p ON p.personId = r.ReporterID
WHERE p.name = '남기준'
  and r.reportType = 1;

# 사망 신고서 출력
## 사망자
select p.name                                      as 성명
        ,
       CONCAT(
               LEFT(CAST(AES_DECRYPT(SUBSTRING(p.idNumber, 1, 16),
                                     SHA2('MySecretPassphrase', 256),
                                     UNHEX(SHA2(RANDOM_BYTES(16), 256))
                         ) AS CHAR(300)), 6),
               '-*******'
       )                                           AS 주민등록번호,
       DATE_FORMAT(d.deathDate, '%Y년%m월%d일%H시%i분') as 사망일시,
       case
           when d.place = 1 then '주택'
           when d.place = 2 then '의료기관'
           when d.place = 3 then '사회복지시설(양로원, 고아원 등)'
           when d.place = 4 then '산업장'
           when d.place = 5 then '공공시설(학교, 운동장 등)'
           when d.place = 6 then '도로'
           when d.place = 7 then '상업/서비스시설(상점, 호텔 등)'
           when d.place = 8 then '농장(논밭, 축사, 양식장 등)'
           when d.place = 9 then '병원 이송 중 사망'
           when d.place = 10 then '기타'
           end                                     as 구분,
       d.deathAddress                              as 주소
from Death d
         left join Person p on p.personId = d.deathPersonId
where p.name = '남길동';

## 신고인
SELECT p.name                                 AS '성명',
       CONCAT(
               LEFT(CAST(AES_DECRYPT(SUBSTRING(p.idNumber, 1, 16),
                                     SHA2('MySecretPassphrase', 256),
                                     UNHEX(SHA2(RANDOM_BYTES(16), 256))
                         ) AS CHAR(300)), 6),
               '-*******'
       )                                      AS '주민등록번호',
       CASE
           WHEN r.ReporterQualified = 1 THEN '동거친족'
           WHEN r.ReporterQualified = 2 THEN '비동거친족'
           WHEN r.ReporterQualified = 3 THEN '동거자'
           WHEN r.ReporterQualified = 4 THEN '기타'
           END                                AS '자격',
       p.email                                AS '이메일',
       CONCAT(SUBSTRING(p.phoneNumber, 1, 3), '-', SUBSTRING(p.phoneNumber, 4, 4), '-',
              SUBSTRING(p.phoneNumber, 8, 4)) AS '전화번호'
FROM Report r
         JOIN Person p ON p.personId = r.ReporterID
WHERE p.name = '남석환'
  and r.reportType = 2;