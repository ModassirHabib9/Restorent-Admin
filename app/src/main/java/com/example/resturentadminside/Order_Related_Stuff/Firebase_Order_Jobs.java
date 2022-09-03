package com.example.resturentadminside.Order_Related_Stuff;

import com.google.firebase.database.Exclude;

public class Firebase_Order_Jobs {
    private String mKey;
    public String order_item;
    public String order_price;
    public String order_quantity;
    public String order_nan_quantity;
    public String order_cold_drink_quanity;
    public String order_resturent_name;
    public String order_mobile_number;
    public String order_additional_notes;
    public String order_location;
    public String order_coments;
    public String order_type;
    public Firebase_Order_Jobs() {

    }

    public Firebase_Order_Jobs(String order_item, String order_price,
                               String order_quantity, String order_nan_quantity,
                               String order_cold_drink_quanity, String order_resturent_name,
                               String order_mobile_number, String order_additional_notes,
                               String order_location, String order_coments, String order_type) {
        this.order_item = order_item;
        this.order_price = order_price;
        this.order_quantity = order_quantity;
        this.order_nan_quantity = order_nan_quantity;
        this.order_cold_drink_quanity = order_cold_drink_quanity;
        this.order_resturent_name = order_resturent_name;
        this.order_mobile_number = order_mobile_number;
        this.order_additional_notes = order_additional_notes;
        this.order_location = order_location;
        this.order_coments = order_coments;
        this.order_type = order_type;
    }

    public String getOrder_item() {
        return order_item;
    }

    public void setOrder_item(String order_item) {
        this.order_item = order_item;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(String order_quantity) {
        this.order_quantity = order_quantity;
    }

    public String getOrder_nan_quantity() {
        return order_nan_quantity;
    }

    public void setOrder_nan_quantity(String order_nan_quantity) {
        this.order_nan_quantity = order_nan_quantity;
    }

    public String getOrder_cold_drink_quanity() {
        return order_cold_drink_quanity;
    }

    public void setOrder_cold_drink_quanity(String order_cold_drink_quanity) {
        this.order_cold_drink_quanity = order_cold_drink_quanity;
    }

    public String getOrder_resturent_name() {
        return order_resturent_name;
    }

    public void setOrder_resturent_name(String order_resturent_name) {
        this.order_resturent_name = order_resturent_name;
    }

    public String getOrder_mobile_number() {
        return order_mobile_number;
    }

    public void setOrder_mobile_number(String order_mobile_number) {
        this.order_mobile_number = order_mobile_number;
    }

    public String getOrder_additional_notes() {
        return order_additional_notes;
    }

    public void setOrder_additional_notes(String order_additional_notes) {
        this.order_additional_notes = order_additional_notes;
    }

    public String getOrder_location() {
        return order_location;
    }

    public void setOrder_location(String order_location) {
        this.order_location = order_location;
    }

    public String getOrder_coments() {
        return order_coments;
    }

    public void setOrder_coments(String order_coments) {
        this.order_coments = order_coments;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}