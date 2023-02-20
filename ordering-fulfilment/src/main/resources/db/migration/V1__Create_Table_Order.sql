CREATE TABLE IF NOT EXISTS order_processed(
    id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    customer_name VARCHAR(150) NOT NULL,
    customer_email VARCHAR(150),
    customer_document VARCHAR(80),
    installation_address VARCHAR(255) NOT NULL,
    installation_date DATE NOT NULL,
    start_time TIME,
    end_time TIME,
    products VARCHAR(500) NOT NULL,
    CONSTRAINT pk_id_order PRIMARY KEY (id)
);