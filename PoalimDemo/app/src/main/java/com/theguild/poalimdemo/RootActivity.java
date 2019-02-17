package com.theguild.poalimdemo;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;
import com.theguild.poalimdemo.sample.MeQuery;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RootActivity extends AppCompatActivity implements TransactionFragment.OnListFragmentInteractionListener {
    PoalimApp app;
    ApolloCall<MeQuery.Data> meCall;
    Handler uiHandler = new Handler(Looper.getMainLooper());
    TransactionViewAdapter adapter;
    RecyclerView feedRecyclerView;
    RecyclerView.LayoutManager viewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        app = (PoalimApp) getApplication();
        viewManager = new LinearLayoutManager(this);
        feedRecyclerView = findViewById(R.id.list);
        adapter = new TransactionViewAdapter(new ArrayList<MeQuery.Transaction>(), this);
        feedRecyclerView.setAdapter(adapter);
        feedRecyclerView.setLayoutManager(viewManager);

        fetchData();
    }


    private ApolloCall.Callback<MeQuery.Data> dataCallback
            = new ApolloCallback<>(new ApolloCall.Callback<MeQuery.Data>() {
        @Override
        public void onResponse(@NotNull Response<MeQuery.Data> response) {
            adapter.setTransactions(response.data().me().accounts().get(0).transactions());
        }

        @Override
        public void onFailure(@NotNull ApolloException e) {
            Log.e("MainActivity", e.getMessage(), e);
        }
    }, uiHandler);


    private void fetchData() {
        Log.i("MainActivity", "Fetching data....");

        final MeQuery meQuery = MeQuery.builder().build();
        meCall = app
                .apolloClient()
                .query(meQuery)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST);

        meCall.enqueue(dataCallback);
    }

    @Override
    public void onListFragmentInteraction(MeQuery.Transaction item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
