package com.example.embadedproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.embadedproject.model.DayList;
import com.example.embadedproject.model.MonthValue;

import java.util.ArrayList;
import java.util.LinkedList;

public class DayPriceListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private ArrayList<DayList.Data> daypurchase;
    private int k=0;

    public DayPriceListAdapter(Context context,ArrayList<DayList.Data> daylist) {
        mInflater=LayoutInflater.from(context);
        daypurchase=daylist;

       /* daypurchase.add(0, new DayList(dayinfo.,monthinfo.day_price.day_1));
        dayPriceArray.add(1,new ListAdapter.data("2",monthinfo.day_price.day_2));
        dayPriceArray.add(2,new ListAdapter.data("3",monthinfo.day_price.day_3));
        dayPriceArray.add(3,new ListAdapter.data("4",monthinfo.day_price.day_4));
        dayPriceArray.add(4,new ListAdapter.data("5",monthinfo.day_price.day_5));
        dayPriceArray.add(5,new ListAdapter.data("6",monthinfo.day_price.day_6));
        dayPriceArray.add(6,new ListAdapter.data("7",monthinfo.day_price.day_7));
        dayPriceArray.add(7,new ListAdapter.data("8",monthinfo.day_price.day_8));
        dayPriceArray.add(8,new ListAdapter.data("9",monthinfo.day_price.day_9));
        dayPriceArray.add(9,new ListAdapter.data("10",monthinfo.day_price.day_10));
        dayPriceArray.add(10,new ListAdapter.data("11",monthinfo.day_price.day_11));
        dayPriceArray.add(11,new ListAdapter.data("12",monthinfo.day_price.day_12));
        dayPriceArray.add(12,new ListAdapter.data("13",monthinfo.day_price.day_13));
        dayPriceArray.add(13,new ListAdapter.data("14",monthinfo.day_price.day_14));
        dayPriceArray.add(14,new ListAdapter.data("15",monthinfo.day_price.day_15));
        dayPriceArray.add(15,new ListAdapter.data("16",monthinfo.day_price.day_16));
        dayPriceArray.add(16,new ListAdapter.data("17",monthinfo.day_price.day_17));
        dayPriceArray.add(17,new ListAdapter.data("18",monthinfo.day_price.day_18));
        dayPriceArray.add(18,new ListAdapter.data("19",monthinfo.day_price.day_19));
        dayPriceArray.add(19,new ListAdapter.data("20",monthinfo.day_price.day_20));
        dayPriceArray.add(20,new ListAdapter.data("21",monthinfo.day_price.day_21));
        dayPriceArray.add(21,new ListAdapter.data("22",monthinfo.day_price.day_22));
        dayPriceArray.add(22,new ListAdapter.data("23",monthinfo.day_price.day_23));
        dayPriceArray.add(23,new ListAdapter.data("24",monthinfo.day_price.day_24));
        dayPriceArray.add(24,new ListAdapter.data("25",monthinfo.day_price.day_25));
        dayPriceArray.add(25,new ListAdapter.data("26",monthinfo.day_price.day_26));
        dayPriceArray.add(26,new ListAdapter.data("27",monthinfo.day_price.day_27));
        dayPriceArray.add(27,new ListAdapter.data("28",monthinfo.day_price.day_28));
        dayPriceArray.add(28,new ListAdapter.data("29",monthinfo.day_price.day_29));
        dayPriceArray.add(29,new ListAdapter.data("30",monthinfo.day_price.day_30));
        dayPriceArray.add(30,new ListAdapter.data("31",monthinfo.day_price.day_31));*/





        /*for(int i=0;i<dayPriceArray.size();i++){

            if(dayPriceArray.get(k).price.equals("0")) {
                dayPriceArray.remove(k);
            }
            else
                k++;
        }*/

    }


    @Override
    public int getCount() {
        return  daypurchase.size();
    }

    @Override
    public DayList.Data getItem(int position) {
        return  daypurchase.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.per_day_purchase_list_item,parent,false);
        }

        TextView dayprice2_text=(TextView)convertView.findViewById(R.id.pruductPrice);
        TextView listname2_text=(TextView)convertView.findViewById(R.id.productName);
        dayprice2_text.setText(""+getItem(position).price+"원");
        listname2_text.setText(getItem(position).item_name);
        return convertView;


    }


}
