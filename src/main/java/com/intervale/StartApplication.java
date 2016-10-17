package com.intervale;

import com.intervale.dao.BDConnectionManager;
import com.intervale.dao.JDBCWrapperImpl;
import com.intervale.dao.dataSource.DataSource;
import com.intervale.services.InitBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, JAXBException, IOException {
        InitBaseService initBaseService = new InitBaseService(
                new JDBCWrapperImpl(
                        new BDConnectionManager(
                                new DataSource()).getConnection()));
        initBaseService.start("commissions.xml");
        SpringApplication.run(StartApplication.class, args);
    }
}
