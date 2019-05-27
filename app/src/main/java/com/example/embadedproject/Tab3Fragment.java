package com.example.embadedproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.model.Budget;
import com.example.embadedproject.model.MonthPercent;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Frgment";
    private TokenManager tokenManager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tokenManager = new TokenManager(getActivity().getApplicationContext());
        Button inputbtn=getView().findViewById(R.id.budgetinput);
        TextView text2=getView().findViewById(R.id.monthbudget);

        String token = tokenManager.getto();
        final APIcall apiCall = RetroClass.getApICall();
        Call<Budget> budgetCall = apiCall.requestBudget(token);
        budgetCall.enqueue(new Callback<Budget>() {
            @Override
            public void onResponse(Call<Budget> call, Response<Budget> response) {
                if(response.code()==200){
                    Budget budgetinfo=response.body();
                    text2.setText(""+budgetinfo.budget+"원");
                }
                else if(response.code()==500)
                    showToast("서버 오류");

            }

            @Override
            public void onFailure(Call<Budget> call, Throwable t) {
                showToast("네트워크 상태를 확인해주세요");
            }
        });

        inputbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText edittext = new EditText(getActivity());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("예산설정");
                builder.setMessage("예산을 입력해주세요");
                builder.setView(edittext);
                builder.setPositiveButton("입력",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(edittext.getText().length()==0)
                                    showToast("금액을 입력해주세요.");
                                else {
                                    Integer budget=new Integer(edittext.getText().toString());
                                    Call<Budget> budgetcall=apiCall.postBudget(token,budget.intValue());
                                    budgetcall.enqueue(new Callback<Budget>() {
                                        @Override
                                        public void onResponse(Call<Budget> call, Response<Budget> response) {
                                            if(response.code()==200) {
                                                showToast("예산설정이 완료되었습니다.");
                                                text2.setText(budget.toString()+"원");
                                            }
                                            else
                                                showToast("서버에러");


                                        }

                                        @Override
                                        public void onFailure(Call<Budget> call, Throwable t) {
                                            showToast("네트워크 연결 상태를 확인해주세요");

                                        }
                                    });

                                }

                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

            }
        });
    }
    void showToast(String msg)
    {
        Toast.makeText(getActivity(),""+msg, Toast.LENGTH_LONG).show();
    }

}
