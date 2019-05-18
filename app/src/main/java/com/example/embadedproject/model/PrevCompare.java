package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrevCompare {
    @SerializedName("diff_price")
    @Expose

    public Integer diff_price;


    public Integer getMessage() {
        return diff_price;
    }


}
