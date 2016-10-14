package com.intervale.dao;

import java.io.IOException;
import java.sql.*;

public class JDBCWrapper implements InterfaceJDBCWrapper {

    private BDConnectionManager bdConnectionManager;

    public JDBCWrapper(BDConnectionManager bdConnectionManager) {
        this.bdConnectionManager = bdConnectionManager;
    }


    @Override
    public boolean isExist(String tableName) throws SQLException, IOException, ClassNotFoundException {
        Connection conn = bdConnectionManager.getConnection();
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }

    @Override
    public int preparedStatementUpdate(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        Connection conn =  bdConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
        int indexer = 1;
        for (Object param : parameters) {
            preparedStatement.setString(indexer++, param.toString());
        }
       return preparedStatement.executeUpdate();
    }

    @Override
    public ResultSet preparedStatementQuery(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        Connection conn =  bdConnectionManager.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sqlString);
        int indexer = 1;
        for (Object param : parameters) {
            preparedStatement.setString(indexer++, param.toString());
        }
        return preparedStatement.executeQuery();
    }
}
