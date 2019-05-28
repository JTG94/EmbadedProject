package com.example.embadedproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.cal.OneDayView;

import java.util.Calendar;


public class CalenderActivity extends FragmentActivity {


    private static final String NAME = "MainActivity";
    private final String CLASS = NAME + "@" + Integer.toHexString(hashCode());

    private TextView thisMonthTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

//        Button addButton = findViewById(R.id.main_add_bt);
//        Button monthButton = findViewById(R.id.main_monthly_bt);
//        Button weekButton = findViewById(R.id.main_weekly_bt);
//        Button dayButton = findViewById(R.id.main_daily_bt);
        thisMonthTv = findViewById(R.id.this_month_tv);

        MonthlyFragment mf = (MonthlyFragment) getSupportFragmentManager().findFragmentById(R.id.monthly);
        mf.setOnMonthChangeListener(new MonthlyFragment.MonthlyFragmentListener() {

            @Override
            public void onChange(int year, int month) {

                thisMonthTv.setText(year + "." + (month + 1));
            }

            @Override
            public void onDayClick(OneDayView dayView) {
                Toast.makeText(CalenderActivity.this, "Click  " + (dayView.get(Calendar.MONTH)+1) + "/" + dayView.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
