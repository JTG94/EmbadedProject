package com.example.embadedproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.embadedproject.model.MonthPercent;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab5Fragment extends Fragment {
    private static final String TAG = "Tab4Frgment";
    private TokenManager tokenManager;
    PieChart pieChart;

    public static int fasion_per;
    public static int cosmetic_per;
    public static int digital_per;
    public static int interior_per;
    public static int kid_per;
    public static int food_per;
    public static int sports_per;
    public static int life_per;
    public static int culture_per;

    public static String fashon_price = "";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment5_layout,container,false);

        return view;

    }


    @Override
    public void onViewCreated(@Nullable View view,@Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        //부여받은 토큰 불러오기
        tokenManager = new TokenManager(getActivity().getApplicationContext());
        String token = tokenManager.getto();

        final APIcall apiCall = RetroClass.getApICall();
        Call<MonthPercent> percentCall = apiCall.PercentList(token);


        percentCall.enqueue(new Callback<MonthPercent>() {
            @Override
            public void onResponse(Call<MonthPercent> call, Response<MonthPercent> response) {
                if (response.code() == 200) {
                    MonthPercent mp = response.body();
                    fasion_per = mp.fashion.getPercent();
                    cosmetic_per = mp.cosmetic.getPercent();
                    digital_per = mp.digital.getPercent();
                    interior_per = mp.interior.getPercent();
                    kid_per = mp.kid.getPercent();
                    food_per = mp.food.getPercent();
                    sports_per = mp.sports.getPercent();
                    life_per = mp.life.getPercent();
                    culture_per = mp.culture.getPercent();

                    fashon_price = mp.fashion.getPrice().toString();

                    Rank_ListAdapter adapter = new Rank_ListAdapter(getActivity().getApplicationContext(), mp);
                    ListView listView = getView().findViewById(R.id.RankList_View);
                    listView.setAdapter(adapter);

                    pieChart = (PieChart) getView().findViewById(R.id.piechart);
                    pieChart.setUsePercentValues(true);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setExtraOffsets(5, 10, 5, 5);
                    pieChart.setDragDecelerationFrictionCoef(0.95f);
                    pieChart.setDrawHoleEnabled(false);
                    pieChart.setHoleColor(Color.WHITE);
                    pieChart.setTransparentCircleRadius(61f);

                    ArrayList<PieEntry> yValues = new ArrayList<>();
                    if (food_per != 0) {
                        yValues.add(new PieEntry(food_per, "식품"));
                    }
                    if (fasion_per != 0) {
                        yValues.add(new PieEntry(fasion_per, "패션"));
                    }
                    if (cosmetic_per != 0) {
                        yValues.add(new PieEntry(cosmetic_per, "화장품/미용"));
                    }
                    if (digital_per != 0) {
                        yValues.add(new PieEntry(digital_per, "디지털/가전"));
                    }
                    if (interior_per != 0) {
                        yValues.add(new PieEntry(interior_per, "가구/인테리어"));
                    }
                    if (kid_per != 0) {
                        yValues.add(new PieEntry(kid_per, "출산/육아"));
                    }
                    if (sports_per != 0) {
                        yValues.add(new PieEntry(sports_per, "스포츠/레저"));
                    }
                    if (life_per != 0) {
                        yValues.add(new PieEntry(life_per, "생활/건강"));
                    }
                    if (culture_per != 0) {
                        yValues.add(new PieEntry(culture_per, "여행/문화"));
                    }
                    pieChart.animateY(1300, Easing.EasingOption.EaseInOutCubic);

                    PieDataSet dataSet = new PieDataSet(yValues, "분류별 점유율");
                    dataSet.setSliceSpace(3f);
                    dataSet.setSelectionShift(5f);
                    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                    PieData data = new PieData((dataSet));
                    data.setValueTextSize(15f);
                    data.setValueTextColor(Color.YELLOW);

                    pieChart.setData(data);
                }else if(response.code()==501){
                    showToast("결과 없음");
                }
            }

            @Override
            public void onFailure(Call<MonthPercent> call, Throwable t) {

            }
        });



    }

    void showToast(String msg)
    {
        Toast.makeText(getActivity(),""+msg, Toast.LENGTH_LONG).show();
    }
}