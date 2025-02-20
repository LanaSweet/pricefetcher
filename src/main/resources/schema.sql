CREATE TABLE BRANDS (
    brand_id INT PRIMARY KEY,
    brand_name VARCHAR(100) NOT NULL
);

CREATE TABLE PRICES (
    price_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id INT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    price_list INT,
    product_id INT,
    priority INT,
    price DECIMAL(10, 2),
    curr VARCHAR(3),
    FOREIGN KEY (brand_id) REFERENCES BRANDS(brand_id)
);
