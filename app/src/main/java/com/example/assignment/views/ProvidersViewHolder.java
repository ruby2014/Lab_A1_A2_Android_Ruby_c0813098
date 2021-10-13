package com.example.assignment.views;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.helpers.DatabaseManager;
import com.example.assignment.models.Provider;

public class ProvidersViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView productsCount;


    public ProvidersViewHolder (final View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        email = (TextView) itemView.findViewById(R.id.email);
        phone = (TextView) itemView.findViewById(R.id.phone);
        productsCount = (TextView) itemView.findViewById(R.id.productsCount);
        context = itemView.getContext();
    }

    public void bindData(final Provider provider) {
        name.setText(provider.getName());
        email.setText(provider.getEmail());
        phone.setText(provider.getPhone());
        DatabaseManager databaseManager = new DatabaseManager(this.context);
        Log.d(TAG, "bindData: " + provider.getId());
        productsCount.setText("Total product count: " + databaseManager.getProductCount(provider.getId()));
    }
}