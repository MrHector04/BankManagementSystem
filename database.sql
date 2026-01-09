CREATE DATABASE BankDB;
USE BankDB;
CREATE TABLE Account (
    account_no VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    pin VARCHAR(10),
    balance FLOAT
);
CREATE TABLE Transactions (
    id INT IDENTITY(1,1) PRIMARY KEY,
    account_no VARCHAR(20),
    type VARCHAR(20),
    amount FLOAT,
    date DATETIME DEFAULT GETDATE()
);
INSERT INTO Account VALUES
('1001','Subhadip','1234',5000);



