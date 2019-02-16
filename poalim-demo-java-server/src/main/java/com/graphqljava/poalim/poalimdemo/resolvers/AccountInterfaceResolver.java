package com.graphqljava.poalim.poalimdemo.resolvers;

import com.graphqljava.poalim.poalimdemo.entities.BaseAccount;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

public class AccountInterfaceResolver implements TypeResolver {
    @Override
    public GraphQLObjectType getType(TypeResolutionEnvironment env) {
        BaseAccount account = env.getObject();

        if (account.getAccountType().equals("CHECKING")) {
            return env.getSchema().getObjectType("CheckingAccount");
        } else if (account.getAccountType().equals("DEPOSIT")) {
            return env.getSchema().getObjectType("DepositAccount");
        }

        return null;
    }
}
