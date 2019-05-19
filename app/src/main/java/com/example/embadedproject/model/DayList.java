package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DayList {
    @SerializedName("day_price")
    @Expose
    public int day_price;

    @SerializedName("day_list") @Expose
    ArrayList<Data> day_list=new ArrayList<>();

    public static class Data{
        @SerializedName("id") @Expose
        public int id;

        @SerializedName("email") @Expose
        public String email;

        @SerializedName("item_name") @Expose
        public String item_name;

        @SerializedName("price") @Expose
        public int price;

        @SerializedName("category") @Expose
        public String category;

        @SerializedName("food_category") @Expose
        public String food_category;

        @SerializedName("purchase_date") @Expose
        public String puchase_date;

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
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

        public String getPuchase_date() {
            return puchase_date;
        }

        public String getItem_name() {
            return item_name;
        }

        public int getItem_price() {
            return price;
        }
    }

    public int getDay_price() {
        return day_price;
    }

    public ArrayList<Data> getDay_list() {
        return day_list;
    }
}
