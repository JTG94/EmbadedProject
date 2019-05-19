package com.example.embadedproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class QuestionActivity extends AppCompatActivity {
    private TokenManager tokenManager;
    private AlertDialog dialog1;
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
        setContentView(R.layout.activity_question);

        final Button sendButton=(Button)findViewById(R.id.sendButton);

        tokenManager = new TokenManager(getApplicationContext());
        ImageButton backbtn = (ImageButton) findViewById(R.id.backbtn4);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getApplicationContext(),MainActivity.class);
                backIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);
            }
        });


        String token = tokenManager.getto();

        final EditText content_txt = (EditText)findViewById(R.id.contentText);

        sendButton.setOnClickListener((v)->{
            final String content = content_txt.getText().toString();
            if(content.equals(null))
                showToast("내용을 입력해주세요");
            else {
                final APIcall apiCall = RetroClass.getApICall();
                Call<Message> Questioncall = apiCall.questionReq(token, content);
                Questioncall.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        Message message = response.body();

                        Log.e("message", message.message);
                        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
                        dialog1 = builder.setMessage("메세지가 관리자에게 전송되었습니다.").setNegativeButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int id) {
                                Intent Ok = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(Ok);
                                finish();
                            }
                        }).create();
                        dialog1.show();
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