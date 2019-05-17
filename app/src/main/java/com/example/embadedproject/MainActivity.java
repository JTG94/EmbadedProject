package com.example.embadedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.embadedproject.model.MonthValue;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
        BottomNavigationView bottomNavigationView;
        private TokenManager tokenManager;
        private DrawerLayout mdrawerlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.navigation_month);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        tokenManager = new TokenManager(getApplicationContext());


        Intent intent = getIntent();
        // String token = ((Intent) intent).getExtras().getString("token");
        String token = tokenManager.getto();
        final APIcall apiCall = RetroClass.getApICall();
        //Call<MonthValue> maincall = apiCall.Mainreq(token);
        Call<MonthValue> maincall = apiCall.Mainreq(token);

        maincall.enqueue(new Callback<MonthValue>() {
            @Override
            public void onResponse(Call<MonthValue> call, Response<MonthValue> response) {
                if (response.isSuccessful()) {
                    MonthValue monthValue = response.body();


                    /*JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(json);
                    boolean success = element.getAsJsonObject().get("success").getAsBoolean();*/
                    /*  try {*/

                           /* Message message = response.body();
                            //String status = response.body().toString();*/
                    if (response.code() == 200) {
                        // showToast(""+status);
                        // showToast(""+response.code());
                        // showToast("" +monthValue.getMonthValue().toString());
                                /*Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(loginIntent);*/

                    }
                }else{

                    if (response.code() == 500) {

                        //    showToast("서버오류");
                    }else if(response.code()==501){
                    }else if(response.code()==502){
                    }
                }
            }
                            /*else if(response.code()==500){
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("계정을 다시 확인하세요.").setNegativeButton("다시시도", null).create();
                                dialog.show();
                            }*/
                      /*  }catch(Exception e){
                            if(response.code()==500){
                                showToast("500 코드 인지"+message.getMessage().toString());
                            }


                            e.printStackTrace();
                        }*/

            /* }*/

            @Override
            public void onFailure(Call<MonthValue> call, Throwable t) {
                showToast("Failer에서 응답!");
            }
        });




    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.out) {
            Intent outitent = new Intent(getApplicationContext(),UserOutActivity.class);
            startActivity(outitent);
        } else if (id == R.id.question) {
            Intent qitent = new Intent(getApplicationContext(),QuestionActivity.class);
            startActivity(qitent);
        } else if (id == R.id.logout) {
            Intent intent = new Intent(getApplication(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                    Intent.FLAG_ACTIVITY_CLEAR_TASK|
                    Intent.FLAG_ACTIVITY_NO_HISTORY);   // 액티비티의 플래그 설정

            startActivity(intent);

        }

     switch(item.getItemId()){
            case R.id.navigation_month:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.container, first_viewFrgment).commit();
                return true;
            case R.id.navigation_money:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.container, second_viewFrgment).commit();
                return true;
            case R.id.navigation_chart:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in,R.anim.fade_out).replace(R.id.container, third_viewFrgment).commit();
                return true;

        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }
    First_View first_viewFrgment = new First_View();
    Second_View second_viewFrgment = new Second_View();
    Third_View third_viewFrgment = new Third_View();




}
