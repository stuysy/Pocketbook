package com.example.ysy.pocketbook;

import android.content.Context;

import java.util.LinkedList;
//全局资源类,单例模式
public class GlobalUtil {
    private static String TAG = "GlobalUtil";
    private static GlobalUtil instance;
    public RecordDatabaseHelper databaseHelper;
    public MainActivity mainActivity;
    private Context context;
    public LinkedList<CategoryResBean> incRes = new LinkedList<>();
    public LinkedList<CategoryResBean> decRes = new LinkedList<>();

    private static int [] incIconRes = {
            R.drawable.learn_white,
            R.drawable.skill_white,
            R.drawable.book_white,
            R.drawable.yinshi_white,
            R.drawable.sport_white,
            R.drawable.social_white,
            R.drawable.extra_white
    };
    private static int [] incIconResBlack = {
            R.drawable.learn,
            R.drawable.skill,
            R.drawable.book,
            R.drawable.yinshi,
            R.drawable.sport,
            R.drawable.social,
            R.drawable.extra
    };
    private static String[] incTitle = {"专业学习", "技能提升", "课外阅读", "健康饮食",
            "运动健身","社交", "其他"};

    private static int[] decIconRes = {
            R.drawable.aoye_white,
            R.drawable.game_white,
            R.drawable.food2_white,
            R.drawable.shuijiao_white};

    private static int[] decIconResBlack = {
            R.drawable.aoye,
            R.drawable.game,
            R.drawable.food2,
            R.drawable.shuijiao};


    private static String[] decTitle = {"熬夜","打游戏", "垃圾食品", "睡懒觉"};


    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
        databaseHelper = new RecordDatabaseHelper(context, RecordDatabaseHelper.DB_NAME, null, 1);

        for (int i = 0; i < incTitle.length; i++) {
            CategoryResBean res = new CategoryResBean();
            res.title = incTitle[i];
            res.resBlack = incIconResBlack[i];
            res.resWhite = incIconRes[i];
            incRes.add(res);
        }

        for (int i = 0; i < decTitle.length; i++) {
            CategoryResBean res = new CategoryResBean();
            res.title = decTitle[i];
            res.resBlack = decIconResBlack[i];
            res.resWhite = decIconRes[i];
            decRes.add(res);
        }
    }

    //实现创建一次数据库连接，不用重复创建
    public static GlobalUtil getInstance(){
        if (instance == null){//如果为空，则创建，否则返回最初创建的那个单例
            instance = new GlobalUtil();
            }
            return instance;
    }

    public int getResourceIcon(String category){
        for (CategoryResBean res:incRes){//遍历incRes
            if (res.title.equals(category)){
                return res.resWhite;
            }
        }
        for (CategoryResBean res:decRes){
            if (res.title.equals(category)){
                return res.resWhite;
            }
        }
        return incRes.get(0).resWhite;
    }
}
