-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.
drop database if exists shopDatabases;
create database shopDatabases;
use shopDatabases;
-- Member Table Create SQL
-- 테이블 생성 SQL - Member
-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- Member Table Create SQL
-- 테이블 생성 SQL - Member
CREATE TABLE Member
(
    `name`       varchar(50)     NOT NULL    COMMENT 'name',
    `phoneNum`   varchar(50)     NOT NULL    COMMENT 'phoneNum',
    `email`      varchar(255)    NOT NULL    COMMENT 'email',
    `memberId`   bigint          NOT NULL    AUTO_INCREMENT COMMENT 'memberId',
    `createdAt`  datetime        NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT 'createdAt',
    `updatedAt`  datetime        NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT 'updatedAt',
    `status`     int             NOT NULL    COMMENT 'status. 1:활성 2:비활성 3:탈퇴',
    `address`    varchar(200)    NOT NULL    COMMENT 'address',
    CONSTRAINT PK_고객 PRIMARY KEY (memberId)
);


-- Address Table Create SQL
-- 테이블 생성 SQL - Address
CREATE TABLE Address
(
    `addressId`    bigint          NOT NULL    AUTO_INCREMENT COMMENT 'addressId',
    `addressName`  varchar(200)    NOT NULL    COMMENT 'addressName',
     PRIMARY KEY (addressId)
);


-- Product Table Create SQL
-- 테이블 생성 SQL - Product
CREATE TABLE Product
(
    `productId`      bigint         NOT NULL    AUTO_INCREMENT COMMENT '상품번호',
    `productName`    varchar(50)    NOT NULL    COMMENT '상품이름',
    `modelId`        bigint         NOT NULL    COMMENT '모델번호',
    `price`          bigint         NOT NULL    COMMENT '가격',
    `stockQuantity`  bigint         NOT NULL    COMMENT '재고수량',
    `status`         int            NOT NULL    COMMENT '주문상태',
     PRIMARY KEY (productId)
);


-- OrderTable Table Create SQL
-- 테이블 생성 SQL - OrderTable
CREATE TABLE OrderTable
(
    `orderId`   bigint      NOT NULL    AUTO_INCREMENT COMMENT 'orderId',
    `memberId`  bigint      NOT NULL    COMMENT 'memberId',
    `date`      datetime    NOT NULL    DEFAULT CURRENT_TIMESTAMP COMMENT 'date',
    `status`    int         NULL        COMMENT 'status',
    `isShare`   real        NULL        DEFAULT false COMMENT 'isShare',
    CONSTRAINT PK_주문 PRIMARY KEY (orderId)
);

