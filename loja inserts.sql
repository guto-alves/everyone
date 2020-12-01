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