package com.ddj.dudujia.activity

import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity

import kotlinx.android.synthetic.main.activity_car_history.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 17/01/2018.
 */
class CarHistoryReportActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_history_report)
        initView()
    }

    private fun initView() {
//        btQuery.onClick {
//            if (etVin.text.toString().length < 17) {
//                ToastUtil.showToast(getString(R.string.vin_error))
//                return@onClick
//            }
//            ToastUtil.showToast(getString(R.string.vin_query_error))
//        }

//        HttpMethods.createService()
//                .doIlleagalQuery("do_Illegalinquiry", phonenumber, code)
//                .compose(TransformUtils.defaultSchedulers())
//                .subscribe(object : HttpResultSubscriber<HttpResult<LoginBean>>() {
//                }
    }

}