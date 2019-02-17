package com.graphqljava.poalim.poalimdemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.BaseAccount;
import com.graphqljava.poalim.poalimdemo.entities.BaseTransaction;
import com.graphqljava.poalim.poalimdemo.entities.User;
import graphql.schema.DataFetcher;

import java.util.Optional;

public class QueryResolver implements GraphQLQueryResolver {

    public DataFetcher<User> me() {
        return env -> Mocks.MOCK_USERS.get(0);
    }

    public DataFetcher<Optional<BaseAccount>> account() {
        return env -> {
            String idArgument = env.getArgument("id");
            return Mocks.MOCK_ACCOUNTS
                    .stream()
                    .filter(a -> a.getId().equals(idArgument))
                    .findFirst();
        };
    }

    public DataFetcher<Optional<BaseTransaction>> transaction() {
        return env -> {
            String idArgument = env.getArgument("id");
            return Mocks.MOCK_TRANSACTIONS
                    .stream()
                    .filter(a -> a.getId().equals(idArgument))
                    .findFirst();
        };
    }
}
