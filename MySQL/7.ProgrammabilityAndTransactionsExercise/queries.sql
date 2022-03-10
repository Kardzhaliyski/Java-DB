-- p1 ------------------------------------------------------
DELIMITER ###

CREATE PROCEDURE usp_get_employees_salary_above_35000() 
BEGIN
SELECT
	`first_name`,
    `last_name`
FROM
	`employees`
WHERE
	`salary` > 35000
ORDER BY `first_name`, `last_name`, `employee_id`;
END

###

DELIMITER ;

CALL usp_get_employees_salary_above_35000;

-- p2 -----------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_get_employees_salary_above(given_salary DECIMAL(19,4)) 
BEGIN
SELECT
	`first_name`,
    `last_name`
FROM
	`employees`
WHERE
	`salary` >= given_salary
ORDER BY `first_name`, `last_name`, `employee_id`;
END

###

DELIMITER ;

CALL usp_get_employees_salary_above(48099);

-- p3 -----------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_get_towns_starting_with(substring VARCHAR(50))
BEGIN
SELECT 
	`name` AS `town_name`
FROM 
	`towns`
WHERE `name` LIKE concat(substring , '%')
ORDER BY `town_name`;
END

###

DELIMITER ;

CALL usp_get_towns_starting_with('b');

-- p4 ---------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_get_employees_from_town(`town_name` VARCHAR(50))
BEGIN
SELECT 
    e.`first_name`, e.`last_name`
FROM
    `employees` AS e
        JOIN
    `addresses` AS a ON e.`address_id` = a.`address_id`
        JOIN
    `towns` AS t ON a.`town_id` = t.`town_id`
WHERE
    t.`name` = `town_name`
ORDER BY e.`first_name` , e.`last_name`;
END

###

DELIMITER ;

CALL usp_get_employees_from_town('Sofia');

-- p5 ---------------------------------------------------

DELIMITER ###

CREATE FUNCTION ufn_get_salary_level (`salary` DOUBLE) 
RETURNS VARCHAR(50)
DETERMINISTIC
RETURN (CASE
			WHEN `salary` < 30000 THEN 'Low'
            WHEN `salary` <= 50000 THEN 'Average'
            ELSE 'High'
		END)
        
###

DELIMITER ;

SELECT ufn_get_salary_level(500000);

-- p6 --------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_get_employees_by_salary_level(`salary_level` VARCHAR(50))
BEGIN
	SELECT 
		e.`first_name`, e.`last_name`
	FROM
		`employees` AS e
	WHERE
		ufn_get_salary_level(e.`salary`) = `salary_level`
	ORDER BY e.`first_name` DESC, e.`last_name` DESC;
END

###

DELIMITER ;

CALL usp_get_employees_by_salary_level('High');

DELIMITER ###

CREATE PROCEDURE usp_get_employees_by_salary_level(`salary_level` VARCHAR(50))
BEGIN
SELECT 
		e.`first_name`, e.`last_name`
	FROM
		`employees` AS e
	WHERE
		(CASE
			WHEN `salary` < 30000 THEN 'Low'
            WHEN `salary` <= 50000 THEN 'Average'
            ELSE 'High'
		END) = 'High'
	ORDER BY e.`first_name` DESC, e.`last_name` DESC;
END

###

DELIMITER ;

-- p7 ------------------------------------------------------

DELIMITER ###

CREATE FUNCTION ufn_is_word_comprised(`set_of_letters` varchar(50), `word`varchar(50))
RETURNS BIT
DETERMINISTIC
BEGIN
RETURN `word` REGEXP concat('^[', `set_of_letters`, ']+$');
END

###

DELIMITER ;

-- p8 -----------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
SELECT 
		concat(h.`first_name`,' ', h.`last_name`) AS `full_name`
	FROM
		`account_holders` AS h
        ORDER BY `full_name`, h.`id`;
END

###

DELIMITER ;

-- p9 -----------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_get_holders_with_balance_higher_than(`balance` DECIMAL)
BEGIN
SELECT 
	h.`first_name`, h.`last_name`
FROM
	`account_holders` as h
		JOIN
	`accounts` as a ON h.`id` = a.`account_holder_id`
GROUP BY h.`id`
HAVING sum(a.balance) > balance
ORDER BY h.`id`;
END

###

DELIMITER ;

-- p10 -----------------------------------------------------

DELIMITER ###

CREATE FUNCTION ufn_calculate_future_value(sum DECIMAL(19,4), yearly_interest_rate DOUBLE, number_of_years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
RETURN (sum * (pow((1 + yearly_interest_rate), number_of_years)));
END

###

DELIMITER ;

SELECT ufn_calculate_future_value(1000, 0.5, 5);

-- p11 ---------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_calculate_future_value_for_account(id_of_account INT, interest_rate DECIMAL(19,4))
BEGIN
SELECT
	a.`id` as `account_id`,
    h.`first_name`,
    h.`last_name`,
    a.`balance` as `current_balance`,
    ufn_calculate_future_value(a.`balance`, interest_rate, 5)
FROM
	`account_holders` as h
		JOIN
	`accounts` as a ON h.`id` = a.`account_holder_id`
WHERE
	a.`id` = id_of_account;
END

###

DELIMITER ;

CALL usp_calculate_future_value_for_account(1, 0.1);

-- ---------------
DELIMITER ###

CREATE PROCEDURE usp_calculate_future_value_for_account(id_of_account INT, interest_rate DECIMAL(19,4))
BEGIN
DECLARE result DECIMAL (19,4);

SET result := (SELECT
    a.`balance` * (pow((1 + interest_rate), 5))
FROM
	`accounts` as a
WHERE
	a.`id` = id_of_account);

SELECT
	a.`id` as `account_id`,
    h.`first_name`,
    h.`last_name`,
    a.`balance` as `current_balance`,
    `result`
FROM
	`account_holders` as h
		JOIN
	`accounts` as a ON h.`id` = a.`account_holder_id`
WHERE
	a.`id` = id_of_account;
END

###

DELIMITER ;

CALL usp_calculate_future_value_for_account2(1, 0.1);

-- p12 ---------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
	IF(money_amount > 0) THEN
		UPDATE
			`accounts` AS acc
		SET
			acc.`balance` = acc.`balance` + money_amount
		WHERE
			`id` = account_id;
	END IF; 
END

###

DELIMITER ;


CALL usp_deposit_money(1, 50.3555);
SELECT * FROM accounts WHERE id = 1;

-- p13 ---------------------------------------------------

DELIMITER ###

CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN

	DECLARE current_balance DECIMAL(19,4);
    SET current_balance = (SELECT `balance` FROM `accounts` WHERE `id` = account_id);
    
	IF(money_amount > 0 AND current_balance >= money_amount) THEN
		UPDATE
			`accounts` AS acc
		SET
			acc.`balance` = acc.`balance` - money_amount
		WHERE
			`id` = account_id;
	END IF; 
END

###

DELIMITER ;

call usp_withdraw_money(1, 81.8480);
