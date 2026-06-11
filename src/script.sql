CREATE DATABASE bank_system;
USE bank_system;

-- ==========================================
-- USERS
-- ==========================================

CREATE TABLE users (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    identification_document VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ==========================================
-- BANK ACCOUNTS
-- ==========================================

CREATE TABLE bank_accounts (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL,
    balance DECIMAL(15,2) NOT NULL DEFAULT 0,
    account_number INT NOT NULL UNIQUE,
    alias VARCHAR(255) NOT NULL UNIQUE,

    CONSTRAINT fk_bank_accounts_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- ==========================================
-- BANK EMPLOYEES
-- ==========================================

CREATE TABLE bank_employees (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT UNSIGNED NOT NULL UNIQUE,

    CONSTRAINT fk_bank_employees_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- ==========================================
-- BANK TELLERS (CAJEROS AUTOMATICOS)
-- ==========================================

CREATE TABLE bank_tellers (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    available_cash DECIMAL(15,2) NOT NULL DEFAULT 0
) ENGINE=InnoDB;

-- ==========================================
-- TRANSACTION TYPES
-- ==========================================

CREATE TABLE bank_transaction_types (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- ==========================================
-- TRANSACTIONS
-- ==========================================

CREATE TABLE bank_transactions (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,

    user_id BIGINT UNSIGNED NOT NULL,

    bank_transaction_type_id BIGINT UNSIGNED NOT NULL,

    amount DECIMAL(15,2),

    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_bank_transactions_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT fk_bank_transactions_type
        FOREIGN KEY (bank_transaction_type_id)
        REFERENCES bank_transaction_types(id)
) ENGINE=InnoDB;

-- ==========================================
-- TRANSACTIONS <-> TELLERS
-- ==========================================

CREATE TABLE bank_transactions_tellers (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,

    bank_transaction_id BIGINT UNSIGNED NOT NULL,

    bank_teller_id BIGINT UNSIGNED NOT NULL,

    CONSTRAINT fk_btt_transaction
        FOREIGN KEY (bank_transaction_id)
        REFERENCES bank_transactions(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_btt_teller
        FOREIGN KEY (bank_teller_id)
        REFERENCES bank_tellers(id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

-- ==========================================
-- TRANSFERS
-- ==========================================

CREATE TABLE bank_transfers (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,

    bank_transaction_id BIGINT UNSIGNED NOT NULL UNIQUE,

    source_account_id BIGINT UNSIGNED NOT NULL,

    destination_account_id BIGINT UNSIGNED NOT NULL,

    CONSTRAINT fk_transfer_transaction
        FOREIGN KEY (bank_transaction_id)
        REFERENCES bank_transactions(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_transfer_source_account
        FOREIGN KEY (source_account_id)
        REFERENCES bank_accounts(id),

    CONSTRAINT fk_transfer_destination_account
        FOREIGN KEY (destination_account_id)
        REFERENCES bank_accounts(id)
) ENGINE=InnoDB;

-- ==========================================
-- INITIAL DATA
-- ==========================================

INSERT INTO bank_transaction_types (name)
VALUES
('DEPOSIT'),
('WITHDRAW'),
('TRANSFER'),
('BALANCE_INQUIRY'),
('CASH_REPLENISHMENT'),
('TEST_OPERATION');

-- ==========================================
-- SEED DATA (DATOS DE PRUEBA)
-- ==========================================

-- 1. Registros en la tabla users (10 clientes y 5 empleados)
INSERT INTO users (first_name, last_name, birth_date, identification_document, email, password)
VALUES 
-- Clientes (IDs 1 al 10)
('Juan', 'Perez', '1990-05-15', '12345678', 'cliente1@banco.com', SHA2('123456', 256)),
('Maria', 'Lopez', '1992-08-22', '23456789', 'cliente2@banco.com', SHA2('123456', 256)),
('Carlos', 'Martinez', '1988-12-10', '34567890', 'cliente3@banco.com', SHA2('123456', 256)),
('Laura', 'Garcia', '1995-03-05', '45678901', 'cliente4@banco.com', SHA2('123456', 256)),
('Diego', 'Fernandez', '1980-11-30', '56789012', 'cliente5@banco.com', SHA2('123456', 256)),
('Sofia', 'Rodriguez', '1998-07-18', '67890123', 'cliente6@banco.com', SHA2('123456', 256)),
('Martin', 'Sanchez', '1985-09-25', '78901234', 'cliente7@banco.com', SHA2('123456', 256)),
('Lucia', 'Romero', '1993-01-14', '89012345', 'cliente8@banco.com', SHA2('123456', 256)),
('Pedro', 'Diaz', '1979-06-08', '90123456', 'cliente9@banco.com', SHA2('123456', 256)),
('Valentina', 'Ruiz', '2000-04-12', '01234567', 'cliente10@banco.com', SHA2('123456', 256)),
-- Empleados (IDs 11 al 15)
('Ana', 'Gomez', '1985-10-20', '87654321', 'empleado1@banco.com', SHA2('admin123', 256)),
('Roberto', 'Silva', '1975-02-28', '76543210', 'empleado2@banco.com', SHA2('admin123', 256)),
('Elena', 'Torres', '1989-12-05', '65432109', 'empleado3@banco.com', SHA2('admin123', 256)),
('Javier', 'Ramirez', '1991-05-16', '54321098', 'empleado4@banco.com', SHA2('admin123', 256)),
('Carmen', 'Castro', '1982-08-09', '43210987', 'empleado5@banco.com', SHA2('admin123', 256));

-- 2. Registros en bank_employees vinculados a los usuarios empleados (IDs 11 al 15)
INSERT INTO bank_employees (user_id)
VALUES 
(11), (12), (13), (14), (15);

-- 3. Registros en bank_accounts vinculados a los clientes (IDs 1 al 10)
INSERT INTO bank_accounts (user_id, balance, account_number, alias)
VALUES 
(1, 50000.00, 100000001, 'juan.perez.banco'),
(2, 75000.50, 100000002, 'maria.lopez.banco'),
(3, 12000.00, 100000003, 'carlos.martinez.banco'),
(4, 250000.75, 100000004, 'laura.garcia.banco'),
(5, 8000.20, 100000005, 'diego.fernandez.banco'),
(6, 43000.00, 100000006, 'sofia.rodriguez.banco'),
(7, 10500.00, 100000007, 'martin.sanchez.banco'),
(8, 62000.90, 100000008, 'lucia.romero.banco'),
(9, 31000.00, 100000009, 'pedro.diaz.banco'),
(10, 95000.00, 100000010, 'valentina.ruiz.banco');

-- 4. Registros en bank_tellers simulando cajeros automáticos en diferentes sucursales
INSERT INTO bank_tellers (location, available_cash)
VALUES 
('Sucursal Central - Cajero 01', 150000.00),
('Sucursal Central - Cajero 02', 120000.00),
('Sucursal Norte - Cajero 01', 80000.00),
('Sucursal Norte - Cajero 02', 65000.00),
('Sucursal Sur - Cajero 01', 45000.00),
('Sucursal Este - Cajero 01', 95000.00),
('Sucursal Oeste - Cajero 01', 30000.00),
('Shopping Las Palmas - Cajero 01', 200000.00),
('Shopping Las Palmas - Cajero 02', 180000.00),
('Aeropuerto Internacional - Cajero 01', 300000.00);