-- Foreign Key 설정 SQL - OrderTable(memberId) -> Member(memberId)
ALTER TABLE OrderTable
    ADD CONSTRAINT FK_OrderTable_memberId_Member_memberId FOREIGN KEY (memberId)
        REFERENCES Member (memberId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - OrderTable(memberId)
-- ALTER TABLE OrderTable
-- DROP FOREIGN KEY FK_OrderTable_memberId_Member_memberId;


-- Category Table Create SQL
-- 테이블 생성 SQL - Category
CREATE TABLE Category
(
    `categoryId`    bigint         NOT NULL    AUTO_INCREMENT COMMENT 'categoryId',
    `categoryName`  varchar(50)    NOT NULL    COMMENT 'categoryName',
    `productId`     bigint         NOT NULL    COMMENT 'productId',
     PRIMARY KEY (categoryId)
);


-- Clothes Table Create SQL
-- 테이블 생성 SQL - Clothes
CREATE TABLE Clothes
(
    `productId`  bigint         NOT NULL    AUTO_INCREMENT COMMENT 'productId',
    `size`       varchar(50)    NOT NULL    COMMENT 'size',
    `color`      varchar(50)    NOT NULL    COMMENT 'color',
     PRIMARY KEY (productId)
);

-- Foreign Key 설정 SQL - Clothes(productId) -> Product(productId)
ALTER TABLE Clothes
    ADD CONSTRAINT FK_Clothes_productId_Product_productId FOREIGN KEY (productId)
        REFERENCES Product (productId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Clothes(productId)
-- ALTER TABLE Clothes
-- DROP FOREIGN KEY FK_Clothes_productId_Product_productId;


-- MemberAddress Table Create SQL
-- 테이블 생성 SQL - MemberAddress
CREATE TABLE MemberAddress
(
    `memberAddressId`  bigint    NOT NULL    COMMENT 'memberAddressId',
    `addressId`        bigint    NOT NULL    COMMENT 'addressId',
    `memberId`         bigint    NOT NULL    COMMENT 'memberId',
     PRIMARY KEY (memberAddressId)
);

-- Foreign Key 설정 SQL - MemberAddress(addressId) -> Address(addressId)
ALTER TABLE MemberAddress
    ADD CONSTRAINT FK_MemberAddress_addressId_Address_addressId FOREIGN KEY (addressId)
        REFERENCES Address (addressId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - MemberAddress(addressId)
-- ALTER TABLE MemberAddress
-- DROP FOREIGN KEY FK_MemberAddress_addressId_Address_addressId;

-- Foreign Key 설정 SQL - MemberAddress(memberId) -> Member(memberId)
ALTER TABLE MemberAddress
    ADD CONSTRAINT FK_MemberAddress_memberId_Member_memberId FOREIGN KEY (memberId)
        REFERENCES Member (memberId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - MemberAddress(memberId)
-- ALTER TABLE MemberAddress
-- DROP FOREIGN KEY FK_MemberAddress_memberId_Member_memberId;


-- Book Table Create SQL
-- 테이블 생성 SQL - Book
CREATE TABLE Book
(
    `productId`  bigint         NOT NULL    COMMENT 'ISBN',
    `author`     varchar(50)    NOT NULL    COMMENT '저자',
     PRIMARY KEY (productId)
);

-- Foreign Key 설정 SQL - Book(productId) -> Product(productId)
ALTER TABLE Book
    ADD CONSTRAINT FK_Book_productId_Product_productId FOREIGN KEY (productId)
        REFERENCES Product (productId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Book(productId)
-- ALTER TABLE Book
-- DROP FOREIGN KEY FK_Book_productId_Product_productId;


-- ProductCategory Table Create SQL
-- 테이블 생성 SQL - ProductCategory
CREATE TABLE ProductCategory
(
    `categoryId`  bigint    NOT NULL    COMMENT 'categoryId',
    `productId`   bigint    NOT NULL    COMMENT 'productId',
    `id`          bigint    NOT NULL    AUTO_INCREMENT COMMENT 'id',
     PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - ProductCategory(categoryId) -> Category(categoryId)
ALTER TABLE ProductCategory
    ADD CONSTRAINT FK_ProductCategory_categoryId_Category_categoryId FOREIGN KEY (categoryId)
        REFERENCES Category (categoryId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ProductCategory(categoryId)
-- ALTER TABLE ProductCategory
-- DROP FOREIGN KEY FK_ProductCategory_categoryId_Category_categoryId;

-- Foreign Key 설정 SQL - ProductCategory(productId) -> Product(productId)
ALTER TABLE ProductCategory
    ADD CONSTRAINT FK_ProductCategory_productId_Product_productId FOREIGN KEY (productId)
        REFERENCES Product (productId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - ProductCategory(productId)
-- ALTER TABLE ProductCategory
-- DROP FOREIGN KEY FK_ProductCategory_productId_Product_productId;


-- Deliver Table Create SQL
-- 테이블 생성 SQL - Deliver
CREATE TABLE Deliver
(
    `deliverId`  bigint         NOT NULL    AUTO_INCREMENT COMMENT 'deliverId',
    `address`    varchar(50)    NOT NULL    COMMENT 'address',
    `staus`      int            NOT NULL    COMMENT 'staus',
    `orderId`    bigint         NOT NULL    COMMENT 'orderId',
    `invoice`    char(20)       NULL        COMMENT 'invoice',
     PRIMARY KEY (deliverId)
);

-- Foreign Key 설정 SQL - Deliver(orderId) -> OrderTable(orderId)
ALTER TABLE Deliver
    ADD CONSTRAINT FK_Deliver_orderId_OrderTable_orderId FOREIGN KEY (orderId)
        REFERENCES OrderTable (orderId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - Deliver(orderId)
-- ALTER TABLE Deliver
-- DROP FOREIGN KEY FK_Deliver_orderId_OrderTable_orderId;


-- OrderProduct Table Create SQL
-- 테이블 생성 SQL - OrderProduct
CREATE TABLE OrderProduct
(
    `productId`   bigint    NOT NULL    COMMENT 'productId',
    `orderId`     bigint    NOT NULL    COMMENT 'orderId',
    `totalPrice`  bigint    NOT NULL    DEFAULT 0 COMMENT 'totalPrice'
);

-- Foreign Key 설정 SQL - OrderProduct(orderId) -> OrderTable(orderId)
ALTER TABLE OrderProduct
    ADD CONSTRAINT FK_OrderProduct_orderId_OrderTable_orderId FOREIGN KEY (orderId)
        REFERENCES OrderTable (orderId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - OrderProduct(orderId)
-- ALTER TABLE OrderProduct
-- DROP FOREIGN KEY FK_OrderProduct_orderId_OrderTable_orderId;

-- Foreign Key 설정 SQL - OrderProduct(productId) -> Product(productId)
ALTER TABLE OrderProduct
    ADD CONSTRAINT FK_OrderProduct_productId_Product_productId FOREIGN KEY (productId)
        REFERENCES Product (productId) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - OrderProduct(productId)
-- ALTER TABLE OrderProduct
-- DROP FOREIGN KEY FK_OrderProduct_productId_Product_productId;


