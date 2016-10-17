package com.intervale.configurations;

import com.intervale.dao.BDConnectionManager;
import com.intervale.dao.dataSource.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {

    @Bean
    public Connection connection() throws SQLException, IOException, ClassNotFoundException {
        BDConnectionManager bdConnectionManager = new BDConnectionManager(new DataSource());
        return bdConnectionManager.getConnection();
    }

}
