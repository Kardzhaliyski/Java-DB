INSERT INTO reviews(content, picture_url, published_at,rating) 
SELECT substring(`description`, 1, 15), reverse(`name`), '2010-10-10', price / 8
FROM products
WHERE id >= 5;

UPDATE products SET quantity_in_stock = quantity_in_stock - 5 WHERE quantity_in_stock BETWEEN 60 AND 70 AND id > 0;

DELETE FROM customers WHERE id NOT IN (SELECT customer_id FROM orders) AND id > 0;

-- -----------------

SELECT id, `name`
FROM categories
ORDER BY `name` DESC;

SELECT id, brand_id, `name`, quantity_in_stock
FROM products
WHERE price > 1000 AND quantity_in_stock < 30
ORDER BY quantity_in_stock, id;

SELECT id, content, rating, picture_url, published_at
FROM reviews
WHERE content LIKE 'My%' AND char_length(content) > 61
ORDER BY rating DESC;

SELECT concat(c.first_name, ' ', c.last_name) as `full_name`, c.address, o.order_datetime as order_date
FROM orders as o
INNER JOIN customers as c
	ON o.customer_id = c.id
WHERE year(order_datetime) <= 2018
ORDER BY `full_name` DESC;

SELECT * FROM online_store.products;

SELECT 
    COUNT(*) AS item_count,
    (SELECT 
            `name`
        FROM
            categories
        WHERE
            id = p.category_id) AS `name`,
    SUM(quantity_in_stock) AS total_quantity
FROM
    products AS p
GROUP BY category_id
ORDER BY item_count DESC , total_quantity
LIMIT 5;


DELIMITER ###
CREATE FUNCTION udf_customer_products_count(`name` VARCHAR(30))
RETURNS INT
DETERMINISTIC

BEGIN
	RETURN (
    SELECT count(*)
FROM customers as c
JOIN orders as o ON o.customer_id = c.id
JOIN orders_products as op ON op.order_id = o.id
WHERE first_name = `name`);
END
###
DELIMITER ;

SELECT c.first_name,c.last_name, udf_customer_products_count('Shirley') as `total_products` FROM customers c
WHERE c.first_name = 'Shirley';

SELECT locate('@', 'pesho@soft@uni.bg');

SELECT udf_customer_products_count(first_name), first_name, last_name FROM customers ;

SELECT count(*)
FROM customers as c
JOIN orders as o ON o.customer_id = c.id
JOIN orders_products as op ON op.order_id = o.id
WHERE first_name = 'Shirley';

DELIMITER ###
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50))
BEGIN
	Update products as pr
	SET pr.price = pr.price * 0.7
	WHERE category_id = (SELECT id FROM categories as c WHERE c.name = `category_name`) 
		AND (SELECT r.rating FROM reviews as r WHERE r.id = pr.review_id) < 4;
END;
###
DELIMITER ;

SELECT * from products AS pr
	WHERE category_id = (SELECT id FROM categories as c WHERE c.name = 'Phones and tablets') 
		AND (SELECT r.rating FROM reviews as r WHERE r.id = pr.review_id) < 4;
    
SELECT * FROM products;

#(SELECT 
#FROM products AS p 
#JOIN categories AS c ON p.category_id = c.id
#JOIN reviews AS r ON p.review_id = r.id
#WHERE c.name = 'Phones and tablets' AND
#	r.rating < 4;

CALL udp_reduce_price('Phones and tablets');

SELECT * FROM products JOIN reviews;


