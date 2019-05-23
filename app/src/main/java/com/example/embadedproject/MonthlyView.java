package com.example.embadedproject;



import android.support.annotation.NonNull;

import com.example.embadedproject.cal.OneDayView;

public interface MonthlyView {
    void onClickDay(@NonNull OneDayView odv);
    void onMonthChanged(int year, int month);
    int getCurrentPosition();
}
