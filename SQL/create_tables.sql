/*Drop database to create it all over*/
DROP DATABASE IF EXISTS EmployeesManagementSystem;

/*Create and use database*/
CREATE DATABASE EmployeesManagementSystem;

USE EmployeesManagementSystem;

/*Create table Employees*/
CREATE TABLE Employees(
    EmployeeID int NOT NULL AUTO_INCREMENT,
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
    Email varchar(255) NOT NULL,
    HireDate date NOT NULL,
    PhoneNumber varchar(13),
    CurrentStatus varchar(10) NOT NULL CHECK (CurrentStatus IN ('Active', 'Not Active')),
    ManagerID int NOT NULL,
    DepartmentID int NOT NULL,
    RoleId int NOT NULL,
    PRIMARY KEY (EmployeeID)
);

/*Create table Departments*/
CREATE TABLE Departments(
    DepartmentID int NOT NULL AUTO_INCREMENT,
    DepartmentName varchar(255) NOT NULL,
    DepartmentDescription varchar(255) NOT NULL,
    DeptManagerID int  NOT NULL,
    PRIMARY KEY (DepartmentID)
);

/*Create table Roles*/
CREATE TABLE Roles(
    RoleID int NOT NULL AUTO_INCREMENT,
    RoleName varchar(255) NOT NULL,
    RoleDescription varchar(255) NOT NULL,
    StartDate date NOT NULL,
    EndDate date,
    Responsibilities varchar(255),
    PRIMARY KEY (RoleID)
);

/*Create table Projects*/
CREATE TABLE Projects(
    ProjectID int NOT NULL AUTO_INCREMENT,
    ProjectName varchar(255) NOT NULL,
    ProjectDescription varchar(255) NOT NULL,
    LocationID int NOT NULL,
    PRIMARY KEY (ProjectID)
);

/*Create table Locations*/
CREATE TABLE Locations(
    LocationID int NOT NULL AUTO_INCREMENT,
    LocationName varchar(255) NOT NULL,
    LocationDescription varchar(255) NOT NULL,
    LocationAddress varchar(255) NOT NULL,
    PRIMARY KEY (LocationID)
);

/*Relationships*/

/*Employees*/
ALTER TABLE Employees
ADD FOREIGN KEY (ManagerID) REFERENCES Employees(EmployeeID),
ADD FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID),
ADD FOREIGN KEY (RoleID) REFERENCES Roles(RoleID);

/*Departments*/
ALTER TABLE Departments
ADD FOREIGN KEY (DeptManagerID) REFERENCES Employees(EmployeeID);

/*Projects*/
ALTER TABLE Projects
ADD FOREIGN KEY (LocationID) REFERENCES Locations(LocationID);

/*Create junction table ProjectsEmployees*/
CREATE TABLE ProjectsEmployees(
    ID int NOT NULL,
    ProjectID int NOT NULL,
    EmployeeID int NOT NULL,
    FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);