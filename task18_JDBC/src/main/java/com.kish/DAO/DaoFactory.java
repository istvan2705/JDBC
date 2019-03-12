package com.kish.DAO;

import java.sql.Connection;

public interface DaoFactory<Context> {

    Connection getContext() throws DBException;

    GeneralDAO getDao(Context context, Class entity) throws DBException;

    interface DaoCreator<Context> {
        GeneralDAO create(Context context);
    }
}
