package com.theguild.poalimdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theguild.poalimdemo.TransactionFragment.OnListFragmentInteractionListener;
import com.theguild.poalimdemo.sample.MeQuery;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a item and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TransactionViewAdapter extends RecyclerView.Adapter<TransactionViewAdapter.ViewHolder> {
    private List<MeQuery.Transaction> values;
    private final OnListFragmentInteractionListener mListener;

    public TransactionViewAdapter(List<MeQuery.Transaction> items, OnListFragmentInteractionListener listener) {
        values = items;
        mListener = listener;
    }

    public void setTransactions(List<MeQuery.Transaction> transactions) {
        values = transactions;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = values.get(position);
        holder.mIdView.setText(holder.mItem.id());
        holder.mContentView.setText(holder.mItem.__typename().concat(" ").concat(String.valueOf(holder.mItem.amount())).concat("\n").concat(holder.mItem.date().toString()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public MeQuery.Transaction mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            if (mItem == null) {
                return "";
            } else {
                return mItem.id();
            }
        }
    }
}
