CREATE TABLE `passports` (
    `passport_id` INT NOT NULL PRIMARY KEY UNIQUE,
    `passport_number` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE `people` (
    `person_id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(30) NOT NULL,
    `salary` DECIMAL(19 , 2 ),
    `passport_id` INT UNIQUE,
    CONSTRAINT `fk_passport` FOREIGN KEY (`passport_id`)
        REFERENCES `passports` (`passport_id`)
);

INSERT INTO 
	`passports`(`passport_id`, `passport_number`) 
VALUES 
	(101, 'N34FG21B'),
    (102, 'K65LO4R7'),
    (103, 'ZE657QP2');

INSERT INTO
	`people`(`first_name`, `salary`, `passport_id`)
VALUES
	('Roberto', 43300.00, 102),
    ('Tom', 56100.00, 103),
    ('Yana', 60200.00, 101);
    
    -- ---------------------------------------------------

CREATE TABLE `manufacturers` (
    `manufacturer_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) UNIQUE NOT NULL,
    `established_on` DATE
);
	
CREATE TABLE `models` (
    `model_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL,
    `manufacturer_id` INT,
    CONSTRAINT `fk_manufacturer` FOREIGN KEY (`manufacturer_id`)
        REFERENCES `manufacturers` (`manufacturer_id`)
);

ALTER TABLE `models` AUTO_INCREMENT=101;

INSERT INTO
	`manufacturers`(`name`, `established_on`)
VALUES
	('BMW', '1916-03-01'),
    ('Tesla', '2003-01-01'),
    ('Lada', '1966-05-01');
    
INSERT INTO
	`models`(`name`, `manufacturer_id`)
VALUES
	('X1', 1),
    ('i6', 1),
    ('Model S', 2),
    ('Model X', 2),
    ('Model 3', 2),
    ('Nova', 3);
    
-- ------------------------------------------------------

CREATE TABLE `students` (
    `student_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `exams` (
    `exam_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

ALTER TABLE `exams` AUTO_INCREMENT = 101;

CREATE TABLE `students_exams` (
    `student_id` INT NOT NULL,
    `exam_id` INT NOT NULL,
    CONSTRAINT fk_students FOREIGN KEY (`student_id`)
        REFERENCES `students` (`student_id`),
    CONSTRAINT `fk_exams` FOREIGN KEY (`exam_id`)
        REFERENCES `exams` (`exam_id`),
    PRIMARY KEY (`student_id` , `exam_id`)
);

INSERT INTO
	`students`(`name`)
VALUES
	('Mila'),
    ('Toni'),
    ('Ron');

INSERT INTO
	`exams`(`name`)
VALUES
	('Spring MVC'),
    ('Neo4j'),
    ('Oracle 11g');
    
INSERT INTO 
	`students_exams`(`student_id`, `exam_id`)
VALUES
	(1,101),
	(1,102),
	(2,101),
	(3,103),
	(2,102),
	(2,103);

SELECT 
    s.name, e.name
FROM
    `students_exams` AS se
        JOIN
    `students` AS s ON s.student_id = se.student_id
        JOIN
    `exams` AS e ON e.exam_id = se.exam_id;

-- -----------------------------------------------------

CREATE TABLE `teachers` (
    `teacher_id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `manager_id` INT,
    CONSTRAINT fk_manager_id_teacher_id FOREIGN KEY (`manager_id`)
        REFERENCES `teachers` (teacher_id)
);

ALTER TABLE `teachers` AUTO_INCREMENT = 101;

INSERT INTO
	`teachers`(`name`)
VALUES
	('John'),
	('Maya'),
	('Silvia'),
	('Ted'),
	('Mark'),
    ('Greta');
    
UPDATE `teachers` 
SET 
    `manager_id` = 106
WHERE
    `teacher_id` = 102;
    
UPDATE `teachers` 
SET 
    `manager_id` = 106
WHERE
    `teacher_id` = 103;
    
UPDATE `teachers` 
SET 
    `manager_id` = 105
WHERE
    `teacher_id` = 104;
    
UPDATE `teachers` 
SET 
    `manager_id` = 101
WHERE
    `teacher_id` = 105;
    
UPDATE `teachers` 
SET 
    `manager_id` = 101
WHERE
    `teacher_id` = 106;

SELECT 
    t1.name AS teacher_name, t2.name AS manager_id
FROM
    teachers AS t1
        JOIN
    teachers AS t2 ON t1.manager_id = t2.teacher_id;

SELECT 
    *
FROM
    teachers
ORDER BY teacher_id;

-- ---------------------------------------------------------------
drop schema `online_store`;
CREATE SCHEMA `online_store`;
use `online_store`;

CREATE TABLE `cities` (
    `city_id` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50)
);

CREATE TABLE `customers` (
    `customer_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `birthday` DATE,
    `city_id` INT(11),
    CONSTRAINT fk_cities FOREIGN KEY (`city_id`)
        REFERENCES `cities` (`city_id`)
);

CREATE TABLE `orders` (
    `order_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `customer_id` INT(11),
    CONSTRAINT fk_customers FOREIGN KEY (`customer_id`)
        REFERENCES `customers` (`customer_id`)
);

CREATE TABLE `item_types` (
    `item_type_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

CREATE TABLE `items` (
    `item_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50),
    `item_type_id` INT(11),
    CONSTRAINT fk_item_types FOREIGN KEY (`item_type_id`)
        REFERENCES `item_types` (`item_type_id`)
);

CREATE TABLE `order_items` (
    `order_id` INT(11),
    `item_id` INT(11),
    CONSTRAINT fk_orders FOREIGN KEY (`order_id`)
        REFERENCES `orders` (`order_id`),
    CONSTRAINT fk_items FOREIGN KEY (`item_id`)
        REFERENCES `items` (`item_id`),
	PRIMARY KEY (`order_id`, `item_id`)
);

-- ------------------------------------------------------
drop schema uni;
CREATE SCHEMA uni;
use uni;
CREATE TABLE `subjects` (
	`subject_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `subject_name` VARCHAR(50)
);

CREATE TABLE `majors` (
	`major_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50)
);

CREATE TABLE `students` (
	`student_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `student_number` VARCHAR(12),
    `student_name` VARCHAR(50),
    `major_id` INT(11),
    CONSTRAINT fk_students_majors
    FOREIGN KEY (`major_id`)
    REFERENCES `majors`(`major_id`)
);

CREATE TABLE `agenda` (
	`student_id` INT(11),
    `subject_id` INT(11),
    PRIMARY KEY (`student_id`, `subject_id`),
    CONSTRAINT fk_agenda_subjects
    FOREIGN KEY (`subject_id`)
    REFERENCES `subjects`(`subject_id`),
    CONSTRAINT fk_agenda_students
    FOREIGN KEY (`student_id`)
    REFERENCES `students`(`student_id`)
);

CREATE TABLE `payments` (
	`payment_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `payment_date` DATE,
    `payment_amount` DECIMAL(8,2),
    `student_id` INT(11),
    CONSTRAINT fk_payments_students
    FOREIGN KEY (`student_id`)
    REFERENCES `students`(`student_id`)
);

--    -------------------------------------------------------

SELECT
	m.`mountain_range`, p.`peak_name`, p.`elevation` as `peak_elevation`
FROM
	`mountains` as m
JOIN
	`peaks` as p ON p.`mountain_id` = m.`id`
WHERE m.`mountain_range` = 'Rila'
ORDER BY `peak_elevation` DESC;











