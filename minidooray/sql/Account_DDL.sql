CREATE TABLE User (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    user_status ENUM('ACTIVE', 'DORMANT', 'ENDED') NOT NULL
);

CREATE TABLE OAuth (
    oauth_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    oauth_provider VARCHAR(255) NOT NULL,
    oauth_user_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON DELETE CASCADE,
    UNIQUE (user_id, oauth_provider)
);
