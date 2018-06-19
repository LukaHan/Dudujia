package com.ddj.dudujia.app;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.ddj.dudujia.R;
import com.ddj.dudujia.utils.SPUtil;
import com.fm.openinstall.OpenInstall;
import com.fm.openinstall.listener.AppInstallListener;
import com.fm.openinstall.model.AppData;
import com.fm.openinstall.model.Error;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * Created by hanshaobo on 15/10/2017.
 */

public class SampleApplicationLike extends DefaultApplicationLike {
    private static int mMainThreadId;// 主线程Id
    private static Handler mHandler;// Handler对象

    private static SampleApplicationLike instance;

    public SampleApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        SampleApplicationLike.setMainThreadId(android.os.Process.myTid());
        SampleApplicationLike.setHandler(new Handler());

        //初始化sp
        SPUtil.init(getApplication());
        Bugly.init(getApplication(), getApplication().getString(R.string.bugly), true);

        if(isMainProcess()){
//            OpenInstall.init(getApplication());
        }
//        initOpen();
    }

    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplication().getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
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

    public static SampleApplicationLike getInstance() {
        return instance;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(int mMainThreadId) {
        SampleApplicationLike.mMainThreadId = mMainThreadId;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static void setHandler(Handler mHandler) {
        SampleApplicationLike.mHandler = mHandler;
    }


    public void share(String path) {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        oks.setImagePath(path);
//
//        //自定义
//        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
//            @Override
//            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
//                //Wechat    WechatMoments   SinaWeibo   QQ
//                String name = platform.getName();
//                LogUtil.d("cmlog", "name:" + name);
//            }
//        });
//
//        // 启动分享GUI
//        oks.show(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }


}
