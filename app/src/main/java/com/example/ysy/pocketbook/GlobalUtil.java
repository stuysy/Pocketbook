package com.example.ysy.pocketbook;

import android.content.Context;

import java.util.LinkedList;
//全局资源类
public class GlobalUtil {
    private static String TAG = "GlobalUtil";
    private static GlobalUtil instance;
    public RecordDatabaseHelper databaseHelper;
    private Context context;
    public LinkedList<CategoryResBean> costRes = new LinkedList<>();
    public LinkedList<CategoryResBean> earnRes = new LinkedList<>();

    private static int [] costIconRes = {
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
    private static int [] costIconResBlack = {
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
    private static String[] costTitle = {"General", "Food", "Shopping", "Entertain", "Transport",
            "Mobile","Gifts", "Housing", "Travel","Books", "Medical","Transfer"};

    private static int[] earnIconRes = {
            R.drawable.general_white,
            R.drawable.salary_white,
            R.drawable.redpocket_white,
            R.drawable.extra_white};

    private static int[] earnIconResBlack = {
            R.drawable.general,
            R.drawable.salary,
            R.drawable.redpocket,
            R.drawable.extra};

    private static String[] earnTitle = {"General",  "Salary","RedPocket","Extra"};


    public Context getContext(){
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
        databaseHelper = new RecordDatabaseHelper(context, RecordDatabaseHelper.DB_NAME, null, 1);

        for (int i = 0; i < costTitle.length; i++) {
            CategoryResBean res = new CategoryResBean();
            res.title = costTitle[i];
            res.resBlack = costIconResBlack[i];
            res.resWhite = costIconRes[i];
            costRes.add(res);
        }

        for (int i = 0; i < earnTitle.length; i++) {
            CategoryResBean res = new CategoryResBean();
            res.title = earnTitle[i];
            res.resBlack = earnIconResBlack[i];
            res.resWhite = earnIconRes[i];
            earnRes.add(res);
        }
    }

    //实现创建一次数据库连接，不用重复创建
    public static GlobalUtil getInstance(){
        if (instance == null){
            instance = new GlobalUtil();
            }
            return instance;
    }

    public int getResourceIcon(String category){
        for (CategoryResBean res:costRes){
            if (res.title.equals(category)){
                return res.resWhite;
            }
        }
        for (CategoryResBean res:earnRes){
            if (res.title.equals(category)){
                return res.resWhite;
            }
        }
        return costRes.get(0).resWhite;
    }


}
