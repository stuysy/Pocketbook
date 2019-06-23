package com.example.ysy.pocketbook;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG ="MainActivity";
    private TickerView scoreText;
    private ViewPager viewPager;
    private MainViewPagerAdapter pagerAdapter;
    private TextView dayText;
    private int currentPagePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalUtil.getInstance().setContext(getApplicationContext());
        GlobalUtil.getInstance().mainActivity = this;
        getSupportActionBar().setElevation(0);

        scoreText = findViewById(R.id.score_text);
        scoreText.setCharacterLists(TickerUtils.provideNumberList());
        dayText = findViewById(R.id.day_text);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(pagerAdapter.getLastIndex());

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivityForResult(intent,1);//关闭此页面的时候刷新一次
            }
        });
        //updateHeader();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult:");
        pagerAdapter.reload();
        updateHeader();

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        Log.i(TAG, "onPageSelected: position = "+ i);
        Log.i(TAG, "COAT: " + pagerAdapter.getTotalScore(i));

        currentPagePosition = i;
        updateHeader();
    }
    @Override
    public void onPageScrollStateChanged(int i) {

    }


    public void updateHeader(){
        String score = String.valueOf(pagerAdapter.getTotalScore(currentPagePosition))+"";
        scoreText.setText(score);
        String date = pagerAdapter.getDateStr(currentPagePosition);
        dayText.setText(DateUtil.getWeekDay(date));
    }

}
