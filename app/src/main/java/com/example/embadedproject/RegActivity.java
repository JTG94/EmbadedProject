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
import com.example.embadedproject.model.Message;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;


import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RegActivity extends AppCompatActivity {
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

                if(name1.length() ==0){
                    name.setError("이름을 입력해주세요.");
                    name.requestFocus();
                    return;
                }else if(email1.isEmpty()){
                    email.setError("이메일을 입력해주세요.");
                    return;

                }else if(phone1.isEmpty()){
                    phone.setError("핸드폰 번호를 입력해주세요.");
                    return;

                }else if(password1.isEmpty()){
                    password.setError("비밀번호를 입력해주세요.");
                    return;

                }else if(coupang_id1.isEmpty()){
                    coupang_id.setError("쿠팡 아이디를 입력해주세요.");
                    return;

                }else if(coupang_pw1.isEmpty()){
                    coupang_pw.setError("쿠팡 비밀번호를 입력해주세요.");
                    return;

                }
                Call<Message> regcall = apiCall.registerUser(name1,email1,phone1,password1,coupang_id1,coupang_pw1);

                regcall.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if (response.isSuccessful()) {
                            Message message = response.body();
                            if (response.code() == 200) {
                                showToast("" +message.getMessage().toString());
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
                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        showToast("네트워크 상태를 확인해주세요");
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
