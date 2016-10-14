package com.intervale.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

interface EntityDao<T> {
    void save(T entity) throws SQLException, IOException, ClassNotFoundException;
    void remove(T entity);
    void update(T entity);

    void findByName(String entityName);
    void findById(int id);
    List<T> getAll() throws SQLException, IOException, ClassNotFoundException;
}
