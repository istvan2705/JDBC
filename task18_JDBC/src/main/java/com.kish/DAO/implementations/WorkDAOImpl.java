package com.kish.DAO.implementations;

import com.kish.DAO.AbstractJDBCDao;
import com.kish.DAO.DBException;
import com.kish.model.Work;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class WorkDAOImpl extends AbstractJDBCDao<Work, Integer> {

    private class PersistWork extends Work {
        public void setId(Integer project_ID) {
            super.setID(project_ID);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT project_ID, employee_ID, job, start_date, deadline FROM mydb.work";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO mydb.work (employee_ID, job, start_date, deadline) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE mydb.work SET employee_ID= ? job= ? start_date= ? \n" +
                " deadline= ? WHERE project_ID= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM mydb.work WHERE project_ID= ?;";
    }

    @Override
    public Work create() throws DBException {
        Work g = new Work();
        return persist(g);
    }

    public WorkDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected List<Work> parseResultSet(ResultSet rs) throws DBException {
        LinkedList<Work> result = new LinkedList<>();
        try {
            while (rs.next()) {
                PersistWork work = new PersistWork();
                work.setId(rs.getInt("project_ID"));
                work.setEmployeeID(rs.getInt("employee_ID"));
                work.setJob(rs.getString("job"));
                work.setStartDate(rs.getDate("start_date"));
                work.setDeadline(rs.getDate("deadline"));
                result.add(work);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Work object) throws DBException {
        try {
            statement.setInt(2, object.getEmployeeID());
            statement.setString(3, object.getJob());
            statement.setDate(4, (Date) object.getStartDate());
            statement.setDate(5, (Date) object.getDeadline());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Work object) throws DBException {
        try {
            statement.setInt(1, object.getEmployeeID());
            statement.setString(2, object.getJob());
            statement.setDate(3, (Date) object.getStartDate());
            statement.setDate(4, (Date) object.getDeadline());
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
