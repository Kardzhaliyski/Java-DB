-- p1 -----------------------------------------------------------------
SELECT 
    e.`employee_id`,
    e.`job_title`,
    e.`address_id`,
    a.`address_text`
FROM
    `employees` AS e
        JOIN
    `addresses` AS a USING (`address_id`)
ORDER BY e.`address_id`
LIMIT 5;

-- p2 -------------------------------------------------------------------
SELECT 
    e.`first_name`, e.`last_name`, t.`name`, a.`address_text`
FROM
    `employees` AS e
        JOIN
    `addresses` AS a USING (`address_id`)
        JOIN
    `towns` AS t USING (`town_id`)
ORDER BY e.`first_name` , e.`last_name`
LIMIT 5;

-- p3 --------------------------------------------------------------------
SELECT 
    e.`employee_id`, e.`first_name`, e.`last_name`, d.`name`
FROM
    `employees` AS e
        JOIN
    `departments` AS d USING (`department_id`)
WHERE
    d.`name` = 'Sales'
ORDER BY `employee_id` DESC;

-- p4 -------------------------------------------------------------------
SELECT 
    e.`employee_id`, e.`first_name`, e.`salary`, d.`name`
FROM
    `employees` AS e
        JOIN
    `departments` AS d USING (`department_id`)
WHERE
    `salary` > 15000
ORDER BY d.`department_id` DESC
LIMIT 5;

-- p5 -------------------------------------------------------------------
SELECT 
    e.`employee_id`, e.`first_name`
FROM
    `employees` AS e
        LEFT JOIN
    `employees_projects` USING (`employee_id`)
        LEFT JOIN
    `projects` USING (`project_id`)
WHERE
    `project_id` IS NULL
ORDER BY `employee_id` DESC
LIMIT 3;

-- p6 -------------------------------------------------------------------
SELECT 
    e.`first_name`,
    e.`last_name`,
    e.`hire_date`,
    d.`name` AS `dept_name`
FROM
    `employees` AS e
        JOIN
    `departments` AS d USING (`department_id`)
WHERE
    d.`name` IN ('Sales' , 'Finance')
        AND e.hire_date > '1999-01-01'
ORDER BY e.`hire_date`;

-- p7 -------------------------------------------------------------------
SELECT 
    e.`employee_id`, e.`first_name`, p.`name` AS `project_name`
FROM
    `employees` AS e
        JOIN
    `employees_projects` AS ep ON e.`employee_id` = ep.`employee_id`
        JOIN
    `projects` AS p ON p.`project_id` = ep.`project_id`
WHERE
    p.`end_date` IS NULL
        AND DATE(p.`start_date`) > DATE('2002-08-13')
ORDER BY e.`first_name` , p.`name`
LIMIT 5;

-- p8 -------------------------------------------------------------------
SELECT 
    e.`employee_id`,
    e.`first_name`,
    IF(YEAR(p.`start_date`) >= 2005,
        '',
        p.`name`) AS `project_name`
FROM
    `employees` AS e
        JOIN
    `employees_projects` AS ep USING (`employee_id`)
        JOIN
    `projects` AS p USING (`project_id`)
WHERE
    e.`employee_id` = 24
ORDER BY `project_name`;

-- p9 -------------------------------------------------------------------

SELECT 
    e.`employee_id`,
    e.`first_name`,
    m.`employee_id` AS `manager_id`,
    m.`first_name` AS `manager_name`
FROM
    `employees` e
        JOIN
    `employees` m ON e.`manager_id` = m.`employee_id`
WHERE
    e.`manager_id` IN (3 , 7)
ORDER BY e.`first_name`;

-- p10 -----------------------------------------------------------------

SELECT 
    e.`employee_id`,
    CONCAT(e.`first_name`, ' ', e.`last_name`) AS `employee_name`,
    CONCAT(m.`first_name`, ' ', m.`last_name`) AS `manager_name`,
    d.`name` AS `department_name`
