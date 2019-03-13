package com.kish;

import com.kish.DAO.DBException;
import com.kish.DAO.DaoFactory;
import com.kish.DAO.GeneralDAO;
import com.kish.DAO.implementations.*;
import com.kish.model.Department;
import com.kish.model.Employee;
import com.kish.model.Project;
import com.kish.model.Work;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.util.List;


import static org.apache.log4j.LogManager.getLogger;


public class Main {
    private static Logger log = getLogger(Main.class);

    public static void main(String[] args) throws DBException {
        DaoFactory factory = new DBDaoFactory();
        Connection connection = factory.getConnection();
        GeneralDAO departmentDAO = factory.getDao(connection, Department.class);
        GeneralDAO employeeDAO = factory.getDao(connection, Employee.class);
       GeneralDAO projectDAO = factory.getDao(connection, Project.class);
        GeneralDAO workDAO = factory.getDao(connection, Work.class);

        // get All rows
        List<Department> listOfDepartments = departmentDAO.getAll();
        listOfDepartments.stream().forEach(System.out::println);

        List<Employee> listOfEmployees =employeeDAO.getAll();
        listOfEmployees.stream().forEach(System.out::println);

        List<Project> listOfProjects =projectDAO.getAll();
        listOfProjects.stream().forEach(System.out::println);

        List<Work> listOfWorks =workDAO.getAll();
        listOfWorks.stream().forEach(System.out::println);

        //get row by ID

        Department departmentFindByID = (Department) departmentDAO.getByID(2);
        log.info(departmentFindByID);
        Employee employeeFindByID = (Employee) employeeDAO.getByID(3);
        log.info(employeeFindByID);
        Project projectFindByID = (Project) projectDAO.getByID(4);
        log.info(projectFindByID);
        Work workFindByID = (Work) workDAO.getByID(5);
        log.info(workFindByID);
        //delete row
       // Department dep = new Department();
//        dep.setID(1);
//         department.delete(dep);


    }



}