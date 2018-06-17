//package com.ddj.dudujia.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//
//import com.ddj.dudujia.R;
//import com.ddj.dudujia.base.BaseActivity;
//import com.ddj.dudujia.presenter.LoginPresenter;
//import com.ddj.dudujia.view.ICodeLoginView;
//import com.first.basket.utils.ToastUtil;
//
//import org.jetbrains.annotations.Nullable;
//
//public class LoginActivity extends BaseActivity implements ICodeLoginView {
//
//    private EditText etPhone;
//    private EditText etCode;
//
//    private LoginPresenter presenter = new LoginPresenter(this);
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        initView();
//    }
//
//    private void initView() {
//        etPhone = findViewById(R.id.etPhone);
//        etCode = findViewById(R.id.etCode);
//
//        findViewById(R.id.btSendCode).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.getCode();
//            }
//        });
//        findViewById(R.id.btLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.doLogin();
//            }
//        });
//    }
//
//    @Override
//    public String getPhone() {
//        return etPhone.getText().toString();
//    }
//
//    @Override
//    public String getCode() {
//        return etCode.getText().toString();
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void showCountDown() {
//
//    }
//
//    @Override
//    public void toSuccessActivity() {
//        ToastUtil.INSTANCE.showToast("成功");
//    }
//
//    @Override
//    public void showFailed(String message) {
//        ToastUtil.INSTANCE.showToast(message);
//
//    }
//}
