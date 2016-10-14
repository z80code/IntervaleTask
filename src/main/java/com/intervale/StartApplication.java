package com.intervale;

import com.intervale.dao.dataSource.DataSource;
import com.intervale.services.InitBaseService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, JAXBException, IOException {
		InitBaseService initBaseService = new InitBaseService(new DataSource());
		initBaseService.start();
		//SpringApplication.run(StartApplication.class, args);
	}
}
