package com.example.embadedproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryData {
    @SerializedName("category") @Expose public String category;
    @SerializedName("price") @Expose public int price;
    @SerializedName("success")@Expose public boolean success;
    @SerializedName("message")@Expose public String message;
    @SerializedName("category_list_mobile")@Expose public Categorylist_mobile categorylist_mobile;

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Categorylist_mobile getCategorylist_mobile() {
        return categorylist_mobile;
    }

    public class Categorylist_mobile{

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

        public ArrayList<purchaselist> getDaylist1() {
            return daylist1;
        }

        public ArrayList<purchaselist> getDaylist2() {
            return daylist2;
        }

        public ArrayList<purchaselist> getDaylist3() {
            return daylist3;
        }

        public ArrayList<purchaselist> getDaylist4() {
            return daylist4;
        }

        public ArrayList<purchaselist> getDaylist5() {
            return daylist5;
        }

        public ArrayList<purchaselist> getDaylist6() {
            return daylist6;
        }

        public ArrayList<purchaselist> getDaylist7() {
            return daylist7;
        }

        public ArrayList<purchaselist> getDaylist8() {
            return daylist8;
        }

        public ArrayList<purchaselist> getDaylist9() {
            return daylist9;
        }

        public ArrayList<purchaselist> getDaylist10() {
            return daylist10;
        }

        public ArrayList<purchaselist> getDaylist11() {
            return daylist11;
        }

        public ArrayList<purchaselist> getDaylist12() {
            return daylist12;
        }

        public ArrayList<purchaselist> getDaylist13() {
            return daylist13;
        }

        public ArrayList<purchaselist> getDaylist14() {
            return daylist14;
        }

        public ArrayList<purchaselist> getDaylist15() {
            return daylist15;
        }

        public ArrayList<purchaselist> getDaylist16() {
            return daylist16;
        }

        public ArrayList<purchaselist> getDaylist17() {
            return daylist17;
        }

        public ArrayList<purchaselist> getDaylist18() {
            return daylist18;
        }

        public ArrayList<purchaselist> getDaylist19() {
            return daylist19;
        }

        public ArrayList<purchaselist> getDaylist20() {
            return daylist20;
        }

        public ArrayList<purchaselist> getDaylist21() {
            return daylist21;
        }

        public ArrayList<purchaselist> getDaylist22() {
            return daylist22;
        }

        public ArrayList<purchaselist> getDaylist23() {
            return daylist23;
        }

        public ArrayList<purchaselist> getDaylist24() {
            return daylist24;
        }

        public ArrayList<purchaselist> getDaylist25() {
            return daylist25;
        }

        public ArrayList<purchaselist> getDaylist26() {
            return daylist26;
        }

        public ArrayList<purchaselist> getDaylist27() {
            return daylist27;
        }

        public ArrayList<purchaselist> getDaylist28() {
            return daylist28;
        }

        public ArrayList<purchaselist> getDaylist29() {
            return daylist29;
        }

        public ArrayList<purchaselist> getDaylist30() {
            return daylist30;
        }

        public ArrayList<purchaselist> getDaylist31() {
            return daylist31;
        }

        public class purchaselist{
            @SerializedName("item_name") @Expose public String item_name;
            @SerializedName("price") @Expose public String price;
            @SerializedName("purchase_date") @Expose public String purchase_date;

            public String getItem_name() {
                return item_name;
            }

            public String getPrice() {
                return price;
            }

            public String getPurchase_date() {
                return purchase_date;
            }
        }
    }
}