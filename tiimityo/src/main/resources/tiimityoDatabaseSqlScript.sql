CREATE TABLE Manufacturer (
manuId CHAR(10) PRIMARY KEY,
manufacturer_name VARCHAR(100) NOT NULL
);

CREATE TABLE ProductType (
productType_id CHAR(10) PRIMARY KEY,
productType VARCHAR(100) NOT NULL
);

CREATE TABLE Product (
id CHAR(10) PRIMARY KEY,
title VARCHAR(150) NOT NULL,
price DECIMAL(10, 2),
color VARCHAR(50),
size VARCHAR(50),
quantity INT,
manuId CHAR(10), productType_id CHAR(10),
FOREIGN KEY (manuId) REFERENCES Manufacturer(manuId),
FOREIGN KEY (productType_id) REFERENCES ProductType(productType_id)
);

CREATE TABLE AppUser (
user_id CHAR(10) PRIMARY KEY,
username VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role VARCHAR(50) NOT NULL
);
