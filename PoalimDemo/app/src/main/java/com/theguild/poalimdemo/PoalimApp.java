package com.theguild.poalimdemo;

import android.app.Application;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy;
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.sql.ApolloSqlHelper;
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory;
import com.apollographql.apollo.response.CustomTypeAdapter;
import com.apollographql.apollo.response.CustomTypeValue;
import com.theguild.poalimdemo.sample.type.CustomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;

public class PoalimApp extends Application {
    private static final String BASE_URL = "http://10.0.2.2:8080/graphql";
    private static final String SQL_CACHE_NAME = "apollodata";
    private ApolloClient apolloClient;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        ApolloSqlHelper apolloSqlHelper = new ApolloSqlHelper(this, SQL_CACHE_NAME);
        NormalizedCacheFactory normalizedCacheFactory = new LruNormalizedCacheFactory(EvictionPolicy.NO_EVICTION)
                .chain(new SqlNormalizedCacheFactory(apolloSqlHelper));

        CacheKeyResolver cacheKeyResolver = new CacheKeyResolver() {
            @NotNull
            @Override
            public CacheKey fromFieldRecordSet(@NotNull ResponseField field, @NotNull Map<String, Object> recordSet) {
                String typeName = (String) recordSet.get("__typename");
                if ("User".equals(typeName)) {
                    String userKey = typeName + "." + recordSet.get("login");
                    return CacheKey.from(userKey);
                }
                if (recordSet.containsKey("id")) {
                    String typeNameAndIDKey = recordSet.get("__typename") + "." + recordSet.get("id");
                    return CacheKey.from(typeNameAndIDKey);
                }
                return CacheKey.NO_KEY;
            }

            // Use this resolver to customize the key for fields with variables: eg entry(repoFullName: $repoFullName).
            // This is useful if you want to make query to be able to resolved, even if it has never been run before.
            @NotNull
            @Override
            public CacheKey fromFieldArguments(@NotNull ResponseField field, @NotNull Operation.Variables variables) {
                return CacheKey.NO_KEY;
            }
        };

        final SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

        CustomTypeAdapter<Date> dateCustomTypeAdapter = new CustomTypeAdapter<Date>() {
            @Override public Date decode(CustomTypeValue value) {
                try {
                    return ISO8601DATEFORMAT.parse(value.value.toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override public CustomTypeValue encode(Date value) {
                return new CustomTypeValue.GraphQLString(ISO8601DATEFORMAT.format(value));
            }
        };


        apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .normalizedCache(normalizedCacheFactory, cacheKeyResolver)
                .addCustomTypeAdapter(CustomType.DATE, dateCustomTypeAdapter)
                .build();
    }

    public ApolloClient apolloClient() {
        return apolloClient;
    }
}
