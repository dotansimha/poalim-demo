package com.graphqljava.poalim.poalimdemo.resolvers;

import com.graphqljava.poalim.poalimdemo.entities.BaseTransaction;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

public class TransactionInterfaceResolver implements TypeResolver {
    @Override
    public GraphQLObjectType getType(TypeResolutionEnvironment env) {
        BaseTransaction transaction = env.getObject();

        switch (transaction.getTransactionType()) {
            case "TRANSFER":
                return env.getSchema().getObjectType("TransferTransaction");
            case "CHECK_DEPOSIT":
                return env.getSchema().getObjectType("CheckDepositTransaction");
            case "MONEY_WITHDRAW":
                return env.getSchema().getObjectType("MoneyWithdrawTransaction");
        }

        return null;
    }
}
