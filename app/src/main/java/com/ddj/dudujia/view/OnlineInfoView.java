package com.ddj.dudujia.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ddj.dudujia.R;

public class OnlineInfoView extends LinearLayout {
    private TextView tvItem;
    private TextView tvProject;
    private ImageView ivLevel;

    public OnlineInfoView(Context context) {
        this(context,null);
    }

    public OnlineInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OnlineInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View v = LayoutInflater.from(context).inflate(R.layout.layout_item_online,this);
        tvItem = v.findViewById(R.id.tvItem);
        ivLevel = v.findViewById(R.id.ivLevel);
        tvProject = v.findViewById(R.id.tvProject);
    }

    public void setItemData(String item,String level,String project){
        tvItem.setText(item);
        tvProject.setText(project);
        ivLevel.setImageResource(level.equals("3")?R.drawable.circle_red :R.drawable.circle_yellow);
    }
}
