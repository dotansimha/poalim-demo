package com.graphqljava.poalim.poalimdemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.ImmutableMap;
import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.User;
import graphql.schema.DataFetcher;

import java.util.Optional;

public class MutationResolver implements GraphQLMutationResolver {
    public DataFetcher<ImmutableMap> login() {
        return env -> {
            String usernameArgument = env.getArgument("username");
            String passwordArgument = env.getArgument("password");

            Optional<User> foundUser = Mocks.MOCK_USERS
                    .stream()
                    .filter(a -> a.getUsername().equals(usernameArgument))
                    .findFirst();

            if (!foundUser.isPresent()) {
                throw new Exception("Invalid username of password");
            } else {
                return ImmutableMap.of("token", "1", "user", foundUser);
            }
        };
    }
}
