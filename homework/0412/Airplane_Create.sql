-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.
DROP DATABASE if exists AirlineDB;
create database AirlineDB;
use AirlineDB;
-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- Customer Table Create SQL
-- 테이블 생성 SQL - Customer
CREATE TABLE Customer
(
    `CustomerID`     BIGINT NOT NULL AUTO_INCREMENT,
    `IsCorpPersonal` REAL   NOT NULL COMMENT 'true : corp, false : personal',
    PRIMARY KEY (CustomerID)
);


-- PNR Table Create SQL
-- 테이블 생성 SQL - PNR
CREATE TABLE PNR
(
    `PnrID`             BIGINT      NOT NULL AUTO_INCREMENT,
    `CustomerID`        BIGINT      NOT NULL,
    `ReservationNumber` VARCHAR(50) NOT NULL,
    PRIMARY KEY (PnrID)
);

-- 테이블 Comment 설정 SQL - PNR
ALTER TABLE PNR
    COMMENT '항공권 예약';

-- Foreign Key 설정 SQL - PNR(CustomerID) -> Customer(CustomerID)
ALTER TABLE PNR
    ADD CONSTRAINT FK_PNR_CustomerID_Customer_CustomerID FOREIGN KEY (CustomerID)
        REFERENCES Customer (CustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PNR(CustomerID)
-- ALTER TABLE PNR
-- DROP FOREIGN KEY FK_PNR_CustomerID_Customer_CustomerID;


-- PAX Table Create SQL
-- 테이블 생성 SQL - PAX
CREATE TABLE PAX
(
    `PnrID`          BIGINT      NOT NULL,
    `PaxID`          BIGINT      NOT NULL AUTO_INCREMENT,
    `FirstName`      varchar(10) not null,
    `LastName`       varchar(20) not null,
    `Country`        char(2)     not null,
    `Birthday`       datetime    not null,
    `Gender`         real        not null,
    `PhoneNumber`    varchar(20) not null,
    `SubPhoneNumber` varchar(20),
    `Email`          varchar(40) not null,
    PRIMARY KEY (PaxID)
);

-- 테이블 Comment 설정 SQL - PAX
ALTER TABLE PAX
    COMMENT '탑승객의 신상정보(이름,나이, 연락처) 등등';

-- Foreign Key 설정 SQL - PAX(PnrID) -> PNR(PnrID)
ALTER TABLE PAX
    ADD CONSTRAINT FK_PAX_PnrID_PNR_PnrID FOREIGN KEY (PnrID)
        REFERENCES PNR (PnrID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PAX(PnrID)
-- ALTER TABLE PAX
-- DROP FOREIGN KEY FK_PAX_PnrID_PNR_PnrID;


-- Crew Table Create SQL
-- 테이블 생성 SQL - Crew
CREATE TABLE Crew
(
    `CrewID`   BIGINT      NOT NULL AUTO_INCREMENT,
    `CrewName` VARCHAR(50) NOT NULL,
    PRIMARY KEY (CrewID)
);

-- 테이블 Comment 설정 SQL - Crew
ALTER TABLE Crew
    COMMENT '승무원';


-- Route Table Create SQL
-- 테이블 생성 SQL - Route
CREATE TABLE Route
(
    `RouteID` BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (RouteID)
);


-- Airline Table Create SQL
-- 테이블 생성 SQL - Airline
CREATE TABLE Airline
(
    `AirlineID`   BIGINT      NOT NULL AUTO_INCREMENT,
    `AirlineName` VARCHAR(50) NOT NULL,
    PRIMARY KEY (AirlineID)
);

-- 테이블 Comment 설정 SQL - Airline
ALTER TABLE Airline
    COMMENT '항공사';


-- Product Table Create SQL
-- 테이블 생성 SQL - Products
CREATE TABLE Product
(
    `ProductID`   BIGINT NOT NULL AUTO_INCREMENT,
    `TicketID`    BIGINT,
    `ServiceID`   BIGINT,
    `AfProductID` BIGINT,
    `AccessoryID` BIGINT,
    `CombinedID`  BIGINT,
    PRIMARY KEY (ProductID)
);


-- Ticket Table Create SQL
-- 테이블 생성 SQL - Ticket
CREATE TABLE Ticket
(
    `TicketID`          BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `TicketingDate`     DATETIME    NOT NULL COMMENT '발권날짜',
    `TicketingPerson`   VARCHAR(50) NOT NULL COMMENT '소유주',
    `ReservationPerson` VARCHAR(50) NOT NULL COMMENT '예약자'
);

-- 테이블 Comment 설정 SQL - Ticket
ALTER TABLE Ticket
    COMMENT '티켓';

-- Foreign Key 설정 SQL - Ticket(TicketID) -> PAX(PaxID)
ALTER TABLE Ticket
    ADD CONSTRAINT FK_Ticket_TicketID_PAX_PaxID FOREIGN KEY (TicketID)
        REFERENCES PAX (PaxID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Ticket(TicketID)
-- ALTER TABLE Ticket
-- DROP FOREIGN KEY FK_Ticket_TicketID_PAX_PaxID;


-- CrewGroup Table Create SQL
-- 테이블 생성 SQL - CrewGroup
CREATE TABLE CrewGroup
(
    `GroupID` BIGINT NOT NULL
);

-- 테이블 Comment 설정 SQL - CrewGroup
ALTER TABLE CrewGroup
    COMMENT '편조';

-- Foreign Key 설정 SQL - CrewGroup(GroupID) -> Crew(CrewID)
ALTER TABLE CrewGroup
    ADD CONSTRAINT FK_CrewGroup_GroupID_Crew_CrewID FOREIGN KEY (GroupID)
        REFERENCES Crew (CrewID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CrewGroup(GroupID)
-- ALTER TABLE CrewGroup
-- DROP FOREIGN KEY FK_CrewGroup_GroupID_Crew_CrewID;


-- AircraftPlan Table Create SQL
-- 테이블 생성 SQL - AircraftPlan
CREATE TABLE AircraftPlan
(
    `AircraftPlanID`     BIGINT   NOT NULL,
    `RouteID`            BIGINT   NOT NULL,
    `SaleableDate`       DATETIME NOT NULL,
    `SeasonCode`         REAL     NOT NULL DEFAULT true COMMENT 'true : 성수기, false : 비수기',
    `AircraftID`         BIGINT   NOT NULL,
    `CraftDepartureTime` DATETIME NOT NULL,
    `CraftArrivalTime`   DATETIME NOT NULL
);

-- Foreign Key 설정 SQL - AircraftPlan(RouteID) -> Route(RouteID)
ALTER TABLE AircraftPlan
    ADD CONSTRAINT FK_AircraftPlan_RouteID_Route_RouteID FOREIGN KEY (RouteID)
        REFERENCES Route (RouteID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - AircraftPlan(RouteID)
-- ALTER TABLE AircraftPlan
-- DROP FOREIGN KEY FK_AircraftPlan_RouteID_Route_RouteID;

-- Foreign Key 설정 SQL - AircraftPlan(AircraftID) -> Airline(AirlineID)
ALTER TABLE AircraftPlan
    ADD CONSTRAINT FK_AircraftPlan_AircraftID_Airline_AirlineID FOREIGN KEY (AircraftID)
        REFERENCES Airline (AirlineID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - AircraftPlan(AircraftID)
-- ALTER TABLE AircraftPlan
-- DROP FOREIGN KEY FK_AircraftPlan_AircraftID_Airline_AirlineID;


-- Refund Table Create SQL
-- 테이블 생성 SQL - Refund
CREATE TABLE Refund
(
    `RefundID` BIGINT NOT NULL
);

-- Foreign Key 설정 SQL - Refund(RefundID) -> Ticket(TicketID)
ALTER TABLE Refund
    ADD CONSTRAINT FK_Refund_RefundID_Ticket_TicketID FOREIGN KEY (RefundID)
        REFERENCES Ticket (TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Refund(RefundID)
-- ALTER TABLE Refund
-- DROP FOREIGN KEY FK_Refund_RefundID_Ticket_TicketID;


-- PersonalCustomer Table Create SQL
-- 테이블 생성 SQL - PersonalCustomer
CREATE TABLE PersonalCustomer
(
    `PersonalCustomerID` BIGINT NOT NULL,
    `RegisterType`       REAL   NULL
);

-- Foreign Key 설정 SQL - PersonalCustomer(PersonalCustomerID) -> Customer(CustomerID)
ALTER TABLE PersonalCustomer
    ADD CONSTRAINT FK_PersonalCustomer_PersonalCustomerID_Customer_CustomerID FOREIGN KEY (PersonalCustomerID)
        REFERENCES Customer (CustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PersonalCustomer(PersonalCustomerID)
-- ALTER TABLE PersonalCustomer
-- DROP FOREIGN KEY FK_PersonalCustomer_PersonalCustomerID_Customer_CustomerID;


-- CorpCustomer Table Create SQL
-- 테이블 생성 SQL - CorpCustomer
CREATE TABLE CorpCustomer
(
    `CorpCustomerID` BIGINT      NOT NULL,
    `CorpName`       VARCHAR(50) NOT NULL,
    `IsPermit`       REAL        NOT NULL,
    `PermitDate`     DATETIME    NOT NULL
);

-- Foreign Key 설정 SQL - CorpCustomer(CorpCustomerID) -> Customer(CustomerID)
ALTER TABLE CorpCustomer
    ADD CONSTRAINT FK_CorpCustomer_CorpCustomerID_Customer_CustomerID FOREIGN KEY (CorpCustomerID)
        REFERENCES Customer (CustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CorpCustomer(CorpCustomerID)
-- ALTER TABLE CorpCustomer
-- DROP FOREIGN KEY FK_CorpCustomer_CorpCustomerID_Customer_CustomerID;


-- CombinedProduct Table Create SQL
-- 테이블 생성 SQL - CombinedProduct
CREATE TABLE CombinedProduct
(
    `CombinedProductID` BIGINT NOT NULL,
    `Price`             INT    NOT NULL,
    PRIMARY KEY (CombinedProductID)
);

-- 테이블 Comment 설정 SQL - CombinedProduct
ALTER TABLE CombinedProduct
    COMMENT '결합상품';


-- Accessory Table Create SQL
-- 테이블 생성 SQL - Accessory
CREATE TABLE Accessory
(
    `ProductID`     BIGINT      NOT NULL,
    `AccessoryName` VARCHAR(50) NOT NULL,
    `Price`         INT         NOT NULL
);

-- Foreign Key 설정 SQL - Accessory(ProductID) -> Product(ProductID)
ALTER TABLE Accessory
    ADD CONSTRAINT FK_Accessory_ProductID_Product_ProductID FOREIGN KEY (ProductID)
        REFERENCES Product (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Accessory(ProductID)
-- ALTER TABLE Accessory
-- DROP FOREIGN KEY FK_Accessory_ProductID_Product_ProductID;


-- AffiliateProduct Table Create SQL
-- 테이블 생성 SQL - AffiliateProduct
CREATE TABLE AffiliateProduct
(
    `ProductID`     BIGINT       NOT NULL,
    `AffiliateName` VARCHAR(50)  NOT NULL,
    `Descript`      VARCHAR(500) NOT NULL COMMENT '이벤트 설명',
    `Price`         INT          NOT NULL
);

-- 테이블 Comment 설정 SQL - AffiliateProduct
ALTER TABLE AffiliateProduct
    COMMENT '제휴상품';

-- Foreign Key 설정 SQL - AffiliateProduct(ProductID) -> Product(ProductID)
ALTER TABLE AffiliateProduct
    ADD CONSTRAINT FK_AffiliateProduct_ProductID_Product_ProductID FOREIGN KEY (ProductID)
        REFERENCES Product (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - AffiliateProduct(ProductID)
-- ALTER TABLE AffiliateProduct
-- DROP FOREIGN KEY FK_AffiliateProduct_ProductID_Product_ProductID;


-- Service Table Create SQL
-- 테이블 생성 SQL - Service
CREATE TABLE Service
(
    `ProductID`   BIGINT      NOT NULL,
    `ServiceName` VARCHAR(50) NOT NULL,
    `Price`       INT         NOT NULL
);

-- 테이블 Comment 설정 SQL - Service
ALTER TABLE Service
    COMMENT '부가서비스. 부가서비스';

-- Foreign Key 설정 SQL - Service(ProductID) -> Product(ProductID)
ALTER TABLE Service
    ADD CONSTRAINT FK_Service_ProductID_Product_ProductID FOREIGN KEY (ProductID)
        REFERENCES Product (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Service(ProductID)
-- ALTER TABLE Service
-- DROP FOREIGN KEY FK_Service_ProductID_Product_ProductID;


-- AirlineTicket Table Create SQL
-- 테이블 생성 SQL - AirlineTicket
CREATE TABLE AirlineTicket
(
    `ProductID`      BIGINT       NOT NULL,
    `DepartureTime`  DATETIME     NOT NULL,
    `ArrivalTime`    DATETIME     NOT NULL,
    `DeparturePlace` VARCHAR(100) NOT NULL,
    `ArrivalPlace`   VARCHAR(100) NOT NULL,
    `Price`          INT          NOT NULL
);

-- 테이블 Comment 설정 SQL - AirlineTicket
ALTER TABLE AirlineTicket
    COMMENT '항공권';

-- Foreign Key 설정 SQL - AirlineTicket(ProductID) -> Product(ProductID)
ALTER TABLE AirlineTicket
    ADD CONSTRAINT FK_AirlineTicket_ProductID_Product_ProductID FOREIGN KEY (ProductID)
        REFERENCES Product (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - AirlineTicket(ProductID)
-- ALTER TABLE AirlineTicket
-- DROP FOREIGN KEY FK_AirlineTicket_ProductID_Product_ProductID;


-- SEG Table Create SQL
-- 테이블 생성 SQL - SEG
CREATE TABLE SEG
(
    `SegID` BIGINT NOT NULL,
    PRIMARY KEY (SegID)
);

-- 테이블 Comment 설정 SQL - SEG
ALTER TABLE SEG
    COMMENT 'PNR의 SEG정보 관리';

-- Foreign Key 설정 SQL - SEG(SegID) -> PNR(PnrID)
ALTER TABLE SEG
    ADD CONSTRAINT FK_SEG_SegID_PNR_PnrID FOREIGN KEY (SegID)
        REFERENCES PNR (PnrID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - SEG(SegID)
-- ALTER TABLE SEG
-- DROP FOREIGN KEY FK_SEG_SegID_PNR_PnrID;


-- Airport Table Create SQL
-- 테이블 생성 SQL - Airport
CREATE TABLE Airport
(
    `AirportID`   BIGINT      NOT NULL AUTO_INCREMENT,
    `AirportName` VARCHAR(50) NOT NULL,
    PRIMARY KEY (AirportID)
);


-- CombinedProductDetail Table Create SQL
-- 테이블 생성 SQL - CombinedProductDetail
CREATE TABLE CombinedProductDetail
(
    `TicketID`    BIGINT NULL,
    `ServiceID`   BIGINT NULL,
    `AfProductID` BIGINT NULL,
    `AccessoryID` BIGINT NULL,
    `CombinedID`  BIGINT NOT NULL
);

-- 테이블 Comment 설정 SQL - CombinedProductDetail
ALTER TABLE CombinedProductDetail
    COMMENT '결합상품 상세';

-- Foreign Key 설정 SQL - CombinedProductDetail(TicketID) -> AirlineTicket(ProductID)
ALTER TABLE CombinedProductDetail
    ADD CONSTRAINT FK_CombinedProductDetail_TicketID_AirlineTicket_ProductID FOREIGN KEY (TicketID)
        REFERENCES AirlineTicket (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CombinedProductDetail(TicketID)
-- ALTER TABLE CombinedProductDetail
-- DROP FOREIGN KEY FK_CombinedProductDetail_TicketID_AirlineTicket_ProductID;

-- Foreign Key 설정 SQL - CombinedProductDetail(ServiceID) -> Service(ProductID)
ALTER TABLE CombinedProductDetail
    ADD CONSTRAINT FK_CombinedProductDetail_ServiceID_Service_ProductID FOREIGN KEY (ServiceID)
        REFERENCES Service (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CombinedProductDetail(ServiceID)
-- ALTER TABLE CombinedProductDetail
-- DROP FOREIGN KEY FK_CombinedProductDetail_ServiceID_Service_ProductID;

-- Foreign Key 설정 SQL - CombinedProductDetail(AfProductID) -> AffiliateProduct(ProductID)
ALTER TABLE CombinedProductDetail
    ADD CONSTRAINT FK_CombinedProductDetail_AfProductID_AffiliateProduct_ProductID FOREIGN KEY (AfProductID)
        REFERENCES AffiliateProduct (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CombinedProductDetail(AfProductID)
-- ALTER TABLE CombinedProductDetail
-- DROP FOREIGN KEY FK_CombinedProductDetail_AfProductID_AffiliateProduct_ProductID;

-- Foreign Key 설정 SQL - CombinedProductDetail(AccessoryID) -> Accessory(ProductID)
ALTER TABLE CombinedProductDetail
    ADD CONSTRAINT FK_CombinedProductDetail_AccessoryID_Accessory_ProductID FOREIGN KEY (AccessoryID)
        REFERENCES Accessory (ProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CombinedProductDetail(AccessoryID)
-- ALTER TABLE CombinedProductDetail
-- DROP FOREIGN KEY FK_CombinedProductDetail_AccessoryID_Accessory_ProductID;

-- Foreign Key 설정 SQL - CombinedProductDetail(CombinedID) -> CombinedProduct(CombinedProductID)
ALTER TABLE CombinedProductDetail
    ADD CONSTRAINT FK_CombinedProductDetail_CombinedID_CombinedProduct_CombinedProd FOREIGN KEY (CombinedID)
        REFERENCES CombinedProduct (CombinedProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CombinedProductDetail(CombinedID)
-- ALTER TABLE CombinedProductDetail
-- DROP FOREIGN KEY FK_CombinedProductDetail_CombinedID_CombinedProduct_CombinedProd;


-- CustomerIdentify Table Create SQL
-- 테이블 생성 SQL - CustomerIdentify
CREATE TABLE CustomerIdentify
(
    `CustomerID`     BIGINT      NOT NULL,
    `IdNumber`       VARCHAR(50) NOT NULL UNIQUE COMMENT '주민번호',
    `PassportNumber` VARCHAR(50) NOT NULL UNIQUE COMMENT '여권번호'
);

-- 테이블 Comment 설정 SQL - CustomerIdentify
ALTER TABLE CustomerIdentify
    COMMENT '고객식별';

-- Foreign Key 설정 SQL - CustomerIdentify(CustomerID) -> Customer(CustomerID)
ALTER TABLE CustomerIdentify
    ADD CONSTRAINT FK_CustomerIdentify_CustomerID_Customer_CustomerID FOREIGN KEY (CustomerID)
        REFERENCES Customer (CustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CustomerIdentify(CustomerID)
-- ALTER TABLE CustomerIdentify
-- DROP FOREIGN KEY FK_CustomerIdentify_CustomerID_Customer_CustomerID;


-- PreferFlight Table Create SQL
-- 테이블 생성 SQL - PreferFlight
CREATE TABLE PreferFlight
(
    `CustomerID` BIGINT NOT NULL,
    `RouteID`    BIGINT NULL
);

-- Foreign Key 설정 SQL - PreferFlight(CustomerID) -> Customer(CustomerID)
ALTER TABLE PreferFlight
    ADD CONSTRAINT FK_PreferFlight_CustomerID_Customer_CustomerID FOREIGN KEY (CustomerID)
        REFERENCES Customer (CustomerID) ON DELETE NO ACTION ON UPDATE RESTRICT,
    ADD CONSTRAINT FK_PreferFLight_RouteID_Route_RouteID FOREIGN KEY (RouteID)
        REFERENCES Route (RouteID) on DELETE NO ACTION ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PreferFlight(CustomerID)
-- ALTER TABLE PreferFlight
-- DROP FOREIGN KEY FK_PreferFlight_CustomerID_Customer_CustomerID;


-- CorpEmployee Table Create SQL
-- 테이블 생성 SQL - CorpEmployee
CREATE TABLE CorpEmployee
(
    `CorpCustomerID`     BIGINT NOT NULL,
    `PersonalCustomerID` BIGINT NOT NULL
);

-- Foreign Key 설정 SQL - CorpEmployee(CorpCustomerID) -> CorpCustomer(CorpCustomerID)
ALTER TABLE CorpEmployee
    ADD CONSTRAINT FK_CorpEmployee_CorpCustomerID_CorpCustomer_CorpCustomerID FOREIGN KEY (CorpCustomerID)
        REFERENCES CorpCustomer (CorpCustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CorpEmployee(CorpCustomerID)
-- ALTER TABLE CorpEmployee
-- DROP FOREIGN KEY FK_CorpEmployee_CorpCustomerID_CorpCustomer_CorpCustomerID;

-- Foreign Key 설정 SQL - CorpEmployee(PersonalCustomerID) -> PersonalCustomer(PersonalCustomerID)
ALTER TABLE CorpEmployee
    ADD CONSTRAINT FK_CorpEmployee_PersonalCustomerID_PersonalCustomer_PersonalCust FOREIGN KEY (PersonalCustomerID)
        REFERENCES PersonalCustomer (PersonalCustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CorpEmployee(PersonalCustomerID)
-- ALTER TABLE CorpEmployee
-- DROP FOREIGN KEY FK_CorpEmployee_PersonalCustomerID_PersonalCustomer_PersonalCust;


-- CorpBenefit Table Create SQL
-- 테이블 생성 SQL - CorpBenefit
CREATE TABLE CorpBenefit
(
    `CorpCustomerID` BIGINT       NOT NULL,
    `BenefitName`    VARCHAR(100) NOT NULL,
    `BenefitDetails` VARCHAR(200) NOT NULL
);

-- 테이블 Comment 설정 SQL - CorpBenefit
ALTER TABLE CorpBenefit
    COMMENT '법인혜택';

-- Foreign Key 설정 SQL - CorpBenefit(CorpCustomerID) -> CorpCustomer(CorpCustomerID)
ALTER TABLE CorpBenefit
    ADD CONSTRAINT FK_CorpBenefit_CorpCustomerID_CorpCustomer_CorpCustomerID FOREIGN KEY (CorpCustomerID)
        REFERENCES CorpCustomer (CorpCustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CorpBenefit(CorpCustomerID)
-- ALTER TABLE CorpBenefit
-- DROP FOREIGN KEY FK_CorpBenefit_CorpCustomerID_CorpCustomer_CorpCustomerID;


-- RegularMember Table Create SQL
-- 테이블 생성 SQL - RegularMember
CREATE TABLE RegularMember
(
    `PersonalCustomerID` BIGINT       NOT NULL,
    `RegularNickname`    VARCHAR(50)  NOT NULL,
    `RegularPassword`    VARCHAR(100) NOT NULL
);

-- 테이블 Comment 설정 SQL - RegularMember
ALTER TABLE RegularMember
    COMMENT '정회원';

-- Foreign Key 설정 SQL - RegularMember(PersonalCustomerID) -> PersonalCustomer(PersonalCustomerID)
ALTER TABLE RegularMember
    ADD CONSTRAINT FK_RegularMember_PersonalCustomerID_PersonalCustomer_PersonalCus FOREIGN KEY (PersonalCustomerID)
        REFERENCES PersonalCustomer (PersonalCustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - RegularMember(PersonalCustomerID)
-- ALTER TABLE RegularMember
-- DROP FOREIGN KEY FK_RegularMember_PersonalCustomerID_PersonalCustomer_PersonalCus;


-- SocialMember Table Create SQL
-- 테이블 생성 SQL - SocialMember
CREATE TABLE SocialMember
(
    `PersonalCustomerID` BIGINT       NOT NULL,
    `SocialType`         INT          NOT NULL,
    `PersonalID`         VARCHAR(100) NOT NULL COMMENT '소셜 아이디'
);

-- Foreign Key 설정 SQL - SocialMember(PersonalCustomerID) -> PersonalCustomer(PersonalCustomerID)
ALTER TABLE SocialMember
    ADD CONSTRAINT FK_SocialMember_PersonalCustomerID_PersonalCustomer_PersonalCust FOREIGN KEY (PersonalCustomerID)
        REFERENCES PersonalCustomer (PersonalCustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - SocialMember(PersonalCustomerID)
-- ALTER TABLE SocialMember
-- DROP FOREIGN KEY FK_SocialMember_PersonalCustomerID_PersonalCustomer_PersonalCust;


-- ProductCustomer Table Create SQL
-- 테이블 생성 SQL - ProductCustomer
CREATE TABLE ProductCustomer
(
    `ProductCustomerID` BIGINT NOT NULL AUTO_INCREMENT,
    `CustomerID`        BIGINT NOT NULL,
    `ProductID`         BIGINT NOT NULL,
    PRIMARY KEY (ProductCustomerID)
);

-- 테이블 Comment 설정 SQL - ProductCustomer
ALTER TABLE ProductCustomer
    COMMENT '상품과 고객을 연결하는 테이블';

-- Foreign Key 설정 SQL - ProductCustomer(ProductID) -> CombinedProduct(CombinedProductID)
ALTER TABLE ProductCustomer
    ADD CONSTRAINT FK_ProductCustomer_ProductID_CombinedProduct_CombinedProductID FOREIGN KEY (ProductID)
        REFERENCES CombinedProduct (CombinedProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ProductCustomer(ProductID)
-- ALTER TABLE ProductCustomer
-- DROP FOREIGN KEY FK_ProductCustomer_ProductID_CombinedProduct_CombinedProductID;

-- Foreign Key 설정 SQL - ProductCustomer(CustomerID) -> Customer(CustomerID)
ALTER TABLE ProductCustomer
    ADD CONSTRAINT FK_ProductCustomer_CustomerID_Customer_CustomerID FOREIGN KEY (CustomerID)
        REFERENCES Customer (CustomerID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ProductCustomer(CustomerID)
-- ALTER TABLE ProductCustomer
-- DROP FOREIGN KEY FK_ProductCustomer_CustomerID_Customer_CustomerID;


-- ServiceReservationHistory Table Create SQL
-- 테이블 생성 SQL - ServiceReservationHistory
CREATE TABLE ServiceReservationHistory
(
    `PnrID`     BIGINT NOT NULL,
    `SegID`     BIGINT NOT NULL,
    `ProductID` BIGINT NOT NULL
);

-- 테이블 Comment 설정 SQL - ServiceReservationHistory
ALTER TABLE ServiceReservationHistory
    COMMENT '부가서비스 예약내역';

-- Foreign Key 설정 SQL - ServiceReservationHistory(PnrID) -> PAX(PnrID)
ALTER TABLE ServiceReservationHistory
    ADD CONSTRAINT FK_ServiceReservationHistory_PnrID_PAX_PnrID FOREIGN KEY (PnrID)
        REFERENCES PAX (PnrID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ServiceReservationHistory(PnrID)
-- ALTER TABLE ServiceReservationHistory
-- DROP FOREIGN KEY FK_ServiceReservationHistory_PnrID_PAX_PnrID;

-- Foreign Key 설정 SQL - ServiceReservationHistory(SegID) -> SEG(SegID)
ALTER TABLE ServiceReservationHistory
    ADD CONSTRAINT FK_ServiceReservationHistory_SegID_SEG_SegID FOREIGN KEY (SegID)
        REFERENCES SEG (SegID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ServiceReservationHistory(SegID)
-- ALTER TABLE ServiceReservationHistory
-- DROP FOREIGN KEY FK_ServiceReservationHistory_SegID_SEG_SegID;

-- Foreign Key 설정 SQL - ServiceReservationHistory(ProductID) -> CombinedProduct(CombinedProductID)
ALTER TABLE ServiceReservationHistory
    ADD CONSTRAINT FK_ServiceReservationHistory_ProductID_CombinedProduct_CombinedP FOREIGN KEY (ProductID)
        REFERENCES CombinedProduct (CombinedProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ServiceReservationHistory(ProductID)
-- ALTER TABLE ServiceReservationHistory
-- DROP FOREIGN KEY FK_ServiceReservationHistory_ProductID_CombinedProduct_CombinedP;


-- SSR Table Create SQL
-- 테이블 생성 SQL - SSR
CREATE TABLE SSR
(
    `SsrID` BIGINT NOT NULL,
    `SegID` BIGINT NOT NULL
);

-- 테이블 Comment 설정 SQL - SSR
ALTER TABLE SSR
    COMMENT 'PNR의 SEG를 관리';

-- Foreign Key 설정 SQL - SSR(SsrID) -> PAX(PnrID)
ALTER TABLE SSR
    ADD CONSTRAINT FK_SSR_SsrID_PAX_PnrID FOREIGN KEY (SsrID)
        REFERENCES PAX (PnrID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - SSR(SsrID)
-- ALTER TABLE SSR
-- DROP FOREIGN KEY FK_SSR_SsrID_PAX_PnrID;

-- Foreign Key 설정 SQL - SSR(SegID) -> SEG(SegID)
ALTER TABLE SSR
    ADD CONSTRAINT FK_SSR_SegID_SEG_SegID FOREIGN KEY (SegID)
        REFERENCES SEG (SegID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - SSR(SegID)
-- ALTER TABLE SSR
-- DROP FOREIGN KEY FK_SSR_SegID_SEG_SegID;


-- Coupon Table Create SQL
-- 테이블 생성 SQL - Coupon
CREATE TABLE Coupon
(
    `SegID`      BIGINT      NOT NULL,
    `TicketID`   BIGINT      NOT NULL,
    `CouponName` VARCHAR(50) NOT NULL
);

-- Foreign Key 설정 SQL - Coupon(SegID) -> SEG(SegID)
ALTER TABLE Coupon
    ADD CONSTRAINT FK_Coupon_SegID_SEG_SegID FOREIGN KEY (SegID)
        REFERENCES SEG (SegID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Coupon(SegID)
-- ALTER TABLE Coupon
-- DROP FOREIGN KEY FK_Coupon_SegID_SEG_SegID;

-- Foreign Key 설정 SQL - Coupon(TicketID) -> Ticket(TicketID)
ALTER TABLE Coupon
    ADD CONSTRAINT FK_Coupon_TicketID_Ticket_TicketID FOREIGN KEY (TicketID)
        REFERENCES Ticket (TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Coupon(TicketID)
-- ALTER TABLE Coupon
-- DROP FOREIGN KEY FK_Coupon_TicketID_Ticket_TicketID;


-- Fee Table Create SQL
-- 테이블 생성 SQL - Fee
CREATE TABLE Fee
(
    `FeeID` BIGINT NOT NULL
);

-- Foreign Key 설정 SQL - Fee(FeeID) -> Ticket(TicketID)
ALTER TABLE Fee
    ADD CONSTRAINT FK_Fee_FeeID_Ticket_TicketID FOREIGN KEY (FeeID)
        REFERENCES Ticket (TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Fee(FeeID)
-- ALTER TABLE Fee
-- DROP FOREIGN KEY FK_Fee_FeeID_Ticket_TicketID;


-- Tax Table Create SQL
-- 테이블 생성 SQL - Tax
CREATE TABLE Tax
(
    `TaxID`    BIGINT NOT NULL,
    `TaxPrice` INT    NOT NULL,
    `TaxType`  INT    NOT NULL COMMENT '1:유류할증 2:제세공과금'
);

-- Foreign Key 설정 SQL - Tax(TaxID) -> Ticket(TicketID)
ALTER TABLE Tax
    ADD CONSTRAINT FK_Tax_TaxID_Ticket_TicketID FOREIGN KEY (TaxID)
        REFERENCES Ticket (TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Tax(TaxID)
-- ALTER TABLE Tax
-- DROP FOREIGN KEY FK_Tax_TaxID_Ticket_TicketID;


-- PaymentMethod Table Create SQL
-- 테이블 생성 SQL - PaymentMethod
CREATE TABLE PaymentMethod
(
    `PaymentID`   BIGINT NOT NULL,
    `PaymentType` INT    NOT NULL
);

-- Foreign Key 설정 SQL - PaymentMethod(PaymentID) -> Ticket(TicketID)
ALTER TABLE PaymentMethod
    ADD CONSTRAINT FK_PaymentMethod_PaymentID_Ticket_TicketID FOREIGN KEY (PaymentID)
        REFERENCES Ticket (TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - PaymentMethod(PaymentID)
-- ALTER TABLE PaymentMethod
-- DROP FOREIGN KEY FK_PaymentMethod_PaymentID_Ticket_TicketID;


-- RefundFee Table Create SQL
-- 테이블 생성 SQL - RefundFee
CREATE TABLE RefundFee
(
    `RefundFeeID`    BIGINT NOT NULL,
    `RefundFeePrice` INT    NOT NULL
);

-- Foreign Key 설정 SQL - RefundFee(RefundFeeID) -> Refund(RefundID)
ALTER TABLE RefundFee
    ADD CONSTRAINT FK_RefundFee_RefundFeeID_Refund_RefundID FOREIGN KEY (RefundFeeID)
        REFERENCES Refund (RefundID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - RefundFee(RefundFeeID)
-- ALTER TABLE RefundFee
-- DROP FOREIGN KEY FK_RefundFee_RefundFeeID_Refund_RefundID;


-- RefundTax Table Create SQL
-- 테이블 생성 SQL - RefundTax
CREATE TABLE RefundTax
(
    `RefundTaxID` BIGINT NOT NULL
);

-- Foreign Key 설정 SQL - RefundTax(RefundTaxID) -> Refund(RefundID)
ALTER TABLE RefundTax
    ADD CONSTRAINT FK_RefundTax_RefundTaxID_Refund_RefundID FOREIGN KEY (RefundTaxID)
        REFERENCES Refund (RefundID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - RefundTax(RefundTaxID)
-- ALTER TABLE RefundTax
-- DROP FOREIGN KEY FK_RefundTax_RefundTaxID_Refund_RefundID;


-- RefundPaymentMethod Table Create SQL
-- 테이블 생성 SQL - RefundPaymentMethod
CREATE TABLE RefundPaymentMethod
(
    `RefundPaymentID` BIGINT NOT NULL,
    `RefundType`      INT    NOT NULL
);

-- Foreign Key 설정 SQL - RefundPaymentMethod(RefundPaymentID) -> Refund(RefundID)
ALTER TABLE RefundPaymentMethod
    ADD CONSTRAINT FK_RefundPaymentMethod_RefundPaymentID_Refund_RefundID FOREIGN KEY (RefundPaymentID)
        REFERENCES Refund (RefundID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - RefundPaymentMethod(RefundPaymentID)
-- ALTER TABLE RefundPaymentMethod
-- DROP FOREIGN KEY FK_RefundPaymentMethod_RefundPaymentID_Refund_RefundID;


-- AdditionalServiceDetails Table Create SQL
-- 테이블 생성 SQL - AdditionalServiceDetails
CREATE TABLE AdditionalServiceDetails
(
    `AdditionalServiceID` BIGINT NOT NULL
);

-- Foreign Key 설정 SQL - AdditionalServiceDetails(AddtionServiceID) -> Ticket(TicketID)
ALTER TABLE AdditionalServiceDetails
    ADD CONSTRAINT FK_AdditionalServiceDetails_AdditionalServiceID_Ticket_TicketID FOREIGN KEY (AdditionalServiceID)
        REFERENCES Ticket (TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - AdditionalServiceDetails(AddtionServiceID)
-- ALTER TABLE AdditionalServiceDetails
-- DROP FOREIGN KEY FK_AdditionalServiceDetails_AddtionServiceID_Ticket_TicketID;


-- ServiceHistory Table Create SQL
-- 테이블 생성 SQL - ServiceHistory
CREATE TABLE ServiceHistory
(
    `ServiceHistoryID` BIGINT NOT NULL,
    `ProductID`        BIGINT NOT NULL
);

-- 테이블 Comment 설정 SQL - ServiceHistory
ALTER TABLE ServiceHistory
    COMMENT '부가서비스 내역';

-- Foreign Key 설정 SQL - ServiceHistory(ServiceHistoryID) -> Ticket(TicketID)
ALTER TABLE ServiceHistory
    ADD CONSTRAINT FK_ServiceHistory_ServiceHistoryID_Ticket_TicketID FOREIGN KEY (ServiceHistoryID)
        REFERENCES Ticket (TicketID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ServiceHistory(ServiceHistoryID)
-- ALTER TABLE ServiceHistory
-- DROP FOREIGN KEY FK_ServiceHistory_ServiceHistoryID_Ticket_TicketID;

-- Foreign Key 설정 SQL - ServiceHistory(ProductID) -> CombinedProduct(CombinedProductID)
ALTER TABLE ServiceHistory
    ADD CONSTRAINT FK_ServiceHistory_ProductID_CombinedProduct_CombinedProductID FOREIGN KEY (ProductID)
        REFERENCES CombinedProduct (CombinedProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ServiceHistory(ProductID)
-- ALTER TABLE ServiceHistory
-- DROP FOREIGN KEY FK_ServiceHistory_ProductID_CombinedProduct_CombinedProductID;


-- Advisory Table Create SQL
-- 테이블 생성 SQL - Advisory
CREATE TABLE Advisory
(
    `AdvisoryID` BIGINT NOT NULL,
    `RouteID`    BIGINT NOT NULL,
    `Status`     INT    NOT NULL DEFAULT 1 COMMENT '1:정상 2:지연 3:결항'
);

-- 테이블 Comment 설정 SQL - Advisory
ALTER TABLE Advisory
    COMMENT 'AirTraffic Control';

-- Foreign Key 설정 SQL - Advisory(AdvisoryID) -> Airport(AirportID)
ALTER TABLE Advisory
    ADD CONSTRAINT FK_Advisory_AdvisoryID_Airport_AirportID FOREIGN KEY (AdvisoryID)
        REFERENCES Airport (AirportID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Advisory(AdvisoryID)
-- ALTER TABLE Advisory
-- DROP FOREIGN KEY FK_Advisory_AdvisoryID_Airport_AirportID;

-- Foreign Key 설정 SQL - Advisory(RouteID) -> Route(RouteID)
ALTER TABLE Advisory
    ADD CONSTRAINT FK_Advisory_RouteID_Route_RouteID FOREIGN KEY (RouteID)
        REFERENCES Route (RouteID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Advisory(RouteID)
-- ALTER TABLE Advisory
-- DROP FOREIGN KEY FK_Advisory_RouteID_Route_RouteID;


-- DepartureArrival Table Create SQL
-- 테이블 생성 SQL - DepartureArrival
CREATE TABLE DepartureArrival
(
    `DepartureArrival` BIGINT      NOT NULL,
    `DeparturePlace`   VARCHAR(50) NOT NULL,
    `ArrivalPlace`     VARCHAR(50) NOT NULL,
    `Mileage`          INT         NOT NULL
);

-- Foreign Key 설정 SQL - DepartureArrival(DepartureArrival) -> Route(RouteID)
ALTER TABLE DepartureArrival
    ADD CONSTRAINT FK_DepartureArrival_DepartureArrival_Route_RouteID FOREIGN KEY (DepartureArrival)
        REFERENCES Route (RouteID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - DepartureArrival(DepartureArrival)
-- ALTER TABLE DepartureArrival
-- DROP FOREIGN KEY FK_DepartureArrival_DepartureArrival_Route_RouteID;


-- CrewFlightPlan Table Create SQL
-- 테이블 생성 SQL - CrewFlightPlan
CREATE TABLE CrewFlightPlan
(
    `CrewFlightPlanID` BIGINT NOT NULL,
    `RouteID`          BIGINT NOT NULL
);

-- Foreign Key 설정 SQL - CrewFlightPlan(CrewFlightPlanID) -> CrewGroup(GroupID)
ALTER TABLE CrewFlightPlan
    ADD CONSTRAINT FK_CrewFlightPlan_CrewFlightPlanID_CrewGroup_GroupID FOREIGN KEY (CrewFlightPlanID)
        REFERENCES CrewGroup (GroupID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CrewFlightPlan(CrewFlightPlanID)
-- ALTER TABLE CrewFlightPlan
-- DROP FOREIGN KEY FK_CrewFlightPlan_CrewFlightPlanID_CrewGroup_GroupID;

-- Foreign Key 설정 SQL - CrewFlightPlan(RouteID) -> AircraftPlan(RouteID)
ALTER TABLE CrewFlightPlan
    ADD CONSTRAINT FK_CrewFlightPlan_RouteID_AircraftPlan_RouteID FOREIGN KEY (RouteID)
        REFERENCES AircraftPlan (RouteID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - CrewFlightPlan(RouteID)
-- ALTER TABLE CrewFlightPlan
-- DROP FOREIGN KEY FK_CrewFlightPlan_RouteID_AircraftPlan_RouteID;


-- AirlineProduct Table Create SQL
-- 테이블 생성 SQL - AirlineProduct
CREATE TABLE AirlineProduct
(
    `AirlineProductID` BIGINT NOT NULL AUTO_INCREMENT,
    `AirlineID`        BIGINT NOT NULL,
    `ProductID`        BIGINT NOT NULL,
    PRIMARY KEY (AirlineProductID)
);

-- Foreign Key 설정 SQL - AirlineProduct(AirlineID) -> Airline(AirlineID)
ALTER TABLE AirlineProduct
    ADD CONSTRAINT FK_AirlineProduct_AirlineID_Airline_AirlineID FOREIGN KEY (AirlineID)
        REFERENCES Airline (AirlineID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - AirlineProduct(AirlineID)
-- ALTER TABLE AirlineProduct
-- DROP FOREIGN KEY FK_AirlineProduct_AirlineID_Airline_AirlineID;

-- Foreign Key 설정 SQL - AirlineProduct(ProductID) -> CombinedProduct(CombinedProductID)
ALTER TABLE AirlineProduct
    ADD CONSTRAINT FK_AirlineProduct_ProductID_CombinedProduct_CombinedProductID FOREIGN KEY (ProductID)
        REFERENCES CombinedProduct (CombinedProductID) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - AirlineProduct(ProductID)
-- ALTER TABLE AirlineProduct
-- DROP FOREIGN KEY FK_AirlineProduct_ProductID_CombinedProduct_CombinedProductID;


