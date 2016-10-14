package com.intervale.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

interface InterfaceJDBCWrapper {
    boolean isExist(String tableName) throws SQLException, IOException, ClassNotFoundException;

    int preparedStatementUpdate(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException;

    ResultSet preparedStatementQuery(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException;

}
