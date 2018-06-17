package com.ddj.dudujia.activity

import android.os.Bundle

import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.presenter.LoginChoosePresenter
import kotlinx.android.synthetic.bussiness.activity_login_chooose.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class LoginActivity : BaseActivity() {
    private val presenter = LoginChoosePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_chooose)
        btPhoneLogin.onClick {
            presenter.phoneLogin()
        }
        ivClose.onClick {
            presenter.closeActivity()
        }
    }
}
