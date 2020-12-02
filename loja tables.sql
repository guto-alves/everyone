USE MASTER
DROP DATABASE loja 

CREATE DATABASE loja
USE loja

--TABLES

--DROP TABLE type
CREATE TABLE type(
id		INT IDENTITY(1,1) PRIMARY KEY,
name	VARCHAR(20) NOT NULL,
)

--DROP TABLE brand
CREATE TABLE brand(
id		INT IDENTITY(1,1) PRIMARY KEY,
name	VARCHAR(20) NOT NULL,
)

--DROP TABLE size
CREATE TABLE size(
name VARCHAR(2) PRIMARY KEY
)

--DROP TABLE color 
CREATE TABLE color(
name VARCHAR(20)  PRIMARY KEY
)

--DROP TABLE product 
CREATE TABLE product(
id			INT IDENTITY(1,1) PRIMARY KEY,
name		VARCHAR(100) NOT NULL,
description	VARCHAR(200),
price		DECIMAL(9,2) NOT NULL,
type		INT NOT NULL,
brand		INT NOT NULL,
stock		INT NOT NULL,
FOREIGN KEY (type) REFERENCES type(id),
FOREIGN KEY (brand) REFERENCES brand(id)
)

--DROP TABLE product_size
CREATE TABLE product_size(
product INT,
size	VARCHAR(2),
PRIMARY KEY (product, size) 
)

--DROP TABLE product_color
CREATE TABLE product_color(
product INT,
color	VARCHAR(20),
PRIMARY KEY (product, color)
)

--DROP TABLE customer 
CREATE TABLE customer(
cpf			CHAR(11) PRIMARY KEY,
name		VARCHAR(100) NOT NULL,
uf			CHAR(2) NOT NULL,
cep			CHAR(8) NOT NULL,
email		VARCHAR(50) NOT NULL,
password	VARCHAR(20) NOT NULL
)

--DROP TABLE card
CREATE TABLE card(
number		CHAR(12) PRIMARY KEY,
validity	CHAR(4) NOT NULL,
customer	CHAR(11) NOT NULL,
FOREIGN KEY (customer) REFERENCES customer(cpf)
)

--DROP TABLE sale
CREATE TABLE sale(
id			INT IDENTITY(1,1) PRIMARY KEY,
customer	CHAR(11) NOT NULL,
date		TIMESTAMP NOT NULL,
status		BIT NOT NULL,
price		DECIMAL(9,2),
card		char(12),
FOREIGN KEY (customer) REFERENCES customer(cpf),
FOREIGN KEY (card) REFERENCES card(number)
)

--DROP TABLE sale_items
CREATE TABLE sale_items(
sale		INT NOT NULL,
product		INT NOT NULL,
quantity	INT NOT NULL,
total		DECIMAL(9,2) NOT NULL,
FOREIGN KEY (sale) REFERENCES sale(id),
FOREIGN KEY (product) REFERENCES product(id),
PRIMARY KEY (sale, product)
)


--------------

INSERT INTO type VALUES
	('Top'),
	('Bottom'),
	('Underwear'),
	('Shoes'),
	('Accessory')

SELECT * FROM type


INSERT INTO brand VALUES
	('Nike'),
	('Oakley'),
	('FATEC'),
	('Roupa & Cia')	

SELECT * FROM brand


INSERT INTO size VALUES 
	('XS'),
	('S'),
	('M'),
	('L'),
	('XL')

SELECT * FROM size


INSERT INTO color VALUES 
	('Black'),
	('White'),
	('Red'),
	('Blue'),
	('Yellow')

SELECT * FROM color





INSERT INTO customer VALUES
	('19905002824', 'Maria', 'SP', '04035040', 'Maria@gmail.com', '123ASD'),
    ('21804502812', 'Zé', 'MG', '21035040', 'Zé@gmail.com', '1234ASD'),
    ('18875694929', 'Luana', 'SP', '49875323', 'Luana@gmail.com', '4321ASD'),
    ('55045999804', 'Keisy', 'MG', '12369486', 'Keisy@gmail.com', '123ASDe'),
    ('98004865152', 'Alexandre', 'RJ', '08756209', 'Alex@gmail.com', '7895qewp'),
    ('21584978654', 'Wesley', 'RJ', '12390895', 'Wesley@gmail.com', '54qesdaA'),
    ('12345678987', 'Leonardo', 'BA', '08075360', 'Leo@hotmail.com', '456213dasWQ'),
    ('11025694632', 'Thiago', 'GO', '07463952', 'Thiago@hotmail.com', 'a32sdAC5'),
    ('45395005489', 'Caio', 'SP', '40892004', 'Caio@hotmail.com', 'SDd541we'),
    ('22104865871', 'Clarissa', 'SC', '06523682', 'Clarissa@hotmail.com', '3491SAD5'),
    ('49762513054', 'Noemi', 'PR', '13099542', 'Noemi@hotmail.com', '6178EQac1')
	
