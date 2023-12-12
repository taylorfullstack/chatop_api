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
  PRIMARY KEY (id)

);

CREATE TABLE messages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    rental_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    message varchar(2000),
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX users_index ON users (email);

ALTER TABLE users ADD FOREIGN KEY (id) REFERENCES rentals (owner_id);
ALTER TABLE messages ADD FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE messages ADD FOREIGN KEY (rental_id) REFERENCES rentals (id);



