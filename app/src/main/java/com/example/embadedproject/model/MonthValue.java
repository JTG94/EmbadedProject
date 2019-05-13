package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthValue {
    @SerializedName("month_price")
    @Expose

    public Integer month_price;



    public Integer getMonthValue(){
        return month_price;
    }
    public void setMonthValue(String month_price){
       // this.month_price=month_price;
    }

    public MonthValue() {
    }
}
