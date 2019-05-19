package com.example.embadedproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.model.Budget;
import com.example.embadedproject.model.MonthValue;
import com.example.embadedproject.model.PrevCompare;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab1Fragment extends Fragment {
    private TokenManager tokenManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@Nullable View view,@Nullable Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);
        TextView monthtext=getView().findViewById(R.id.MainMonth);
        TextView yeartext=getView().findViewById(R.id.yearSave);
        tokenManager = new TokenManager(getActivity().getApplicationContext());
        String token = tokenManager.getto();


        ImageButton prevmonth=getView().findViewById(R.id.prevmonth);
        prevmonth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String curMonth=monthtext.getText().toString();
                String curYear=yeartext.getText().toString();
                Integer intMonth=new Integer(curMonth);
                Integer intYear=new Integer(curYear);

                if(intMonth==1) {
                    intMonth = 12;
                    intYear--;
                }
                else
                    intMonth--;

                monthtext.setText(intMonth.toString());
                yeartext.setText(intYear.toString());

                final APIcall apiCall = RetroClass.getApICall();
                Call<MonthValue> monthcall=apiCall.MontlyList(token,intYear,intMonth);
                monthcall.enqueue(new Callback<MonthValue>() {
                    @Override
                    public void onResponse(Call<MonthValue> call, Response<MonthValue> response) {
                        TextView monthpricetext = getView().findViewById(R.id.MonthPrice);
                        TextView monthText = getView().findViewById(R.id.MainMonth);
                        Integer intMonth2 = new Integer(monthtext.getText().toString());
                        if(response.code()==200) {

                            MonthValue monthinfo = response.body();
                            ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext(), intMonth2, monthinfo);
                            ListView listview = getView().findViewById(R.id.listVIew);
                            listview.setAdapter(adapter);

                            monthText.setText(intMonth2.toString());

                            monthpricetext.setText("" + monthinfo.month_price);
                            TextView yeartext = getView().findViewById(R.id.yearSave);
                        }
                        else if(response.code()==501) {
                            monthpricetext.setText("0");
                            showToast("결과 없음");
                        }

                    }

                    @Override
                    public void onFailure(Call<MonthValue> call, Throwable t) {
                        showToast("네트워크 상태를 확인하세요");

                    }
                });
                Call<PrevCompare> Prevcall=apiCall.PrevCompareCall(token,intMonth);
                Prevcall.enqueue(new Callback<PrevCompare>() {
                    @Override
                    public void onResponse(Call<PrevCompare> call, Response<PrevCompare> response) {
                        TextView yeartext=getView().findViewById(R.id.yearSave);
                        Integer intYear=new Integer(yeartext.getText().toString());
                        TextView prevText=getView().findViewById(R.id.prevText);
                        PrevCompare prv=response.body();
                        if(intYear!=LocalDate.now().getYear())
                            prevText.setText("전 월 대비 지출조회는 현재 년도만 지원합니다");

                        else prevText.setText(prv.diff_price.toString()+"원");

                    }

                    @Override
                    public void onFailure(Call<PrevCompare> call, Throwable t) {
                        showToast("네트워크 상태를 확인하세요");

                    }
                });

            }
        });

        ImageButton nextmonth=getView().findViewById(R.id.nextMonth);
        nextmonth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String curMonth=monthtext.getText().toString();
                String curYear=yeartext.getText().toString();
                Integer intMonth=new Integer(curMonth);
                Integer intYear=new Integer(curYear);

                if(intMonth==12) {
                    intMonth = 1;
                    intYear++;
                }
                else
                    intMonth++;

                monthtext.setText(intMonth.toString());
                yeartext.setText(intYear.toString());

                final APIcall apiCall = RetroClass.getApICall();
                Call<MonthValue> monthcall=apiCall.MontlyList(token,intYear,intMonth);
                monthcall.enqueue(new Callback<MonthValue>() {
                    @Override
                    public void onResponse(Call<MonthValue> call, Response<MonthValue> response) {
                        if(response.code()==200) {
                            Integer intMonth2 = new Integer(monthtext.getText().toString());
                            MonthValue monthinfo = response.body();
                            ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext(), intMonth2, monthinfo);
                            ListView listview = getView().findViewById(R.id.listVIew);
                            listview.setAdapter(adapter);
                            TextView monthText = getView().findViewById(R.id.MainMonth);
                            monthText.setText(intMonth2.toString());
                            TextView monthpricetext = getView().findViewById(R.id.MonthPrice);
                            monthpricetext.setText("" + monthinfo.month_price);
                            TextView yeartext = getView().findViewById(R.id.yearSave);
                        }
                        else if(response.code()==501)
                            showToast("결과 없음");

                    }

                    @Override
                    public void onFailure(Call<MonthValue> call, Throwable t) {

                    }
                });
                Call<PrevCompare> Prevcall=apiCall.PrevCompareCall(token,intMonth);
                Prevcall.enqueue(new Callback<PrevCompare>() {
                    @Override
                    public void onResponse(Call<PrevCompare> call, Response<PrevCompare> response) {
                        PrevCompare prv=response.body();
                        TextView prevText=getView().findViewById(R.id.prevText);
                        prevText.setText(prv.diff_price.toString()+"원");


                    }

                    @Override
                    public void onFailure(Call<PrevCompare> call, Throwable t) {

                    }
                });

            }

        });




    }
    void showToast(String msg)
    {
        Toast.makeText(getActivity(),""+msg, Toast.LENGTH_LONG).show();
    }

}


