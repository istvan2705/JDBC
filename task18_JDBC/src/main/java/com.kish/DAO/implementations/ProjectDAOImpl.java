package com.kish.DAO.implementations;

import com.kish.DAO.AbstractJDBCDao;
import com.kish.DAO.DBException;
import com.kish.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ProjectDAOImpl extends AbstractJDBCDao<Project, Integer> {

    public ProjectDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT ID, project_name, budget, employee_ID  FROM mydb.project";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mydb.project (project_name, budget, employee_ID) \n" +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mydb.project SET project_name=? budget= ? employee_ID= ? WHERE ID= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mydb.project WHERE ID= ?;";
    }

    @Override
    protected List<Project> parseResultSet(ResultSet rs) throws DBException {
        LinkedList<Project> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Project project = new Project();
                project.setID(rs.getInt("ID"));
                project.setProjectName(rs.getString("project_name"));
                project.setBudget(rs.getInt("budget"));
                project.setEmployeeID(rs.getInt("employee_ID"));
                result.add(project);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Project object) throws DBException {
        try {
            statement.setString(2, object.getProjectName());
            statement.setInt(3, object.getBudget());
            statement.setInt(4, object.getEmployeeID());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Project object) throws DBException {
        try {
            statement.setString(1, object.getProjectName());
            statement.setInt(2, object.getBudget());
            statement.setInt(3, object.getEmployeeID());
            statement.setInt(4, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
