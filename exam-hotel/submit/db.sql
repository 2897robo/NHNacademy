CREATE TABLE IF NOT EXISTS `hotels` (
    `hotel_id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`hotel_id`)
) ENGINE = InnoDB;

insert into hotels values (100, 'Hilton');
INSERT INTO hotels VALUES (101, 'Marriott');
INSERT INTO hotels VALUES (102, 'Hyatt');

CREATE TABLE IF NOT EXISTS `rooms` (
    `room_id` BIGINT NOT NULL AUTO_INCREMENT,
    `hotel_id` BIGINT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `capacity` TINYINT NOT NULL,
    `floor` TINYINT NOT NULL,
    `bathtub_flag` TINYINT(1) NOT NULL DEFAULT 1,
    `view_type` TINYINT NOT NULL,
    `created_at` DATETIME NOT NULL,
    `peak_price` DECIMAL NOT NULL,
    `non_peak_price` DECIMAL NOT NULL,
    PRIMARY KEY (`room_id`),
    INDEX `fk_Rooms_hotels_idx` (`hotel_id` ASC),
    CONSTRAINT `fk_Rooms_hotels`
    FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

INSERT INTO rooms (hotel_id, name, capacity, floor, bathtub_flag, view_type, created_at, peak_price, non_peak_price) VALUES
(100, 'Deluxe King', 2, 5, 1, 0, NOW(), 300.00, 250.00),
(100, 'Executive Suite', 3, 10, 1, 1, NOW(), 500.00, 400.00),
(100, 'Ocean View Twin', 2, 8, 1, 2, NOW(), 350.00, 300.00);

CREATE TABLE `users` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL,
    `email` VARCHAR(30) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `phone` VARCHAR(13) NOT NULL,
    PRIMARY KEY (`user_id`)
);

INSERT INTO users (name, email, password, phone) VALUES ('John Doe', 'john@example.com', 'password123', '123-456-7890');
INSERT INTO users (name, email, password, phone) VALUES ('Jane Smith', 'jane@example.com', 'securepass', '987-654-3210');
INSERT INTO users (name, email, password, phone) VALUES ('Bob Johnson', 'bob@example.com', 'bobpass123', '555-555-5555');

CREATE TABLE `reservations` (
    `reservation_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `room_id` BIGINT NOT NULL,
    `check-in` DATE NOT NULL,
    `check-out` DATE NOT NULL,
    `peoples` TINYINT NOT NULL,
    `status` VARCHAR(15) NOT NULL DEFAULT 'pending' COMMENT 'pending, confirmed, cancelled',
    PRIMARY KEY (`reservation_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`)
);

INSERT INTO reservations (user_id, room_id, `check-in`, `check-out`, peoples, status) VALUES
(1, 1, '2024-05-01', '2024-05-05', 2, 'confirmed'),
(2, 2, '2024-05-15', '2024-05-20', 3, 'confirmed'),
(3, 3, '2024-06-01', '2024-06-07', 2, 'pending');

CREATE TABLE `images` (
    `image_id` BIGINT NOT NULL AUTO_INCREMENT,
    `url` VARCHAR(300) NOT NULL,
    PRIMARY KEY (`image_id`)
);

INSERT INTO images (url) VALUES ('https://www.lottehotel.com/content/dam/lotte-hotel/lotte/world/accommodation/residential-room/3813-04-roo-2000-LTWO.jpg.thumb.768.768.jpg');
INSERT INTO images (url) VALUES ('https://wehotel.co.kr/wp-content/uploads/sites/35/2024/01/%EB%94%94%EB%9F%AD%EC%8A%A4-scaled.jpg');
INSERT INTO images (url) VALUES ('https://www.tokyodome-hotels.co.jp/files/20160802/img_stay_japaneseroom.jpg');

CREATE TABLE `rooms_images` (
    `room_image_id` BIGINT NOT NULL AUTO_INCREMENT,
    `room_id` BIGINT NOT NULL,
    `image_id` BIGINT NOT NULL,
    PRIMARY KEY (`room_image_id`),
    FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`),
    FOREIGN KEY (`image_id`) REFERENCES `images` (`image_id`)
);

INSERT INTO rooms_images (room_id, image_id) VALUES (1, 1);
INSERT INTO rooms_images (room_id, image_id) VALUES (2, 2);
INSERT INTO rooms_images (room_id, image_id) VALUES (3, 3);

CREATE TABLE `point_earning_policy` (
    `point_earning_policy_id` BIGINT NOT NULL AUTO_INCREMENT,
    `point_earning_policy_type` VARCHAR(15) NOT NULL,
    `point_earning_policy_amount` DECIMAL NOT NULL,
    PRIMARY KEY (`point_earning_policy_id`)
);

INSERT INTO point_earning_policy (point_earning_policy_type, point_earning_policy_amount)
VALUES ('Booking', 100.00);
INSERT INTO point_earning_policy (point_earning_policy_type, point_earning_policy_amount)
VALUES ('Review', 200.00);
INSERT INTO point_earning_policy (point_earning_policy_type, point_earning_policy_amount)
VALUES ('Referral', 500.00);

CREATE TABLE `point_transactions` (
    `point_transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
    `point_earning_policy_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `point_transaction_amount` DECIMAL NOT NULL,
    `point_transaction_date` DATE NOT NULL,
    PRIMARY KEY (`point_transaction_id`),
    FOREIGN KEY (`point_earning_policy_id`) REFERENCES `point_earning_policy` (`point_earning_policy_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

INSERT INTO point_transactions (point_earning_policy_id, user_id, point_transaction_amount, point_transaction_date)
VALUES (1, 1, 100.00, '2024-05-01');
INSERT INTO point_transactions (point_earning_policy_id, user_id, point_transaction_amount, point_transaction_date)
VALUES (2, 1, 200.00, '2024-05-12');
INSERT INTO point_transactions (point_earning_policy_id, user_id, point_transaction_amount, point_transaction_date)
VALUES (2, 2, 200.00, '2024-05-21');
INSERT INTO point_transactions (point_earning_policy_id, user_id, point_transaction_amount, point_transaction_date)
VALUES (3, 3, 500.00, '2024-09-15');

CREATE TABLE `reviews` (
    `review_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `comment` VARCHAR(400) NOT NULL,
    `review_date` DATE NOT NULL DEFAULT (CURRENT_DATE),
    `status` VARCHAR(15) NOT NULL DEFAULT 'pending' COMMENT 'pending, approved, rejected',
    PRIMARY KEY (`review_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

INSERT INTO reviews (user_id, comment, review_date, status) VALUES
(1, 'Great stay at Hilton!', '2024-05-12', 'approved'),
(2, 'Excellent service and comfortable room', '2024-05-21', 'approved'),
(3, 'Nice view but room service was slow', '2024-06-08', 'pending');

CREATE TABLE `reviews_images` (
    `review_image_id` BIGINT NOT NULL AUTO_INCREMENT,
    `review_id` BIGINT NOT NULL,
    `image_id` BIGINT NOT NULL,
    PRIMARY KEY (`review_image_id`),
    FOREIGN KEY (`review_id`) REFERENCES `reviews` (`review_id`),
    FOREIGN KEY (`image_id`) REFERENCES `images` (`image_id`)
);

INSERT INTO reviews_images (review_id, image_id) VALUES (1, 1);
INSERT INTO reviews_images (review_id, image_id) VALUES (2, 2);
INSERT INTO reviews_images (review_id, image_id) VALUES (3, 3);