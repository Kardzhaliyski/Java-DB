SELECT `id`, `first_name`, `last_name`, `job_title` FROM `employees` ORDER BY `id`;

SELECT `id`, concat_ws(' ', `first_name`, `last_name`) AS 'full_name', `job_title`, `salary` FROM `employees` WHERE `salary` > 1000 ORDER BY `id`;

UPDATE `employees` SET `salary` = (`salary` + 100) WHERE `job_title` = 'Manager' AND `id` > 0;
SELECT `salary` FROM `employees`;

SELECT * FROM `employees` ORDER BY `salary` DESC LIMIT 1;

SELECT * FROM `employees` WHERE `department_id` = 4 AND `salary` > 1000 ORDER BY `id`;

DELETE FROM `employees` WHERE `department_id` = 1 OR `department_id` = 2;
SELECT * FROM `employees` ORDER BY `id`;
