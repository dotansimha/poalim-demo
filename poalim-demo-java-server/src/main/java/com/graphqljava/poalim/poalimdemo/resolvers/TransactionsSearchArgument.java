package com.graphqljava.poalim.poalimdemo.resolvers;

import java.util.LinkedHashMap;

public class TransactionsSearchArgument {
    public String freeText = null;
    public String transactionType = null;

    public static TransactionsSearchArgument create(LinkedHashMap<String, String> hashMap) {
        TransactionsSearchArgument instance = new TransactionsSearchArgument();

        if (hashMap == null) {
            return instance;
        }

        instance.freeText = hashMap.get("freeText");
        instance.transactionType = hashMap.get("transactionType");

        return instance;
    }
}
