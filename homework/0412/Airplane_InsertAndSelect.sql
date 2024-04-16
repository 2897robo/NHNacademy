use AirlineDB;

# 1.
INSERT INTO Crew (CrewName) VALUE ('김채호');
INSERT INTO Crew (CrewName) VALUE ('채정훈');
INSERT INTO Crew (CrewName) VALUE ('김기욱');
INSERT INTO Crew (CrewName) VALUE ('박기량');
select *
from crew;

# 2.
INSERT INTO CrewGroup (GroupID) VALUE (1);
INSERT INTO CrewGroup (GroupID) VALUE (2);
INSERT INTO CrewGroup (GroupID) VALUE (3);
INSERT INTO CrewGroup (GroupID) VALUE (4);
select *
from crewgroup;

# 3. Route
insert into Route
values
    (), -- 1번 인천 -> 후쿠오카
    (), -- 2번 김해 -> 나리타
    (), -- 3번 김해 -> 간사이
    (), -- 4번 후쿠오카 -> 인천
    (), -- 5번 서울 -> 괌
    (); -- 6번 괌 -> 서울
select *
from Route;

insert into DepartureArrival (DepartureArrival, DeparturePlace, ArrivalPlace, Mileage)
values (1, '인천', '후쿠오카', 3400);
insert into DepartureArrival (DepartureArrival, DeparturePlace, ArrivalPlace, Mileage)
values (2, '김해', '나리타', 4000);
insert into DepartureArrival (DepartureArrival, DeparturePlace, ArrivalPlace, Mileage)
values (3, '김해', '간사이', 5000);
insert into DepartureArrival (DepartureArrival, DeparturePlace, ArrivalPlace, Mileage)
values (4, '후쿠오카', '인천', 3400);
insert into DepartureArrival (DepartureArrival, DeparturePlace, ArrivalPlace, Mileage)
values (5, '서울', '괌', 5000);
insert into DepartureArrival (DepartureArrival, DeparturePlace, ArrivalPlace, Mileage)
values (6, '괌', '서울', 5000);

insert into Airline (AirlineName) value ('제주항공');
insert into Airline (AirlineName) value ('대한항공');
insert into Airline (AirlineName) value ('아시아나항공');

select *
from DepartureArrival;
# 4. AircraftPlan
INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (1, 1, '2024-03-23 09:11:11', true, 1, '2024-04-23 09:11:11', '2024-04-23 13:11:11');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (2, 2, '2024-04-21 23:11:11', true, 2, '2024-05-21 23:11:11', '2024-05-22 03:11:11');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (3, 3, '2024-05-02 12:11:11', true, 3, '2024-06-02 12:11:11', '2024-06-02 16:11:11');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (4, 4, '2024-03-23 09:11:11', true, 1, '2024-04-30 11:11:11', '2024-04-30 14:11:11');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (5, 5, '2024-05-30 09:11:11', true, 1, '2024-06-07 06:20:00', '2024-06-08 02:45:00');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (6, 6, '2024-05-30 09:11:11', true, 1, '2024-06-16 03:05:00', '2024-06-17 06:55:00');


# 5. CrewFlightPlan
INSERT INTO CrewFlightPlan (CrewFlightPlanID, RouteID)
VALUES (1, 1);
INSERT INTO CrewFlightPlan (CrewFlightPlanID, RouteID)
VALUES (2, 2);
INSERT INTO CrewFlightPlan (CrewFlightPlanID, RouteID)
VALUES (3, 3);
INSERT INTO CrewFlightPlan (CrewFlightPlanID, RouteID)
VALUES (4, 4);
select *
from Airline;

insert into Airport (AirportName)
values ('김해공항');
insert into Airport (AirportName)
values ('인천공항');
insert into Airport (AirportName)
values ('나리타공항');
insert into Airport (AirportName)
values ('후쿠오카공항');
insert into Airport (AirportName)
values ('간사이공항');

select *
from Airport;


select *
from Advisory;
-- 1 : 정상 ( 2:지연 3:결항 ) => 결항
insert into Advisory (AdvisoryID, RouteID, Status)
values (1, 2, 1);
insert into Advisory (AdvisoryID, RouteID, Status)
values (2, 1, 2);
insert into Advisory (AdvisoryID, RouteID, Status)
values (1, 3, 3);
insert into Advisory (AdvisoryID, RouteID, Status)
values (4, 4, 1);

select *
from Airport a
         left join Advisory on a.AirportID = Advisory.AdvisoryID
         left join AirlineDB.Route R on Advisory.RouteID = R.RouteID
         left join DepartureArrival da on da.DepartureArrival = R.RouteID;

