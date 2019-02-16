package com.graphqljava.poalim.poalimdemo.entities;

public class CheckingAccount extends BaseAccount {
    public CheckingAccount(String id, Integer number, Integer branch, Integer bank, String currency, String ownerId) {
        super("CHECKING", id, number, branch, bank, currency, ownerId);
    }
}
