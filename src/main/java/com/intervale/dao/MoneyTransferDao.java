package com.intervale.dao;

import com.google.gson.Gson;
import com.intervale.dao.dataSource.DataSource;
import com.intervale.models.Card;
import com.intervale.models.Currency;
import com.intervale.models.MoneyTransfer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MoneyTransferDao implements EntityDao<MoneyTransfer> {

    private final static String TABLE_NAME = "TRANSFERS";

    private final static String INSERT_QUERY =
            "insert into " + TABLE_NAME + " (" +
                    "`transfer_from_card`, `transfer_to_card`, " +
                    "`transfer_datetime`, `transfer_currency`, " +
                    "`transfer_amount`, `transfer_commission`" + ") " +
                    "values (?,?,?,?,?,?)";

    private final static String SELECT_ALL_QUERY =
            "select * from " + TABLE_NAME;

    private JDBCWrapper jdbcWrapper;

    public MoneyTransferDao(DataSource dataSource) {
        jdbcWrapper = new JDBCWrapper(new BDConnectionManager(dataSource));
    }


    @Override
    public void save(MoneyTransfer entity) throws SQLException, IOException, ClassNotFoundException {
        jdbcWrapper.preparedStatementQuery(INSERT_QUERY,
                entity.getFromCard(),
                entity.getToCard(),
                "now()",
                entity.getCurrency(),
                entity.getAmount(),
                entity.getCommission()
        );

    }

    @Override
    public void remove(MoneyTransfer entity) {

    }

    @Override
    public void update(MoneyTransfer entity) {

    }

    @Override
    public void findByName(String entityName) {

    }

    @Override
    public void findById(int id) {

    }

    @Override
    public List<MoneyTransfer> getAll() throws SQLException, IOException, ClassNotFoundException {
        ResultSet resultSet = jdbcWrapper.preparedStatementQuery(SELECT_ALL_QUERY);
        List<MoneyTransfer> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(getFromResult(resultSet));
        }
        return list;
    }

    private MoneyTransfer getFromResult(ResultSet result) throws SQLException {
        Gson gson = new Gson();
        SimpleDateFormat format = new SimpleDateFormat();
        return new MoneyTransfer(
                result.getInt("transferId"),
                gson.fromJson(result.getString("transfer_from_card"),Card.class ),
                gson.fromJson(result.getString("transfer_to_card"), Card.class ),
                result.getDate("transfer_datetime"),
                Enum.valueOf(Currency.class, result.getString("transfer_currency")),
                result.getBigDecimal("transfer_amount"),
                result.getBigDecimal("transfer_commission")
        );
    }
}
