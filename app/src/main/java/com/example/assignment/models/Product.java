package com.example.assignment.models;

public class Product {
    private final String id;
    private final String name;
    private final String description;
    private final String price;
    private final String provider;

    public Product(String id, String name, String description, String price, String provider) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getProvider() {
        return provider;
    }
}
