package com.example.embadedproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.embadedproject.model.JWTToken;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private long time =0;
    private TokenManager tokenManager;
    private AlertDialog dialog;

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog != null){
            dialog.dismiss();
            dialog=null;
        }
    }

    @Override
    public void onBackPressed() {

        if(System.currentTimeMillis()-time>=2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "종료를 원하시면 한번더 눌러주세요!", Toast.LENGTH_LONG).show();
        }else if(System.currentTimeMillis()-time<2000){
            finish();
            return;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);


       tokenManager = new TokenManager(getApplicationContext());

         final EditText edt_email = (EditText) findViewById(R.id.idText);
         final EditText edt_password = (EditText) findViewById(R.id.passwordText);
        Button loginbtn = (Button) findViewById(R.id.loginButton);
        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        TextView findpwButton = (TextView) findViewById(R.id.findpwButton);
        TextView findidButton = (TextView) findViewById(R.id.findidButton);
        TextView tv = (TextView) findViewById(R.id.textView);
        String str = "CourAIt";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#E74C3C")), 4, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(ssb);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegActivity.class);
                startActivity(registerIntent);
            }
        });

        findidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findidIntent = new Intent(getApplicationContext(), FindidActivity.class);
                startActivity(findidIntent);
            }
        });

        findpwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findpwIntent = new Intent(getApplicationContext(), FindpwActivity.class);
                startActivity(findpwIntent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final APIcall apiCall = RetroClass.getApICall();

                final String email = edt_email.getText().toString();
                final String password = edt_password.getText().toString();
                if(email.length() ==0){
                    edt_email.setError("이메일을 입력하세요.");
                    edt_email.requestFocus();
                    return;
                }else if(password.isEmpty()){
                    edt_password.setError("비밀번호를 입력하세요.");
                    return;

                }

               Call<JWTToken> jwtTokenCall = apiCall.userlogin(email,password);

               jwtTokenCall.enqueue(new Callback<JWTToken>() {
                   @Override
                   public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {

                       if (response.isSuccessful()){
                            JWTToken jwtToken = response.body();
                            tokenManager.createSession(jwtToken.getToken());

                            if(response.code() == 200) {
                                Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                                loginIntent.putExtra("token",jwtToken.getToken());
                                startActivity(loginIntent);
                                finish();

                            }
                        }else{
                                if(response.code()==500){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    dialog = builder.setMessage("이메일 혹은 비밀번호가 틀렸습니다.").setNegativeButton("확인", null).create();
                                    dialog.show();
                                }
                            }
                   }

                   @Override
                   public void onFailure(Call<JWTToken> call, Throwable t) {
                       showToast("요청 자체가 가질 않음. 인터넷 연결 확인 부탁");
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





