CREATE TABLE IF NOT EXISTS package_product(
    id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    id_product INT(11) UNSIGNED NOT NULL,
    name VARCHAR(150) NOT NULL,
    description VARCHAR(150) NOT NULL,
    CONSTRAINT pk_id_package_product PRIMARY KEY (id),
    CONSTRAINT fk_package_id_product FOREIGN KEY (id_product) REFERENCES product(id)
);