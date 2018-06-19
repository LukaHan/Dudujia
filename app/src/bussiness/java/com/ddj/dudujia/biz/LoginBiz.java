package com.ddj.dudujia.biz;

import com.ddj.dudujia.bean.CodeBean;
import com.ddj.dudujia.bean.LoginBean;
import com.ddj.dudujia.common.CommonMethod;
import com.ddj.dudujia.http.HttpResult;
import com.ddj.dudujia.http.HttpMethods;
import com.ddj.dudujia.http.HttpResultSubscriber;
import com.ddj.dudujia.http.TransformUtils;

import org.jetbrains.annotations.NotNull;

public class LoginBiz implements ILoginBiz {

    @Override
    public void getCode(String phone, final OnGetcodeListener listener) {
        if(!CommonMethod.isMobile(phone)){
            listener.getCodeFail("请输入正确的手机号码");
            return;
        }
        HttpMethods.Companion.createService().getCode("get_code", phone)
                .compose(TransformUtils.Companion.<HttpResult<CodeBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<HttpResult<CodeBean>>() {
                    @Override
                    public void onNext(HttpResult<CodeBean> codeBeanHttpResult) {
                        super.onNext(codeBeanHttpResult);
                        listener.getCodeSuccess(codeBeanHttpResult.info);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        listener.getCodeFail(e.getMessage());
                    }
                });
    }

    @Override
    public void login(String phone, String code, final OnLoginListener listener) {
        HttpMethods.Companion.createService().doLogin("do_login",phone,code)
                .compose(TransformUtils.Companion.<HttpResult<LoginBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<HttpResult<LoginBean>>() {
                    @Override
                    public void onNext(HttpResult<LoginBean> loginBeanHttpResult) {
                        super.onNext(loginBeanHttpResult);
                        listener.loginSuccess(loginBeanHttpResult.result);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        super.onError(e);
                        listener.loginFailed(e.getMessage());
                    }
                });
    }
}
