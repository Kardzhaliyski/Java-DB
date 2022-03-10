DROP SCHEMA `minions`;
CREATE SCHEMA `minions`;
USE `minusersusersions`;

CREATE TABLE `minions` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) NOT NULL,
`age` INT UNSIGNED
);

CREATE TABLE `towns` (
`town_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

ALTER TABLE `towns`
RENAME COLUMN `town_id` TO `id`;

ALTER TABLE `minions`
ADD COLUMN `town_id` INT AFTER `age`;

ALTER TABLE `minions`
ADD FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`);

INSERT INTO `towns`(`name`) VALUES ('Sofia');
INSERT INTO `towns`(`name`) VALUES ('Plovdiv');
INSERT INTO `towns`(`name`) VALUES ('Varna');

INSERT INTO `minions`(`name`, `age`, `town_id`) VALUES ('Kevin', '22', '1'), ('Bob', '15', '3'), ('Steward', NULL , '2');

TRUNCATE `minions`;

DROP TABLE `minions`, `towns`;

SELECT * FROM `minions`;

CREATE TABLE `people` (
`id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`picture` MEDIUMBLOB,
`height` DOUBLE(3,2),
`weight` DOUBLE(5,2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` LONGTEXT
);

INSERT INTO `people`(`name`, `gender`, `birthdate`) VALUES ('Pesho', 'm', CURDATE()), ('Misho', 'm', CURDATE()), ('Gosho', 'm', CURDATE()), ('Stefka', 'f', CURDATE()), ('Boby', 'f', CURDATE());

DROP TABLE users;

CREATE TABLE `users` (
`id` BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`username` VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(26) NOT NULL,
`profile_picture` MEDIUMBLOB,
`last_login_time` DATETIME,
`is_deleted` TINYINT DEFAULT FALSE
);

INSERT INTO `users`(`username`, `password`) VALUES ('Gosho', 'Peshoev'), ('Pesho', 'Peshoev'), ('Misho', 'Peshoev'), ('Stefka', 'Peshoev'), ('Boby', 'Peshoev');
    
ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY (`id`, `username`);

ALTER TABLE `users`
MODIFY COLUMN `last_login_time` DATETIME DEFAULT CURRENT_TIMESTAMP(),
MODIFY COLUMN `id` BIGINT AUTO_INCREMENT NOT NULL;


    
select count(id) from people;
SELECT *, IF(`is_deleted`, 'True', 'False')'Deleted' FROM `users`;

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY (`id`),
ADD CONSTRAINT unique_username
UNIQUE (`username`);


CREATE SCHEMA `movies`;
USE `movies`;

CREATE TABLE `directors` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `genres`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`genre_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`category_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);


CREATE TABLE `movies` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(50) NOT NULL,
`director_id` INT,
`copyright_year` YEAR,
`length` TIME,
`genre_id` INT,
`category_id` INT,
`rating` INT,
`notes` TEXT
);

INSERT INTO `directors`(`director_name`) VALUES ('Pesho'), ('Gosho'), ('Stefo'), ('Minka'), ('Donka');
INSERT INTO `genres`(`genre_name`) VALUES ('Pesho'), ('Gosho'), ('Stefo'), ('Minka'), ('Donka');
INSERT INTO `categories`(`category_name`) VALUES ('Pesho'), ('Gosho'), ('Stefo'), ('Minka'), ('Donka');
INSERT INTO `movies`(`title`) VALUES ('Pesho'), ('Gosho'), ('Stefo'), ('Minka'), ('Donka');

DROP SCHEMA `car_rental`;
CREATE SCHEMA `car_rental`;
USE `car_rental`;


CREATE TABLE `categories` (
id INT PRIMARY KEY AUTO_INCREMENT,
category VARCHAR(50) NOT NULL,
daily_rate DECIMAL,
weekly_rate DECIMAL,
monthly_rate DECIMAL,
weekend_rate DECIMAL
);

