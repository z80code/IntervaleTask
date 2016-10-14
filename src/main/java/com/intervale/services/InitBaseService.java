package com.intervale.services;

import com.intervale.dao.BDConnectionManager;
import com.intervale.dao.JDBCWrapper;
import com.intervale.dao.dataSource.DataSource;
import com.intervale.models.Commission;
import com.intervale.models.modelsXML.Commissions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class InitBaseService {

    private BDConnectionManager bdConnectionManager;

    public InitBaseService(DataSource dataSource) {
        this.bdConnectionManager = new BDConnectionManager(dataSource);
    }

    public void start() throws ClassNotFoundException, SQLException, JAXBException, IOException {

        Commissions commissions = readCommissions();
        JDBCWrapper implDB = new JDBCWrapper(bdConnectionManager);
        if (!implDB.isExist("commissions".toUpperCase())) {
            implDB.preparedStatementQuery("CREATE TABLE `commissions` (" +
                    "`commissionId` INT(5) NOT NULL, " +
                    "`brand` VARCHAR(50) NOT NULL, " +
                    "`currency` VARCHAR(10) NOT NULL," +
                    "`value` DOUBLE(10) NOT NULL" +
                    ");"
            );
            for (Commission commission : commissions.getCommissions()) {
                implDB.preparedStatementUpdate("INSERT INTO `commissions` " +
                                "(`commissionId`, `brand`, `currency`, `value`) " +
                                "VALUES (?,?,?,?);",
                                commission.getId(),
                                commission.getBrand(),
                                commission.getCurrency(),
                                commission.getValue()
                );
            }
        }
        if (!implDB.isExist("transfers".toUpperCase())) {
            implDB.preparedStatementQuery("CREATE TABLE `transfers` (" +
                    "`transferId` INT(5) NOT NULL AUTO_INCREMENT, " +
                    "`transfer_from_card` VARCHAR(255) NOT NULL, " +
                    "`transfer_to_card` VARCHAR(255) NOT NULL," +
                    "`transfer_datetime` DATETIME(7) NOT NULL," +
                    "`transfer_currency` VARCHAR(10) NOT NULL," +
                    "`transfer_amount` DECIMAL NOT NULL," +
                    "`transfer_commission` DECIMAL NOT NULL," +
                    ");"
            );
        }
    }

    private void saveCommissions(Commissions commissions) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Commissions.class, Commission.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(commissions, new File("commissions.xml"));
    }

    private static Commissions readCommissions() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Commissions.class, Commission.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Commissions) unmarshaller.unmarshal(new File("commissions.xml"));
    }

}
