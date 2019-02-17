package com.graphqljava.poalim.poalimdemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.BaseTransaction;
import com.graphqljava.poalim.poalimdemo.entities.CheckingAccount;
import com.graphqljava.poalim.poalimdemo.entities.User;
import graphql.schema.DataFetcher;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckingAccountResolver implements GraphQLResolver<CheckingAccount> {
    public DataFetcher<Optional<User>> owner() {
        return environment -> {
            CheckingAccount account = environment.getSource();
            String ownerId = account.getOwnerId();

            return Mocks.MOCK_USERS.stream().filter(user -> user.getId().equals(ownerId)).findFirst();
        };
    }

    public DataFetcher<String> balance() {
        return environment -> {
            CheckingAccount account = environment.getSource();
            Double balance = Mocks.MOCK_TRANSACTIONS
                    .stream()
                    .filter(t -> t.getAccountId().equals(account.getId()))
                    .mapToDouble(t -> t.getAmount().doubleValue())
                    .sum();

            return new DecimalFormat("#.##").format(balance);

        };
    }

    public DataFetcher<List<BaseTransaction>> transactions() {
        return environment -> {
            CheckingAccount account = environment.getSource();
            TransactionsFilterArgument filterArgument = TransactionsFilterArgument.create(environment.getArgument("filter"));
            TransactionsSearchArgument searchArgument = TransactionsSearchArgument.create(environment.getArgument("search"));

            Stream<BaseTransaction> transactionStream = Mocks.MOCK_TRANSACTIONS
                    .stream()
                    .filter(t -> t.getAccountId().equals(account.getId()));

            if (searchArgument.transactionType != null) {
                transactionStream = transactionStream.filter(t -> t.getTransactionType().equals(searchArgument.transactionType));
            }

            if (searchArgument.freeText != null) {
                transactionStream = transactionStream.filter(t -> t.matchesSearch(searchArgument.freeText));
            }

            if (filterArgument.skip != null) {
                transactionStream = transactionStream.skip(filterArgument.skip);
            }
            if (filterArgument.limit != null) {
                transactionStream = transactionStream.limit(filterArgument.limit);
            }

            return transactionStream.collect(Collectors.toList());
        };
    }
}
