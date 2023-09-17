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

- And per department?

- And per role?

- What data inconsistencies we have?
    - Since the data was generated randomly, it's possible that there's not a clear hierarchy with the roles (e.g., an agent has a team of Directors assigned, CEO in HR, more than one CEO, manager as manager of himself, etc.)
- How many projects are allocated in each one of the Locations?

## Fixes to the data
- Promote all the managers to their corresponding role
    - The CEO shouldn't have a manager, so the ManagerID FK needs to be altered to accept NULL values
- Redistribute the teams so that each manager has a better number of collaborators.
- 