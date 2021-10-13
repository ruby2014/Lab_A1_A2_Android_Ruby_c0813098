package com.example.assignment.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.SwipeHelper;
import com.example.assignment.adapters.ProvidersAdapter;
import com.example.assignment.helpers.AddProductActivity;
import com.example.assignment.helpers.DatabaseManager;
import com.example.assignment.models.Provider;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ProvidersActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_providers);
        DatabaseManager databaseManager = new DatabaseManager(ProvidersActivity.this);
        ArrayList<Provider> providers = databaseManager.getAllProviders();
        ProvidersAdapter adapter = new ProvidersAdapter(ProvidersActivity.this, providers);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.simple_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        SearchView providerQuery = (SearchView) findViewById(R.id.providerQ);
        providerQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
        SwipeHelper swipeHelper = new SwipeHelper(this) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Remove",
                        0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(final int pos) {
                                final Provider item = adapter.getData().get(pos);
                                adapter.removeItem(pos);
                                databaseManager.removeProvider(item.getId());
                                Snackbar snackbar = Snackbar.make(recyclerView, "Provider removed!", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                ));
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Locate",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                final Provider provider = adapter.getData().get(pos);
                                Toast.makeText(getApplicationContext(), "Location information.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ProvidersActivity.this, ProviderLocationActivity.class);
                                intent.putExtra("Provider", provider.getId());
                                ProvidersActivity.this.startActivity(intent);
                            }
                        }
                ));
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Info",
                        0,
                        Color.parseColor("#C7C7CB"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(getApplicationContext(), "Checking provider details.", Toast.LENGTH_LONG).show();
                            }
                        }
                ));
            }
        };
        swipeHelper.attachToRecyclerView(recyclerView);
    }

    public void checkProducts(View view) {
        Intent intent = new Intent(ProvidersActivity.this, ProductsActivity.class);
        ProvidersActivity.this.startActivity(intent);
    }

    public void checkProviders(View view) {
        Intent intent = new Intent(ProvidersActivity.this, ProvidersActivity.class);
        ProvidersActivity.this.startActivity(intent);
    }

    public void addProduct(View view) {
        Intent intent = new Intent(ProvidersActivity.this, AddProductActivity.class);
        ProvidersActivity.this.startActivity(intent);
    }
}