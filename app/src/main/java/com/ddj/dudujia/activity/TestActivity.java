package com.ddj.dudujia.activity;

import android.os.Bundle;

import com.ddj.dudujia.R;
import com.ddj.dudujia.base.BaseActivity;
import com.ddj.dudujia.utils.PhoneInfoUtils;
import com.first.basket.utils.LogUtils;

import org.jetbrains.annotations.Nullable;

public class TestActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        ImageView ivImage = findViewById(R.id.ivImg);
//        ivImage.setImageBitmap(null);
//        ImageUtils.showImg(this, "", ivImage);


        PhoneInfoUtils phoneInfoUtils = new PhoneInfoUtils(this);
        String phone = phoneInfoUtils.getNativePhoneNumber();
        LogUtils.Companion.d("phone:" + phone);

    }
}
