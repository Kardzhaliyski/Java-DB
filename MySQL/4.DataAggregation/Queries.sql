SELECT 
    `department_id`, COUNT(*) AS `Number of employees`
FROM
    `employees`
GROUP BY `department_id`
ORDER BY `department_id` , `Number of employees`;

SELECT 
    `department_id`, ROUND(AVG(`salary`), 2)
FROM
    `employees`
GROUP BY `department_id`
ORDER BY `department_id`;

SELECT 
	`department_id`, round(min(`salary`), 2) AS `Min Salary`
FROM
	`employees`
GROUP BY `department_id`
HAVING `Min Salary` > 800; 

SELECT 
    COUNT(*)
FROM
    products
WHERE
    `category_id` = 2 AND `price` > 8;

SELECT 
    `category_id`,
    ROUND(AVG(`price`), 2) AS `Average Price`,
    MIN(`price`) AS `Cheapest Product`,
    MAX(`price`) AS `Most Expensive Product`
FROM
    `products`
GROUP BY `category_id`;