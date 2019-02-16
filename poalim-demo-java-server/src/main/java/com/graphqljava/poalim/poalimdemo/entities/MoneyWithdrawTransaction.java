package com.graphqljava.poalim.poalimdemo.entities;

public class MoneyWithdrawTransaction extends BaseTransaction {
    private String atmId;

    public MoneyWithdrawTransaction(String id, String accountId, String date, Float amount, String atmId) {
        super("MONEY_WITHDRAW", id, accountId, date, amount);

        this.atmId = atmId;
    }

    public String getAtmId() {
        return atmId;
    }
}
