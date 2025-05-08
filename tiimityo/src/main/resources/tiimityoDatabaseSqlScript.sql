DROP TABLE IF EXISTS Manufacturer
DROP TABLE IF EXISTS ProductType
DROP TABLE IF EXISTS Product
DROP TABLE IF EXISTS AppUser

CREATE TABLE Manufacturer (
manuId CHAR(10) PRIMARY KEY,
manufacturer_name VARCHAR(100) NOT NULL
);

INSERT INTO Manufacturer (manuId, manufacturer_name)
VALUES  (1, "Rukka"),
        (2, "Pomppa"),
        (3, "Feel Active");

CREATE TABLE ProductType (
productType_id CHAR(10) PRIMARY KEY,
productType VARCHAR(100) NOT NULL
);

INSERT INTO ProductType (productType_id, productType)
VALUES  (1, "Vaate"),
        (2, "Ruoka"),
        (3, "Lelu");

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

INSERT INTO Product (id, title, price, color, size, quantity, manuId, productType_id)
VALUES  (1, "Talvitakki", 53.90, "Violetti", "M", 11, 1, 1),
        (2, "Sadetakki", 44.90, "Keltainen", "L", 3, 2, 1),
        (3, "Neule", 21.99, "Vihreä", "S", 7, 3, 1);

CREATE TABLE AppUser (
user_id CHAR(10) PRIMARY KEY,
username VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role VARCHAR(50) NOT NULL
);

INSERT INTO AppUser (user_id, username, password, role)
VALUES  (1, "admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");