package com.example.embadedproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.embadedproject.model.MonthValue;

import java.util.HashMap;
import java.util.LinkedList;

public class ListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private int month;
    private LinkedList<data> dayPriceArray=new LinkedList<>();
    private int k=0;

    public ListAdapter(Context context,int month, MonthValue monthinfo) {
        mInflater=LayoutInflater.from(context);
        this.month=month;

        dayPriceArray.add(0,new data("1",monthinfo.day_price.day_1));
        dayPriceArray.add(1,new data("2",monthinfo.day_price.day_2));
        dayPriceArray.add(2,new data("3",monthinfo.day_price.day_3));
        dayPriceArray.add(3,new data("4",monthinfo.day_price.day_4));
        dayPriceArray.add(4,new data("5",monthinfo.day_price.day_5));
        dayPriceArray.add(5,new data("6",monthinfo.day_price.day_6));
        dayPriceArray.add(6,new data("7",monthinfo.day_price.day_7));
        dayPriceArray.add(7,new data("8",monthinfo.day_price.day_8));
        dayPriceArray.add(8,new data("9",monthinfo.day_price.day_9));
        dayPriceArray.add(9,new data("10",monthinfo.day_price.day_10));
        dayPriceArray.add(10,new data("11",monthinfo.day_price.day_11));
        dayPriceArray.add(11,new data("12",monthinfo.day_price.day_12));
        dayPriceArray.add(12,new data("13",monthinfo.day_price.day_13));
        dayPriceArray.add(13,new data("14",monthinfo.day_price.day_14));
        dayPriceArray.add(14,new data("15",monthinfo.day_price.day_15));
        dayPriceArray.add(15,new data("16",monthinfo.day_price.day_16));
        dayPriceArray.add(16,new data("17",monthinfo.day_price.day_17));
        dayPriceArray.add(17,new data("18",monthinfo.day_price.day_18));
        dayPriceArray.add(18,new data("19",monthinfo.day_price.day_19));
        dayPriceArray.add(19,new data("20",monthinfo.day_price.day_20));
        dayPriceArray.add(20,new data("21",monthinfo.day_price.day_21));
        dayPriceArray.add(21,new data("22",monthinfo.day_price.day_22));
        dayPriceArray.add(22,new data("23",monthinfo.day_price.day_23));
        dayPriceArray.add(23,new data("24",monthinfo.day_price.day_24));
        dayPriceArray.add(24,new data("25",monthinfo.day_price.day_25));
        dayPriceArray.add(25,new data("26",monthinfo.day_price.day_26));
        dayPriceArray.add(26,new data("27",monthinfo.day_price.day_27));
        dayPriceArray.add(27,new data("28",monthinfo.day_price.day_28));
        dayPriceArray.add(28,new data("29",monthinfo.day_price.day_29));
        dayPriceArray.add(29,new data("30",monthinfo.day_price.day_30));
        dayPriceArray.add(30,new data("31",monthinfo.day_price.day_31));


        for(int i=0;i<31;i++){

            if(dayPriceArray.get(k).price.equals("0")) {
                dayPriceArray.remove(k);
            }
            else
                k++;
        }

    }


    @Override
    public int getCount() {
        return  dayPriceArray.size();
    }

    @Override
    public data getItem(int position) {
        return  dayPriceArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.dayprice_list_item,parent,false);
        }

        TextView dayprice_text=(TextView)convertView.findViewById(R.id.dayprice);
        TextView listdate_text=(TextView)convertView.findViewById(R.id.listdate);
        dayprice_text.setText(getItem(position).price+"원");
        listdate_text.setText(""+month+"월"+getItem(position).day+"일");
        return convertView;


    }
    public class data{
        public String day;
        public String price;
        public data(String day,String price){
            this.day=day;
            this.price=price;
        }

        public String getDay() {
            return day;
        }

        public String getPrice() {
            return price;
        }
    }
}
