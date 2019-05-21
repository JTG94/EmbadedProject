package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryData {
    @SerializedName("category") @Expose public String category;
    @SerializedName("price") @Expose public int price;
    @SerializedName("success")@Expose public boolean success;


}
