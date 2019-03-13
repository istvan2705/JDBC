package com.kish.model;

import com.kish.DAO.Identified;

public class Project implements Identified<Integer> {
    private int ID;
    private String projectName;
    private int budget;
    private Integer employeeID;

    @Override
    public Integer getId() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString() {
        return "Project{" +
                "ID=" + ID +
                ", projectName='" + projectName + '\'' +
                ", budget=" + budget +
                ", employeeID=" + employeeID +
                '}';
    }
}
