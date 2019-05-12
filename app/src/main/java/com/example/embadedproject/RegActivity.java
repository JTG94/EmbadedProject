package com.example.embadedproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.embadedproject.Retrofit.INodeJS;
import com.example.embadedproject.Retrofit.RetrofitClient;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RegActivity extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        //Init API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);


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
                registerUser(name.getText().toString(),email.getText().toString(),phone.getText().toString(),password.getText().toString(),coupang_id.getText().toString(),coupang_pw.getText().toString());
            }
        });
    }

    private void registerUser(String name, String email, String phone, String password, String coupang_id, String coupang_pw) {
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

                        }else{


                            Toast.makeText(RegActivity.this,"회원가입 실패",Toast.LENGTH_SHORT).show();

                        }
                    }
                })
        );
    }
}
