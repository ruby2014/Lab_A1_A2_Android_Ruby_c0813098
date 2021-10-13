package com.example.assignment.adapters;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.ui.ProductsActivity;
import com.example.assignment.views.ProvidersViewHolder;
import com.example.assignment.R;
import com.example.assignment.models.Provider;

import java.util.ArrayList;
import java.util.Random;

public class ProvidersAdapter extends RecyclerView.Adapter {

    private final Context context;
    private ArrayList<Provider> providers;
    private LayoutInflater inflater;
    ArrayList<Provider> providersCopy;
    private RecyclerView.ViewHolder holder;
    private int position;

    public ProvidersAdapter(Context context, ArrayList<Provider> providers) {
        this.inflater = LayoutInflater.from(context);
        this.providers = providers;
        this.providersCopy = new ArrayList<>(providers);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.providers_list, parent, false);
        return new ProvidersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.holder = holder;
        this.position = position;
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.itemView.setBackgroundColor(currentColor);
        ((ProvidersViewHolder) holder).bindData(providers.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("Provider", providers.get(holder.getAdapterPosition()).getName());
                Log.d(TAG, "onClick: " +  providers.get(holder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

    public void filter(String text) {
        providers.clear();
        if(text.isEmpty()){
            providers.addAll(providersCopy);
        } else{
            text = text.toLowerCase();
            for(Provider provider: providersCopy){
                if(provider.getName().toLowerCase().contains(text)){
                    providers.add(provider);
                }
            }
        }
        notifyDataSetChanged();
    }

    public ArrayList<Provider> getData() {
        return providers;
    }

    public void removeItem(int pos) {
        providers.remove(pos);
        providersCopy.remove(pos);
        notifyItemRemoved(pos);
    }

    public void restoreItem(Provider item, int pos) {
        providers.add(pos, item);
        providersCopy.add(pos, item);
        notifyItemInserted(pos);
    }
}