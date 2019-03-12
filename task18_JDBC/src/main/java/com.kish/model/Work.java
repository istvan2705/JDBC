package com.kish.model;

import com.kish.DAO.Identified;

import java.util.Date;

public class Work implements Identified<Integer> {
    private Integer projectID = null;
    private Integer employeeID;
    private String job;
    private Date startDate;
    private Date deadline;

    @Override
    public Integer getId() {
        return projectID;
    }

    public void setID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Work{" +
                "projectID=" + projectID +
                ", employeeID=" + employeeID +
                ", job='" + job + '\'' +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                '}';
    }
}
