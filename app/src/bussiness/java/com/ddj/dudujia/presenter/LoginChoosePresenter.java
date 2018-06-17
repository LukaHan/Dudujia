package com.ddj.dudujia.presenter;

import android.content.Intent;

import com.ddj.dudujia.activity.LoginActivity1;
import com.ddj.dudujia.base.BaseActivity;
import com.ddj.dudujia.biz.ILoginChooseBiz;
import com.ddj.dudujia.biz.LoginChooseBiz;
import com.ddj.dudujia.biz.OnCloseListener;
import com.ddj.dudujia.biz.OnLoginChooseListerner;

public class LoginChoosePresenter {
    private final BaseActivity context;
    private ILoginChooseBiz loginChooseBiz;


    public LoginChoosePresenter(BaseActivity context) {
        loginChooseBiz = new LoginChooseBiz();
        this.context = context;
    }

    public void phoneLogin() {
        loginChooseBiz.phoneLogin(new OnLoginChooseListerner() {
            @Override
            public void onPhoneLogin() {
                context.startActivity(new Intent(context, LoginActivity1.class));
            }
        });
    }

    public void closeActivity(){
        loginChooseBiz.closeActivity(new OnCloseListener() {
            @Override
            public void onCloseActivity() {
                context.finish();
            }
        });
    }
}
