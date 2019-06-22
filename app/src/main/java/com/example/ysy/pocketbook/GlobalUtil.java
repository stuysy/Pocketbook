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
            R.drawable.general_white,
            R.drawable.food_white,
            R.drawable.shopping_white,
            R.drawable.entertain_white,
            R.drawable.transport_white,
            R.drawable.mobile_white,
            R.drawable.gift_white,
            R.drawable.housing_white,
            R.drawable.travel_white,
            R.drawable.book_white,
            R.drawable.medical_white,
            R.drawable.transfer_white
    };
    private static int [] incIconResBlack = {
            R.drawable.general,
            R.drawable.food,
            R.drawable.shopping,
            R.drawable.entertain,
            R.drawable.transport,
            R.drawable.mobile,
            R.drawable.gift,
            R.drawable.housing,
            R.drawable.travel,
            R.drawable.book,
            R.drawable.medical,
            R.drawable.transfer
    };
    private static String[] incTitle = {"General", "Food", "Shopping", "Entertain", "Transport",
            "Mobile","Gifts", "Housing", "Travel","Books", "Medical","Transfer"};

    private static int[] decIconRes = {
            R.drawable.general_white,
            R.drawable.salary_white,
            R.drawable.redpocket_white,
            R.drawable.extra_white};

    private static int[] decIconResBlack = {
            R.drawable.general,
            R.drawable.salary,
            R.drawable.redpocket,
            R.drawable.extra};


    private static String[] decTitle = {"General",  "Salary","RedPocket","Extra"};


    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
        databaseHelper = new RecordDatabaseHelper(context, RecordDatabaseHelper.DB_NAME, null, 1);

        for (int i = 0; i < incTitle.length; i++) {
            CategoryResBean res = new CategoryResBean();
            res.title = incTitle[i];
            res.resBlack = decIconResBlack[i];
            res.resWhite = incIconRes[i];
            incRes.add(res);
        }

        for (int i = 0; i < decTitle.length; i++) {
            CategoryResBean res = new CategoryResBean();
            res.title = decTitle[i];
            res.resBlack = incIconResBlack[i];
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
