package com.kish.DAO;

import java.io.Serializable;
import java.util.List;

public interface GeneralDAO<T extends Identified<ID>, ID extends Serializable> {

     void create(T object) throws DBException;

     T getByID(ID key) throws DBException;

     void update(T object) throws DBException;

     void delete(T object) throws DBException;

     List<T> getAll() throws DBException;
}

