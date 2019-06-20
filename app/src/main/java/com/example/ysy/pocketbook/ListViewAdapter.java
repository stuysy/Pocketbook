package com.example.ysy.pocketbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class ListViewAdapter extends BaseAdapter {

    private LinkedList<Record> records = new LinkedList<>();

    private LayoutInflater inflater;
    private Context context;

    public ListViewAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);//从上下文初始化
    }

    public void setData(LinkedList<Record> records){
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.cell_list_view,null);

            Record record = (Record) getItem(position);
            holder = new ViewHolder(convertView,record);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}

class ViewHolder{
    TextView remarkTV;
    TextView scoreTV;
    TextView timeTV;
    ImageView categoryIcon;

    public ViewHolder(View itemView, Record record){
        remarkTV = itemView.findViewById(R.id.textView_remark);
        scoreTV = itemView.findViewById(R.id.textView_score);
        timeTV = itemView.findViewById(R.id.textView_time);
        categoryIcon = itemView.findViewById(R.id.imageView_category);

        remarkTV.setText(record.getRemark());

        if (record.getType() == 1){
            scoreTV.setText("- "+ record.getScore());
        } else {
            scoreTV.setText("+ "+ record.getScore());
        }

        timeTV.setText(DateUtil.getFormattedTime(record.getTimeStamp()));
        categoryIcon.setImageResource(GlobalUtil.getInstance().getResourceIcon(record.getCategory()));
    }

}