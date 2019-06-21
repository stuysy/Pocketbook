package com.example.ysy.pocketbook;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    LinkedList<MainFragment> fragments = new LinkedList<>();
    LinkedList<String> dates = new LinkedList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    //初始化所有的Fragment
    private void initFragments(){
        dates = GlobalUtil.getInstance().databaseHelper.getAvaliableDate();

        if(!dates.contains(DateUtil.getFormattedDate())){
            dates.addLast(DateUtil.getFormattedDate());//新的一天第一次打开时，创建一个日期在链表末尾
        }

        for(String date:dates){
            MainFragment fragment = new MainFragment(date);//用dates初始化fragment
            fragments.add(fragment);
        }
    }

    public void reload(){//每添加一条记录返回主页面时时执行刷新
            for (MainFragment fragment:fragments){
                fragment.reload();
            }
        }


    public int getLastIndex(){
        return fragments.size() - 1;

    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public String getDateStr(int index){
        return dates.get(index);
    }

    public int getTotalScore(int i){
        return fragments.get(i).getTotalScore();
    }

}
