/*Script to load the mock data to the created Employees Management Database*/

USE EmployeesManagementSystem;

/*Allow to insert foreign keys without having data in the other table*/
SET FOREIGN_KEY_CHECKS = 0;

/*Employees*/
LOAD DATA
    LOCAL
    INFILE '/home/alejoba1097/Portfolio/SQL/data/employees_data.csv'
    INTO TABLE Employees
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

/*Departments*/
LOAD DATA
    LOCAL
    INFILE '/home/alejoba1097/Portfolio/SQL/data/departments_data.csv'
    INTO TABLE Departments
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

/*Roles*/
LOAD DATA
    LOCAL
    INFILE '/home/alejoba1097/Portfolio/SQL/data/roles_data.csv'
    INTO TABLE Roles
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

/*Projects*/
LOAD DATA
    LOCAL
    INFILE '/home/alejoba1097/Portfolio/SQL/data/projects_data.csv'
    INTO TABLE Projects
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

/*Locations*/
LOAD DATA
    LOCAL
    INFILE '/home/alejoba1097/Portfolio/SQL/data/locations_data.csv'
    INTO TABLE Locations
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;

/*ProjectsEmployees*/
LOAD DATA
    LOCAL
    INFILE '/home/alejoba1097/Portfolio/SQL/data/projects_employees_data.csv'
    INTO TABLE ProjectsEmployees
    FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;


SET FOREIGN_KEY_CHECKS = 1;