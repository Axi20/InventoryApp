package com.example.inventoryapp;

public class HelperClassRents {

    String rent_costume_name;

    public HelperClassRents(String rent_costume_name, String rent_customer_name, String rent_paying_options, String rent_customer_email, int rent_days, int rent_final_price) {
        this.rent_costume_name = rent_costume_name;
        this.rent_customer_name = rent_customer_name;
        this.rent_paying_options = rent_paying_options;
        this.rent_customer_email = rent_customer_email;
        this.rent_days = rent_days;
        this.rent_final_price = rent_final_price;
    }

    String rent_customer_name;
    String rent_paying_options;
    String rent_customer_email;
    int rent_days;
    int rent_final_price;

    public String getRent_costume_name() {return rent_costume_name;}

    public void setRent_costume_name(String rent_costume_name) {this.rent_costume_name = rent_costume_name;}

    public String getRent_customer_name() {return rent_customer_name;}

    public void setRent_customer_name(String rent_customer_name) {this.rent_customer_name = rent_customer_name;}

    public String getRent_customer_email() {return rent_customer_email;}

    public void setRent_customer_email(String rent_customer_email) {this.rent_customer_email = rent_customer_email;}

    public int getRent_days() {return rent_days;}

    public void setRent_days(int rent_days) {this.rent_days = rent_days;}

    public int getFinal_price(){return rent_final_price;}

    public void setFinal_price(int final_price) {this.rent_final_price = final_price;}

    public String getPaying_options() {return rent_paying_options;}

    public void setPaying_options(String paying_options) {this.rent_paying_options = paying_options;}

    public HelperClassRents() {}


}
