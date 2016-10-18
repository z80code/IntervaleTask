package com.intervale.configurations;

import com.intervale.dao.BDConnectionManager;
import com.intervale.dao.dataSource.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Конфигурационный файл
 */
@Configuration
public class AppConfiguration {

    /**
     *
     * @return объект Connection
     * @throws SQLException - ошибка обмена данными с базой
     * @throws IOException - ошибка ввода вывода
     * @throws ClassNotFoundException -
     * Все ошибки должны бвть обработаны
     * в вызываемом метод месте
     */
    @Bean
    public Connection connection() throws SQLException, IOException, ClassNotFoundException {
        BDConnectionManager bdConnectionManager = new BDConnectionManager(new DataSource());
        return bdConnectionManager.getConnection();
    }

}
