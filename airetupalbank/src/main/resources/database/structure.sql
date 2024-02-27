CREATE TABLE users(id INT PRIMARY KEY, nombre VARCHAR(50), role VARCHAR(50), email VARCHAR(50),password VARCHAR(50) );
CREATE TABLE bills(reference VARCHAR(10) PRIMARY KEY, price INT, paid VARCHAR(6), id_cliente INT);
CREATE TABLE services(id VARCHAR(10) PRIMARY KEY, nombre VARCHAR(50), company VARCHAR(50), consume INT, ref_bill VARCHAR(10));


--Foraneas 
ALTER TABLE bills ADD CONSTRAINT FK_Bill_user
FOREIGN KEY (id_cliente) REFERENCES users(id);

ALTER TABLE services ADD CONSTRAINT FK_service_bills
FOREIGN KEY (ref_bill) REFERENCES bills(reference);