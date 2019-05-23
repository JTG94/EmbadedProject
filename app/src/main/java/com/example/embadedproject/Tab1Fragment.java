package com.example.embadedproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.cal.DayMoneyinfo;
import com.example.embadedproject.model.Budget;
import com.example.embadedproject.model.MonthValue;
import com.example.embadedproject.model.PrevCompare;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import java.time.LocalDate;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab1Fragment extends Fragment {
    private TokenManager tokenManager;
    int year_x,month_x,day_x;
    static final int DILOG_ID =0;
    public static Integer intMonth;
    public static DayMoneyinfo dm;
    public static Integer intYear;
    Button cla;
    public static int[] caldayprice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout,container,false);
        Integer month= LocalDate.now().getMonthValue();
        Integer year=LocalDate.now().getYear();
        tokenManager = new TokenManager(getActivity().getApplicationContext());



        // String token = ((Intent) intent).getExtras().getString("token");
        String token = tokenManager.getto();
        final APIcall apiCall = RetroClass.getApICall();
        Call<MonthValue> monthcall=apiCall.MontlyList(token,year,month);
        Call<Budget> maincall = apiCall.requestBudget(token);
        monthcall.enqueue(new Callback<MonthValue>() {
            @Override
            public void onResponse(Call<MonthValue> call, Response<MonthValue> response) {
                TextView monthText = (TextView) view.findViewById(R.id.MainMonth);
                TextView monthpricetext = (TextView) view.findViewById(R.id.MonthPrice);
                TextView yeartext = view.findViewById(R.id.yearSave);
                if(response.code()==200) {
                    MonthValue monthinfo = response.body();
                    ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext(), month, monthinfo);
                    ListView listview = (ListView) view.findViewById(R.id.listVIew);
                    listview.setAdapter(adapter);

                    caldayprice = new int[31];
                    for(int i =0; i<31; i++){
                        caldayprice[i]=0;
                    }
                    caldayprice[0] =Integer.parseInt(monthinfo.day_price.day_1);
                    caldayprice[1] = Integer.parseInt(monthinfo.day_price.day_2);
                    caldayprice[2] = Integer.parseInt(monthinfo.day_price.day_3);
                    caldayprice[3] = Integer.parseInt(monthinfo.day_price.day_4);
                    caldayprice[4] = Integer.parseInt(monthinfo.day_price.day_5);
                    caldayprice[5] = Integer.parseInt(monthinfo.day_price.day_6);
                    caldayprice[6] = Integer.parseInt(monthinfo.day_price.day_7);
                    caldayprice[7] = Integer.parseInt(monthinfo.day_price.day_8);
                    caldayprice[8] = Integer.parseInt(monthinfo.day_price.day_9);
                    caldayprice[9] = Integer.parseInt(monthinfo.day_price.day_10);
                    caldayprice[10] = Integer.parseInt(monthinfo.day_price.day_11);
                    caldayprice[11] = Integer.parseInt(monthinfo.day_price.day_12);
                    caldayprice[12] = Integer.parseInt(monthinfo.day_price.day_13);
                    caldayprice[13] = Integer.parseInt(monthinfo.day_price.day_14);
                    caldayprice[14] = Integer.parseInt(monthinfo.day_price.day_15);
                    caldayprice[15] = Integer.parseInt(monthinfo.day_price.day_16);
                    caldayprice[16] = Integer.parseInt(monthinfo.day_price.day_17);
                    caldayprice[17] = Integer.parseInt(monthinfo.day_price.day_18);
                    caldayprice[18] = Integer.parseInt(monthinfo.day_price.day_19);
                    caldayprice[19] = Integer.parseInt(monthinfo.day_price.day_20);
                    caldayprice[20] = Integer.parseInt(monthinfo.day_price.day_21);
                    caldayprice[21] = Integer.parseInt(monthinfo.day_price.day_22);
                    caldayprice[22] = Integer.parseInt(monthinfo.day_price.day_23);
                    caldayprice[23] = Integer.parseInt(monthinfo.day_price.day_24);
                    caldayprice[24] = Integer.parseInt(monthinfo.day_price.day_25);
                    caldayprice[25] = Integer.parseInt(monthinfo.day_price.day_26);
                    caldayprice[26] = Integer.parseInt(monthinfo.day_price.day_27);
                    caldayprice[27] = Integer.parseInt(monthinfo.day_price.day_28);
                    caldayprice[28] = Integer.parseInt(monthinfo.day_price.day_29);
                    caldayprice[29] = Integer.parseInt(monthinfo.day_price.day_30);
                    caldayprice[30] = Integer.parseInt(monthinfo.day_price.day_31);
                   /* dm = new DayMoneyinfo();
                    dm.setDay_1(monthinfo.day_price.day_1.toString());
                    dm.setDay_2(monthinfo.day_price.day_2.toString());
                    dm.setDay_3(monthinfo.day_price.day_3.toString());
                    dm.setDay_4(monthinfo.day_price.day_4.toString());
                    dm.setDay_5(monthinfo.day_price.day_5.toString());
                    dm.setDay_6(monthinfo.day_price.day_6.toString());
                    dm.setDay_7(monthinfo.day_price.day_7.toString());
                    dm.setDay_8(monthinfo.day_price.day_9.toString());
                    dm.setDay_10(monthinfo.day_price.day_10.toString());
                    dm.setDay_11(monthinfo.day_price.day_11.toString());
                    dm.setDay_12(monthinfo.day_price.day_12.toString());
                    dm.setDay_13(monthinfo.day_price.day_13.toString());
                    dm.setDay_14(monthinfo.day_price.day_14.toString());
                    dm.setDay_15(monthinfo.day_price.day_15.toString());
                    dm.setDay_16(monthinfo.day_price.day_16.toString());
                    dm.setDay_17(monthinfo.day_price.day_17.toString());
                    dm.setDay_18(monthinfo.day_price.day_18.toString());
                    dm.setDay_19(monthinfo.day_price.day_19.toString());
                    dm.setDay_20(monthinfo.day_price.day_20.toString());
                    dm.setDay_21(monthinfo.day_price.day_21.toString());
                    dm.setDay_22(monthinfo.day_price.day_22.toString());
                    dm.setDay_23(monthinfo.day_price.day_23.toString());
                    dm.setDay_24(monthinfo.day_price.day_24.toString());
                    dm.setDay_25(monthinfo.day_price.day_25.toString());
                    dm.setDay_26(monthinfo.day_price.day_26.toString());
                    dm.setDay_27(monthinfo.day_price.day_27.toString());
                    dm.setDay_28(monthinfo.day_price.day_28.toString());
                    dm.setDay_29(monthinfo.day_price.day_29.toString());
                    dm.setDay_30(monthinfo.day_price.day_30.toString());
                    dm.setDay_31(monthinfo.day_price.day_31.toString());
*/










                    monthText.setText(month.toString());
                    monthpricetext.setText("" + monthinfo.month_price);
                    yeartext.setText(year.toString());
                }
                else if(response.code()==501) {
                    monthpricetext.setText("0");
                    NullListAdapter adapter = new NullListAdapter(getActivity().getApplicationContext());
                    ListView listview = (ListView) view.findViewById(R.id.listVIew);
                    listview.setAdapter(adapter);
                    showToast("결과 없음");
                }

            }

            @Override
            public void onFailure(Call<MonthValue> call, Throwable t) {

            }
        });
        Call<PrevCompare> Prevcall=apiCall.PrevCompareCall(token,month);
        Prevcall.enqueue(new Callback<PrevCompare>() {
            @Override
            public void onResponse(Call<PrevCompare> call, Response<PrevCompare> response) {
                PrevCompare prv=response.body();
                TextView prevText=(TextView) view.findViewById(R.id.prevText);
                prevText.setText(prv.diff_price.toString()+"원");


            }

            @Override
            public void onFailure(Call<PrevCompare> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@Nullable View view,@Nullable Bundle saveInstanceState){
        super.onViewCreated(view,saveInstanceState);
        TextView monthtext=getView().findViewById(R.id.MainMonth);
        TextView yeartext=getView().findViewById(R.id.yearSave);







        tokenManager = new TokenManager(getActivity().getApplicationContext());
        String token = tokenManager.getto();
        final Calendar cal = Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x =cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

        ImageButton prevmonth=getView().findViewById(R.id.prevmonth);
        prevmonth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String curMonth=monthtext.getText().toString();
                String curYear=yeartext.getText().toString();
                intMonth=new Integer(curMonth);
                intYear=new Integer(curYear);

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

                            caldayprice = new int[31];
                            for(int i =0; i<30; i++){
                                caldayprice[i]=0;
                            }


                            caldayprice[0] =Integer.parseInt(monthinfo.day_price.day_1);
                            caldayprice[1] = Integer.parseInt(monthinfo.day_price.day_2);
                            caldayprice[2] = Integer.parseInt(monthinfo.day_price.day_3);
                            caldayprice[3] = Integer.parseInt(monthinfo.day_price.day_4);
                            caldayprice[4] = Integer.parseInt(monthinfo.day_price.day_5);
                            caldayprice[5] = Integer.parseInt(monthinfo.day_price.day_6);
                            caldayprice[6] = Integer.parseInt(monthinfo.day_price.day_7);
                            caldayprice[7] = Integer.parseInt(monthinfo.day_price.day_8);
                            caldayprice[8] = Integer.parseInt(monthinfo.day_price.day_9);
                            caldayprice[9] = Integer.parseInt(monthinfo.day_price.day_10);
                            caldayprice[10] = Integer.parseInt(monthinfo.day_price.day_11);
                            caldayprice[11] = Integer.parseInt(monthinfo.day_price.day_12);
                            caldayprice[12] = Integer.parseInt(monthinfo.day_price.day_13);
                            caldayprice[13] = Integer.parseInt(monthinfo.day_price.day_14);
                            caldayprice[14] = Integer.parseInt(monthinfo.day_price.day_15);
                            caldayprice[15] = Integer.parseInt(monthinfo.day_price.day_16);
                            caldayprice[16] = Integer.parseInt(monthinfo.day_price.day_17);
                            caldayprice[17] = Integer.parseInt(monthinfo.day_price.day_18);
                            caldayprice[18] = Integer.parseInt(monthinfo.day_price.day_19);
                            caldayprice[19] = Integer.parseInt(monthinfo.day_price.day_20);
                            caldayprice[20] = Integer.parseInt(monthinfo.day_price.day_21);
                            caldayprice[21] = Integer.parseInt(monthinfo.day_price.day_22);
                            caldayprice[22] = Integer.parseInt(monthinfo.day_price.day_23);
                            caldayprice[23] = Integer.parseInt(monthinfo.day_price.day_24);
                            caldayprice[24] = Integer.parseInt(monthinfo.day_price.day_25);
                            caldayprice[25] = Integer.parseInt(monthinfo.day_price.day_26);
                            caldayprice[26] = Integer.parseInt(monthinfo.day_price.day_27);
                            caldayprice[27] = Integer.parseInt(monthinfo.day_price.day_28);
                            caldayprice[28] = Integer.parseInt(monthinfo.day_price.day_29);
                            caldayprice[29] = Integer.parseInt(monthinfo.day_price.day_30);
                            caldayprice[30] = Integer.parseInt(monthinfo.day_price.day_31);

























                            monthText.setText(intMonth2.toString());

                            monthpricetext.setText("" + monthinfo.month_price);
                            TextView yeartext = getView().findViewById(R.id.yearSave);
                        }
                        else if(response.code()==501) {
                            monthpricetext.setText("0");
                            NullListAdapter adapter = new NullListAdapter(getActivity().getApplicationContext());
                            ListView listview = (ListView) getView().findViewById(R.id.listVIew);
                            listview.setAdapter(adapter);
                            // showToast("결과 없음");

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
                        else if(response.code()==501){
                            TextView monthpricetext=getView().findViewById(R.id.MonthPrice);
                            monthpricetext.setText("0");

                            NullListAdapter adapter = new NullListAdapter(getActivity().getApplicationContext());
                            ListView listview = (ListView) getView().findViewById(R.id.listVIew);
                            listview.setAdapter(adapter);
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
                        PrevCompare prv=response.body();
                        TextView prevText=getView().findViewById(R.id.prevText);
                        prevText.setText(prv.diff_price.toString()+"원");


                    }

                    @Override
                    public void onFailure(Call<PrevCompare> call, Throwable t) {
                        showToast("네트워크 상태를 확인하세요");
                    }
                });

            }

        });




        Button cla = getView().findViewById(R.id.calendarbutton);
        cla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CalenderActivity.class);
                startActivity(intent);
            }
        });




    }
    public void showDialogOnButtonClick(){
        cla = getView().findViewById(R.id.calendarbutton);
        cla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog(DILOG_ID).show();
            }
        });

    }
    protected Dialog onCreateDialog(int id){
        if(id==DILOG_ID) {
            return new DatePickerDialog(getContext(), dpickerListner, year_x, month_x, day_x);
        }
        return null;
    }
    DatePickerDialog.OnClickListener  clickListener = new DatePickerDialog.OnClickListener(){
        Activity root = getActivity();
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(root,caldayprice[2]+"원",Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dpickerListner=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Activity root = getActivity();
            if(intMonth!=null) {
                year_x = intYear;
                day_x = dayOfMonth;
            }else {
                year_x = year;
                month_x = month;
                day_x = dayOfMonth;
            }
            Toast.makeText(root,  caldayprice[day_x-1]+"원",Toast.LENGTH_LONG).show();
        }
    };

    void showToast(String msg)
    {
        Toast.makeText(getActivity(),""+msg, Toast.LENGTH_LONG).show();
    }

}


