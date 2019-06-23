package com.example.ysy.pocketbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder>{
    private LayoutInflater layoutInflater;
    public Context context;
    private LinkedList<CategoryResBean> cellList = GlobalUtil.getInstance().incRes;

    public String getSelected() {
        return selected;
    }

    private String selected = "";

    public CategoryRecyclerAdapter(Context context){
        //GlobalUtil.getInstance().setContext(context);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        selected = cellList.get(0).title;
    }

    public void setOnCategoryClickListener(CategoryRecyclerAdapter.OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    private OnCategoryClickListener onCategoryClickListener;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.cell_category,viewGroup,false);
        CategoryViewHolder myViewHolder = new CategoryViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        final CategoryResBean res = cellList.get(i);
        categoryViewHolder.imageView.setImageResource(res.resBlack);
        categoryViewHolder.textView.setText(res.title);

        categoryViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //每被点击一下就更新一下RecyclerView的值
                selected = res.title;
                notifyDataSetChanged();

                if (onCategoryClickListener != null){
                    onCategoryClickListener.onClick(res.title);
                }
            }
        });

        if (categoryViewHolder.textView.getText().toString().equals(selected)){
            categoryViewHolder.background.setBackgroundResource(R.drawable.bg_edit_text);
        }else {
            categoryViewHolder.background.setBackgroundResource(R.color.colorPrimary);
        }

    }

    public void changeType(Record.RecordType type){
        if (type == Record.RecordType.RECORD_TYPE_INCREASE){
            cellList = GlobalUtil.getInstance().incRes;
        }else {
            cellList = GlobalUtil.getInstance().decRes;
        }
        selected = cellList.get(0).title;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cellList.size();
    }

    //为CategoryRecyclerAdapter adapter编写一个接口
    public interface OnCategoryClickListener{
        void onClick(String category);
    }
}

class CategoryViewHolder extends RecyclerView.ViewHolder{

    RelativeLayout background;
    ImageView imageView;
    TextView textView;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        background = itemView.findViewById(R.id.cell_background);
        imageView = itemView.findViewById(R.id.pic);
        textView = itemView.findViewById(R.id.txt);
    }
}