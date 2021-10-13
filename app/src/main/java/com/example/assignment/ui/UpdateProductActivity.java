package com.example.assignment.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment.R;
import com.example.assignment.helpers.DatabaseManager;
import com.example.assignment.models.Product;

public class UpdateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        String name = extras.getString("name");
        String description = extras.getString("description");
        String price = extras.getString("price");
        String provider = extras.getString("provider");
        EditText productId = (EditText) findViewById(R.id.productID);
        EditText productName = (EditText) findViewById(R.id.productName);
        EditText productDescription = (EditText) findViewById(R.id.description);
        EditText productPrice = (EditText) findViewById(R.id.price);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_providers);
        spinner.setAdapter(
                new ArrayAdapter<CharSequence>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        UpdateProductActivity.this.getResources().getTextArray(R.array.providers_labels)));
        productId.setText(id);
        productId.setEnabled(false);
        productName.setText(name);
        productDescription.setText(description);
        productPrice.setText(price);
        spinner.setSelection(getIndex(spinner, provider));
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
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
        DatabaseManager databaseManager = new DatabaseManager(UpdateProductActivity.this);
        databaseManager.updateProduct(product);
        Toast.makeText(getApplicationContext(), "Product updated!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(UpdateProductActivity.this, ProductsActivity.class);
        UpdateProductActivity.this.startActivity(intent);
    }
}