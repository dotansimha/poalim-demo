package com.graphqljava.poalim.poalimdemo.entities;

public abstract class BaseTransaction {
    protected String transactionType;
    protected String id;
    protected Float amount;
    protected String accountId;
    protected String date;

    public BaseTransaction(String transactionType, String id, String accountId, String date, Float amount) {
        this.transactionType = transactionType;
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getId() {
        return id;
    }

    public Float getAmount() {
        return amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getDate() {
        return date;
    }

    public Boolean matchesSearch(String freeText) {
        return false;
    }
}
