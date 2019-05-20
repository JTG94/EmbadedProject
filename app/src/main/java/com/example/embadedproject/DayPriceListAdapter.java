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
