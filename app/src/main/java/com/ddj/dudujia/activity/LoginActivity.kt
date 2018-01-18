package com.ddj.dudujia.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.CodeBean
import com.ddj.dudujia.bean.LoginBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.CountDownUtil
import com.ddj.dudujia.utils.SPUtil
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.LogUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by hanshaobo on 15/10/2017.
 */
class LoginActivity : BaseActivity(), View.OnClickListener {
    private val mProgressDialog: ProgressDialog? = null

    override fun onClick(p0: View?) {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initData()
        initListener()

    }

    private fun initListener() {
        btSendCode.onClick {
            if (!CommonMethod.isMobile(etPhone.getText().toString())) {
                ToastUtil.showToast(this@LoginActivity, "请输入正确的手机号")
                return@onClick
            }
            getLoginVerifyCode(etPhone.getText().toString())
        }

        btLogin.onClick {
            doLogin(etPhone.text.toString(), etCode.text.toString())
        }
    }

    private fun initData() {

    }

    private fun initView() {
//        titleView = findViewById(R.id.titleView)
//        titleView.setBackgroundColor(resources.getColor(R.color.colorLogin))
    }


    private fun getLoginVerifyCode(phonenumber: String) {

        HttpMethods.createService()
                .getCode("get_code", phonenumber)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<CodeBean>>() {
                    override fun onNext(t: HttpResult<CodeBean>) {
                        super.onNext(t)
                        countDown()
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                    }
                })
    }

    private fun doLogin(phonenumber: String, code: String) {
        HttpMethods.createService()
                .doLogin("do_login", phonenumber, code)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<LoginBean>>() {
                    override fun onNext(t: HttpResult<LoginBean>) {
                        super.onNext(t)
                        if (t.status == 0) {
                            ToastUtil.showToast(this@LoginActivity, "登录成功")

                            SPUtil.setBoolean(StaticValue.SP_LOGIN_STATUS, true)
                            SPUtil.setString(StaticValue.SP_LOGIN_PHONE, t.result.data.phone)
                            SPUtil.setString(StaticValue.USER_ID, t.result.data.userid)
                            setResult(Activity.RESULT_OK)
                            CommonMethod.hideKeyboard(etCode)
                            Handler().postDelayed({ finish() }, 1000)
                        } else {
                            ToastUtil.showToast(this@LoginActivity, t.info)
                        }
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        ToastUtil.showToast(this@LoginActivity, e.message.toString())
                    }
                })
    }

    private fun countDown() {
        setButtonStatus(false)
        CountDownUtil.countDown(120, object : CountDownUtil.onCountDownListener {
            override fun onCountDownNext(integer: Int?) {
                btSendCode.text = integer.toString() + "秒后重新发送"
            }

            override fun onCountDownError(e: Throwable?) {

            }

            override fun onCountDownComplete() {
                countDownComplete()
            }
//            }
        })
    }

    private fun setButtonStatus(isComplete: Boolean) {
        if (isComplete) {
            btSendCode.background = resources.getDrawable(R.color.colorMain)
            btSendCode.setTextColor(resources.getColor(R.color.white))
            btSendCode.isClickable = true
            btSendCode.text = "获取验证码"
            btSendCode.setOnClickListener(this)
        } else {
            btSendCode.background = resources.getDrawable(R.color.text_bg)
            btSendCode.setTextColor(resources.getColor(R.color.gray99))
            btSendCode.isClickable = false
            btSendCode.setOnClickListener(null)
        }
    }

    private fun countDownComplete() {
        setButtonStatus(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && (requestCode == 101 || requestCode == 102)) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}