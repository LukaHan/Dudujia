package com.ddj.dudujia.view;

public interface ICodeLoginView {
    String getPhone();
    String getCode();

    void showLoading();
    void hideLoading();

    void showCountDown();

    void toSuccessActivity();
    void showFailed(String message);
}
