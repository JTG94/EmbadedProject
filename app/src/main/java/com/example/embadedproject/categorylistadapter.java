/*
package com.example.embadedproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.embadedproject.model.CategoryData;
import com.example.embadedproject.model.MonthValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class categorylistadapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private int month;
    private int k=0;
    ArrayList<CategoryData.day_list> items=new ArrayList<>();

    public categorylistadapter(Context context, CategoryData categoryinfo) {
        mInflater=LayoutInflater.from(context);

        items=categoryinfo.category_list;

    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public CategoryData.day_list getItem(int position) {
        return  items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.detail_list_item,parent,false);
        }

        TextView categorydate_text=(TextView)convertView.findViewById(R.id.textView13);
        categorydate_text.setText(getItem(position).daylist1.get(position).purchase_date);
        return convertView;


    }

}*/
