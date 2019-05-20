package com.example.embadedproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;

public class InputPurchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_purchase);

        Button pushbtn=findViewById(R.id.pushdata);
        Button cancelbtn=findViewById(R.id.pushdata2);

        pushbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText product_name=findViewById(R.id.productName);
                EditText product_price=findViewById(R.id.productPrice);

                if(product_name.getText().length()==0 || product_price.getText().length()==0)
                    showToast("상품명과 금액을 모두 입력해주세요.");

                else{
                    final APIcall apiCall = RetroClass.getApICall();


                }

            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }
}
