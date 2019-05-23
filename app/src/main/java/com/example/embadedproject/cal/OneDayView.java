package com.example.embadedproject.cal;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.embadedproject.R;
import com.example.embadedproject.Tab1Fragment;

import java.util.Calendar;


/**
 * View to display a day
 * @author Brownsoo
 *
 */
public class OneDayView extends RelativeLayout {


    private static final String NAME = "OneDayView";
    private final String CLASS = NAME + "@" + Integer.toHexString(hashCode());

    /** number text field */
    @NonNull
    private TextView dayTv;
    /** message text field*/
    @NonNull
    private TextView msgTv;
    /** Weather icon */
    @NonNull
    private TextView moneyTv;
    /** Value object for a day info */
    @NonNull
    private OneDayData one;

    /**
     * OneDayView constructor
     * @param context context
     */
    public OneDayView(@NonNull Context context) {
        this(context, null);
    }

    /**
     * OneDayView constructor for xml
     * @param context context
     * @param attrs AttributeSet
     */
    public OneDayView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View v = View.inflate(context, R.layout.oneday, this);
        dayTv = v.findViewById(R.id.onday_dayTv);
        /* weatherIv = v.findViewById(R.id.onday_weatherIv);*/
        msgTv = v.findViewById(R.id.onday_msgTv);
        moneyTv = v.findViewById(R.id.moneytxt);
        one = new OneDayData();

    }

    /**
     * Set the day to display
     * @param year 4 digits of year
     * @param month Calendar.JANUARY ~ Calendar.DECEMBER
     * @param day day of month
     */
    public void setDay(int year, int month, int day) {
        this.one.cal.set(year, month, day);
    }

    /**
     * Set the day to display
     * @param cal Calendar instance
     */
    public void setDay(Calendar cal) {
        this.one.setDay((Calendar) cal.clone());
    }

    /**
     * Set the day to display
     * @param one OneDayData instance
     */
    public void setDay(OneDayData one) {
        this.one = one;
    }

    /**
     * Get the day to display
     * @return OneDayData instance
     */
    public OneDayData getDay() {
        return one;
    }

    /**
     * Set the message to display
     * @param msg message
     */
    public void setMessage(String msg){
        one.setMessage(msg);
    }

    /**
     * Get the message
     * @return message
     */
    public CharSequence getMessage(){
        return  one.getMessage();
    }

    /**
     * Same function with {@link Calendar#get(int)}<br>
     * <br>
     * Returns the value of the given field after computing the field values by
     * calling {@code complete()} first.
     *
     * @param field Calendar.YEAR or Calendar.MONTH or Calendar.DAY_OF_MONTH
     *
     * @throws IllegalArgumentException
     *                if the fields are not set, the time is not set, and the
     *                time cannot be computed from the current field values.
     * @throws ArrayIndexOutOfBoundsException
     *                if the field is not inside the range of possible fields.
     *                The range is starting at 0 up to {@code FIELD_COUNT}.
     */
    public int get(int field) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        return one.get(field);
    }

    /**
     * Set weather
     * @param weather Weather instance
     */
    public void setWeather(WeatherInfo.Weather weather) {
        this.one.setWeather(weather);
    }

    /**
     * Updates UI upon the value object.
     */
    public void refresh() {

        //HLog.d(TAG, CLASS, "refresh");

        dayTv.setText(String.valueOf(one.get(Calendar.DAY_OF_MONTH)));
       /* moneyTv.setText(String.valueOf(Tab1Fragment.dm.getDay_1().toString()));*/
        if(one.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayTv.setTextColor(Color.RED);
        }
        else if(one.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            dayTv.setTextColor(Color.BLUE);
        }
        else {
            dayTv.setTextColor(Color.BLACK);
        }

/*        moneyTv.setText((one.getMessage()==null)?"":one.getMessage());
        switch(one.weather) {
        case CLOUDY :
        case SUN_CLOUDY:
            weatherIv.setImageResource(R.drawable.cloudy);
            break;
        case RAINY:
            weatherIv.setImageResource(R.drawable.rainy);
            break;
        case SNOW :
            weatherIv.setImageResource(R.drawable.snowy);
            break;
        case SUNSHINE :
            weatherIv.setImageResource(R.drawable.sunny);
            break;
        }*/
        if(one.get(Calendar.DAY_OF_MONTH)==1)
        {
            if(Tab1Fragment.caldayprice[0]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
           moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[0]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==2){
            if(Tab1Fragment.caldayprice[1]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[1]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==3){
            if(Tab1Fragment.caldayprice[2]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[2]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==4){
            if(Tab1Fragment.caldayprice[3]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[3]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==5){
            if(Tab1Fragment.caldayprice[4]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[4]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==6){
            if(Tab1Fragment.caldayprice[5]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[5]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==7){
            if(Tab1Fragment.caldayprice[6]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[6]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==8){
            if(Tab1Fragment.caldayprice[7]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[7]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==9){
            if(Tab1Fragment.caldayprice[8]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[8]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==10){
            if(Tab1Fragment.caldayprice[9]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[9]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==11){
            if(Tab1Fragment.caldayprice[10]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[10]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==12){
            if(Tab1Fragment.caldayprice[11]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[11]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==13){
            if(Tab1Fragment.caldayprice[12]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[12]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==14){
            if(Tab1Fragment.caldayprice[13]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[13]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==15){
            if(Tab1Fragment.caldayprice[14]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[14]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==16){
            if(Tab1Fragment.caldayprice[15]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[15]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==17){
            if(Tab1Fragment.caldayprice[16]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[16]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==18){
            if(Tab1Fragment.caldayprice[17]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[17]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==19){
            if(Tab1Fragment.caldayprice[18]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[18]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==20){
            if(Tab1Fragment.caldayprice[19]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[19]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==21){
            if(Tab1Fragment.caldayprice[20]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[20]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==22){
            if(Tab1Fragment.caldayprice[21]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[21]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==23){

            if(Tab1Fragment.caldayprice[22]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[22]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==24){
            if(Tab1Fragment.caldayprice[23]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[23]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==25){
            if(Tab1Fragment.caldayprice[24]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[24]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==26){
            if(Tab1Fragment.caldayprice[25]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[25]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==27){
            if(Tab1Fragment.caldayprice[26]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[26]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==28){
            if(Tab1Fragment.caldayprice[27]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[27]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==29){
            if(Tab1Fragment.caldayprice[28]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[28]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==30){
            if(Tab1Fragment.caldayprice[29]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[29]) + " 원");
        }else if(one.get(Calendar.DAY_OF_MONTH)==31){
            if(Tab1Fragment.caldayprice[30]==0){
                moneyTv.setTextColor(R.color.colorAccent);
            }
            moneyTv.setText(Integer.toString(Tab1Fragment.caldayprice[30]) + " 원");
        }




}
}