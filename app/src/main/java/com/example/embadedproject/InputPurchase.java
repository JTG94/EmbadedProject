package com.example.embadedproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.embadedproject.model.PurchaseList;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputPurchase extends AppCompatActivity {
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
        setContentView(R.layout.activity_input_purchase);

        Button pushbtn=findViewById(R.id.pushdata);
        Button cancelbtn=findViewById(R.id.canceldata);
        tokenManager = new TokenManager(getApplicationContext());
        String token = tokenManager.getto();



        pushbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText product_name=findViewById(R.id.productName);
                EditText product_price=findViewById(R.id.productPrice2);
                EditText product_date=findViewById(R.id.productDate);

                if(product_name.getText().length()==0 || product_price.getText().toString().length()==0||product_date.getText().length()==0)
                    showToast("상품명, 금액, 구매날짜를 모두 입력해주세요.");

                else{
                    String productname = product_name.getText().toString();
                    Integer productprice=new Integer(product_price.getText().toString());
                    String productdate=product_date.getText().toString();


                    showToast(productname+productprice.toString()+productdate);
                    int price3=productprice;

                    final APIcall apiCall = RetroClass.getApICall();
                    Call<PurchaseList> purcall=apiCall.InputPurchase(token,productname,price3,productdate);
                    purcall.enqueue(new Callback<PurchaseList>() {
                        @Override
                        public void onResponse(Call<PurchaseList> call, Response<PurchaseList> response) {
                            if(response.code()==200){
                                PurchaseList purlist=response.body();
                                AlertDialog.Builder builder = new AlertDialog.Builder(InputPurchase.this);
                                dialog1 = builder.setMessage("지출내역 입력이 완료되었습니다.").setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        Intent Ok = new Intent(getApplicationContext(),Tab2Fragment.class);
                                        finish();
                                    }
                                }).create();
                                dialog1.show();
                            }
                            else if(response.code()==500){
                                showToast("전송 실패. 서버에러");
                            }
                        }

                        @Override
                        public void onFailure(Call<PurchaseList> call, Throwable t) {
                            showToast("네트워크 연결 상태를 확인해주세요.");

                        }
                    });


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
