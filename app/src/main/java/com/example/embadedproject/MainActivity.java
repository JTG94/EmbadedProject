package com.example.embadedproject;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embadedproject.model.Budget;
import com.example.embadedproject.model.MonthValue;
import com.example.embadedproject.model.PrevCompare;
import com.example.embadedproject.remote.APIcall;
import com.example.embadedproject.remote.RetroClass;
import com.example.embadedproject.tokenmanager.TokenManager;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private SectionPagerAdapter mSectionPagerAdapter;
    private ViewPager mViewPager;
    private long time =0;
    private DrawerLayout mdrawerlayout;
    private TokenManager tokenManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Integer month= LocalDate.now().getMonthValue();
        Integer year=LocalDate.now().getYear();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setText("월 별");
        tabLayout.getTabAt(1).setText("일 별");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.navigation_month:

                        break;
                    case R.id.navigation_money:
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                         startActivity(intent);
                         finish();
                        break;
                    case R.id.navigation_chart:
                        Intent intent2 = new Intent(MainActivity.this, MainActivity3.class);
                        startActivity(intent2);
                        finish();
                        break;
                }

                return false;
            }
        });

        tokenManager = new TokenManager(getApplicationContext());


        Intent intent = getIntent();
        // String token = ((Intent) intent).getExtras().getString("token");
        String token = tokenManager.getto();
        final APIcall apiCall = RetroClass.getApICall();
        Call<MonthValue> monthcall=apiCall.MontlyList(token,year,month);
        Call<Budget> maincall = apiCall.requestBudget(token);
        monthcall.enqueue(new Callback<MonthValue>() {
            @Override
            public void onResponse(Call<MonthValue> call, Response<MonthValue> response) {
                TextView monthText = (TextView) findViewById(R.id.MainMonth);
                TextView monthpricetext = (TextView) findViewById(R.id.MonthPrice);
                TextView yeartext = findViewById(R.id.yearSave);
                if(response.code()==200) {
                    MonthValue monthinfo = response.body();
                    ListAdapter adapter = new ListAdapter(getApplicationContext(), month, monthinfo);
                    ListView listview = (ListView) findViewById(R.id.listVIew);
                    listview.setAdapter(adapter);

                    monthText.setText(month.toString());
                    monthpricetext.setText("" + monthinfo.month_price);
                    yeartext.setText(year.toString());
                }
                else if(response.code()==501) {
                    monthpricetext.setText("0");
                    NullListAdapter adapter = new NullListAdapter(getApplicationContext());
                    ListView listview = (ListView) findViewById(R.id.listVIew);
                    listview.setAdapter(adapter);
                    showToast("결과 없음");
                }

            }

            @Override
            public void onFailure(Call<MonthValue> call, Throwable t) {

            }
        });
        Call<PrevCompare> Prevcall=apiCall.PrevCompareCall(token,month);
        Prevcall.enqueue(new Callback<PrevCompare>() {
            @Override
            public void onResponse(Call<PrevCompare> call, Response<PrevCompare> response) {
                PrevCompare prv=response.body();
                TextView prevText=(TextView)findViewById(R.id.prevText);
                prevText.setText(prv.diff_price.toString()+"원");


            }

            @Override
            public void onFailure(Call<PrevCompare> call, Throwable t) {

            }
        });







    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment());
        adapter.addFragment(new Tab2Fragment());
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if(System.currentTimeMillis()-time>=2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "종료를 원하시면 한번더 눌러주세요!", Toast.LENGTH_LONG).show();

        }else if(System.currentTimeMillis()-time<2000) {
            finish();
            return;
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






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    void showToast(String msg)
    {
        Toast.makeText(this, ""+msg, Toast.LENGTH_LONG).show();
    }

}
