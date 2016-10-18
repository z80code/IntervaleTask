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

/**
 * REST service для сервиса приема денег.
 * API не имеют версии и  она тут пропущена.
 */

@RestController
@RequestMapping("/api/transfer/")
public class MoneyTransferREST {

    private static Logger logger = Logger.getLogger(MoneyTransferREST.class);

    /**
     * Метод заполняет объект расчитанными данными из класса CalcService.
     * @param moneyTransfer Объект, для которого расчитывается комиссия.
     * @return Возвращает объект с расчитанными данными.
     * Все ошидки логируются.
     * Логируются также полученные и отправленные данные.
     */
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

    /**
     * Метод добавляет операцию в базу.
     * @param moneyTransfer Объект, который записывается в базу.
     *                      TODO добавить проверку корректности данных
     *                      т.к. проверка не производится или должна производится на странице.
     * @return Значение результата операции: Success = успешная запись в базу.
     *                                       False = ошидка при записи.
     */
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

    /**
     * Возвращает Все комиссии из базы.
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @PostMapping("commissions")
    public List<Commission> commissionsGetAll() throws SQLException, IOException, ClassNotFoundException {
        CommissionService commissionService = new CommissionService(new CommissionDaoImpl());
        return commissionService.getAll();
    }
    /**
     * Возвращает Все операции по переводу из базы.
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @PostMapping("transfers")
    public List<MoneyTransfer> moneyTransferGetAll() throws SQLException, IOException, ClassNotFoundException {
        MoneyTransferService moneyTransferService = new MoneyTransferService();
        return moneyTransferService.getAll();
    }
}
