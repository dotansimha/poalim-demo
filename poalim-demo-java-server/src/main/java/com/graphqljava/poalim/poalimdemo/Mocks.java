package com.graphqljava.poalim.poalimdemo;

import com.graphqljava.poalim.poalimdemo.entities.*;

import java.util.Arrays;
import java.util.List;

public class Mocks {
    public static List<User> MOCK_USERS = Arrays.asList(
            new User("1", "dotansimha@gmail.com", "dotansimha", "Dotan", "Simha"),
            new User("2", "uri.goldshtein@gmail.com", "urigo", "Uri", "Goldshtein")
    );

    public static List<BaseAccount> MOCK_ACCOUNTS = Arrays.asList(
            new CheckingAccount( "1", 5000, 10, 14, "USD", Mocks.MOCK_USERS.get(0).getId()),
            new CheckingAccount( "2", 5001, 11, 14, "USD", Mocks.MOCK_USERS.get(1).getId())
    );

    public static List<Atm> MOCK_ATMS = Arrays.asList(
            new Atm("1", "Tel Aviv")
    );

    public static List<BaseTransaction> MOCK_TRANSACTIONS = Arrays.asList(
            new TransferTransaction("1", MOCK_ACCOUNTS.get(0).getId(), "2019-10-02", 25920.01f, MOCK_ACCOUNTS.get(1).getId(), "money!"),
            new TransferTransaction("2", MOCK_ACCOUNTS.get(0).getId(), "2019-10-02", 920f, MOCK_ACCOUNTS.get(1).getId(), null),
            new CheckDepositTransaction("3", MOCK_ACCOUNTS.get(0).getId(), "2018-02-11", 500f, 9988),
            new MoneyWithdrawTransaction("4", MOCK_ACCOUNTS.get(0).getId(), "2018-10-10", -600f, MOCK_ATMS.get(0).getId())
    );
}
