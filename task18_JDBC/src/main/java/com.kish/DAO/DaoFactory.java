package com.kish.DAO;

import java.sql.Connection;

public interface DaoFactory {

    Connection getConnection() throws DBException;

    GeneralDAO getDao(Connection context, Class entity) throws DBException;

    interface DaoCreator {
        GeneralDAO create(Connection context);
    }
}
