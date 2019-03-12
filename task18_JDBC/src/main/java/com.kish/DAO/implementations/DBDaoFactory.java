package com.kish.DAO.implementations;

import com.kish.DAO.DBException;
import com.kish.DAO.DaoFactory;
import com.kish.DAO.GeneralDAO;
import com.kish.model.Department;
import com.kish.model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBDaoFactory implements DaoFactory<Connection> {

    private String user = "root";
    private String password = "1401198n";
    private String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private Map<Class, DaoCreator> creators;

    public DBDaoFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<>();
        creators.put(Department.class, (DaoCreator<Connection>) connection -> new DepartmentDAOImpl(connection));
        creators.put(Employee.class, (DaoCreator<Connection>) connection -> new EmployeeDAOImpl(connection));
    }

    public Connection getContext() throws DBException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return connection;
    }

    @Override
    public GeneralDAO getDao(Connection connection, Class entity) throws DBException {
        DaoCreator creator = creators.get(entity);
        if (creator == null) {
            throw new DBException("Dao object for " + entity + " not found.");
        }
        return creator.create(connection);
    }

}
