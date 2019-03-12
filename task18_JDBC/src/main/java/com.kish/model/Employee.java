package com.kish.model;

import com.kish.DAO.Identified;

public class Employee implements Identified<Integer> {
    private Integer ID = null;
    private String lastName;
    private String name;
    private Integer departmentID;


    @Override
    public Integer getId() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", departmentID=" + departmentID +
                '}';
    }
}
