use project;

CREATE TABLE product (
    asin VARCHAR(20) NOT NULL,
    title VARCHAR(1000), 
    brand VARCHAR(1000), 
    p_rank VARCHAR(3000),
    rate INT(2),
    price VARCHAR(20),
    description TEXT, 
    PRIMARY KEY (asin));
        
LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/product.csv'
INTO TABLE product
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, asin, title, brand,p_rank,rate,price, description);

CREATE TABLE AlsoBuy (
		asin VARCHAR(20) NOT NULL, 
		asinB VARCHAR(20) NOT NULL, 
		PRIMARY KEY (asin, asinB),
		FOREIGN KEY (asin) REFERENCES product (asin), 
		FOREIGN KEY (asinB) REFERENCES product (asin));
        
LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/test.csv'
INTO TABLE AlsoBuy
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, asin, asinB);

select * from review;

		
CREATE TABLE AlsoView (
		asin VARCHAR(20) NOT NULL, 
		asinV VARCHAR(20) NOT NULL, 
		PRIMARY KEY (asin, asinV),
		FOREIGN KEY (asin) REFERENCES product (asin), 
		FOREIGN KEY (asinV) REFERENCES product (asin));

LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/AlsoViewTab.csv'
INTO TABLE AlsoView
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, asin, asinV);		
	
CREATE TABLE category (
		cID VARCHAR(20) NOT NULL, 
		asin VARCHAR(20) NOT NULL, 
		cname VARCHAR(500) NOT NULL, 
		PRIMARY KEY (cID, asin), 
		FOREIGN KEY (asin) REFERENCES product (asin));
        
LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/cat.csv'
INTO TABLE category
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, @dummy, cID, asin, cname);

        
select * from category;        

CREATE TABLE customer (
    customerID VARCHAR(20) PRIMARY KEY,
    customerName VARCHAR(255)
);

LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/new_customer.csv'
INTO TABLE customer
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, customerID, customerName);

select * from customer where customerID like '0000%';

CREATE TABLE review (
    reviewID VARCHAR(15) NOT NULL,
    asin VARCHAR(20),
    customerID VARCHAR(20),
    overall INT,
    vote FLOAT,
    reviewTime DATE,
    style VARCHAR(1000),
    summary TEXT,
    reviewText TEXT,
    PRIMARY KEY (customerID, asin, reviewTime),
    FOREIGN KEY (customerID) REFERENCES customer (customerID),
    FOREIGN KEY (asin) REFERENCES product (asin)
);

LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/Review_3300w.csv'
INTO TABLE review
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, asin, customerID, @dummy, overall, vote, reviewTime, style, summary, reviewText);

CREATE TABLE transaction (
    transID VARCHAR(20) NOT NULL,
    customerID VARCHAR(20) NOT NULL,
    transTime DATE,
    PRIMARY KEY (transID),
    FOREIGN KEY (customerID) REFERENCES customer(customerID)
);

LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/transaction.csv'
INTO TABLE transaction
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, transID, customerID, transTime);

select * from transaction;

CREATE TABLE TransAsin (
    transID VARCHAR(20) NOT NULL,
    asin VARCHAR(20),
    quantity INT,
    PRIMARY KEY (transID, asin),
    FOREIGN KEY (asin) REFERENCES product(asin)
);

LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/trans_asin.csv'
INTO TABLE TransAsin
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, transID, asin, quantity);

select * from TransAsin;

CREATE TABLE product_image (
    asin VARCHAR(20) NOT NULL,
    each_image VARCHAR(255) NOT NULL,
    PRIMARY KEY (asin, each_image),
    FOREIGN KEY (asin) REFERENCES product(asin)
);

LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/product_image.csv'
INTO TABLE product_image
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, asin, each_image);

CREATE TABLE review_image (
    reviewID VARCHAR(15) NOT NULL,
    each_image VARCHAR(255) NOT NULL,
    PRIMARY KEY (reviewID, each_image),
    FOREIGN KEY (reviewID) REFERENCES customer(customerID)
);
LOAD DATA LOCAL INFILE '/Users/shenhaiyalxt163.com/Downloads/review_image.csv'
INTO TABLE review_image
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, reviewID, each_image);
	
