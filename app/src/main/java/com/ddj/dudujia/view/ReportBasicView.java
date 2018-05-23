package com.ddj.dudujia.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.ddj.dudujia.R;

public class ReportBasicView extends LinearLayout {
    public ReportBasicView(Context context) {
        this(context,null);
    }

    public ReportBasicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ReportBasicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_report_basic,this);

    }
}
