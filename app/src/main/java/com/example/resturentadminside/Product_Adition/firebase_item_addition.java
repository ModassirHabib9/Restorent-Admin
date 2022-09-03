package com.example.resturentadminside.Product_Adition;

public class firebase_item_addition {
    private String mKey;
    private String user_ImageUrl;
    private String item_name;
    private String item_price;
    private String item_description;
    private String item_quantity;

    public firebase_item_addition() {
    }

    public firebase_item_addition(String user_ImageUrl, String item_name, String item_price,
                                  String item_description, String item_quantity) {
        this.user_ImageUrl = user_ImageUrl;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        this.item_quantity = item_quantity;
    }

    public String getUser_ImageUrl() {
        return user_ImageUrl;
    }

    public void setUser_ImageUrl(String user_ImageUrl) {
        this.user_ImageUrl = user_ImageUrl;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }
}
