CREATE TABLE users(
    id INT auto_increment NOT NULL PRIMARY KEY,
    login VARCHAR(255) NOT NULL, 
    password VARCHAR(256) NOT NULL,
    role VARCHAR(255) NOT NULL 
);