CREATE TABLE `cars` (
id INT PRIMARY KEY AUTO_INCREMENT,
plate_number VARCHAR(50) NOT NULL,
make VARCHAR(50),
model VARCHAR(50),
car_year YEAR,
category_id INT,
doors INT,
picture BLOB,
car_condition VARCHAR(50),
available TINYINT
);

CREATE TABLE `employees` (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
title VARCHAR(50),
notes TEXT
);

CREATE TABLE `customers` (
id INT PRIMARY KEY AUTO_INCREMENT,
driver_licence_number VARCHAR(50),
full_name VARCHAR(100) NOT NULL,
address VARCHAR(100),
city VARCHAR(50),
zip_code VARCHAR(50),
notes TEXT
);

CREATE TABLE `rental_orders` (
id INT PRIMARY KEY AUTO_INCREMENT,
employee_id INT,
customer_id INT NOT NULL,
car_id INT NOT NULL,
car_condition VARCHAR(50),
tank_level DOUBLE,
kilometrage_start INT,
kilometrage_end INT,
total_kilometrage INT,
start_date DATE,
end_date DATE,
total_days INT,
rate_applied DECIMAL,
tax_rate DECIMAL,
order_status VARCHAR(50),
notes TEXT
);

INSERT INTO `categories`(`category`)
VALUES 
('Test1'),
('Test2'),
('Test3');

INSERT INTO `cars`(`plate_number`)
VALUES 
('Test1'),
('Test2'),
('Test3');

INSERT INTO `employees`(`first_name`, `last_name`)
VALUES 
('Test1','Test1'),
('Test2','Test1'),
('Test3','Test1');

INSERT INTO `customers`(`full_name`)
VALUES 
('Test1'),
('Test2'),
('Test3');

INSERT INTO `rental_orders`(`customer_id`, `car_id`)
VALUES 
('1','4'),
('2','5'),
('3','6');


CREATE SCHEMA soft_uni;
USE soft_uni;

CREATE TABLE `towns` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `addresses` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`address_text` VARCHAR(50) NOT NULL,
`town_id` INT,
FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
);

CREATE TABLE `departments` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

DROP TABLE `employees`;

CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`middle_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL,
`job_title` VARCHAR(50) NOT NULL,
`department_id` INT,
`hire_date` DATE,
`salary` DECIMAL(10,2),
`address_id` INT,
FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`),
FOREIGN KEY (`address_id`) REFERENCES `addresses`(`id`)
);

INSERT INTO `towns`(`name`) VALUES ('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');
INSERT INTO `departments`(`name`) VALUES ('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');

INSERT INTO `employees`(`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`) 
	VALUES 
		('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', (SELECT `id` FROM `departments` WHERE `name` = 'Software Development'), '2013-02-01', 3500.00),
		('Petar', 'Petrov', 'Petrov', 'Senior Engineer', (SELECT `id` FROM `departments` WHERE `name` = 'Engineering'), '2004-03-02', 4000.00),
		('Maria', 'Petrova', 'Ivanova', 'Intern', (SELECT `id` FROM `departments` WHERE `name` = 'Quality Assurance'), '2016-08-28', 525.25),
		('Georgi', 'Terziev', 'Ivanov', 'CEO', (SELECT `id` FROM `departments` WHERE `name` = 'Sales'), '2007-12-09', 3000.00),
		('Peter', 'Pan', 'Pan', 'Intern', (SELECT `id` FROM `departments` WHERE `name` = 'Marketing'), '2016-08-028', 599.88);

SELECT `id` FROM `departments` WHERE `name` = 'Software Development';

SELECT * FROM `towns`;
SELECT * FROM `departments`;
SELECT * FROM `employees`;

SELECT * FROM `towns` ORDER BY `name`;
SELECT * FROM `departments` ORDER BY `name`;
SELECT * FROM `employees` ORDER BY `salary` DESC;

SELECT `name` FROM `towns` ORDER BY `name`;
SELECT `name` FROM `departments` ORDER BY `name`;
SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees` ORDER BY `salary` DESC;

UPDATE `employees` SET `salary` = `salary` * 1.1 WHERE id >= 1;
SELECT `salary` FROM `employees`;

TRUNCATE `occupancies`;