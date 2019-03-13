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
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import static org.apache.log4j.LogManager.getLogger;


public class Main {
    private static Logger log = getLogger(Main.class);

    public static void main(String[] args) throws DBException, SQLException {
        DaoFactory factory = new DBDaoFactory();
        Connection connection = factory.getConnection();
        GeneralDAO departmentDAO = factory.getDao(connection, Department.class);
        GeneralDAO employeeDAO = factory.getDao(connection, Employee.class);
        GeneralDAO projectDAO = factory.getDao(connection, Project.class);
        GeneralDAO workDAO = factory.getDao(connection, Work.class);
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        // get All rows
        List<Department> listOfDepartments = departmentDAO.getAll();
        listOfDepartments.stream().forEach(System.out::println);

        List<Employee> listOfEmployees = employeeDAO.getAll();
        listOfEmployees.stream().forEach(System.out::println);

        List<Project> listOfProjects = projectDAO.getAll();
        listOfProjects.stream().forEach(System.out::println);

        List<Work> listOfWorks = workDAO.getAll();
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
        Department department = new Department();
        Employee employee = new Employee();
        Project project = new Project();
        Work work = new Work();

        //delete row
        // Department dep = new Department();
//        dep.setID(1);
//         department.delete(dep);


        //update row
        department.setName("public_relations");
        department.setLocation("Lviv");
        department.setID(4);
        departmentDAO.update(department);

        employee.setLastName("Petrov");
        employee.setName("Petro");
        employee.setDepartmentID(3);
        employee.setID(2);
        employeeDAO.update(employee);

        project.setProjectName("realise");
        project.setBudget(250000);
        project.setEmployeeID(2);
        project.setID(3);
        projectDAO.update(project);

        work.setEmployeeID(3);
        work.setJob("left_menu");
        work.setStartDate(convert(new Date(2019 - 01 - 14)));
        work.setDeadline(convert(new Date(2019 - 01 - 15)));
        work.setID(4);
        workDAO.update(work);

        //insert row
        department.setID(10);
        department.setName("foreign relationship");
        department.setLocation("Dnipro");
        departmentDAO.create(department);

        //database metadata

        //database product name
        String dataBaseName = databaseMetaData.getDatabaseProductName();
        log.info("DataBase name: " + dataBaseName);

        //database version
        String productVersion = databaseMetaData.getDatabaseProductVersion();
        log.info("DataBase version: " + productVersion);

        //database driver version
        int driverMajorVersion = databaseMetaData.getDriverMajorVersion();
        log.info("DataBase driver version: " + driverMajorVersion);
    }
        public static java.sql.Date convert(java.util.Date date) {
            return new java.sql.Date(date.getTime());
            }
        }

