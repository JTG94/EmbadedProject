package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BudgetStatus {
    @SerializedName("over")
    @Expose
    public boolean over;

    @SerializedName("month_price")
    @Expose
    public int month_price;

    @SerializedName("budget")
    @Expose
    public int budget;

    @SerializedName("diff_price")
    @Expose
    public int diff_price;

    @SerializedName("rest_day")
    @Expose
    public int rest_day;

    @SerializedName("rest_price")
    @Expose
    public int rest_price;

    public int getMonth_price() {
        return month_price;
    }

    public int getDiff_price() {
        return diff_price;
    }

    public int getRest_day() {
        return rest_day;
    }

    public int getRest_price() {
        return rest_price;
    }

    public boolean getBudget() {
        return over;
    }
}
