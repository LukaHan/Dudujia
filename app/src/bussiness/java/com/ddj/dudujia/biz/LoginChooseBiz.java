package com.ddj.dudujia.biz;

public class LoginChooseBiz implements ILoginChooseBiz{

    @Override
    public void phoneLogin(OnLoginChooseListerner listerner) {
        listerner.onPhoneLogin();
    }

    @Override
    public void closeActivity(OnCloseListener listener) {
        listener.onCloseActivity();
    }
}
