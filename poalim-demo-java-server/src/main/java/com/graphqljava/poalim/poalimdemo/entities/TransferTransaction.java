package com.graphqljava.poalim.poalimdemo.entities;

public class TransferTransaction extends BaseTransaction {
    private String description;
    private String sourceAccountId;

    public TransferTransaction(String id, String accountId, String date, Float amount, String sourceAccountId, String description) {
        super("TRANSFER", id, accountId, date, amount);

        this.description = description;
        this.sourceAccountId = sourceAccountId;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    @Override
    public Boolean matchesSearch(String freeText) {
        if (this.description != null) {
            return this.description.contains(freeText);
        }

        return false;
    }
}
