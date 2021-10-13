package com.example.assignment.views;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.models.Product;

public class ProductsViewHolder extends RecyclerView.ViewHolder  {

    private TextView name;
    private TextView description;
    private TextView price;
    private TextView provider;



    public ProductsViewHolder (final View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        description = (TextView) itemView.findViewById(R.id.description);
        price = (TextView) itemView.findViewById(R.id.price);
        provider = (TextView) itemView.findViewById(R.id.provider);

    }

    public void bindData(final Product product) {
        name.setText(product.getName());
        description.setText(product.getDescription());
        price.setText(product.getPrice());
        provider.setText("Provider: " + product.getProvider());
    }


}