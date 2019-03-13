package com.kish.DAO.implementations;

import com.kish.DAO.AbstractJDBCDao;
import com.kish.DAO.DBException;
import com.kish.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DepartmentDAOImpl extends AbstractJDBCDao<Department, Integer> {

    public DepartmentDAOImpl(Connection connection) {
        super(connection);
    }


    @Override
    public String getSelectQuery() {
        return "SELECT ID, name, location FROM mydb.department";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mydb.department (name, location) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mydb.department SET name= ?, location = ? WHERE ID= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mydb.department WHERE ID= ?;";
    }

    @Override
    protected List<Department> parseResultSet(ResultSet rs) throws DBException {
        LinkedList<Department> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Department department = new Department();
                department.setID(rs.getInt("ID"));
                department.setName(rs.getString("name"));
                department.setLocation(rs.getString("location"));
                result.add(department);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Department object) throws DBException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getLocation());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Department object) throws DBException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getLocation());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
