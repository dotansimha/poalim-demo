package com.graphqljava.poalim.poalimdemo.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.poalim.poalimdemo.entities.User;
import graphql.schema.DataFetcher;

public class ProfileResolver implements GraphQLResolver<User> {
    public DataFetcher<String> firstName() {
        return environment -> environment.<User>getSource().getFirstName();
    }

    public DataFetcher<String> lastName() {
        return environment -> environment.<User>getSource().getLastName();
    }
}
