CREATE TABLE IF NOT EXISTS users(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50) NOT NULL UNIQUE, role VARCHAR(50) NOT NULL, email VARCHAR(50) NOT NULL UNIQUE,password VARCHAR(50) NOT NULL UNIQUE);
CREATE TABLE IF NOT EXISTS bills(reference VARCHAR(10) PRIMARY KEY, price INT, paid VARCHAR(6), emisionDate Date, paidDate Date,id_cliente INT);
CREATE TABLE IF NOT EXISTS services(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), company VARCHAR(50), consume INT, ref_bill VARCHAR(10)); 
CREATE TABLE IF NOT EXISTS accounts(id VARCHAR(10) PRIMARY KEY, type-account VARCHAR(15), amount INT NOT NULL, bank VARCHAR(20) NOT NULL, user_id INT);
ALTER TABLE bills ADD CONSTRAINT FK_Bill_user FOREIGN KEY (id_cliente) REFERENCES users(id);
ALTER TABLE services ADD CONSTRAINT FK_service_bills FOREIGN KEY (ref_bill) REFERENCES bills(reference);