package com.ddj.dudujia.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.ddj.dudujia.R;
import com.ddj.dudujia.base.BaseActivity;

import org.jetbrains.annotations.Nullable;

/**
 * 销售业绩
 */
public class SalePerformanceActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sale);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

    }
}
