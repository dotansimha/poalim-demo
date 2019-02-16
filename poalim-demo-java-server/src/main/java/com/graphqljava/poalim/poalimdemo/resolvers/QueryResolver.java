package com.graphqljava.poalim.poalimdemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphqljava.poalim.poalimdemo.Mocks;
import com.graphqljava.poalim.poalimdemo.entities.User;
import graphql.schema.DataFetcher;

public class QueryResolver implements GraphQLQueryResolver {

    public DataFetcher<User> getMeDataFetcher() {
        return env -> Mocks.MOCK_USERS.get(0);
    }
}
