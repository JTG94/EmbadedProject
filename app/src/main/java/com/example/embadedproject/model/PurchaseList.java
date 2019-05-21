package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseList {
    @SerializedName("email") @Expose public String email;
    @SerializedName("item_name") @Expose public String item_name;
    @SerializedName("price") @Expose public int price;
    @SerializedName("category") @Expose public String category;
    @SerializedName("food_category") @Expose public String food_category;

    public String getEmail() {
        return email;
    }

    public String getItem_name() {
        return item_name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getFood_category() {
        return food_category;
    }
}
