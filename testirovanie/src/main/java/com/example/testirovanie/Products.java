package com.example.testirovanie;

public class Products {
     String name_products;

     String callories;

     String grames;

     String id;
    public Products(String name_products, String callories, String grames) {
        this.name_products = name_products;
        this.callories=callories;
        this.grames=grames;
    }
    public Products(String id,String name_products, String callories, String grames) {
        this.name_products = name_products;
        this.callories=callories;
        this.grames=grames;
        this.id= id;
    }

    public Products(String name) {
        this.name_products = name;
    }

    public Products() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_products() {
        return name_products;
    }

    public void setName_products(String name_products) {
        this.name_products = name_products;
    }

    public String getCallories() {
        return callories;
    }

    public void setCallories(String callories) {
        this.callories = callories;
    }

    public String getGrames() {
        return grames;
    }

    public void setGrames(String grames) {
        this.grames = grames;
    }
}
