package com.intervale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;

@Component
public class JDBCWrapperImpl implements InterfaceJDBCWrapper {

    private final Connection connection;

    public JDBCWrapperImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean isExist(String tableName) throws SQLException, IOException, ClassNotFoundException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }

    @Override
    public int preparedStatementUpdate(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeUpdate();
    }

    @Override
    public ResultSet preparedStatementQuery(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeQuery();
    }

    @Override
    public boolean preparedStatement(String sqlString, Object... parameters) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
        setParameters(preparedStatement, parameters);
        return preparedStatement.execute();
    }

    private PreparedStatement setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {

        int indexer = 1; // нужна для автоматизации перечисления параметров
        for (Object param : parameters) {
            preparedStatement.setString(indexer++, param.toString());
        }
        return preparedStatement;
    }
}
