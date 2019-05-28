package com.example.embadedproject;

import android.content.DialogInterface;
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
import com.example.embadedproject.tokenmanager.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserOutActivity extends AppCompatActivity {
    private TokenManager tokenManager;
    private AlertDialog dialog1;

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog1 != null) {
            dialog1.dismiss();
            dialog1 = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_out);
        tokenManager = new TokenManager(getApplicationContext());
        String token = tokenManager.getto();
        AlertDialog.Builder builder = new AlertDialog.Builder(UserOutActivity.this);
        ImageButton backbtn = (ImageButton) findViewById(R.id.backbtn3);
        final EditText pass = (EditText) findViewById(R.id.passText);
        Button useroutbtn = (Button) findViewById(R.id.useroutButton);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),MainActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);
            }
        });

        useroutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final APIcall apiCall = RetroClass.getApICall();
                final String pass1 = pass.getText().toString();

                if (pass.length() == 0) {
                    showToast("비밀번호를 입력하세요");
                    return;
                } else {

                    Call<Message> userOutcall = apiCall.userOut(token, pass1);
                    userOutcall.enqueue(new Callback<Message>() {
                        @Override
                        public void onResponse(Call<Message> call, Response<Message> response) {
                            Message message = response.body();
                            if(response.code()==200) {
                                dialog1 = builder.setMessage("회원 탈퇴가 완료되었습니다.").setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(loginIntent);
                                        finish();;
                                    }
                                }).create();
                                dialog1.show();

                            }

                            else if (response.code() == 501)
                                showToast("비밀번호가 올바르지 않습니다");
                        }

                        @Override
                        public void onFailure(Call<Message> call, Throwable t) {
                            showToast("네트워크 통신 상태를 확인하세요.");

                        }
                    });
                }
            }
        });
    }

    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }
}






