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

/*- How many employees are per project?*/

/*List all the employees assigned to each project*/
SELECT
    P.ProjectID, P.ProjectName,
    E.FirstName, E.LastName
FROM Projects AS P
INNER JOIN ProjectsEmployees AS PE
    ON P.ProjectID=PE.ProjectID
INNER JOIN Employees AS E
    ON PE.EmployeeID=E.EmployeeID;

/*Group to count how many employees are there per project*/
SELECT
    P.ProjectID, P.ProjectName,
    COUNT(P.ProjectID) As EmployeesNumber
FROM Projects AS P
INNER JOIN ProjectsEmployees AS PE
    ON P.ProjectID=PE.ProjectID
GROUP BY P.ProjectID, P.ProjectName;

/*- And per department?*/
SELECT
    D.DepartmentID, D.DepartmentName,
    COUNT(D.DepartmentID) AS EmployeesNumber
FROM Departments AS D
INNER JOIN Employees AS E
    ON D.DepartmentID=E.DepartmentID
GROUP BY D.DepartmentID, D.DepartmentName;

/*- And per role?*/
SELECT
    R.RoleID, R.RoleName,
    COUNT(R.RoleID) AS EmployeesNumber
FROM Roles AS R
INNER JOIN Employees AS E
    ON R.RoleID=E.RoleID
GROUP BY R.RoleID, R.RoleName;

/*- How many projects are allocated in each one of the Locations?*/
SELECT
    L.LocationID, L.LocationName,
    COUNT(L.LocationID) AS ProjectsNumber
FROM Locations AS L
INNER JOIN Projects AS P
    ON L.LocationID=P.LocationID
GROUP BY L.LocationID, L.LocationName;

/*And how many employees per Location?*/
SELECT
    L.LocationID, L.LocationName,
    COUNT(L.LocationID) AS EmployeesNumber
FROM Locations AS L
INNER JOIN Projects AS P
    ON L.LocationID=P.LocationID
INNER JOIN ProjectsEmployees AS PE
    ON P.ProjectID=PE.ProjectID
GROUP BY L.LocationID, L.LocationName;