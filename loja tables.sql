USE MASTER
DROP DATABASE loja 

CREATE DATABASE loja
USE loja

--TABLES

DROP TABLE type
CREATE TABLE type(
id		INT IDENTITY(1,1) PRIMARY KEY,
name	VARCHAR(20) NOT NULL,
)

DROP TABLE brand
CREATE TABLE brand(
id		INT IDENTITY(1,1) PRIMARY KEY,
name	VARCHAR(20) NOT NULL,
)

DROP TABLE size
CREATE TABLE size(
name VARCHAR(2) PRIMARY KEY
)

DROP TABLE color 
CREATE TABLE color(
name VARCHAR(20)  PRIMARY KEY
)

DROP TABLE product 
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

DROP TABLE product_size
CREATE TABLE product_size(
product INT,
size	VARCHAR(2),
PRIMARY KEY (product, size) 
)

DROP TABLE product_color
CREATE TABLE product_color(
product INT,
color	VARCHAR(20),
PRIMARY KEY (product, color)
)

DROP TABLE customer 
CREATE TABLE customer(
cpf			CHAR(11) PRIMARY KEY,
name		VARCHAR(100) NOT NULL,
uf			CHAR(2) NOT NULL,
cep			CHAR(8) NOT NULL,
email		VARCHAR(50) NOT NULL,
password	VARCHAR(20) NOT NULL
)

DROP TABLE card
CREATE TABLE card(
number		CHAR(12) PRIMARY KEY,
validity	CHAR(4) NOT NULL,
customer	CHAR(11),
FOREIGN KEY (customer) REFERENCES customer(cpf)
)

DROP TABLE sale
CREATE TABLE sale(
customer	char(11) NOT NULL,
date		TIMESTAMP NOT NULL,
status		BIT NOT NULL,
price		DECIMAL(9,2) NOT NULL,
card		char(12),
FOREIGN KEY (customer) REFERENCES customer(cpf),
FOREIGN KEY (card) REFERENCES card(number),
PRIMARY KEY (customer, date)
)

DROP TABLE sale_items
CREATE TABLE sale_items(
customer	INT NOT NULL,
date		TIMESTAMP NOT NULL,
product		INT NOT NULL,
quantity	INT NOT NULL,
total		DECIMAL(9,2),
FOREIGN KEY (customer) REFERENCES sale(customer),
FOREIGN KEY (date) REFERENCES sale(date),
FOREIGN KEY (product) REFERENCES product(id),
PRIMARY KEY (customer, date, product)
)
