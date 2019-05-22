package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryData {
    @SerializedName("category") @Expose public String category;
    @SerializedName("price") @Expose public int price;
    @SerializedName("success")@Expose public boolean success;
    @SerializedName("message")@Expose public String message;
    @SerializedName("sibal")@Expose public String sibal;
    @SerializedName("category_list_moblie")@Expose public ArrayList<day_list> category_list=new ArrayList<day_list>();
    public class day_list{
        @SerializedName("1") @Expose public ArrayList<purchaselist> daylist1=new ArrayList<>();
        @SerializedName("2") @Expose public ArrayList<purchaselist> daylist2=new ArrayList<>();
        @SerializedName("3") @Expose public ArrayList<purchaselist> daylist3=new ArrayList<>();
        @SerializedName("4") @Expose public ArrayList<purchaselist> daylist4=new ArrayList<>();
        @SerializedName("5") @Expose public ArrayList<purchaselist> daylist5=new ArrayList<>();
        @SerializedName("6") @Expose public ArrayList<purchaselist> daylist6=new ArrayList<>();
        @SerializedName("7") @Expose public ArrayList<purchaselist> daylist7=new ArrayList<>();
        @SerializedName("8") @Expose public ArrayList<purchaselist> daylist8=new ArrayList<>();
        @SerializedName("9") @Expose public ArrayList<purchaselist> daylist9=new ArrayList<>();
        @SerializedName("10") @Expose public ArrayList<purchaselist> daylist10=new ArrayList<>();
        @SerializedName("11") @Expose public ArrayList<purchaselist> daylist11=new ArrayList<>();
        @SerializedName("12") @Expose public ArrayList<purchaselist> daylist12=new ArrayList<>();
        @SerializedName("13") @Expose public ArrayList<purchaselist> daylist13=new ArrayList<>();
        @SerializedName("14") @Expose public ArrayList<purchaselist> daylist14=new ArrayList<>();
        @SerializedName("15") @Expose public ArrayList<purchaselist> daylist15=new ArrayList<>();
        @SerializedName("16") @Expose public ArrayList<purchaselist> daylist16=new ArrayList<>();
        @SerializedName("17") @Expose public ArrayList<purchaselist> daylist17=new ArrayList<>();
        @SerializedName("18") @Expose public ArrayList<purchaselist> daylist18=new ArrayList<>();
        @SerializedName("19") @Expose public ArrayList<purchaselist> daylist19=new ArrayList<>();
        @SerializedName("20") @Expose public ArrayList<purchaselist> daylist20=new ArrayList<>();
        @SerializedName("21") @Expose public ArrayList<purchaselist> daylist21=new ArrayList<>();
        @SerializedName("22") @Expose public ArrayList<purchaselist> daylist22=new ArrayList<>();
        @SerializedName("23") @Expose public ArrayList<purchaselist> daylist23=new ArrayList<>();
        @SerializedName("24") @Expose public ArrayList<purchaselist> daylist24=new ArrayList<>();
        @SerializedName("25") @Expose public ArrayList<purchaselist> daylist25=new ArrayList<>();
        @SerializedName("26") @Expose public ArrayList<purchaselist> daylist26=new ArrayList<>();
        @SerializedName("27") @Expose public ArrayList<purchaselist> daylist27=new ArrayList<>();
        @SerializedName("28") @Expose public ArrayList<purchaselist> daylist28=new ArrayList<>();
        @SerializedName("29") @Expose public ArrayList<purchaselist> daylist29=new ArrayList<>();
        @SerializedName("30") @Expose public ArrayList<purchaselist> daylist30=new ArrayList<>();
        @SerializedName("31") @Expose public ArrayList<purchaselist> daylist31=new ArrayList<>();


        public class purchaselist{
            @SerializedName("item_name") @Expose public String item_name;
            @SerializedName("price") @Expose public String price;
            @SerializedName("purchase_date") @Expose public String purchase_date;

        }

    }

}
