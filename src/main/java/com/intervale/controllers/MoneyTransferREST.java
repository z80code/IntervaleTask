package com.intervale.controllers;

import com.google.gson.Gson;
import com.intervale.dao.CommissionDaoImpl;
import com.intervale.models.Commission;
import com.intervale.models.MoneyTransfer;
import com.intervale.services.CalcService;
import com.intervale.services.CommissionService;
import com.intervale.services.MoneyTransferService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/transfer/")
public class MoneyTransferREST {

    private static Logger logger = Logger.getLogger(MoneyTransferREST.class);

    @PostMapping("commission")
    public MoneyTransfer calc(@RequestBody MoneyTransfer moneyTransfer) {
        moneyTransfer.status = null;

        logger.info("Received: " + new Gson().toJson(moneyTransfer));

        try {
            moneyTransfer = CalcService.calcCommissionForMoneyTransfer(moneyTransfer);
        } catch (SQLException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (ClassNotFoundException e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        }

        logger.info("Send: " + new Gson().toJson(moneyTransfer));

        return moneyTransfer;
    }

    @PutMapping("put")
    public String putCommission(@RequestBody MoneyTransfer moneyTransfer) {

        logger.info("Received: " + new Gson().toJson(moneyTransfer));

        MoneyTransferService moneyTransferService = new MoneyTransferService();
        MoneyTransfer moneyTransfer1 = null;
        try {
            moneyTransfer1 = moneyTransferService.save(moneyTransfer);
        } catch (SQLException e1) {
            logger.error(e1.getMessage());
            e1.printStackTrace();
        } catch (IOException e2) {
            logger.error(e2.getMessage());
            e2.printStackTrace();
        } catch (ClassNotFoundException e3) {
            logger.error(e3.getMessage());
            e3.printStackTrace();
        }
        if (moneyTransfer1 != null || moneyTransfer1.getTransferId() != 0) {
            logger.info("Saved: " + new Gson().toJson(moneyTransfer1));
            return new Gson().toJson("Success");
        } else return new Gson().toJson("False");
    }

    @PostMapping("commissions")
    public List<Commission> getAll() throws SQLException, IOException, ClassNotFoundException {
        CommissionService commissionService = new CommissionService(new CommissionDaoImpl());
        return commissionService.getAll();
    }
}
