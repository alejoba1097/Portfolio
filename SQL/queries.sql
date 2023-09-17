/*- What is the list of managers or employees with people in charge?*/


/*- How many employees are assigned to each one of the managers?*/
DROP TABLE Managers IF EXISTS;

CREATE TABLE Managers
SELECT
    M.EmployeeID AS ManagerID,
    M.FirstName AS ManagerName,
    M.LastName AS ManagerLastName,
    Roles.RoleName,
    Departments.DepartmentName,
    COUNT(M.FirstName) AS EmployeesAssigned
FROM Employees AS M
INNER JOIN Employees AS E
    ON M.EmployeeID = E.ManagerID
INNER JOIN Departments
    ON M.EmployeeID=Departments.DeptManagerID
INNER JOIN Roles
    ON M.RoleID=Roles.RoleID
GROUP BY M.EmployeeID, Roles.RoleName, Departments.DepartmentName;

/*Save the Managers table into a CSV file*/
SELECT 'ManagerID','ManagerName','ManagerLastName','RoleName','DepartmentName','EmployeesAssigned'
UNION
SELECT * FROM Managers
INTO OUTFILE '/home/Portfolio/SQL/results/managers.csv'
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY ''
LINES TERMINATED BY '\n';