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

DROP PROCEDURE existing_user
CREATE PROCEDURE existing_user (@email VARCHAR(20), @existing BIT OUTPUT)
AS 
BEGIN
	SET @existing = 0
	IF @email IN (SELECT email FROM customer)
	BEGIN
		SET @existing = 1
	END
	RETURN
END
GO

DROP PROCEDURE validate_login 
CREATE PROCEDURE validate_login (@email VARCHAR(50), @password VARCHAR(20), @valid BIT OUTPUT)
AS
BEGIN
	EXEC existing_user @email, @valid OUTPUT 
	IF @valid > 0
	BEGIN
		DECLARE @expected VARCHAR(20)
		SET @expected = (SELECT password FROM customer WHERE email = @email) 
		IF @password <> @expected 
		BEGIN
			SET @valid = 0
		END
	END
	RETURN
END
GO

DROP PROCEDURE new_sale
CREATE PROCEDURE new_sale (@customer CHAR(11), @date TIMESTAMP, @sale_id INT OUTPUT)
AS
BEGIN
	INSERT INTO sale(customer, date, status) VALUES (@customer, @date, 0)
	SET @sale_id = (SELECT MAX(id) FROM sale)
	RETURN @sale_id
END
GO

DROP PROCEDURE add_items 
CREATE PROCEDURE add_items (@sale_id INT, @product INT, @quantity INT)
AS
BEGIN
	DECLARE @total DECIMAL(9,2)
	SET @total = @quantity * (SELECT price FROM product WHERE id = @product)
	INSERT INTO sale_items (@sale_id, @product, @quantity, @total)
END
GO


DROP PROCEDURE close_sale 
CREATE PROCEDURE close_sale (@sale_id INT, @card CHAR(12), @success BIT OUTPUT)
AS
BEGIN
	SET @success = 0
	DECLARE @valid_card BIT
	EXEC validate_card  @card, @valid_card OUTPUT
	IF (@valid_card = 1)
	BEGIN
		UPDATE sale SET card = @card WHERE id = @sale_id
		UPDATE sale SET price = (SELECT SUM(total) FROM sale_items WHERE sale = @sale_id)
		SET @success = 1
	END
	RETURN
END
GO

DROP PROCEDURE validate_card 
CREATE PROCEDURE validate_card (@card CHAR(12), @valid BIT OUTPUT )
AS
BEGIN
	DECLARE	@month CHAR(2),
			@year CHAR(2)
	SET @month = (SELECT SUBSTRING(validity, 1, 2) FROM card WHERE number = @card)
	SET @month = (SELECT SUBSTRING(validity, 3, 2) FROM card WHERE number = @card)
	IF ( YEAR(GETDATE()) <= @year )
	BEGIN
		IF ( MONTH(GETDATE()) <= @month )
		BEGIN
			SET @valid = 1
		END
	END
	ELSE 
	BEGIN
			SET @valid = 0
	END
	RETURN
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



