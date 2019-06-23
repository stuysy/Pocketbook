package com.example.ysy.pocketbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener,CategoryRecyclerAdapter.OnCategoryClickListener {
    private static String TAG = "AddRecordActivity";
    private String userInput = "";
    private TextView scoreText;
    private EditText editText;

    private RecyclerView recyclerView;
    private CategoryRecyclerAdapter adapter;

    private String category = "Read";
    private Record.RecordType type = Record.RecordType.RECORD_TYPE_INCREASE;
    private String remark = category;

    public Record record = new Record();

    private boolean inEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        findViewById(R.id.keyboard_one).setOnClickListener(this);
        findViewById(R.id.keyboard_two).setOnClickListener(this);
        findViewById(R.id.keyboard_three).setOnClickListener(this);
        findViewById(R.id.keyboard_four).setOnClickListener(this);
        findViewById(R.id.keyboard_five).setOnClickListener(this);
        findViewById(R.id.keyboard_six).setOnClickListener(this);
        findViewById(R.id.keyboard_seven).setOnClickListener(this);
        findViewById(R.id.keyboard_eight).setOnClickListener(this);
        findViewById(R.id.keyboard_nine).setOnClickListener(this);
        findViewById(R.id.keyboard_zero).setOnClickListener(this);

        //getSupportActionBar().setElevation(0);

        scoreText = (TextView) findViewById(R.id.textView_score);
        editText = findViewById(R.id.editText);
        editText.setText(remark);

        handleBackspace();
        handleDone();
        handleTypeChange();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CategoryRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);//一行2个
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.notifyDataSetChanged();

        adapter.setOnCategoryClickListener(this);

        Record record = (Record) getIntent().getSerializableExtra("record");
        if (record != null) {
            inEdit = true;
            this.record = record;
        }


    }

    //处理两种类别转换按键的方法
    private void handleTypeChange(){
        findViewById(R.id.keyboard_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == Record.RecordType.RECORD_TYPE_INCREASE){
                    type = Record.RecordType.RECORD_TYPE_DECREASE;
                }else {
                    type = Record.RecordType.RECORD_TYPE_INCREASE;
                }
                adapter.changeType(type);
                category = adapter.getSelected();
            }
        });
    }

    //处理退格按键
    private void handleBackspace(){
        findViewById(R.id.keyboard_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userInput.length() > 0) {
                    userInput = userInput.substring(0, userInput.length() - 1);
                }
                updateAmountText();
            }
        });
    }

    //处理完成按键
    private void handleDone(){
        findViewById(R.id.keyboard_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userInput.equals("")){
                    int score = Integer.valueOf(userInput);

                    record.setScore(score);
                    if (type == Record.RecordType.RECORD_TYPE_INCREASE){
                        record.setType(1);
                    }else {
                        record.setType(2);
                    }
                    record.setCategory(adapter.getSelected());//得到category
                    record.setRemark(editText.getText().toString());

                    if (inEdit){
                        GlobalUtil.getInstance().databaseHelper.editRecord(record.getUuid(),record);
                    }else {
                        GlobalUtil.getInstance().databaseHelper.addRecord(record);
                    }
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(),"输入内容不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String input = button.getText().toString();
        Log.i(TAG, input);
        userInput += input;
        updateAmountText();
    }

    //更新TextView的方法
    private void updateAmountText() {
        if (userInput.equals("")) {
            scoreText.setText("00");
        }else {
            scoreText.setText(userInput);
        }
    }


    @Override
    public void onClick(String category) {
        this.category = category;
        Log.i(TAG, "category:"+category);
        editText.setText(category);
    }
}