SELECT * FROM customer


INSERT INTO card VALUES
	('109876543210','0328','19905002824'),
	('123456789910','0125','21804502812'),
	('498736123549','0226','18875694929'),
	('554699316685','0425','55045999804'),
	('436221905362','0724','98004865152'),
	('100365600984','0322','21584978654'),
	('197364826459','0622','12345678987'),
	('400569753220','0525','11025694632'),
	('478760006645','0321','45395005489'),
	('227900672630','0923','22104865871'),
    ('655322600216','1124','49762513054')

SELECT * FROM card


INSERT INTO product VALUES
	('Crazy T-Shirt', 'A crazy cotton t-shirt', 59.99, 1, 1, 100),
    ('Wild T-Shirt', 'The best in the market, made of cotton', 99.99, 1, 1, 100),
    ('Simple T-Shirt', 'Just a cotton tee', 120.99, 1, 2, 40),
    ('Classic Jeans', 'Classic pair of jeans', 149.99, 2, 2, 70),
	('Premium Jeans', 'The best pants ever', 139.99, 2, 4, 70),
    ('Summer Jeans', 'New in the summer collection', 199.99, 2, 1, 100),
    ('Cotton Shorts', 'Basic cotton shorts', 60.80, 2, 1, 50),
    ('Denim Shorts', 'Durable and comfortable shorts', 99.80, 2, 4, 60),
    ('TankTop', 'Basic cotton tanktop', 120.00, 1, 4, 100),
    ('Vegan Belt', 'A basic synthetic belt', 100.00, 5, 4, 40),
	('Leather Belt', 'Made of true leather', 149.00, 5, 4, 60),
    ('LX Watch', 'Leather Watch', 220.00, 5, 4, 20),
	('DX Watch', 'Digital Watch', 250.00, 5, 4, 30),
    ('Runners', 'Running shoes made for perfect comfort', 299.99, 4, 1, 100),
    ('Sneakers', 'Casual sneakers for teens', 249.99, 4, 2, 90),
    ('Business Shoes', 'Formal shoes to be worn in serious occasions', 309.99, 4, 2, 100)

SELECT * FROM product


INSERT INTO product_color VALUES
	(1,'Black'),
    (1,'White'),
	(2,'Black'),
    (2,'White'),
    (3,'Red'),
    (3,'Blue'),
    (3,'Yellow'),
    (4,'Blue'),
    (5,'Blue'),
    (6,'Blue'),
	(4,'Black'),
    (5,'Black'),
    (6,'Black'),
    (7,'White'),
    (8,'Blue'),
    (9,'Red'),
    (10,'Black'),
    (11,'Black'),
    (12,'Black'),
    (13,'Yellow'),
    (14,'White'),
    (15,'Red'),
    (16,'Black'),
    (13,'White'),
    (14,'Blue'),
    (15,'Yellow'),
    (16,'Red')

SELECT * FROM product_color 

    
INSERT INTO product_size VALUES
	(1,'M'),
    (1,'L'),
    (2,'M'),
    (2,'L'),
    (2,'XL'),
    (3,'XL'),
    (3,'M'),
    (4,'XS'),
    (4,'M'),
    (4,'L'),
    (5,'M'),
    (5,'S'),
    (6,'XL'),
    (6,'L'),
    (7,'XS'),
	(7,'S'),
	(7,'M'),
    (8,'S'),
    (9,'M'),
    (10,'M'),
    (11,'L'),
    (12,'L'),
    (13,'XL'),
    (13,'L'),
    (13,'M'),
    (14,'M'),
    (15,'M'),
    (14,'L'),
    (15,'L'),
    (14,'XS'),
    (15,'S'),
    (16,'M'),
    (16,'XL'),
    (16,'XS')

SELECT * FROM product_size