package com.ddj.dudujia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.rxjava.RxjavaUtil
import com.ddj.dudujia.rxjava.UITask
import com.ddj.dudujia.utils.SPUtil
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.concurrent.TimeUnit

/**
 * Created by hanshaobo on 15/10/2017.
 */
class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()

    }

    private fun initView() {
        tvUserType.hint = SPUtil.getString(StaticValue.SP_LOGIN_USERTYPE, "")

        if (SPUtil.getBoolean(StaticValue.SP_LOGIN_STATUS, false)) {
            loginOut.setTextColor(resources.getColor(R.color.black))
            loginOut.onClick {
                SPUtil.clear()
                SPUtil.setBoolean(StaticValue.SP_LOGIN_STATUS, false)
                SPUtil.setString(StaticValue.SP_LOGIN_PHONE, "")
                setResult(Activity.RESULT_OK)
                RxjavaUtil.doInUIThreadDelay(object : UITask<Any>() {
                    override fun doInUIThread() {
                        ToastUtil.showToast(this@SettingActivity, "退出登录")
                        myFinish()
                    }

                }, 500, TimeUnit.MILLISECONDS)
            }
        } else {
            loginOut.setTextColor(resources.getColor(R.color.gray99))
            loginOut.onClick { }
        }

        rlModifyNickname.onClick {
            startActivity(Intent(this@SettingActivity, ModifyNicknameActivity::class.java))
        }

        tvVersion.text = "v" + CommonMethod.getVersionName()
    }

    override fun onResume() {
        super.onResume()
        tvNickname.hint = SPUtil.getString(StaticValue.SP_LOGIN_USERNAME, "")
    }
}