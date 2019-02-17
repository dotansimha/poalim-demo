package com.graphqljava.poalim.poalimdemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.BaseAccount;
import com.graphqljava.poalim.poalimdemo.entities.User;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserResolver implements GraphQLResolver<User> {
    public DataFetcher<String> id() {
        return environment -> environment.<User>getSource().getId();
    }

    public DataFetcher<String[]> emails() {
        return environment -> new String[]{environment.<User>getSource().getEmail()};
    }

    public DataFetcher<User> profile() {
        return DataFetchingEnvironment::getSource;
    }

    public DataFetcher<List<BaseAccount>> accounts() {
        return environment -> {
            User user = environment.getSource();
            Stream<BaseAccount> baseAccounts = Mocks.MOCK_ACCOUNTS.stream().filter(account -> account.getOwnerId().equals(user.getId()));
            LinkedHashMap<String, String> filterArgument = environment.getArgument("filter");

            if (filterArgument != null && filterArgument.containsKey("id")) {
                baseAccounts = baseAccounts.filter(a -> a.getId().equals(filterArgument.get("id")));
            }

            return baseAccounts.collect(Collectors.toList());
        };
    }
}
