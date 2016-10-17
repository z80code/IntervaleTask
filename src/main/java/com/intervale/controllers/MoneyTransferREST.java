package com.intervale.controllers;

import com.intervale.dao.BDConnectionManager;
import com.intervale.dao.CommissionDaoImpl;
import com.intervale.dao.JDBCWrapperImpl;
import com.intervale.dao.dataSource.DataSource;
import com.intervale.models.Commission;
import com.intervale.models.MoneyTransfer;
import com.intervale.services.CommissionService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

@RestController
@RequestMapping("/api/transfer/")
public class MoneyTransferREST {


    private static Logger logger = Logger.getLogger(MoneyTransferREST.class);

    @PostMapping("commission")
    public MoneyTransfer calc(@RequestBody MoneyTransfer moneyTransfer) throws SQLException, IOException, ClassNotFoundException {
        moneyTransfer.status = null;
        logger.info(moneyTransfer);

        CommissionService commissionService = new CommissionService(new CommissionDaoImpl(new JDBCWrapperImpl(new BDConnectionManager(new DataSource()).getConnection())));

        List<Commission> commissions = commissionService.getByBrandAndCurrency(
                moneyTransfer.getClientCard().getBrand(),
                moneyTransfer.getCurrency());

        List<Commission> commission = commissions.stream()
                .sorted((x, y) -> Double.compare(x.getValue(), y.getValue()))
                .collect(Collectors.toList());

        if (commission.size() > 0) {

            Commission commission1 = commission.get(0);
            moneyTransfer.setCommission(new BigDecimal(commission1.getValue()));

            BigDecimal value = valueOf(commission1.getValue()/100).multiply(moneyTransfer.getAmount());

            moneyTransfer.commissionOfAmount =value.doubleValue();

            moneyTransfer.resultOperation = moneyTransfer.commissionOfAmount + moneyTransfer.getAmount().doubleValue();
            moneyTransfer.isError = false;
        } else {
            moneyTransfer.status = "Для данного вида операции сервис не поддерживается.";
            moneyTransfer.isError = true;
        }
        return moneyTransfer;
    }

    @PostMapping("commissions")
    public List<Commission> getAll() throws SQLException, IOException, ClassNotFoundException {
        logger.info("Тестовое сообщение!");
        CommissionService commissionService = new CommissionService(new CommissionDaoImpl(new JDBCWrapperImpl(new BDConnectionManager(new DataSource()).getConnection())));
        return commissionService.getAll();
    }

    @PostMapping("date")
    public LocalDateTime getDate() {
        return LocalDateTime.now();
    }

    @PostMapping("dt")
    public LocalDate regetDate(@RequestBody Long localDateTime) {
        return LocalDate.ofEpochDay(localDateTime / (1000 * 60 * 60 * 24));
    }
}
