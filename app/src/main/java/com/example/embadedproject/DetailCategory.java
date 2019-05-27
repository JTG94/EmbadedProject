package com.example.embadedproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.embadedproject.model.CategoryData;
import com.example.embadedproject.model.DayList;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCategory extends AppCompatActivity {
    private TokenManager tokenManager;
    private ArrayList<String> mGroupList = null;
    private ArrayList<String> mChildList = null;
    private ExpandableListView mListView;


    public ExpandableListView expandableListView; // ExpandableListView 변수 선언
    public CustomExpandableListViewAdapter mCustomExpListViewAdapter; // 위 ExpandableListView를 받을 CustomAdapter(2번 class에 해당)를 선언
    public ArrayList<String> parentList; // ExpandableListView의 Parent 항목이 될 List 변수 선언
    public ArrayList<CategoryData> fruit; // ExpandableListView의 Child 항목이 될 List를 각각 선언





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

        String category= getIntent().getStringExtra("Category");
        tokenManager = new TokenManager(getApplicationContext());
        String token = tokenManager.getto();


        final APIcall apiCall = RetroClass.getApICall();
        Call<CategoryData> categorycall = apiCall.requestCategoryPrice(token,category);
        categorycall.enqueue(new Callback<CategoryData>() {
            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {
                if (response.code() == 200) {
                    CategoryData categoryinfo = response.body();

                    mCustomExpListViewAdapter = new CustomExpandableListViewAdapter(getApplicationContext(),categoryinfo);
                    mListView = (ExpandableListView) findViewById(R.id.elv_list);
                    mListView.setAdapter(mCustomExpListViewAdapter);



                } else if (response.code() == 501) {

                }
            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {

            }
        });



        /*expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });*/
    }



    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }
}