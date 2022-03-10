CREATE TABLE `mountains` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `peaks` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `mountain_id` INT,
    CONSTRAINT fk_mountains_id
    FOREIGN KEY (`mountain_id`)
    REFERENCES `mountains`(`id`)
);

SELECT 
    c.id,
    v.vehicle_type,
    CONCAT_WS(' ', c.first_name, c.last_name) AS driver_name
FROM
    campers AS c
        JOIN
    vehicles AS v ON c.id = v.driver_id;
    
SELECT 
    r.starting_point AS route_starting_point,
    r.end_point AS route_ending_point,
    r.leader_id,
    CONCAT_WS(' ', c.first_name, c.last_name)
FROM
    routes AS r
        JOIN
    campers AS c ON r.leader_id = c.id;
    
CREATE TABLE `mountains` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `peaks` (
	`id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `mountain_id` INT,
    CONSTRAINT fk_mountain
    FOREIGN KEY (`mountain_id`)
    REFERENCES `mountains`(`id`)
    ON DELETE CASCADE
);
