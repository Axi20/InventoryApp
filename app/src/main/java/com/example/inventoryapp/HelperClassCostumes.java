package com.example.inventoryapp;

public class HelperClassCostumes {

    String costume_name;
    String costume_size;

    public HelperClassCostumes(String costume_name, String costume_size, String costume_price) {
        this.costume_name = costume_name;
        this.costume_size = costume_size;
        this.costume_price = costume_price;
    }

    String costume_price;

    public String getCostume_name() {
        return costume_name;
    }

    public void setCostume_name(String costume_name) {
        this.costume_name = costume_name;
    }

    public String getCostume_size() {
        return costume_size;
    }

    public void setCostume_size(String costume_size) {
        this.costume_size = costume_size;
    }

    public String getCostume_price() {
        return costume_price;
    }

    public void setCostume_price(String costume_price) {
        this.costume_price = costume_price;
    }


}
