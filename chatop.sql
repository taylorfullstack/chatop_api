DROP DATABASE IF EXISTS chatop;
CREATE DATABASE chatop;
USE chatop;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)

);

CREATE TABLE rentals(
  id BIGINT NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  surface int NOT NULL,
  price int NOT NULL,
  picture varchar(2000) DEFAULT NULL,
  description text,
  owner_id BIGINT NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rental_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    message varchar(2000),
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (rental_id) REFERENCES rentals(id)
);

CREATE UNIQUE INDEX users_index ON users (email);


INSERT INTO users (name, email, password) VALUES
('Test User', 'test@test.com', 'test!31');

INSERT INTO rentals (name, surface, price, picture, description, owner_id) VALUES
('Lakefront Cottage', 100, 150, 'https://cdn.pixabay.com/photo/2016/08/11/23/48/mountains-1587287_1280.jpg', 'Quiet cottage in the mountains', 1),
('Beach House', 200, 250, 'https://cdn.pixabay.com/photo/2020/10/13/13/28/ameland-5651866_1280.jpg', 'Beautiful beach house', 1),
('Snowy Cabin', 100, 399,  'https://cdn.pixabay.com/photo/2016/11/19/14/30/aurora-borealis-1839582_1280.jpg', 'Gorgeous view of the aurora borealis', 1);

