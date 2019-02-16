package com.graphqljava.poalim.poalimdemo.entities;

public class CheckDepositTransaction extends BaseTransaction {
    private Integer checkNumber;

    public CheckDepositTransaction(String id, String accountId, String date, Float amount, Integer checkNumber) {
        super("CHECK_DEPOSIT", id, accountId, date, amount);

        this.checkNumber = checkNumber;
    }

    public Integer getCheckNumber() {
        return checkNumber;
    }

    @Override
    public Boolean matchesSearch(String freeText) {
        if (this.checkNumber != null) {
            return this.checkNumber.toString().contains(freeText);
        }

        return false;
    }
}
