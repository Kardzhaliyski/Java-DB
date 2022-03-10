CREATE SCHEMA gamebar DEFAULT CHARSET utf8 DEFAULT COLLATE utf8_general_ci;
USE gamebar;

CREATE TABLE `employees` (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50),
last_name VARCHAR(50)
);

CREATE TABLE `categories` (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL
);

CREATE TABLE `products` (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
category_id INT,
FOREIGN KEY (category_id) REFERENCES `categories`(id)
);

ALTER TABLE `products`
ADD FOREIGN KEY (category_id) REFERENCES `categories`(id);

ALTER TABLE `employees`
MODIFY COLUMN `middle_name` VARCHAR(100);

INSERT INTO gamebar.employees(first_name, last_name) VALUES ('Pesho', 'Peshev');
INSERT INTO gamebar.employees(first_name, last_name) VALUES ('Gosho', 'Peshev');
INSERT INTO gamebar.employees(first_name, last_name) VALUES ('Stefo', 'Peshev');

ALTER TABLE gamebar.employees ADD COLUMN `middle_name` VARCHAR(50) AFTER `first_name`;

INSERT INTO gamebar.categories(`name`) VALUES('Food');
INSERT INTO gamebar.categories(`name`) VALUES('Vegetables');
INSERT INTO gamebar.categories(`name`) VALUES('mango');

INSERT INTO gamebar.products(name,category_id) VALUES ('mango', 1);
INSERT INTO gamebar.products(name, category_id) VALUES ('Tomato', '3');
INSERT INTO gamebar.products(name, category_id) VALUES ('Potato', '3');

UPDATE gamebar.products SET `name` = 'Mango' WHERE (`id` = '3');

DELETE FROM gamebar.products WHERE (`id` = '5');

SELECT * FROM gamebar.products;
select * FROM employees;

DROP SCHEMA gamebar;
DROP TABLE gamebar.employees;
