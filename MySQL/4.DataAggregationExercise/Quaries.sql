SELECT 
    COUNT(*) AS `count`
FROM
    `wizzard_deposits`;

SELECT 
    MAX(`magic_wand_size`) AS `longest_magic_wand`
FROM
    `wizzard_deposits`;
    
SELECT 
    `deposit_group`,
    MAX(`magic_wand_size`) AS `longest_magic_wand`
FROM
    `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `longest_magic_wand` , `deposit_group`;

SELECT 
    `deposit_group`
FROM
    `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY AVG(`magic_wand_size`)
LIMIT 1;

SELECT 
    `deposit_group`, SUM(`deposit_amount`) AS `total_sum`
FROM
    `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `total_sum`;
    
SELECT 
    `deposit_group`, SUM(`deposit_amount`) AS `total_sum`
FROM
    `wizzard_deposits`
WHERE
    `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
ORDER BY `deposit_group`;

SELECT 
    `deposit_group`, SUM(`deposit_amount`) AS `total_sum`
FROM
    `wizzard_deposits`
WHERE
    `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

SELECT 
    `deposit_group`,
    `magic_wand_creator`,
    MIN(`deposit_charge`) AS `min_deposit_charge`
FROM
    `wizzard_deposits`
GROUP BY `deposit_group` , `magic_wand_creator`
ORDER BY `magic_wand_creator` , `deposit_group`;

SELECT 
    CASE
        WHEN `age` BETWEEN 0 AND 10 THEN '[0-10]'
        WHEN `age` BETWEEN 11 AND 20 THEN '[11-20]'
        WHEN `age` BETWEEN 21 AND 30 THEN '[21-30]'
        WHEN `age` BETWEEN 31 AND 40 THEN '[31-40]'
        WHEN `age` BETWEEN 41 AND 50 THEN '[41-50]'
        WHEN `age` BETWEEN 51 AND 60 THEN '[51-60]'
        ELSE '[61+]'
    END AS `age_group`,
    COUNT(*) AS `wizard_count`
FROM
    `wizzard_deposits`
GROUP BY `age_group`
ORDER BY `wizard_count`;

SELECT 
    LEFT(`first_name`, 1) AS `first_letter`
FROM
    `wizzard_deposits`
WHERE
    `deposit_group` = 'Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;

SELECT 
    `deposit_group`,
    `is_deposit_expired`,
    AVG(`deposit_interest`) AS `average_interest`
FROM
    `wizzard_deposits`
WHERE
    YEAR(DATE_SUB(`deposit_start_date`,
            INTERVAL 1 DAY)) >= 1985
GROUP BY `deposit_group` , `is_deposit_expired`
ORDER BY `deposit_group` DESC , `is_deposit_expired`;

SELECT 
    `deposit_group`,
    `is_deposit_expired`,
    AVG(`deposit_interest`) AS `average_interest`
FROM
    `wizzard_deposits`
WHERE
    TIMESTAMPDIFF(DAY,
        '1985-01-01',
        `deposit_start_date`) > 0
GROUP BY `deposit_group` , `is_deposit_expired`
ORDER BY `deposit_group` DESC , `is_deposit_expired`;

use soft_uni;

SELECT 
    `department_id`, MIN(`salary`)
FROM
    `employees`
WHERE
    `department_id` IN (2 , 5, 7)
        AND TIMESTAMPDIFF(DAY,
        '2000-01-01',
        `hire_date`) > 0
GROUP BY `department_id`
ORDER BY `department_id`;

CREATE TABLE `high_paid_employees` AS SELECT * FROM
    `employees`
WHERE
    `salary` > 30000;
    

DELETE FROM `high_paid_employees` 
WHERE
    `manager_id` = 42;

UPDATE `high_paid_employees` 
SET 
    `salary` = `salary` + 5000
WHERE
    `department_id` = 1;

SELECT 
    `department_id`, AVG(`salary`) AS `avg_salary`
FROM
    `high_paid_employees`
GROUP BY `department_id`
ORDER BY `department_id`;

SELECT 
    *
FROM
    `high_paid_employees`;

SELECT 
    COUNT(*)
FROM
    `employees`
WHERE
    `manager_id` IS NULL;

SELECT 
    `department_id`, MAX(`salary`) AS `max_salary`
FROM
    `employees`
GROUP BY `department_id`
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY `department_id`;

SELECT `department_id`, `salary`
FROM
( SELECT `department_id`,`salary`,
	ROW_NUMBER() OVER(partition by `department_id` ORDER BY `salary` DESC) AS rn
    FROM (SELECT DISTINCT`department_id`, `salary` FROM `employees`) as dis
) AS `grp`
WHERE grp.rn = 3
ORDER BY `department_id`;

SELECT 
    `department_id`, `salary`
FROM
    employees
ORDER BY department_id , salary DESC;

SELECT DISTINCT
    `department_id`, `salary`
FROM
    `employees`;

SELECT 
    *
FROM
    `employees`;






SELECT 
    `first_name`, `last_name`, `department_id`
FROM
    `employees` AS e
WHERE
    `salary` > (SELECT 
            AVG(`salary`)
        FROM
            `employees` AS ie
        WHERE
            ie.department_id = e.department_id)
ORDER BY `department_id` , `employee_id`
LIMIT 10;




SELECT DISTINCT
    `department_id`, `salary`
FROM
    employees
WHERE
    `department_id` = 1
ORDER BY `salary` DESC
LIMIT 2 , 1;



SELECT 
    `department_id`, `salary`
FROM
    employees AS e
WHERE
    employee_id = (SELECT 
            ANY_VALUE(employee_id)
        FROM
            employees
        WHERE
            `department_id` = e.department_id
        GROUP BY `department_id` , `salary`
        ORDER BY `salary` DESC
        LIMIT 2 , 1)
ORDER BY `department_id`;
        
        
SELECT 
    ANY_VALUE(employee_id)
FROM
    employees
WHERE
    `department_id` = 2
GROUP BY `department_id` , `salary`
ORDER BY `salary` DESC
LIMIT 2 , 1;

use soft_uni;

SELECT `department_id`, sum(`salary`)
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;

































