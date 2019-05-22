package com.example.embadedproject;

import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.embadedproject.model.CategoryData;
import com.example.embadedproject.model.DayList;
import com.example.embadedproject.model.Fragment6Item;
import com.example.embadedproject.model.PrevCompare;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab6Fragment extends Fragment {
    private static final String TAG = "Tab4Frgment";
    Context mContext;
    RecyclerView recyclerView;
    RecyclerView.Adapter Adapter;
    RecyclerView.LayoutManager layoutManager;
    private TokenManager tokenManager;
    ArrayList items = new ArrayList<Item>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment6_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity().getApplicationContext();
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());

        tokenManager = new TokenManager(getActivity().getApplicationContext());
        String token = tokenManager.getto();


        final APIcall apiCall = RetroClass.getApICall();
        /*Call<CategoryData> foodcall = apiCall.requestCategoryPrice(token,"food");
        foodcall.enqueue(new Callback<CategoryData>() {
            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {
                if(response.code()==200){
                    items.add(new Item((R.drawable.shop1), response.body()));
                }
                else if(response.code()==500)
                    return;
            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {
                return;
            }
        });*/








        /*Call<CategoryData> sportcall = apiCall.requestCategoryPrice(token,"sports");
        sportcall.enqueue(new Callback<CategoryData>() {
            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {
                if(response.code()==200){
                    items.add(new Item((R.drawable.cloths),response.body()));
                }
                else if(response.code()==500)
                    return;
            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {
                return;

            }
        });

        Call<CategoryData> fashioncall = apiCall.requestCategoryPrice(token,"fashion");
        fashioncall.enqueue(new Callback<CategoryData>() {
            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {
                if(response.code()==200){
                    items.add(new Item((R.drawable.cloths),response.body()));
                    layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    Adapter = new MyAdapter(items, mContext);
                    recyclerView.setAdapter(Adapter);

                }
                else if(response.code()==500)
                    return;
            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {
                return;

            }
        });*/

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Call<CategoryData> foodcall = apiCall.requestCategoryPrice(token,"food");
                try {
                    CategoryData realdata=foodcall.execute().body();
                    items.add(new Item((R.drawable.shop1),realdata));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> fashioncall = apiCall.requestCategoryPrice(token,"");
                try {
                    CategoryData realdata2=fashioncall.execute().body();
                    items.add(new Item((R.drawable.cloths),realdata2));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return  null;
        }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                recyclerView.setLayoutManager(layoutManager);
                Adapter = new MyAdapter(items, mContext);
                recyclerView.setAdapter(Adapter);

            }
        }.execute();











































    }

    class MyAdapter extends RecyclerView.Adapter{
        private Context context;
        private ArrayList<Item> mItems;

        public MyAdapter(ArrayList items,Context mContext){
            mItems=items;
            context=mContext;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);
           MyViewHolder holder = new MyViewHolder(v);
            return holder;


        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            ((MyViewHolder)viewHolder).imageView.setImageResource(mItems.get(position).image);
            ((MyViewHolder)viewHolder).textView.setText(mItems.get(position).categorydayta.category);
            ((MyViewHolder)viewHolder).priceView.setText(""+mItems.get(position).categorydayta.price);


        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView priceView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.categoryimage);
            textView= (TextView)view.findViewById(R.id.categoryname);
            priceView=(TextView)view.findViewById(R.id.categoryprice);

        }
    }
    public class Item {
        int image;
       CategoryData categorydayta;

        public int getImage() {
            return image;
        }

        public CategoryData getCategorydayta() {
            return categorydayta;
        }

        public Item(int image, CategoryData data) {
            this.image=image;
            this.categorydayta=data;
        }
    }
}
