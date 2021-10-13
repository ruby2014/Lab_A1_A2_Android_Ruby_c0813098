package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.assignment.helpers.AddProductActivity;
import com.example.assignment.ui.ProductsActivity;
import com.example.assignment.ui.ProvidersActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkProducts(View view) {
        Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void checkProviders(View view) {
        Intent intent = new Intent(MainActivity.this, ProvidersActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void addProduct(View view) {
        Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
        MainActivity.this.startActivity(intent);
    }
}