package com.graphqljava.poalim.poalimdemo.resolvers;

import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.BaseAccount;
import com.graphqljava.poalim.poalimdemo.entities.TransferTransaction;
import graphql.schema.DataFetcher;

import java.util.Optional;

public class TransferTransactionResolver extends TransactionResolver {
    public DataFetcher<String> description() {
        return environment -> {
            TransferTransaction transaction = environment.getSource();

            if (transaction.getDescription() == null) {
                return "";
            }

            return transaction.getDescription();
        };
    }

    public DataFetcher<Optional<BaseAccount>> source() {
        return environment -> {
            TransferTransaction transaction = environment.getSource();

            return Mocks.MOCK_ACCOUNTS.stream().filter(a -> a.getId().equals(transaction.getSourceAccountId())).findFirst();
        };
    }
}
