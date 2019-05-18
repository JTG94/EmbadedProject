package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Budget {
    @SerializedName("budget")
    @Expose

    public int budget;


    public int getBudget() {
        return budget;
    }

}
