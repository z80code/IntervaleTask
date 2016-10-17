package com.intervale.dao;

import com.intervale.dao.dataSource.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.SQLException;

@Configuration
public class JDBCWrapperImplManager {
    @Bean
    public static JDBCWrapperImpl getJDBCWrapper() throws SQLException, IOException, ClassNotFoundException {
        return new JDBCWrapperImpl(new BDConnectionManager(new DataSource()).getConnection());
    }
}
