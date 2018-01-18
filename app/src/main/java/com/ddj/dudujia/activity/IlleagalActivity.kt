package com.ddj.dudujia.activity

import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.LoginBean
import com.ddj.dudujia.http.HttpResult
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import kotlinx.android.synthetic.main.activity_illeagal.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 17/01/2018.
 */
class IlleagalActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_illeagal)
        initView()
    }

    private fun initView() {
        btQuery.onClick {
//            checkParams()
        }

//        HttpMethods.createService()
//                .doIlleagalQuery("do_Illegalinquiry", phonenumber, code)
//                .compose(TransformUtils.defaultSchedulers())
//                .subscribe(object : HttpResultSubscriber<HttpResult<LoginBean>>() {
//                }
    }

}