package com.kish.model;

import com.kish.DAO.Identified;

public class Department implements Identified<Integer> {
    private Integer ID = null;
    private String name;
    private String location;

    @Override
    public Integer getId() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
