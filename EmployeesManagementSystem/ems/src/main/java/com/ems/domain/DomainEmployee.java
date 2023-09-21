package com.ems.domain;

public class DomainEmployee {
    private int employeeId;
    private String firstName;
    private int deptId;
    private int roleId;
    private String status;
    private DomainRole role;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DomainRole getRole() {
        return role;
    }

    public void setRole(DomainRole role) {
        this.role = role;
    }
}
