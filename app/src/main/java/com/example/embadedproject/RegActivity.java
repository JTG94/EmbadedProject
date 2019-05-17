package com.example.embadedproject;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.embadedproject.Retrofit.INodeJS;
import com.example.embadedproject.Retrofit.RetrofitClient;
import com.example.embadedproject.model.JWTToken;
import com.example.embadedproject.model.Message;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegActivity extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private AlertDialog dialog1;
    public String check;
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
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reg);


        //Init API
     /*   Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);*/


        ImageButton backbtn = (ImageButton) findViewById(R.id.backbtn);
       final EditText name = (EditText)findViewById(R.id.nameText);
       final EditText email = (EditText)findViewById(R.id.idText);
        final EditText phone = (EditText)findViewById(R.id.phonenumText);
        final EditText password = (EditText)findViewById(R.id.passText);
        final EditText coupang_id = (EditText)findViewById(R.id.coupangidText);
        final EditText coupang_pw = (EditText)findViewById(R.id.coupangpwText);
        Button regbtn = (Button)findViewById(R.id.regButton);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),LoginActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);

            }
        });


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final APIcall apiCall = RetroClass.getApICall();
                final String name1 = name.getText().toString();
                final String email1 = email.getText().toString();
                final String phone1 = phone.getText().toString();
                final String password1 = password.getText().toString();
                final String coupang_id1 = coupang_id.getText().toString();
                final String coupang_pw1 = coupang_pw.getText().toString();
                Call<Message> regcall = apiCall.registerUser(name1,email1,phone1,password1,coupang_id1,coupang_pw1);

                regcall.enqueue(new Callback<Message>() {
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
                                showToast("" +message.getMessage().toString());
                                /*Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(loginIntent);*/
                                finish();
                            }
                        }else{

                            if (response.code() == 500) {

                                showToast("서버오류");
                            }else if(response.code()==501){
                                email.setError("다른 아이디를 사용하세요.");
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegActivity.this);
                                dialog1 = builder.setMessage("중복된 아이디 입니다.").setNegativeButton("확인", null).create();
                                dialog1.show();
                            }else if(response.code()==502){
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegActivity.this);
                                dialog1 = builder.setMessage("값을 모두 입력해 주세요.").setNegativeButton("확인", null).create();
                                dialog1.show();
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

    /*private void registerUser(String name, String email, String phone, String password, String coupang_id, String coupang_pw) {
        compositeDisposable.add(myAPI.registerUser(name,email,phone,password,coupang_id,coupang_pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>(){
                    @Override
                    public void accept(String s) throws Exception {
                        String json = s;
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(json);
                        boolean success = element.getAsJsonObject().get("success").getAsBoolean();

                        if(success) {

                            Intent regIntent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(regIntent);
                           Toast.makeText(RegActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                           // Toast.makeText(RegActivity.this,""+status,Toast.LENGTH_LONG).show();
                        }else{


                            Toast.makeText(RegActivity.this,"회원가입 실패",Toast.LENGTH_SHORT).show();

                        }
                    }

                })
        );
    }*/
    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }
}
