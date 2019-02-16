package com.graphqljava.poalim.poalimdemo.resolvers;

import java.util.LinkedHashMap;

public class TransactionsFilterArgument {
    public Integer skip = null;
    public Integer limit = null;

    public static TransactionsFilterArgument create(LinkedHashMap<String, Integer> hashMap) {
        TransactionsFilterArgument instance = new TransactionsFilterArgument();

        if (hashMap == null) {
            return instance;
        }

        instance.skip = hashMap.get("skip");
        instance.limit = hashMap.get("limit");

        return instance;
    }
}
