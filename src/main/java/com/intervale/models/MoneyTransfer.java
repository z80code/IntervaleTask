package com.intervale.models;

import java.math.BigDecimal;
import java.util.Date;

public class MoneyTransfer {

    private int transferId;
    private Card fromCard;
    private Card toCard;
    private Date dateTime;
    private Currency currency;
    private BigDecimal amount;
    private BigDecimal commission;


    public MoneyTransfer() {
    }

    public MoneyTransfer(int transferId,
                         Card fromCard,
                         Card toCard,
                         Date dateTime,
                         Currency currency,
                         BigDecimal amount,
                         BigDecimal commission) {
        this.transferId = transferId;
        this.fromCard = fromCard;
        this.toCard = toCard;
        this.dateTime = dateTime;
        this.currency = currency;
        this.amount = amount;
        this.commission = commission;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public void setFromCard(Card fromCard) {
        this.fromCard = fromCard;
    }

    public void setToCard(Card toCard) {
        this.toCard = toCard;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public int getTransferId() {
        return transferId;
    }

    public Card getFromCard() {
        return fromCard;
    }

    public Card getToCard() {
        return toCard;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }
}
