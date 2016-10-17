package com.intervale.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface InterfaceEntityDao<T> {
    void save(T entity) throws SQLException, IOException, ClassNotFoundException;

    void remove(T entity);

    void update(T entity);

    void findById(int id);

    List<T> getAll() throws SQLException, IOException, ClassNotFoundException;
}
