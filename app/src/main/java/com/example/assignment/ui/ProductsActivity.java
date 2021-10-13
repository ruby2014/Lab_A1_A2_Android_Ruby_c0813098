package com.example.assignment.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.SwipeHelper;
import com.example.assignment.adapters.ProductsAdapter;
import com.example.assignment.helpers.AddProductActivity;
import com.example.assignment.helpers.DatabaseManager;
import com.example.assignment.models.Product;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    String providerQuery;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Bundle extras = getIntent().getExtras();
        try {
            providerQuery = extras.getString("Provider");
        } catch (NullPointerException exception) {
            // pass
        }
        Log.d(TAG, "onClick: " +  providerQuery);
        DatabaseManager databaseManager = new DatabaseManager(ProductsActivity.this);
        products = databaseManager.getAllProducts();
        ProductsAdapter adapter = new ProductsAdapter(ProductsActivity.this, products);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.simple_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
//        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                // Toast.makeText(ProductsActivity.this, "on Move", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                Toast.makeText(ProductsActivity.this, "Product removed!", Toast.LENGTH_SHORT).show();
//                // Remove swiped item from list and notify the RecyclerView
//                int position = viewHolder.getAdapterPosition();
//                products.remove(position);
//                adapter.notifyDataSetChanged();
//
//            }
//
//        };
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
        SearchView productQuery = (SearchView) findViewById(R.id.productQuery);

        productQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.filter(query);
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
                                final Product item = adapter.getData().get(pos);
                                adapter.removeItem(pos);
                                databaseManager.removeProduct(item.getId());
                                Snackbar snackbar = Snackbar.make(recyclerView, "Product removed!", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                ));
                underlayButtons.add(new SwipeHelper.UnderlayButton(
                        "Update",
                        0,
                        Color.parseColor("#FF9502"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                final Product product = adapter.getData().get(pos);
                                Toast.makeText(getApplicationContext(), "Changing product information.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ProductsActivity.this, UpdateProductActivity.class);
                                intent.putExtra("id", product.getId());
                                intent.putExtra("name", product.getName());
                                intent.putExtra("description", product.getDescription());
                                intent.putExtra("price", product.getPrice());
                                intent.putExtra("provider", product.getProvider());
                                ProductsActivity.this.startActivity(intent);
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
                                Toast.makeText(getApplicationContext(), "Checking product details.", Toast.LENGTH_LONG).show();
                            }
                        }
                ));
            }
        };
        swipeHelper.attachToRecyclerView(recyclerView);

        if (providerQuery != null) {
            productQuery.setQuery("", false);
            productQuery.setQuery(providerQuery, false);
        }


    }

    public void checkProducts(View view) {
        Intent intent = new Intent(ProductsActivity.this, ProductsActivity.class);
        ProductsActivity.this.startActivity(intent);
    }

    public void checkProviders(View view) {
        Intent intent = new Intent(ProductsActivity.this, ProvidersActivity.class);
        ProductsActivity.this.startActivity(intent);
    }

    public void addProduct(View view) {
        Intent intent = new Intent(ProductsActivity.this, AddProductActivity.class);
        ProductsActivity.this.startActivity(intent);
    }


}