package com.example.embadedproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Tab6Fragment extends Fragment {
    private static final String TAG = "Tab4Frgment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment6_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        class MyAdapter extends RecyclerView.Adapter{
            private Context context;
            private ArrayList mItems;

            private int lastPosition=-1;

            public MyAdapter(ArrayList items,Context mContext){
                mItems=items;
                context=mContext;
            }
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);
                RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(v);
                return holder;


            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
                holder.imageView.setImageResource(mItems.get(position).image);
                holder.textView.setText(mItems.get(position).imagetitle);


            }

            @Override
            public int getItemCount() {
                return mItems.size();
            }

        }
         class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textView;
            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.image);
                textView = (TextView) view.findViewById(R.id.imagetitle);
            }
        }



    }
}
