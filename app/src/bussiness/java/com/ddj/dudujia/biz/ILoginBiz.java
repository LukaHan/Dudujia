package com.ddj.dudujia.biz;

public interface ILoginBiz {
    void getCode(String phone, OnGetcodeListener listener);

    void login(String phone, String code, OnLoginListener listener);
}
