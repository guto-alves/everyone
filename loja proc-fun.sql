USE MASTER
DROP DATABASE loja 

CREATE DATABASE loja
USE loja

-- PROCEDURES

DROP PROCEDURE populate_product
CREATE PROCEDURE populate_product (@qt INT)
AS
BEGIN
	DECLARE @name VARCHAR(20),
			@price DECIMAL(9,2),
			@type INT,
			@brand INT,
			@stock INT,
			@counter INT

	SET @counter = 0

	WHILE @counter < @qt
	BEGIN
		SET @name = '' 

		DECLARE @name_counter INT
		SET		@name_counter = 5
		WHILE @name_counter > 0
		BEGIN
			SET @name = @name + CHAR((SELECT FLOOR(RAND()*(200-0)+0)))
			SET @name_counter = @name_counter - 1
		END
		
		SET @price = (SELECT FLOOR(RAND()*(122-65)+65))

		DECLARE @type_min INT, @type_max INT
		SET @type_min = (SELECT MIN(id) FROM type)
		SET @type_max = (SELECT MAX(id) FROM type) + 1
		SET @type = (SELECT FLOOR(RAND()*(@type_max-@type_min)+@type_min))

		DECLARE @brand_min INT, @brand_max INT
		SET @brand_min = (SELECT MIN(id) FROM brand)
		SET @brand_max = (SELECT MAX(id) FROM brand) + 1
		SET @brand = (SELECT FLOOR(RAND()*(@brand_max-@brand_min)+@brand_min))

		SET @stock = (SELECT FLOOR(RAND()*(999-50)+50))

		INSERT INTO product VALUES (@name, NULL, @price, @type, @brand, @stock)

		SET @counter = @counter +1
	END
END
GO

 
-- FUNCTIONS

DROP FUNCTION f_search_text
CREATE FUNCTION f_search_text (@term VARCHAR(100))
RETURNS @by_name TABLE(
id	INT
)
AS
BEGIN
	SET @term = '%' + @term + '%'
	INSERT INTO @by_name
		SELECT 
		DISTINCT p.id
		FROM	product p, brand b, product_color pc
		WHERE	p.name LIKE @term OR
				p.description LIKE @term OR
				b.name LIKE @term OR
				pc.color LIKE @term OR
				p.brand = b.id AND
				pc.product = p.id
	RETURN
END
GO

DROP FUNCTION f_search_price
CREATE FUNCTION f_search_price (@min DECIMAL(9,2), @max DECIMAL(9,2))
RETURNS @by_price TABLE(
id	INT
)
AS
BEGIN
	INSERT INTO @by_price 
		SELECT	id 
		FROM	product
		WHERE	price >= @min AND
				price <= @max
	RETURN
END
GO

DROP FUNCTION f_search_color 
CREATE FUNCTION f_search_color (@term VARCHAR(100))
RETURNS @by_color TABLE(
id INT
)
BEGIN
	SET @term = '%' + @term + '%'
	INSERT INTO @by_color 
		SELECT	p.id 
		FROM	product p, product_color pc
		WHERE	pc.color LIKE @term AND
				pc.product = p.id
		RETURN
END
GO

DROP FUNCTION f_search_size 
CREATE FUNCTION f_search_size (@term CHAR(2))
RETURNS @by_size TABLE(
id INT
)
BEGIN
	INSERT INTO @by_size 
		SELECT	p.id 
		FROM	product p, product_size ps
		WHERE	ps.size = @term AND
				ps.product = p.id
	RETURN
END
GO

DROP FUNCTION f_search_brand
CREATE FUNCTION f_search_brand (@id INT)
RETURNS @by_brand TABLE(
id INT
)
BEGIN
	INSERT INTO @by_brand 
		SELECT	p.id 
		FROM	product p, brand b
		WHERE	b.id LIKE @id AND
				p.brand = b.id
	RETURN
END
GO

DROP FUNCTION f_search_type
CREATE FUNCTION f_search_type (@id INT)
RETURNS @by_type TABLE(
id INT
)
BEGIN
	INSERT INTO @by_type 
		SELECT	id
		FROM	product
		WHERE	product.type = @id
	RETURN
END
GO

DROP FUNCTION f_existing_user
CREATE FUNCTION f_existing_user (@email VARCHAR(20))
RETURNS BIT 
AS 
BEGIN
	DECLARE @existing BIT
	SET @existing = 0
	IF @email IN (SELECT email FROM customer)
	BEGIN
		SET @existing = 1
	END
	RETURN @existing
END
GO

DROP FUNCTION f_validate_login 
CREATE FUNCTION f_validate_login (@email VARCHAR(50), @password VARCHAR(20))
RETURNS BIT 
AS
BEGIN
	DECLARE @valid BIT
	SET @valid = (SELECT * FROM dbo.f_existing_user(@email))
	IF @valid > 0
	BEGIN
		DECLARE @expected VARCHAR(20)
		SET @expected = (SELECT password FROM customer WHERE email = @email) 
		IF @password <> @expected 
		BEGIN
			SET @valid = 0
		END
	END
	RETURN @valid
END
GO


-- TESTES

EXEC populate_product 10
SELECT * FROM product

SELECT * FROM dbo.f_search_text('Blue')
SELECT * FROM product

SELECT * FROM dbo.f_search_price(60.55, 90.78)
SELECT * FROM product

SELECT * FROM dbo.f_search_color('Blue')

SELECT * FROM dbo.f_search_size('XL')

SELECT * FROM dbo.f_search_brand(11)

SELECT * FROM dbo.f_search_type(5)


