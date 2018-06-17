package com.ddj.dudujia.biz;

public interface ILoginChooseBiz {
    void phoneLogin(OnLoginChooseListerner listerner);
    void closeActivity(OnCloseListener listener);
}
