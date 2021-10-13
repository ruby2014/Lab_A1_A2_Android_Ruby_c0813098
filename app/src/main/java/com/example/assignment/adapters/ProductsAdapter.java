package com.example.assignment.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.views.ProductsViewHolder;
import com.example.assignment.R;
import com.example.assignment.models.Product;

import java.util.ArrayList;
import java.util.Random;

public class ProductsAdapter extends RecyclerView.Adapter {

    private ArrayList<Product> products;
    private LayoutInflater inflater;
    ArrayList<Product> productsCopy;

    public ProductsAdapter(Context context, ArrayList<Product> products) {
        this.inflater = LayoutInflater.from(context);
        this.products = products;
        this.productsCopy = new ArrayList<>(products);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.products_list, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.itemView.setBackgroundColor(currentColor);
        ((ProductsViewHolder) holder).bindData(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void filter(String text) {
        products.clear();
        if(text.isEmpty()){
            products.addAll(productsCopy);
        } else{
            text = text.toLowerCase();
            for(Product product: productsCopy){
                if(product.getName().toLowerCase().contains(text)
                        || product.getDescription().toLowerCase().contains(text)
                        || product.getProvider().toLowerCase().contains(text)){
                    products.add(product);
                }
            }
        }
        notifyDataSetChanged();
    }

    public ArrayList<Product> getData() {
        return products;
    }

    public void removeItem(int pos) {
        products.remove(pos);
        productsCopy.remove(pos);
        notifyItemRemoved(pos);
    }

    public void restoreItem(Product item, int pos) {
        products.add(pos, item);
        productsCopy.add(pos, item);
        notifyItemInserted(pos);
    }
}