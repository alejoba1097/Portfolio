# Employees Management System with SQL

## 

## Data Generation

Data was generated in https://www.mockaroo.com/.

## Queries

### Distribution of the employees

- What is the list of managers or employees with people in charge?
- How many employees are assigned to each one of the managers?

The following sentence allows to answer these questions:
```
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
```

It can be seen that there are currently 10 managers, with about 100 people assigned each. This would make sense for Directors, but it's necessary to create a hierarchy for each department as follows:

- Director
- Manager
- Coordinator
- Supervisor / Team Lead
- Analyst / Developer
- Intern

The CEO also shouldn't have any manager assigned, and should be the manager of all directors.

- How many employees are per project?

```
SELECT
    P.ProjectID, P.ProjectName,
    COUNT(P.ProjectID) As EmployeesNumber
FROM Projects AS P
INNER JOIN ProjectsEmployees AS PE
    ON P.ProjectID=PE.ProjectID
GROUP BY P.ProjectID, P.ProjectName;
```

- And per department?
```
SELECT
    D.DepartmentID, D.DepartmentName,
    COUNT(D.DepartmentID) AS EmployeesNumber
FROM Departments AS D
INNER JOIN Employees AS E
    ON D.DepartmentID=E.DepartmentID
GROUP BY D.DepartmentID, D.DepartmentName;
```

- And per role?
```
SELECT
    R.RoleID, R.RoleName,
    COUNT(R.RoleID) AS EmployeesNumber
FROM Roles AS R
INNER JOIN Employees AS E
    ON R.RoleID=E.RoleID
GROUP BY R.RoleID, R.RoleName;
```

- How many projects are allocated in each one of the Locations?
```
SELECT
    L.LocationID, L.LocationName,
    COUNT(L.LocationID) AS ProjectsNumber
FROM Locations AS L
INNER JOIN Projects AS P
    ON L.LocationID=P.LocationID
GROUP BY L.LocationID, L.LocationName;
```

- And how many employees per Location?
```
SELECT
    L.LocationID, L.LocationName,
    COUNT(L.LocationID) AS EmployeesNumber
FROM Locations AS L
INNER JOIN Projects AS P
    ON L.LocationID=P.LocationID
INNER JOIN ProjectsEmployees AS PE
    ON P.ProjectID=PE.ProjectID
GROUP BY L.LocationID, L.LocationName;
```

- What data inconsistencies we have?
    - Since the data was generated randomly, it's possible that there's not a clear hierarchy with the roles (e.g., an agent has a team of Directors assigned, CEO in HR, more than one CEO, manager as manager of himself, etc.)


## Fixes to the data
- Promote all the managers to their corresponding role
    - The CEO shouldn't have a manager, so the ManagerID FK needs to be altered to accept NULL values
- Redistribute the teams so that each manager has a better number of collaborators.