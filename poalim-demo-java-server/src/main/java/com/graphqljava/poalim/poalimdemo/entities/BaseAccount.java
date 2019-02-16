package com.graphqljava.poalim.poalimdemo.entities;

public abstract class BaseAccount {
    protected String accountType;
    protected String id;
    protected Integer number;
    protected Integer branch;
    protected Integer bank;
    protected String currency;
    protected String ownerId;

    BaseAccount(String accountType, String id, Integer number, Integer branch, Integer bank, String currency, String ownerId) {
        this.accountType = accountType;
        this.id = id;
        this.number = number;
        this.branch = branch;
        this.bank = bank;
        this.currency = currency;
        this.ownerId = ownerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getBranch() {
        return branch;
    }

    public Integer getBank() {
        return bank;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
