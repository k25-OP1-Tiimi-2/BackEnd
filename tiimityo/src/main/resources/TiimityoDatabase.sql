-- CREATE TABLE manufacturer (
-- id BIGSERIAL PRIMARY KEY, 
-- name VARCHAR(50) NOT NULL
-- );

-- INSERT INTO manufacturer (name)
-- VALUES  ('Rukka'),
--         ('Pomppa'),
--         ('Feel Active');

-- CREATE TABLE product_type (
-- id BIGSERIAL PRIMARY KEY,
-- name VARCHAR(255)
-- );

-- INSERT INTO product_type (name)
-- VALUES  ('Vaate'),
--         ('Ruoka'),
--         ('Lelu');

-- CREATE TABLE product (
-- id BIGSERIAL PRIMARY KEY,
-- price FLOAT(53) NOT NULL,
-- quantity INTEGER NOT NULL, 
-- manuid BIGINT NOT NULL, 
-- product_typeid BIGINT NOT NULL, 
-- color VARCHAR(255), 
-- size VARCHAR(255), 
-- title VARCHAR(255) NOT NULL,
-- FOREIGN KEY (manuid) REFERENCES manufacturer(id),
-- FOREIGN KEY (product_typeid) REFERENCES product_type(id)
-- );

-- INSERT INTO product (title, price, color, size, quantity, manuid, product_typeid)
-- VALUES  ('Talvitakki', 53.90, 'Violetti', 'M', 11, 1, 1),
--         ('Sadetakki', 44.90, 'Keltainen', 'L', 3, 2, 1),
--         ('Neule', 21.99, 'Vihreä', 'S', 7, 3, 1);


-- CREATE TABLE users (
-- id BIGSERIAL PRIMARY KEY, 
-- password VARCHAR(255) NOT NULL, 
-- role VARCHAR(255) NOT NULL, 
-- username VARCHAR(255) NOT NULL UNIQUE
-- );

-- INSERT INTO users (username, password, role) 
-- VALUES ('admin', '$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C', 'ADMIN');