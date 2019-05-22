/*
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
    private ArrayList<String> mChildListContent = null;

    public ExpandableListView expandableListView; // ExpandableListView 변수 선언
    public CustomExpandableListViewAdapter mCustomExpListViewAdapter; // 위 ExpandableListView를 받을 CustomAdapter(2번 class에 해당)를 선언
    public ArrayList<String> parentList; // ExpandableListView의 Parent 항목이 될 List 변수 선언
    public ArrayList<ChildListData> fruit; // ExpandableListView의 Child 항목이 될 List를 각각 선언
    public ArrayList<ChildListData> vegetables;
    public ArrayList<ChildListData> etc;
    public HashMap<String, ArrayList<ChildListData>> childList; // 위 ParentList와 ChildList를 연결할 HashMap 변수 선언




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

        String category= getIntent().getStringExtra("Category");
        tokenManager = new TokenManager(getApplicationContext());
        String token = tokenManager.getto();
        showToast(category);

        */
/*final APIcall apiCall = RetroClass.getApICall();
        Call<CategoryData> categorycall = apiCall.requestCategoryPrice(token,category);
        categorycall.enqueue(new Callback<CategoryData>() {
            @Override
            public void onResponse(Call<CategoryData> call, Response<CategoryData> response) {
                if (response.code() == 200) {
                    CategoryData categoryinfo = response.body();
                    categorylistadapter adapter = new categorylistadapter(getApplicationContext(), categoryinfo);
                    ListView listview = findViewById(R.id.listview4);
                    listview.setAdapter(adapter);
                } else if (response.code() == 501) {
                    NullListAdapter adapter = new NullListAdapter(getApplicationContext());
                    ListView listview = (ListView)findViewById(R.id.listview4);
                    listview.setAdapter(adapter);
                    showToast("결과없음");
                }
            }

            @Override
            public void onFailure(Call<CategoryData> call, Throwable t) {

            }
        });*//*


        parentList = new ArrayList<String>();
        parentList.add("과일");
        parentList.add("채소");
        parentList.add("기타");


        // 앞서 ParentList에 연결할 ChildList 항목을 선언 및 입력
        ChildListData apple = new ChildListData(getResources().getDrawable(R.mipmap.apple), "사과");
        ChildListData pair = new ChildListData(getResources().getDrawable(R.mipmap.pair), "배");
        ChildListData persimmon = new ChildListData(getResources().getDrawable(R.mipmap.persimmon), "감");
        fruit = new ArrayList<ChildListData>();
        fruit.add(apple);
        fruit.add(pair);
        fruit.add(persimmon);

        ChildListData onion = new ChildListData(getResources().getDrawable(R.mipmap.onion), "양파");
        ChildListData cabbage = new ChildListData(getResources().getDrawable(R.mipmap.cabbage), "양배추");
        ChildListData potato = new ChildListData(getResources().getDrawable(R.mipmap.potato), "감자");
        ChildListData sweetPotato = new ChildListData(getResources().getDrawable(R.mipmap.sweetpotato), "고구마");
        ChildListData pumpkin = new ChildListData(getResources().getDrawable(R.mipmap.pumpkin), "호박");
        vegetables = new ArrayList<ChildListData>();
        vegetables.add(onion);
        vegetables.add(cabbage);
        vegetables.add(potato);
        vegetables.add(sweetPotato);
        vegetables.add(pumpkin);

        ChildListData seaweed = new ChildListData(getResources().getDrawable(R.mipmap.seaweed), "미역");
        ChildListData bread = new ChildListData(getResources().getDrawable(R.mipmap.bread), "빵");
        ChildListData mackerel = new ChildListData(getResources().getDrawable(R.mipmap.mackerel), "고등어");
        etc = new ArrayList<ChildListData>();
        etc.add(seaweed);
        etc.add(bread);
        etc.add(mackerel);

        // 위에서 선언한 ParentList와 ChildList를 HashMap을 통해
        childList = new HashMap<String, ArrayList<ChildListData>>();
        childList.put(parentList.get(0), fruit);
        childList.put(parentList.get(1), vegetables);
        childList.put(parentList.get(2), etc);

        // 앞서 정의해 놓은 ExpandableListView와 그 CustomAdapter를 선언 및 연결한 후
        // ExpandableListView에 대한 OnClickListener 등을 선언
        expandableListView = (ExpandableListView)findViewById(R.id.expandablelist);
        mCustomExpListViewAdapter = new CustomExpandableListViewAdapter(this, parentList, childList);
        expandableListView.setAdapter(mCustomExpListViewAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
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
        });
    }





    }


    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }
}
*/
