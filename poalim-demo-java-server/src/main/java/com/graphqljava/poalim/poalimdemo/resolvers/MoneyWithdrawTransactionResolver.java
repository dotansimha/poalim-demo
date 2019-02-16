package com.graphqljava.poalim.poalimdemo.resolvers;

import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.Atm;
import com.graphqljava.poalim.poalimdemo.entities.MoneyWithdrawTransaction;
import graphql.schema.DataFetcher;

import java.util.Optional;

public class MoneyWithdrawTransactionResolver extends TransactionResolver {
    public DataFetcher<Optional<Atm>> atmDetails() {
        return environment -> {
            MoneyWithdrawTransaction transaction = environment.getSource();

            return Mocks.MOCK_ATMS.stream().filter(a -> a.getId().equals(transaction.getAtmId())).findFirst();
        };
    }
}
