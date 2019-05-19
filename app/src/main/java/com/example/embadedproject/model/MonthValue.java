package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MonthValue {
     @SerializedName("month_price") @Expose public int month_price;
     @SerializedName("day_price") @Expose public Day_price day_price;
     @SerializedName("month_list")@Expose ArrayList<data> month_list=new ArrayList<>();


            public ArrayList<data> getMonth_list() {
                 return month_list;
            }




             public class Day_price {
         @SerializedName("1") @Expose public String day_1;
         @SerializedName("2") @Expose public String day_2;
         @SerializedName("3") @Expose public String day_3;
        @SerializedName("4") @Expose public String day_4;
         @SerializedName("5") @Expose public String day_5;
         @SerializedName("6") @Expose public String day_6;
         @SerializedName("7") @Expose public String day_7;
         @SerializedName("8") @Expose public String day_8;
         @SerializedName("9") @Expose public String day_9;
         @SerializedName("10") @Expose public String day_10;
         @SerializedName("11") @Expose public String day_11;
        @SerializedName("12") @Expose public String day_12;
         @SerializedName("13") @Expose public String day_13;
        @SerializedName("14") @Expose public String day_14;
        @SerializedName("15") @Expose public String day_15;
        @SerializedName("16") @Expose public String day_16;
         @SerializedName("17") @Expose public String day_17;
         @SerializedName("18") @Expose public String day_18;
         @SerializedName("19") @Expose public String day_19;
        @SerializedName("20") @Expose public String day_20;
        @SerializedName("21") @Expose public String day_21;
        @SerializedName("22") @Expose public String day_22;
        @SerializedName("23") @Expose public String day_23;
        @SerializedName("24") @Expose public String day_24;
         @SerializedName("25") @Expose public String day_25;
        @SerializedName("26") @Expose public String day_26;
        @SerializedName("27") @Expose public String day_27;
         @SerializedName("28") @Expose public String day_28;
        @SerializedName("29") @Expose public String day_29;
        @SerializedName("30") @Expose public String day_30;
         @SerializedName("31") @Expose public String day_31;


        public String getDay_1() { return day_1; }
        public String getDay_2() { return day_2; }
         public String getDay_3() { return day_3; }
         public String getDay_4() { return day_4; }
         public String getDay_5() { return day_5; }
         public String getDay_6() { return day_6; }
         public String getDay_7() { return day_7; }
        public String getDay_8() { return day_8; }
        public String getDay_9() { return day_9; }
        public String getDay_10() { return day_10; }
        public String getDay_11() { return day_11; }
        public String getDay_12() { return day_12; }
      public String getDay_13() { return day_13; }
         public String getDay_14() { return day_14; }
         public String getDay_15() { return day_15; }
         public String getDay_16() { return day_16; }
        public String getDay_17() { return day_17; }
        public String getDay_18() { return day_18; }
         public String getDay_19() { return day_19; }
         public String getDay_20() { return day_20; }
         public String getDay_21() { return day_21; }
        public String getDay_22() { return day_22; }
         public String getDay_23() { return day_23; }
        public String getDay_24() { return day_24; }
         public String getDay_25() { return day_25; }
        public String getDay_26() { return day_26; }
       public String getDay_27() { return day_27; }
        public String getDay_28() { return day_28; }
        public String getDay_29() { return day_29; }
       public String getDay_30() { return day_30; }
        public String getDay_31() { return day_31; }
         }


             public static class data{
        @SerializedName("id")
        @Expose
         public Integer id;
        @SerializedName("email")
        @Expose
         public String email;
         @SerializedName("item_name")
       @Expose
         public String itemName;
       @SerializedName("price")
        @Expose
        public Integer price;
        @SerializedName("category")
        @Expose
        public String category;
        @SerializedName("food_category")
         @Expose
        public String foodCategory;
        @SerializedName("purchase_date")
        @Expose
        public String purchaseDate;


               public Integer getId() {
                         return id;
                    }


                 public String getEmail() {
                        return email;
                     }

                public String getItemName() {
                         return itemName;
                    }

                 public Integer getPrice() {
                        return price;
                    }

                 public String getCategory() {
                        return category;
                    }


                 public String getFoodCategory() {
                         return foodCategory;
                  }


                 public String getPurchaseDate() {
                        return purchaseDate;
                    }
    }
     public int getMonthValue() {
                return month_price;
             }

            public Day_price getDay_price() {
                return day_price;
            }
}