FROM
    `employees` AS e
        JOIN
    `employees` AS m ON e.`manager_id` = m.`employee_id`
        JOIN
    `departments` AS d ON e.`department_id` = d.`department_id`
WHERE
    e.`manager_id` IS NOT NULL
ORDER BY e.`employee_id`
LIMIT 5;

-- p11 ---------------------------------------------------------------

SELECT 
    AVG(`salary`) AS `min_average_salary`
FROM
    `employees`
GROUP BY `department_id`
ORDER BY `min_average_salary`
LIMIT 1;

-- -------------------------------------------------------------------
USE geography;
-- p12 ---------------------------------------------------------------

SELECT 
    c.`country_code`,
    m.`mountain_range`,
    p.`peak_name`,
    p.`elevation`
FROM
    `countries` AS c
        JOIN
    `mountains_countries` AS mc ON c.`country_code` = mc.`country_code`
        JOIN
    `mountains` AS m ON mc.`mountain_id` = m.`id`
        JOIN
    `peaks` AS p ON m.`id` = p.`mountain_id`
WHERE
    c.`country_code` = 'BG'
        AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;

-- p13 ---------------------------------------------------------------

SELECT 
    c.`country_code`, COUNT(*) AS `mountain_range`
FROM
    `countries` AS c
        JOIN
    `mountains_countries` AS mc ON c.`country_code` = mc.`country_code`
WHERE
    c.`country_name` IN ('Bulgaria' , 'Russia', 'United States')
GROUP BY c.`country_code`
ORDER BY `mountain_range` DESC;

-- p14 --------------------------------------------------------------

SELECT 
    c.`country_name`, r.`river_name`
FROM
    `countries` AS c
        LEFT JOIN
    `countries_rivers` AS cr ON c.`country_code` = cr.`country_code`
        LEFT JOIN
    `rivers` AS r ON cr.`river_id` = r.`id`
        LEFT JOIN
    `continents` AS co ON c.`continent_code` = co.`continent_code`
WHERE
    co.`continent_name` = 'Africa'
ORDER BY c.`country_name`
LIMIT 5;

-- p15 -------------------------------------------------------------

SELECT 
    `continent_code`,
    `currency_code`,
    COUNT(`country_code`) AS `currency_usage`
FROM
    `countries` AS c
GROUP BY `continent_code` , `currency_code`
HAVING `currency_usage` > 1
    AND `currency_usage` >= (SELECT 
        COUNT(`country_code`) AS `count`
    FROM
        `countries` AS bc
    WHERE
        bc.`continent_code` = c.`continent_code`
    GROUP BY `continent_code` , `currency_code`
    ORDER BY `count` DESC
    LIMIT 1)
ORDER BY `continent_code` , `currency_code`;


-- p16 -----------------------------------------------------------

SELECT 
    COUNT(`country_code`) AS `country_count`
FROM
    `countries`
        LEFT JOIN
    `mountains_countries` AS mc USING (`country_code`)
WHERE
    mc.`mountain_id` IS NULL;

-- p17 ----------------------------------------------------------

SELECT 
    c.`country_name`,
    MAX(p.elevation) AS `highest_peak_elevation`,
    MAX(r.`length`) AS `longest_river_length`
FROM
    `countries` AS c
        JOIN
    `mountains_countries` AS mc USING (`country_code`)
        LEFT JOIN
    `mountains` AS m ON mc.`mountain_id` = m.`id`
        LEFT JOIN
    `peaks` AS p ON m.`id` = p.`mountain_id`
        LEFT JOIN
    `countries_rivers` AS cr USING (`country_code`)
        LEFT JOIN
    `rivers` AS r ON cr.`river_id` = r.`id`
GROUP BY c.`country_name`
ORDER BY `highest_peak_elevation` DESC , `longest_river_length` DESC , c.`country_name`
LIMIT 5;





