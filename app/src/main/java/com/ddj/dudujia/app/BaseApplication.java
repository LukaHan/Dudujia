package com.ddj.dudujia.app;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.ddj.dudujia.R;
import com.ddj.dudujia.utils.SPUtil;
import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallListener;
import com.fm.openinstall.model.AppData;
import com.fm.openinstall.model.Error;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

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

        if(isMainProcess()){
            OpenInstall.init(this);
        }
        Bugly.init(getApplicationContext(), getString(R.string.bugly), true);
        initOpen();
    }

    private void initOpen() {
        OpenInstall.getInstall(new AppInstallListener() {
            @Override
            public void onInstallFinish(AppData appData, Error error) {
                if (error == null) {
                    if (appData == null || appData.isEmpty()) return;
                    //获取自定义数据
                    Log.d("OpenInstall", "getInstall : bindData = " + appData.getData());
                    //获取渠道数据
                    Log.d("OpenInstall", "getInstall : channelCode = " + appData.getChannel());
                } else {
                    Log.e("OpenInstall", "getInstall : errorMsg = " + error.toString());
                }
            }
        });

    }

    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
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
