package com.example.embadedproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.embadedproject.model.Message;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Excel extends AppCompatActivity {
    Integer year;
    Integer month;
    private TokenManager tokenManager;
    private AlertDialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);

        EditText yearInput=findViewById(R.id.yearInput2);
        EditText monthInput=findViewById(R.id.monthInput);
        final Button excelButton=(Button)findViewById(R.id.excelButton);

        tokenManager = new TokenManager(getApplicationContext());
        String token = tokenManager.getto();
        ImageButton backbtn = (ImageButton) findViewById(R.id.backbtn15);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),MainActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);
            }
        });


        excelButton.setOnClickListener((v)->{
            if(yearInput.getText().length()==0||monthInput.getText().length()==0)
                showToast("연도와 월을 모두 입력해주세요");
            else {
                final APIcall apiCall = RetroClass.getApICall();
                year=new Integer(yearInput.getText().toString());
                month=new Integer(monthInput.getText().toString());
                Call<Message> excelcall = apiCall.requestExcel(token,year,month);
                excelcall.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if (response.code() == 200) {
                            Message message = response.body();
                            AlertDialog.Builder builder = new AlertDialog.Builder(Excel.this);
                            dialog1 = builder.setMessage("엑셀 파일이 메일로 전송되었습니다.").setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    Intent Ok = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(Ok);
                                    finish();
                                }
                            }).create();
                            dialog1.show();
                        }
                        else if(response.code()==501){
                            showToast("결과없음");
                        }
                        else if(response.code()==500){
                            showToast("서버에러");
                        }
                    }
                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        showToast("네트워크 통신 상태를 확인하세요.");
                    }

                });
            }




        });

    }
    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }
}
