
-- Base de datos
CREATE DATABASE IF NOT EXISTS BDBank;

USE BDBank;

-- Tabla de Clientes
CREATE TABLE IF NOT EXISTS Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,    
    dni VARCHAR(20) NOT NULL UNIQUE, 
    name VARCHAR(100) NOT NULL,                      
    lastname VARCHAR(100) NOT NULL,                         
    email VARCHAR(100) NOT NULL                 
);

-- Tabla de Cuentas Bancarias
CREATE TABLE IF NOT EXISTS BankAccounts (
    bankAccount_id INT AUTO_INCREMENT PRIMARY KEY,  
    dni VARCHAR(20) NOT NULL,
    accountNumber VARCHAR(20) NOT NULL UNIQUE,        
    balance DOUBLE NOT NULL DEFAULT 0.0,                
    accountType ENUM('AHORROS', 'CORRIENTE') NOT NULL,
    FOREIGN KEY (dni) REFERENCES Customers(dni) ON DELETE CASCADE
);

-- Insertar algunos clientes de ejemplo
INSERT INTO Customers (dni, name, lastname, email) VALUES
('12345678', 'Maria', 'Sanchez Ruiz', 'maria13@gmail.com'),
('73612345', 'Gustavo', 'Quintanilla Osorio', 'gosorio@gmail.com'),
('73842900', 'Pablo', 'Garcia Garcia', 'pgarcia@gmail.com');

-- Insertar algunas cuentas bancarias de ejemplo
INSERT INTO BankAccounts (dni, accountNumber, balance, accountType) VALUES
('12345678', '12345678', 450.00, 'AHORROS'), 
('73612345', '22334455', 900.00, 'CORRIENTE'),
('73842900', '77553311', -250.00, 'CORRIENTE');

-- Listar Clientes
SELECT * FROM Customers;

-- Listar Cuentas Bancarias
SELECT * FROM BankAccounts;

-- Añadir saldo de una Cuenta Bancaria
UPDATE BankAccounts
SET balance = balance + 200.00
WHERE accountNumber = '12345678';

-- Retirar saldo de una Cuenta Bancaria
UPDATE BankAccounts
SET balance = balance - 100.00
WHERE accountNumber = '12345678';

-- Consultar saldo de una Cuenta Bancaria
SELECT balance FROM BankAccounts WHERE accountNumber = '12345678';

