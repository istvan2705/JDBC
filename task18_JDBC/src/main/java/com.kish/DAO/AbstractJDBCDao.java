package com.kish.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class AbstractJDBCDao<T extends Identified<ID>, ID extends Integer> implements GeneralDAO<T, ID> {
    private Connection connection;

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }
    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    protected abstract List<T> parseResultSet(ResultSet rs) throws DBException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DBException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DBException;

    @Override
    public void create(T object) throws DBException {
           String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On create modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
       }

    @Override
    public T getByID(Integer key) throws DBException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DBException(e);
        }
        if (list == null || list.size() == 0) {
            throw new DBException("Record with ID = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new DBException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws DBException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            int count = statement.executeUpdate();
            System.out.println(count);
            if (count != 1) {
                throw new DBException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    public void delete(T object) throws DBException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new DBException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DBException("On delete modify more then 1 record: " + count);
            }
           } catch (Exception e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<T> getAll() throws DBException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DBException(e);
        }
        return list;
    }

}
