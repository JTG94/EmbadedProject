package com.example.embadedproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.model.Budget;
import com.example.embadedproject.model.BudgetStatus;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab4Fragment extends Fragment {
    private static final String TAG = "Tab4Frgment";
    private TokenManager tokenManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4_layout,container,false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tokenManager = new TokenManager(getActivity().getApplicationContext());
        Button inputbtn=getView().findViewById(R.id.budgetinput);
        TextView text2=getView().findViewById(R.id.monthbudget2);
        ConstraintLayout back=getView().findViewById(R.id.kkkk);
        EditText text3=getView().findViewById(R.id.editText3);
        TextView text4=getView().findViewById(R.id.textView15);
        ImageView img=getView().findViewById(R.id.imageView2);

        String token = tokenManager.getto();
        final APIcall apiCall = RetroClass.getApICall();
        Call<BudgetStatus> budgetCall = apiCall.requestBudgetStatus(token);
        budgetCall.enqueue(new Callback<BudgetStatus>() {
            @Override
            public void onResponse(Call<BudgetStatus> call, Response<BudgetStatus> response) {
                if(response.code()==200){
                    BudgetStatus budgetinfo=response.body();
                    if(budgetinfo.over==false) {
                        text2.setText("" + budgetinfo.budget);
                        text2.setTextColor(Color.GREEN);
                        back.setBackgroundColor(getResources().getColor(R.color.white_20));
                        text3.setText("이번 달 오늘 까지 쓴 금액은 "+budgetinfo.month_price+"원 이며 앞으로 "+budgetinfo.diff_price+"원의 여유가 있습니다.");
                        text4.setText("하루에 "+budgetinfo.rest_price+"원 씩 쓰셔야해요!");
                        img.setImageResource(R.drawable.smile);
                    }
                    else if(budgetinfo.over==true) {
                        text2.setText("" + budgetinfo.budget);
                        text2.setTextColor(Color.RED);
                        back.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        text3.setText("예산 설정한 금액을 초과했습니다.\n초과한 금액:"+budgetinfo.diff_price+"원");
                        text4.setText("다음에는 잘 관리하세요!");
                        img.setImageResource(R.drawable.sad);
                    }
                }
                else if(response.code()==500)
                    showToast("서버 오류");

            }

            @Override
            public void onFailure(Call<BudgetStatus> call, Throwable t) {
                showToast("네트워크 상태를 확인해주세요");
            }
        });


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tokenManager = new TokenManager(getActivity().getApplicationContext());
        Button inputbtn=getView().findViewById(R.id.budgetinput);
        TextView text2=getView().findViewById(R.id.monthbudget2);
        ConstraintLayout back=getView().findViewById(R.id.kkkk);
        EditText text3=getView().findViewById(R.id.editText3);
        TextView text4=getView().findViewById(R.id.textView15);
        ImageView img=getView().findViewById(R.id.imageView2);

        String token = tokenManager.getto();
        final APIcall apiCall = RetroClass.getApICall();
        Call<BudgetStatus> budgetCall = apiCall.requestBudgetStatus(token);
        budgetCall.enqueue(new Callback<BudgetStatus>() {
            @Override
            public void onResponse(Call<BudgetStatus> call, Response<BudgetStatus> response) {
                if(response.code()==200){
                    BudgetStatus budgetinfo=response.body();
                    if(budgetinfo.over==false) {
                        text2.setText("" + budgetinfo.budget);
                        text2.setTextColor(Color.GREEN);
                        back.setBackgroundColor(getResources().getColor(R.color.white_20));
                        text3.setText("이번 달 오늘 까지 쓴 금액은 "+budgetinfo.month_price+"원 이며 앞으로 "+budgetinfo.diff_price+"원의 여유가 있습니다.");
                        text4.setText("하루에 "+budgetinfo.rest_price+"원 씩 쓰셔야해요!");
                        img.setImageResource(R.drawable.smile);
                    }
                    else if(budgetinfo.over==true) {
                        text2.setText("" + budgetinfo.budget);
                        text2.setTextColor(Color.RED);
                        back.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        text3.setText("예산 설정한 금액을 초과했습니다.\n초과한 금액:"+budgetinfo.diff_price+"원");
                        text4.setText("다음에는 잘 관리하세요!");
                        img.setImageResource(R.drawable.sad);
                    }
                }
                else if(response.code()==500)
                    showToast("서버 오류");

            }

            @Override
            public void onFailure(Call<BudgetStatus> call, Throwable t) {
                showToast("네트워크 상태를 확인해주세요");
            }
        });


    }
    void showToast(String msg)
    {
        Toast.makeText(getActivity(),""+msg, Toast.LENGTH_LONG).show();
    }

}


