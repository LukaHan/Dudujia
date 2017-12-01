package com.ddj.dudujia.app;

import android.os.Handler;
import android.support.multidex.MultiDexApplication;

import com.ddj.dudujia.R;
import com.ddj.dudujia.utils.SPUtil;
import com.tencent.bugly.Bugly;

/**
 * Created by hanshaobo on 15/10/2017.
 */

public class BaseApplication extends MultiDexApplication {
    private static int mMainThreadId;// 主线程Id
    private static Handler mHandler;// Handler对象

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        BaseApplication.setMainThreadId(android.os.Process.myTid());
        BaseApplication.setHandler(new Handler());

        //初始化sp
        SPUtil.init(this);
        Bugly.init(getApplicationContext(), getString(R.string.bugly), true);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(int mMainThreadId) {
        BaseApplication.mMainThreadId = mMainThreadId;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static void setHandler(Handler mHandler) {
        BaseApplication.mHandler = mHandler;
    }
}
