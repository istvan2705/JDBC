package com.kish;

import com.kish.DAO.DBException;
import com.kish.DAO.DaoFactory;
import com.kish.DAO.GeneralDAO;
import com.kish.DAO.implementations.*;
import com.kish.model.Department;
import com.kish.model.Employee;
import com.kish.model.Project;
import com.kish.model.Work;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws DBException {
        DaoFactory factory = new DBDaoFactory();
        Connection connection = factory.getContext();

        // get All rows
        GeneralDAO department = new DepartmentDAOImpl(connection);
        List<Department> list = department.getAll();
        list.stream().forEach(System.out::println);

        GeneralDAO employee = new EmployeeDAOImpl(connection);
        List<Employee> list1 =employee.getAll();
        list1.stream().forEach(System.out::println);

        GeneralDAO project = new ProjectDAOImpl(connection);
        List<Project> list2 =project.getAll();
        list2.stream().forEach(System.out::println);

        GeneralDAO work = new WorkDAOImpl(connection);
        List<Work> list3 =work.getAll();
        list3.stream().forEach(System.out::println);

        //get row by ID
        System.out.println(department.getByID(2));
        System.out.println(employee.getByID(4));
        System.out.println(project.getByID(3));
       // System.out.println(work.getByID(1));

        //delete row
        Department dep = new Department();
        dep.setID(1);
         department.delete(dep);
    }
}