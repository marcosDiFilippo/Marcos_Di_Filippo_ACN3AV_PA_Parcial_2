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

-- 1. Dos registros en la tabla users (un cliente normal y un empleado)
INSERT INTO users (first_name, last_name, birth_date, identification_document, email, password)
VALUES 
('Juan', 'Perez', '1990-05-15', '12345678', 'cliente@banco.com', SHA2('123456', 256)),
('Ana', 'Gomez', '1985-10-20', '87654321', 'empleado@banco.com', SHA2('admin123', 256));

-- 2. Un registro en bank_employees vinculado al usuario empleado (id 2)
INSERT INTO bank_employees (user_id)
VALUES (2);

-- 3. Un registro en bank_accounts vinculado al usuario cliente (id 1), con saldo 50000.00
INSERT INTO bank_accounts (user_id, balance, account_number, alias)
VALUES (1, 50000.00, 123456789, 'JuanPerez1');

-- 4. Un registro en bank_tellers simulando un cajero automático con 100000.00
INSERT INTO bank_tellers (location, available_cash)
VALUES ('Sucursal Central - Cajero 01', 100000.00);