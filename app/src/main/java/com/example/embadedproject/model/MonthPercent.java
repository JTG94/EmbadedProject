package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthPercent {

    @SerializedName("success") @Expose
    public boolean success;
    @SerializedName("fashion") @Expose public Fashion fashion;
    @SerializedName("cosmetic") @Expose public Cosmetic cosmetic;
    @SerializedName("digital") @Expose public Digital digital;
    @SerializedName("interior") @Expose public Interior interior;
    @SerializedName("kid") @Expose public Kid kid;
    @SerializedName("food") @Expose public Food food;
    @SerializedName("sports") @Expose public Sports sports;
    @SerializedName("life") @Expose public Life life;
    @SerializedName("culture") @Expose public Culture culture;



    public class Fashion{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getRank(){ return rank;}
        public String getPrice() { return price; }

    }
    public class Cosmetic{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }
    public class Digital{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }
    public class Interior{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }
    public class Kid{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }
    public class Food{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }
    public class Sports{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }
    public class Life{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }
    public class Culture{
        @SerializedName("percent") @Expose public String percent;
        @SerializedName("price") @Expose public String price;
        @SerializedName("rank") @Expose public String rank;
        public int getPercent() { return Integer.parseInt(percent); }
        public String getPrice() { return price; }
        public String getRank(){ return rank;}
    }



}
