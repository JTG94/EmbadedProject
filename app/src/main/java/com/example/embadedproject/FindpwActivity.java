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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class FindpwActivity extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpw);

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);

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
                findUserpw(name.getText().toString(),email.getText().toString(), phone.getText().toString());
            }
        });

    }

    private void findUserpw(String name, String email, String phone) {

        compositeDisposable.add(myAPI.findUserpw(name,email,phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>(){
                    @Override
                    public void accept(String s) throws Exception {
                        String json = s;
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(json);
                        String message = element.getAsJsonObject().get("message").getAsString();
                        if(message.length()!=0) {


                            Toast.makeText(FindpwActivity.this, ""+message, Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(FindpwActivity.this,""+s,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }
}
