package com.ddj.dudujia.biz;

import com.ddj.dudujia.bean.LoginBean;

public interface OnLoginListener {
    void loginSuccess(LoginBean loginBean);
    void loginFailed(String message);
}
