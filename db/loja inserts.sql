USE MASTER
DROP DATABASE loja 

CREATE DATABASE loja
USE loja

-- INSERTS

INSERT INTO type VALUES
	('Top'),
	('Bottom'),
	('Underwear'),
	('Shoes'),
	('Accessory')

INSERT INTO brand VALUES
	('Nike'),
	('Oakley'),
	('FATEC'),
	('Roupa & Cia')	

INSERT INTO size VALUES 
	('XS'),
	('S'),
	('M'),
	('L'),
	('XL')

INSERT INTO color VALUES 
	('Black'),
	('White'),
	('Red'),
	('Blue'),
	('Yellow')

INSERT INTO product_color VALUES
	(120,'Black'),
	(122,'Blue'),
	(124,'Yellow'),
	(130,'Red'),
	(131,'Blue'),
	(128,'Blue'),
	(129,'Red')

INSERT INTO product_size VALUES
	(120,'XS'),
	(122,'S'),
	(124,'S'),
	(130,'L'),
	(131,'M'),
	(128,'XL'),
	(129,'M')

INSERT INTO CUSTOMER VALUES
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
;    
SELECT * FROM CUSTOMER;

INSERT INTO CARD VALUES
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

INSERT INTO product VALUES
	('T-Shirt', 'Cotton T-Shirt', 59.99, 1, 1, 100),
    ('T-Shirt', 'Cotton T-Shirt', 99.99, 1, 1, 100),
    ('T-Shirt', 'Cotton T-Shirt', 120.99, 1, 2, 40),
    ('Jeans', 'Jeans Pants', 149.99, 2, 2, 70),
	('Jeans', 'Jeans Pants', 139.99, 2, 4, 70),
    ('Jeans', 'Jeans Pants', 199.99, 2, 1, 100),
    ('Shorts', 'Basic Cotton Shorts', 60.80, 2, 1, 50),
    ('Shorts', 'Jeans Shorts', 99.80, 2, 4, 60),
    ('TankTop', 'Basic Cotton tanktop', 120.00, 1, 4, 100),
    ('Belt', 'Basic Belt', 100.00, 5, 4, 40),
	('Belt', 'Leather Belt', 149.00, 5, 4, 60),
    ('Watch', 'Leather Watch', 220.00, 5, 4, 20),
	('Watch', 'Digital Watch', 250.00, 5, 4, 30),
    ('Sneakers', 'Running Sneakers', 299.99, 4, 1, 100),
    ('Sneakers', 'Casual Sneakers', 249.99, 4, 2, 90),
    ('Shoes', 'Formal Shoes', 309.99, 4, 2, 100)