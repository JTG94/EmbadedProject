package com.example.embadedproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NullListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    public NullListAdapter(Context context){
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.null_list_item,parent,false);
        }

        TextView listdate_text2=(TextView)convertView.findViewById(R.id.listdate2);
        listdate_text2.setText("구매내역 없음");
        return convertView;
    }
}
