USE soft_uni;

SELECT `first_name`, `last_name` FROM `employees` WHERE `first_name` LIKE 'Sa%';
SELECT `first_name`, `last_name` FROM `employees` WHERE locate('sa', `first_name`) = 1;
SELECT `first_name`, `last_name` FROM `employees` WHERE substring(`first_name`, 1, 2) = 'sa';

SELECT `first_name`, `last_name` FROM `employees` WHERE locate('ei', `last_name`) != 0;

SELECT `first_name` FROM `employees` WHERE `department_id` IN (3 , 10) AND YEAR(`hire_date`) BETWEEN 1995 AND 2005 ORDER BY `employee_id`;

SELECT `first_name`, `last_name` FROM `employees` WHERE locate('engineer',`job_title`) = 0 ORDER BY `employee_id`;

SELECT `name` FROM `towns` WHERE char_length(`name`) BETWEEN 5 AND 6 ORDER BY `name`;

SELECT * FROM `towns` WHERE substring(`name`, 1,1) IN ('m', 'k', 'b', 'e') ORDER BY `name`;

SELECT * FROM `towns` WHERE substring(`name`, 1,1) NOT IN ('r', 'b', 'd') ORDER BY `name`;

CREATE VIEW v_employees_hired_after_2000 AS 
SELECT `first_name`, `last_name` FROM `employees` WHERE year(`hire_date`) > 2000;

SELECT * FROM v_employees_hired_after_2000;

SELECT `first_name`, `last_name` FROM `employees` WHERE char_length(`last_name`) = 5;

USE `geography`;

SELECT `country_name`, `iso_code` FROM `countries` WHERE (char_length(`country_name`) - 3) >= (char_length(replace(lower(`country_name`), 'a', ''))) ORDER BY `iso_code`;

SELECT `name`, date_format(`start`, '%Y-%m-%d') AS 'start' FROM games WHERE year(`start`) BETWEEN 2011 AND 2012 ORDER BY `start`, `name` LIMIT 50;

SELECT `user_name`, (substring(`email`, locate('@', `email`) + 1)) AS `email provider` FROM `users` ORDER BY `email provider`, `user_name`;

SELECT `user_name`, `ip_address` FROM `users` WHERE `ip_address` LIKE '___.1%.%.___' ORDER BY `user_name`;

SELECT * FROM `games`;

SELECT `name` AS 'game', hour(`start`) FROM `games`;

SELECT 
    `name` AS 'Game',
    (CASE
        WHEN HOUR(`start`) < 12 THEN 'Morning'
        WHEN HOUR(`start`) < 18 THEN 'Afternoon'
        ELSE 'Evening'
    END) AS 'Part of the Day',
    (CASE
		WHEN `duration` <= 3 THEN 'Extra Short'
		WHEN `duration` <= 6 THEN 'Short'
		WHEN `duration` <= 10 THEN 'Long'
		ELSE 'Extra Long'
    END) AS 'Duration'
FROM
    `games`;
    
SELECT `product_name`, `order_date` , adddate(`order_date`, INTERVAL 3 DAY) AS 'pay_due', adddate(`order_date`, INTERVAL 1 MONTH) AS 'deliver_due' FROM `orders`;

USE `geography`;

SELECT 
    p.peak_name,
    r.river_name,
    LOWER(CONCAT(p.peak_name, SUBSTRING(r.river_name, 2))) AS mix
FROM
    peaks AS p,
    rivers AS r
WHERE
    LOWER(RIGHT(p.peak_name, 1)) = LOWER(LEFT(r.river_name, 1))
ORDER BY mix;

