package com.ddj.dudujia.presenter;

import com.ddj.dudujia.bean.LoginBean;
import com.ddj.dudujia.biz.ILoginBiz;
import com.ddj.dudujia.biz.LoginBiz;
import com.ddj.dudujia.biz.OnGetcodeListener;
import com.ddj.dudujia.biz.OnLoginListener;
import com.ddj.dudujia.view.ICodeLoginView;

public class LoginPresenter {
    private ILoginBiz loginBiz;
    private ICodeLoginView codeLoginView;

    public LoginPresenter(ICodeLoginView codeLoginView) {
        this.codeLoginView = codeLoginView;
        loginBiz = new LoginBiz();
    }

    public void getCode() {
        loginBiz.getCode(codeLoginView.getPhone(), new OnGetcodeListener() {

            @Override
            public void getCodeSuccess(String message) {
//                codeLoginView.showLoading();
                codeLoginView.showCountDown();
                codeLoginView.toSuccessActivity();
            }

            @Override
            public void getCodeFail(String message) {
                codeLoginView.hideLoading();
                codeLoginView.showFailed(message);
            }
        });
    }

    public void doLogin() {
        loginBiz.login(codeLoginView.getPhone(), codeLoginView.getCode(), new OnLoginListener() {
            @Override
            public void loginSuccess(LoginBean loginBean) {
                codeLoginView.hideLoading();
                codeLoginView.toSuccessActivity();
            }

            @Override
            public void loginFailed(String message) {
                codeLoginView.hideLoading();
            }
        });

    }
}
