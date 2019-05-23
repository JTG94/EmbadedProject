package com.example.embadedproject;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.Button;
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
        int flag;

        tokenManager = new TokenManager(getActivity().getApplicationContext());
        String token = tokenManager.getto();


        final APIcall apiCall = RetroClass.getApICall();
        new AsyncTask<Void, Void, Void>() {
            int flag=1;
            @Override
            protected Void doInBackground(Void... voids) {
                Call<CategoryData> foodcall = apiCall.requestCategoryPrice(token,"food");

                try {
                    CategoryData realdata=foodcall.execute().body();
                    items.add(new Item((R.drawable.shop1),realdata));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> fashioncall = apiCall.requestCategoryPrice(token,"fashion");
                try {
                    CategoryData realdata2=fashioncall.execute().body();
                    items.add(new Item((R.drawable.cloths),realdata2));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> sportscall = apiCall.requestCategoryPrice(token,"sports");
                try{
                    Response<CategoryData> response=sportscall.execute();
                    if(response.code()==200) {
                        CategoryData realdata3=response.body();
                        items.add(new Item((R.drawable.sports), realdata3));
                    }
                    else
                        flag=0;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> digitalcall = apiCall.requestCategoryPrice(token,"digital");
                try{
                    Response<CategoryData> response=digitalcall.execute();
                    if(response.code()==200) {
                        CategoryData realdata4=response.body();
                        items.add(new Item((R.drawable.digital), realdata4));
                    }
                    else
                        flag=0;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> comesticcall = apiCall.requestCategoryPrice(token,"comestic");
                try{
                    Response<CategoryData> response=comesticcall.execute();
                    if(response.code()==200) {
                        CategoryData realdata4=response.body();
                        items.add(new Item((R.drawable.comestic), realdata4));
                    }
                    else
                        flag=0;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> interiorcall = apiCall.requestCategoryPrice(token,"interior");
                try{
                    Response<CategoryData> response=interiorcall.execute();
                    if(response.code()==200) {
                        CategoryData realdata4=response.body();
                        items.add(new Item((R.drawable.homeliving2), realdata4));
                    }
                    else
                        flag=0;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> kidcall = apiCall.requestCategoryPrice(token,"kid");
                try{
                    Response<CategoryData> response=kidcall.execute();
                    if(response.code()==200) {
                        CategoryData realdata4=response.body();
                        items.add(new Item((R.drawable.baby), realdata4));
                    }
                    else
                        flag=0;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> lifecall = apiCall.requestCategoryPrice(token,"life");
                try{
                    Response<CategoryData> response=lifecall.execute();
                    if(response.code()==200) {
                        CategoryData realdata4=response.body();
                        items.add(new Item((R.drawable.health), realdata4));
                    }
                    else
                        flag=0;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Call<CategoryData> culturecall = apiCall.requestCategoryPrice(token,"culture");
                try{
                    Response<CategoryData> response=culturecall.execute();
                    if(response.code()==200) {
                        CategoryData realdata4=response.body();
                        items.add(new Item((R.drawable.travel), realdata4));
                    }
                    else
                        flag=0;

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

            ((MyViewHolder)viewHolder).imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), DetailCategory.class);
                    intent.putExtra("Category",((MyViewHolder)viewHolder).textView.getText());
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public TextView priceView;
     //  private OnItemClickListener mListener;
        public ImageView cartbtn;

       // public interface OnItemClickListener{

        //   void onMoveClick(int position);
     //   }
      //  public void setOnItemClickListener(OnItemClickListener listener){mListener = listener;}


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.categoryimage);
            textView= (TextView)view.findViewById(R.id.categoryname);
            priceView=(TextView)view.findViewById(R.id.categoryprice);
            cartbtn =(ImageView)view.findViewById(R.id.btn_item_cart1);

            cartbtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                   // if(listener != null){
                     //   int position = getAdapterPosition();
                    //    if(position != RecyclerView.NO_POSITION){
                       //     listener.onMoveClick(position);


                }
            });
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
