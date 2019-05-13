package com.example.embadedproject;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.model.Message;
import com.example.embadedproject.model.MonthValue;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import org.w3c.dom.Text;

import java.time.Month;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
public static String year = "2019";
public static String month="5";
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokenManager = new TokenManager(getApplicationContext());
        TextView tv = (TextView) findViewById(R.id.to);
        Intent intent = getIntent();
       // String token = ((Intent) intent).getExtras().getString("token");
        String token = tokenManager.getto();
        final APIcall apiCall = RetroClass.getApICall();
        //Call<MonthValue> maincall = apiCall.Mainreq(token);
        Call<MonthValue> maincall = apiCall.Mainreq(token);

        maincall.enqueue(new Callback<MonthValue>() {
            @Override
            public void onResponse(Call<MonthValue> call, Response<MonthValue> response) {
                if (response.isSuccessful()) {
                    MonthValue monthValue = response.body();


                    /*JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(json);
                    boolean success = element.getAsJsonObject().get("success").getAsBoolean();*/
                    /*  try {*/

                           /* Message message = response.body();
                            //String status = response.body().toString();*/
                    if (response.code() == 200) {
                        // showToast(""+status);
                        // showToast(""+response.code());
                       // showToast("" +monthValue.getMonthValue().toString());
                                /*Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(loginIntent);*/

                    }
                }else{

                    if (response.code() == 500) {

                        showToast("서버오류");
                    }else if(response.code()==501){
                                          }else if(response.code()==502){
                    }
                }
            }
                            /*else if(response.code()==500){
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("계정을 다시 확인하세요.").setNegativeButton("다시시도", null).create();
                                dialog.show();
                            }*/
                      /*  }catch(Exception e){
                            if(response.code()==500){
                                showToast("500 코드 인지"+message.getMessage().toString());
                            }


                            e.printStackTrace();
                        }*/

            /* }*/

            @Override
            public void onFailure(Call<MonthValue> call, Throwable t) {
                showToast("Failer에서 응답");
            }
        });

    }


        void showToast(String msg)
        {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
        }
}
