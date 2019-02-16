package com.graphqljava.poalim.poalimdemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.BaseAccount;
import com.graphqljava.poalim.poalimdemo.entities.BaseTransaction;
import graphql.schema.DataFetcher;

import java.util.Optional;

public abstract class TransactionResolver implements GraphQLResolver<BaseTransaction> {
    public DataFetcher<Optional<BaseAccount>> account() {
        return environment -> {
            BaseTransaction transaction = environment.getSource();

            return Mocks.MOCK_ACCOUNTS.stream().filter(a -> a.getId().equals(transaction.getAccountId())).findFirst();
        };
    }
}
