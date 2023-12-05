drop database UserManagement
use UserManagement
-- Create tbl_Mobile table
CREATE TABLE tbl_Mobile (
  mobileId VARCHAR(10) PRIMARY KEY,
  description VARCHAR(250) NOT NULL,
  price FLOAT,
  mobileName VARCHAR(20) NOT NULL,
  yearOfProduction INT,
  quantity INT,
  notSale BIT
);

-- Create tbl_User table
CREATE TABLE tbl_User (
  userId VARCHAR(20) PRIMARY KEY,
  password INT NOT NULL,
  fullName VARCHAR(50) NOT NULL,
  role INT
);

-- Insert sample data into tbl_Mobile
INSERT INTO tbl_Mobile (mobileId, description, price, mobileName, yearOfProduction, quantity, notSale)
VALUES
  ('M1', 'Sample description 1', 100.50, 'Mobile 1', 2022, 10, 0),
  ('M2', 'Sample description 2', 200.75, 'Mobile 2', 2021, 5, 1),
  ('M3', 'Sample description 3', 150.25, 'Mobile 3', 2023, 8, 0);

-- Insert sample data into tbl_User
INSERT INTO tbl_User (userId, password, fullName, role)
VALUES
  ('user1', 123456, 'User 1', 0),
  ('user2', 789012, 'User 2', 0),
  ('manager1', 345678, 'Manager 1', 1),
  ('staff1', 901234, 'Staff 1', 2);

