package com.graphqljava.poalim.poalimdemo;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.graphqljava.poalim.poalimdemo.resolvers.*;
import com.zhokhov.graphql.datetime.GraphQLDate;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {
    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildWiring() {
        QueryResolver queryResolver = new QueryResolver();
        MutationResolver mutationResolver = new MutationResolver();
        UserResolver userResolver = new UserResolver();
        ProfileResolver profileResolver = new ProfileResolver();
        CheckingAccountResolver checkingAccountResolver = new CheckingAccountResolver();
        TransferTransactionResolver transferTransactionResolver = new TransferTransactionResolver();
        MoneyWithdrawTransactionResolver moneyWithdrawTransactionResolver = new MoneyWithdrawTransactionResolver();
        CheckDepositTransactionResolver checkDepositTransactionResolver = new CheckDepositTransactionResolver();

        return RuntimeWiring.newRuntimeWiring()
                // Scalars
                .scalar(new GraphQLDate())
                // Interfaces
                .type(newTypeWiring("Account").typeResolver(new AccountInterfaceResolver()))
                .type(newTypeWiring("Transaction").typeResolver(new TransactionInterfaceResolver()))
                // Root Queries
                .type(newTypeWiring("Query").dataFetcher("me", queryResolver.me()))
                .type(newTypeWiring("Query").dataFetcher("account", queryResolver.account()))
                .type(newTypeWiring("Query").dataFetcher("transaction", queryResolver.transaction()))
                // Root Mutations
                .type(newTypeWiring("Mutation").dataFetcher("login", mutationResolver.login()))
                // User Fields
                .type(newTypeWiring("User").dataFetcher("id", userResolver.id()))
                .type(newTypeWiring("User").dataFetcher("emails", userResolver.emails()))
                .type(newTypeWiring("User").dataFetcher("profile", userResolver.profile()))
                .type(newTypeWiring("User").dataFetcher("accounts", userResolver.accounts()))
                // Profile Fields
                .type(newTypeWiring("Profile").dataFetcher("firstName", profileResolver.firstName()))
                .type(newTypeWiring("Profile").dataFetcher("lastName", profileResolver.lastName()))
                // Checking Account Fields
                .type(newTypeWiring("CheckingAccount").dataFetcher("owner", checkingAccountResolver.owner()))
                .type(newTypeWiring("CheckingAccount").dataFetcher("balance", checkingAccountResolver.balance()))
                .type(newTypeWiring("CheckingAccount").dataFetcher("transactions", checkingAccountResolver.transactions()))
                // Transfer Transaction Fields
                .type(newTypeWiring("TransferTransaction").dataFetcher("description", transferTransactionResolver.description()))
                .type(newTypeWiring("TransferTransaction").dataFetcher("source", transferTransactionResolver.source()))
                .type(newTypeWiring("TransferTransaction").dataFetcher("account", transferTransactionResolver.account()))
                // MoneyWithdrawTransaction fields
                .type(newTypeWiring("MoneyWithdrawTransaction").dataFetcher("account", moneyWithdrawTransactionResolver.account()))
                .type(newTypeWiring("MoneyWithdrawTransaction").dataFetcher("atmDetails", moneyWithdrawTransactionResolver.atmDetails()))
                // CheckDepositTransaction fields
                .type(newTypeWiring("CheckDepositTransaction").dataFetcher("account", checkDepositTransactionResolver.account()))
                .build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }
}
