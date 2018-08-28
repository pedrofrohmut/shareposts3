CREATE DATABASE shareposts
	CHARSET utf8mb4
    COLLATE utf8mb4_unicode_ci;

DROP TABLE IF EXISTS shareposts.user;

DROP TABLE IF EXISTS shareposts.post;

CREATE TABLE shareposts.user (
	id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE shareposts.post (
	id INT AUTO_INCREMENT NOT NULL,
	user_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

/*FIX: server time zone defference when try to connect*/
SELECT @@system_time_zone;
SELECT @@time_zone;
SET GLOBAL time_zone='-3:00';

TRUNCATE TABLE shareposts.user;
TRUNCATE TABLE shareposts.post;