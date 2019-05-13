package com.example.embadedproject;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.embadedproject.Retrofit.INodeJS;
import com.example.embadedproject.Retrofit.RetrofitClient;
import com.example.embadedproject.model.Message;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FindpwActivity extends AppCompatActivity {
    private AlertDialog dialog1;

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog1 != null){
            dialog1.dismiss();
            dialog1=null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpw);



        final EditText name = (EditText)findViewById(R.id.nameText);
        final EditText email = (EditText)findViewById(R.id.emailText);
        final EditText phone = (EditText)findViewById(R.id.phoneText);
        Button findpwbtn =(Button)findViewById(R.id.findpwButton);
        ImageButton backbtn = (ImageButton) findViewById(R.id.backbtn2);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),LoginActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);

            }
        });

        findpwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final APIcall apiCall = RetroClass.getApICall();
                final String name1 =name.getText().toString();
                final String email1 = email.getText().toString();
                final String phone1 = phone.getText().toString();
              if(name.length() ==0){
                    name.setError("이름을 입력하세요.");
                    name.requestFocus();
                    return;
                }else if(email1.isEmpty()){
                phone.setError("이메일을 입력하세요.");
                return;
                } else if(phone1.isEmpty()){
                    phone.setError("핸드폰 번호를 입력하세요.");
                    return;

                }
                Call<Message> findpwcall = apiCall.findUserpw(name1,email1,phone1);

                findpwcall.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if (response.isSuccessful()) {
                            Message message = response.body();

                            /*  try {*/

                           /* Message message = response.body();
                            //String status = response.body().toString();*/
                            if (response.code() == 200) {
                                // showToast(""+status);
                                // showToast(""+response.code());
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindpwActivity.this);
                                dialog1 = builder.setMessage("" + message.getMessage().toString()).setNegativeButton("확인", null).create();
                                dialog1.show();
                                /*Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(loginIntent);*/

                            }
                        } else {

                            if (response.code() == 500) {

                                showToast("입력하신 정보가 일치하지 않습니다.");
                            }
                            if(response.code()==501)
                            {
                                showToast("죄송합니다. 메일서버가 현재 오류가 났습니다.");
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
                    public void onFailure(Call<Message> call, Throwable t) {
                        showToast("Failer에서 응답");
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
