package com.example.assignment.helpers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment.ui.ProductsActivity;
import com.example.assignment.ui.ProvidersActivity;
import com.example.assignment.R;
import com.example.assignment.models.Product;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_providers);
        spinner.setAdapter(
                new ArrayAdapter<CharSequence>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        AddProductActivity.this.getResources().getTextArray(R.array.providers_labels)));
    }

    public void checkProducts(View view) {
        Intent intent = new Intent(AddProductActivity.this, ProductsActivity.class);
        AddProductActivity.this.startActivity(intent);
    }

    public void checkProviders(View view) {
        Intent intent = new Intent(AddProductActivity.this, ProvidersActivity.class);
        AddProductActivity.this.startActivity(intent);
    }

    public void pushProduct(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_providers);
        String provider = getResources().getStringArray(R.array.providers)[spinner.getSelectedItemPosition()];
        String providerID;
        switch (provider) {
            case "Smith&Smith":
                providerID = "PROV1";
                break;
            case "Deaken":
                providerID = "PROV2";
                break;
            case "Wells":
                providerID = "PROV3";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + provider);
        }
        // Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();
        String productID = String.valueOf(((EditText) findViewById(R.id.productID)).getText());
        String productName = String.valueOf(((EditText) findViewById(R.id.productName)).getText());
        String description = String.valueOf(((EditText) findViewById(R.id.description)).getText());
        String price = String.valueOf(((EditText) findViewById(R.id.price)).getText());
        Product product = new Product(productID, productName, description, price, providerID);
        DatabaseManager databaseManager = new DatabaseManager(AddProductActivity.this);
        databaseManager.addNewProduct(product);
        Toast.makeText(getApplicationContext(), "Product added!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddProductActivity.this, ProductsActivity.class);
        AddProductActivity.this.startActivity(intent);
    }
}