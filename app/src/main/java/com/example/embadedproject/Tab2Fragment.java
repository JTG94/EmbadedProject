package com.example.embadedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.model.DayList;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import org.w3c.dom.Text;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Frgment";
    private TokenManager tokenManager;
    Integer month = LocalDate.now().getMonthValue();
    Integer year = LocalDate.now().getYear();
    Integer day = LocalDate.now().getDayOfMonth()-1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        TextView monthtext = getView().findViewById(R.id.frag2_month);
        TextView daytext = getView().findViewById(R.id.frag2_day);
        TextView yeartext = getView().findViewById(R.id.yearSave2);
        yeartext.setText(year.toString());
        TextView monthprice = getView().findViewById(R.id.MonthPrice2);

        monthtext.setText(month.toString());
        daytext.setText(day.toString());
        tokenManager = new TokenManager(getActivity().getApplicationContext());
        String token = tokenManager.getto();
        ImageButton prevmonth2 = getView().findViewById(R.id.prevmonth2);
        ImageButton nextmonth2 = getView().findViewById(R.id.nextMonth2);



        final APIcall apiCall = RetroClass.getApICall();
        Call<DayList> daycall = apiCall.DayListCall(token, year, month,day);
        daycall.enqueue(new Callback<DayList>() {
            @Override
            public void onResponse(Call<DayList> call, Response<DayList> response) {
                if (response.code() == 200) {
                    DayList dayinfo = response.body();
                    monthprice.setText("" + dayinfo.getDay_price());
                    DayPriceListAdapter adapter = new DayPriceListAdapter(getActivity().getApplicationContext(), dayinfo.getDay_list());
                    ListView listview = getView().findViewById(R.id.listView2);
                    listview.setAdapter(adapter);
                } else if (response.code() == 501) {
                    NullListAdapter adapter = new NullListAdapter(getActivity().getApplicationContext());
                    ListView listview = (ListView) getView().findViewById(R.id.listView2);
                    listview.setAdapter(adapter);
                    showToast("결과없음");
                }
            }

            @Override
            public void onFailure(Call<DayList> call, Throwable t) {
                showToast("네트워크 연결 상태를 확인해주세요");

            }
        });

        prevmonth2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String curMonth = monthtext.getText().toString();
                String curDay = daytext.getText().toString();
                String curYear = yeartext.getText().toString();

                Integer int_day2= new Integer(curDay);
                Integer int_month2 = new Integer(curMonth);
                Integer int_year2 = new Integer(curYear);




                if (int_day2 == 1) {
                    showToast("끝입니다.");
                    return;
                }
                else {
                    int_day2--;

                    monthtext.setText(int_month2.toString());
                    daytext.setText(int_day2.toString());
                    Call<DayList> daycall2 = apiCall.DayListCall(token, int_year2,int_month2,int_day2);
                    daycall2.enqueue(new Callback<DayList>() {
                        @Override
                        public void onResponse(Call<DayList> call, Response<DayList> response) {
                            if (response.code() == 200) {
                                DayList dayinfo = response.body();
                                monthprice.setText("" + dayinfo.getDay_price());
                                DayPriceListAdapter adapter = new DayPriceListAdapter(getActivity().getApplicationContext(), dayinfo.getDay_list());
                                ListView listview = getView().findViewById(R.id.listView2);
                                listview.setAdapter(adapter);
                            } else if (response.code() == 501) {
                                monthprice.setText("0");
                                NullListAdapter adapter = new NullListAdapter(getActivity().getApplicationContext());
                                ListView listview = (ListView) getView().findViewById(R.id.listView2);
                                listview.setAdapter(adapter);
                                showToast("결과없음");
                            }
                        }

                        @Override
                        public void onFailure(Call<DayList> call, Throwable t) {
                            showToast("네트워크 연결 상태를 확인해주세요");
                        }


                    });
                }
            }
        });

        nextmonth2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curMonth = monthtext.getText().toString();
                String curDay = daytext.getText().toString();
                String curYear = yeartext.getText().toString();

                Integer int_day2= new Integer(curDay);
                Integer int_month2 = new Integer(curMonth);
                Integer int_year2 = new Integer(curYear);




                if (int_day2 == 31) {
                    showToast("끝입니다.");
                    return;
                }
                else {
                    int_day2++;

                    monthtext.setText(int_month2.toString());
                    daytext.setText(int_day2.toString());
                    Call<DayList> daycall2 = apiCall.DayListCall(token, int_year2,int_month2,int_day2);
                    daycall2.enqueue(new Callback<DayList>() {
                        @Override
                        public void onResponse(Call<DayList> call, Response<DayList> response) {
                            if (response.code() == 200) {
                                DayList dayinfo = response.body();
                                monthprice.setText("" + dayinfo.getDay_price());
                                DayPriceListAdapter adapter = new DayPriceListAdapter(getActivity().getApplicationContext(), dayinfo.getDay_list());
                                ListView listview = getView().findViewById(R.id.listView2);
                                listview.setAdapter(adapter);
                            } else if (response.code() == 501){
                                showToast("결과없음");
                                NullListAdapter adapter = new NullListAdapter(getActivity().getApplicationContext());
                                ListView listview = (ListView) getView().findViewById(R.id.listView2);
                                listview.setAdapter(adapter);
                                monthprice.setText("0");
                            }

                        }

                        @Override
                        public void onFailure(Call<DayList> call, Throwable t) {
                            showToast("네트워크 연결 상태를 확인해주세요");
                        }


                    });
                }
            }
        });
        Button listinputbtn =getView().findViewById(R.id.InputList);
        listinputbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity().getApplicationContext(), InputPurchase.class);
                startActivity(intent);
            }
        });


    }



    void showToast(String msg)
    {
        Toast.makeText(getActivity(),""+msg, Toast.LENGTH_LONG).show();
    }

}

