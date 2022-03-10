
-- p1 ---------------------------------------------------------------

DELIMITER ###
CREATE FUNCTION ufn_count_employees_by_town(`town_name` VARCHAR(50)) 
RETURNS INT
DETERMINISTIC
BEGIN

	RETURN (SELECT
	count(e.`employee_id`)
FROM
	`employees` as e
		JOIN
	`addresses` as a ON e.`address_id` = a.`address_id`
		JOIN
	`towns` as t ON t.`town_id` = a.`town_id`
WHERE t.`name` = `town_name`);

END
###
DELIMITER ;

-- p2 ---------------------------------------------------------------

DELIMITER ### 

CREATE PROCEDURE usp_raise_salaries(`department_name` VARCHAR(100))
BEGIN
	UPDATE `employees` AS e
        JOIN
    `departments` AS d ON e.`department_id` = d.`department_id`
SET e.`salary` = e.`salary` * 1.05
WHERE
    d.`name` = `department_name`;
END
###

DELIMITER ;

CALL usp_raise_salaries('Finance');

-- p3 -------------------------------------------------------------


DELIMITER ### 

CREATE PROCEDURE usp_raise_salary_by_id(`id` INT)
BEGIN

	IF((SELECT count(*) FROM `employees` WHERE `employee_id` = `id`) = 1) 
    THEN
		UPDATE `employees`  AS e
		SET e.`salary` = e.`salary` * 1.05
		WHERE
		e.`employee_id` = `id`;
	END IF;
END
###

DELIMITER ;
-- p4 -------------------------------------------------------------



CREATE TABLE `deleted_employees` (
    `employee_id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `middle_name` VARCHAR(50),
    `job_title` VARCHAR(50),
    `department_id` INT,
    `salary` DECIMAL(19 , 4 )
);
    
DELIMITER ###

CREATE TRIGGER del_employees BEFORE DELETE ON `employees`
FOR EACH ROW
BEGIN
	INSERT INTO 
		`deleted_employees`(`first_name`, `last_name`, `middle_name`,`job_title`,`department_id`, `salary`) 
	VALUES 
		(OLD.`first_name`, OLD.`last_name`, OLD.`middle_name`, OLD.`job_title`, OLD.`department_id`, OLD.`salary`);
END
###
    
DELIMITER ;

DELETE FROM employees 
WHERE employee_id = 5;

SET FOREIGN_KEY_CHECKS = 1;