-- 보라색 (고객 테이블)
-- PreferFlight 즐겨찾기 경로 수정 필요
-- Customer 생성
DESC Customer;
INSERT INTO Customer(isCorpPersonal)
VALUES (true);
INSERT INTO Customer(isCorpPersonal)
VALUES (true);
INSERT INTO Customer(isCorpPersonal)
VALUES (false);
SELECT *
FROM Customer;

-- CustomerIdentify
DESC CustomerIdentify;
INSERT INTO CustomerIdentify(CustomerId, IdNumber, PassportNumber)
VALUES (1, '980109-1234567', 'M12345677');
INSERT INTO CustomerIdentify(CustomerId, IdNumber, PassportNumber)
VALUES (2, '980109-1234568', 'M12345678');
INSERT INTO CustomerIdentify(CustomerId, IdNumber, PassportNumber)
VALUES (3, '980109-1234569', 'M12345679');
SELECT *
FROM CustomerIdentify;

DESC PersonalCustomer;
INSERT INTO PersonalCustomer
VALUES (1, true);
INSERT INTO PersonalCustomer
VALUES (2, false);
INSERT INTO PersonalCustomer
VALUES (3, true);
SELECT *
FROM PersonalCustomer;

DESC RegularMember;
INSERT INTO RegularMember
VALUES (1, 'Nickname1', 'Password1');
INSERT INTO RegularMember
VALUES (2, 'Nickname2', 'Password2');
INSERT INTO RegularMember
VALUES (3, 'Nickname3', 'Password3');
SELECT *
FROM RegularMember;

DESC SocialMember;
INSERT INTO SocialMember
VALUES (1, 1, 'IdFromKakao');
INSERT INTO SocialMember
VALUES (2, 2, 'IdFromNaver');
INSERT INTO SocialMember
VALUES (3, 3, 'IdFromApple');
SELECT *
FROM SocialMember;

DESC CorpCustomer;
INSERT INTO CorpCustomer
VALUES (3, 'Samsung', true, '2024-01-05');
SELECT *
FROM CorpCustomer;

DESC CorpBenefit;
INSERT INTO CorpBenefit
VALUES (3, 'Free Meal!', 'Have some free meals before your flight.');
SELECT *
FROM CorpBenefit;

DESC CorpEmployee;
INSERT INTO CorpEmployee
VALUES (3, 3);
SELECT *
FROM CorpEmployee;

-- 11시방향
insert into Product(TicketID, ServiceID, AfProductID, AccessoryID, CombinedID)
values (1, 1, 1, 1, 1),
       (2, 2, 2, 2, 2),
       (3, 3, 3, 3, 3);

