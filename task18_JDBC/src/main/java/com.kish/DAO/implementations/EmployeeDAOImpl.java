package com.kish.DAO.implementations;

import com.kish.DAO.AbstractJDBCDao;
import com.kish.DAO.DBException;
import com.kish.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAOImpl extends AbstractJDBCDao<Employee, Integer> {

    public EmployeeDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT ID, last_name, name, department_ID FROM mydb.employee";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mydb.employee (last_name, name, department_ID) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mydb.employee SET last_name=? name= ? department_ID = ? WHERE ID= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mydb.employee WHERE ID= ?;";
    }

    @Override
    protected List<Employee> parseResultSet(ResultSet rs) throws DBException {
        LinkedList<Employee> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setID(rs.getInt("ID"));
                employee.setName(rs.getString("last_name"));
                employee.setLastName(rs.getString("name"));
                employee.setDepartmentID(rs.getInt("department_ID"));
                result.add(employee);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Employee object) throws DBException {
        try {
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getName());
            statement.setInt(4, object.getDepartmentID());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Employee object) throws DBException {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getLastName());
            statement.setInt(3, object.getDepartmentID());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
