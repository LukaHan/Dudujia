package com.ddj.dudujia.activity

import android.os.Bundle
import android.text.TextUtils
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.LoginBean
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.SPUtil
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_modify_nickname.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 15/10/2017.
 */
class ModifyNicknameActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_nickname)
        initView()

    }

    private fun initView() {
        etNickname.hint = SPUtil.getString(StaticValue.SP_LOGIN_USERNAME,"")
        tvModifyNickname.onClick {
            var username = etNickname.text.toString()
            if(TextUtils.isEmpty(username)){
                return@onClick
            }
            HttpMethods.createService().modifyUsername("do_modifyusername", SPUtil.getString(StaticValue.SP_LOGIN_USER_ID,""),username)
                    .compose(TransformUtils.defaultSchedulers())
                    .subscribe(object : HttpResultSubscriber<HttpResult<LoginBean>>() {
                        override fun onNext(t: HttpResult<LoginBean>) {
                            super.onNext(t)
                            if(t.status==0){
                                ToastUtil.showToast("修改成功")
                                SPUtil.setString(StaticValue.SP_LOGIN_USERNAME,t.result.data.username)
                                myFinish()
                            }
                        }
                    })
        }
    }
}