insert into Product(TicketID)
values (4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (1, '2024-04-23 09:11:11', '2024-04-23 13:11:11', '인천', '후쿠오카', 150000);
insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (1, '2024-05-21 23:11:11', '2024-05-22 03:11:11', '후쿠오카', '제주', 350000);
insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (1, '2024-06-02 12:11:11', '2024-06-02 16:11:11', '인천', '제주', 120000);
insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (1, '2024-04-27 12:11:11', '2024-04-27 16:11:11', '후쿠오카', '인천', 150000);

## 서울에서 괌, 괌에서 서울
insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (4, '2024-06-07 06:20:50', '2024-06-07 11:45:50', '괌', '서울', 107900);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (5, '2024-06-07 10:04:50', '2024-06-07 14:45:50', '괌', '서울', 110000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (6, '2024-06-08 10:20:50', '2024-06-08 15:45:50', '괌', '서울', 149900);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (7, '2024-06-08 10:20:50', '2024-06-08 15:45:50', '괌', '서울', 149900);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (8, '2024-06-08 20:20:50', '2024-06-09 02:50:50', '괌', '서울', 50000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (9, '2024-06-09 15:20:50', '2024-06-08 20:45:50', '괌', '서울', 134900);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (10, '2024-06-10 13:20:50', '2024-06-10 17:45:50', '괌', '서울', 189900);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (11, '2024-06-13 03:20:50', '2024-06-13 06:45:50', '서울', '괌', 100000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (12, '2024-06-14 05:20:50', '2024-06-14 09:45:50', '서울', '괌', 50000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (13, '2024-06-15 07:20:50', '2024-06-15 11:45:50', '서울', '괌', 50000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (14, '2024-06-16 10:10:50', '2024-06-16 14:45:50', '서울', '괌', 107000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (15, '2024-06-17 11:20:50', '2024-06-17 15:45:50', '서울', '괌', 70000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (16, '2024-06-18 15:20:50', '2024-06-18 19:45:50', '서울', '괌', 90000);

insert into AirlineTicket(ProductID, DepartureTime, ArrivalTime, DeparturePlace, ArrivalPlace, Price)
values (17, '2024-06-16 17:20:50', '2024-06-16 21:45:50', '서울', '괌', 110000);

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (7, 6, '2024-04-23', true, 1, '2024-06-07 06:20:50', '2024-06-07 11:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (8, 6, '2024-04-23', true, 1, '2024-06-07 10:04:50', '2024-06-07 14:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (9, 6, '2024-04-23', true, 1, '2024-06-08 10:20:50', '2024-06-08 15:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (10, 6, '2024-04-23', true, 1, '2024-06-08 10:20:50', '2024-06-08 15:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (11, 6, '2024-04-23', true, 1, '2024-06-08 20:20:50', '2024-06-09 02:50:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (12, 6, '2024-04-23', true, 1, '2024-06-09 15:20:50', '2024-06-08 20:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (13, 6, '2024-04-23', true, 1, '2024-06-10 13:20:50', '2024-06-10 17:45:50');


INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (14, 5, '2024-04-23', true, 1, '2024-06-13 03:20:50', '2024-06-13 06:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (15, 5, '2024-04-23', true, 1, '2024-06-14 05:20:50', '2024-06-14 09:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (16, 5, '2024-04-23', true, 1, '2024-06-15 07:20:50', '2024-06-15 11:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (17, 5, '2024-04-23', true, 1, '2024-06-16 10:10:50', '2024-06-16 14:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (18, 5, '2024-04-23', true, 1, '2024-06-17 11:20:50', '2024-06-17 15:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (19, 5, '2024-04-23', true, 1, '2024-06-18 15:20:50', '2024-06-18 19:45:50');

INSERT INTO AircraftPlan (AircraftPlanID, RouteID, SaleableDate, SeasonCode, AircraftID, CraftDepartureTime,
                          CraftArrivalTime)
VALUES (20, 5, '2024-04-23', true, 1, '2024-06-16 17:20:50', '2024-06-16 21:45:50');


insert into AffiliateProduct(ProductID, AffiliateName, Descript, Price)
values (1, '호텔제휴상품', 'A호텔 스위트룸', 150000);

insert into AffiliateProduct(ProductID, AffiliateName, Descript, Price)
values (2, '렌터카 제휴상품', '소나타 3인 렌트권', 100000);

insert into AffiliateProduct(ProductID, AffiliateName, Descript, Price)
values (3, '렌터카 제휴상품', 'B호텔 스위트룸', 10000);

-- 악세사리
insert into Accessory(ProductID, AccessoryName, Price)
values (1, '2024 제주항공 NEW YEAR KIT', 27000);
insert into Accessory(ProductID, AccessoryName, Price)
values (2, '제주항공 모형비행기', 53000);
insert into Accessory(ProductID, AccessoryName, Price)
values (3, '제주항공 폴딩 에코백', 19000);


-- 부가서비스
insert into Service(ProductID, ServiceName, Price) value (1, '수하물 프리미엄 플러스', 74000);
insert into Service(ProductID, ServiceName, Price) value (2, '여행자보험', 85000);
insert into Service(ProductID, ServiceName, Price) value (3, '제주패스(렌터카)', 120000);

insert into CombinedProduct(combinedproductid, price)
values (1, 300000),
       (2, 150000),
       (3, 89000);

insert into CombinedProductDetail(ticketid, serviceid, afproductid, accessoryid, combinedid)
values (1, 2, 3, 1, 1),
       (1, 1, 2, 2, 3),
       (1, 3, 1, 3, 2);

insert into PNR (CustomerID, ReservationNumber)
values (1, 'A1234567');
insert into PNR (CustomerID, ReservationNumber)
values (2, 'B1234567');
insert into PNR (CustomerID, ReservationNumber)
values (3, 'C1234567');

-- pax
insert into PAX (PnrID, Birthday, Country, Email, FirstName, LastName, Gender, PhoneNumber)
    value (1, '1999-04-17', 'ko', 'qlqlzbclzlss@naver.com', 'Kim', 'Hao', true, '010123456789');
insert into PAX (PnrID, Birthday, Country, Email, FirstName, LastName, Gender, PhoneNumber)
    value (2, '1999-05-17', 'ko', 'saddas@naver.com', 'Lee', 'Hao', true, '010126456789');
insert into PAX (PnrID, Birthday, Country, Email, FirstName, LastName, Gender, PhoneNumber)
    value (3, '1999-07-17', 'ko', 'wqeeqw@naver.com', 'Jo', 'Hao', true, '010133456789');

-- SEG
insert into SEG (SegID) value (1),(2),(3);
-- SSR
insert into SSR (SsrID, SegID)
values (1, 1),
       (2, 2),
       (3, 3);

# Ticket
insert into Ticket (TicketingDate, TicketingPerson, ReservationPerson)
values ('2024-04-22 13:11:11', 'Nickname1', '조현아');
insert into Ticket (TicketingDate, TicketingPerson, ReservationPerson)
values ('2024-05-19 03:11:11', 'Nickname2', '안철수');
insert into Ticket (TicketingDate, TicketingPerson, ReservationPerson)
values ('2024-05-31 16:11:11', 'Nickname3', '홍준표');

-- Coupon
insert into Coupon
values (1, 1, '호텔숙박권!');
insert into Coupon
values (2, 2, '쏘카 할인!');
insert into Coupon
values (3, 3, '라운지 이용권');

-- Fee
insert into Fee
values (1),
       (2),
       (3);

-- Tax
insert into Tax
values (1, 50000, 1);
insert into Tax
values (2, 80000, 2);
insert into Tax
values (3, 70000, 1);

-- 결제 수단
insert into PaymentMethod (PaymentID, PaymentType)
values (1, 1);
insert into PaymentMethod (PaymentID, PaymentType)
values (2, 2);
insert into PaymentMethod (PaymentID, PaymentType)
values (3, 1);

-- 부가서비스 내역
insert into ServiceHistory (ServiceHistoryID, ProductID)
VALUES (1, 1);
insert into ServiceHistory (ServiceHistoryID, ProductID)
VALUES (2, 2);
insert into ServiceHistory (ServiceHistoryID, ProductID)
VALUES (3, 3);

-- 연결테이블 ProductCustomer
insert into ProductCustomer (CustomerID, ProductID)
VALUES (1, 1);
insert into ProductCustomer (CustomerID, ProductID)
VALUES (2, 2);
insert into ProductCustomer (CustomerID, ProductID)
VALUES (3, 3);

-- ServiceReservationHistory
insert into ServiceReservationHistory (PnrID, SegID, ProductID)
VALUES (1, 1, 1);
insert into ServiceReservationHistory (PnrID, SegID, ProductID)
VALUES (2, 2, 2);
insert into ServiceReservationHistory (PnrID, SegID, ProductID)
VALUES (3, 3, 3);

-- AirlineProduct
insert into AirlineProduct (AirlineID, ProductID)
VALUES (1, 1);
insert into AirlineProduct (AirlineID, ProductID)
VALUES (1, 2);
insert into AirlineProduct (AirlineID, ProductID)
VALUES (1, 3);

# Select
# 출발 지 -> 도착지 (서울, 괌)
## 가는편
select DATE_FORMAT(ap.CraftDepartureTime, '%H:%i') 출발시간,
       DATE_FORMAT(ap.CraftArrivalTime, '%H:%i') 도착시간,
       da.DeparturePlace 출발지,
       da.ArrivalPlace 도착지
from AircraftPlan ap
    inner join AirlineDB.Route r on ap.RouteID = r.RouteID
    inner join DepartureArrival da on da.DepartureArrival = r.RouteID
where da.DeparturePlace = '서울' and da.ArrivalPlace = '괌'
      and ap.CraftDepartureTime between '2024-06-04' and '2024-06-10'
order by ap.CraftDepartureTime;

## 가는편 가격
select at.Price '가는편 가격'
from AirlineTicket at
where at.DeparturePlace = '서울' and at.ArrivalPlace = '괌'
  and at.DepartureTime between '2024-06-07' and '2024-06-16'
order by at.Price;


## 오는편
select DATE_FORMAT(ap.CraftDepartureTime, '%H:%i') 출발시간,
       DATE_FORMAT(ap.CraftArrivalTime, '%H:%i') 도착시간,
       da.DeparturePlace 출발지,
       da.ArrivalPlace 도착지
from AircraftPlan ap
         left join AirlineDB.Route r on ap.RouteID = r.RouteID
         left join DepartureArrival da on da.DepartureArrival = r.RouteID
where da.DeparturePlace = '괌' and da.ArrivalPlace = '서울' and
    ap.CraftDepartureTime between '2024-06-07' and '2024-06-16';

## 오는편 가격
select at.Price '오는편 가격'
from AirlineTicket at
where DeparturePlace = '괌' and ArrivalPlace = '서울' and
    DepartureTime between '2024-06-07' and '2024-06-16';

## 탑승객 정보 입력
insert into PAX (PnrID, FirstName, LastName, Country, Birthday, Gender, PhoneNumber, Email)
value (1, 'KIM', 'SANGHOON', 'ko', '1973-01-01', true, '010123456789', 'qlqlzbclzlss@naver.com');