package com.intervale.dao;

import com.intervale.dao.dataSource.DataSource;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDConnectionManager {

    private DataSource dataSource;

    private Connection connection;

    public BDConnectionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        if (connection == null || connection.isClosed()) {
            Class.forName(dataSource.getDriver());
            connection = DriverManager.getConnection(
                    dataSource.getUrl(),
                    dataSource.getUser(),
                    dataSource.getPassword());
        }
        return connection;
    }
}
