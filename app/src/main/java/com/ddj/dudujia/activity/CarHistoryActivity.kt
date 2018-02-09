package com.ddj.dudujia.activity

import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.CheckVinBean
import com.ddj.dudujia.http.HttpResult
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_car_history.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 17/01/2018.
 */
class CarHistoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_history)
        initView()
    }

    private fun initView() {
        btQuery.onClick {
            var vin = etVin.text.toString()
            if (vin.length < 17) {
                ToastUtil.showToast(getString(R.string.vin_error))
                return@onClick
            }
            HttpMethods.createService()
                    .checkVin("do_checkvin", vin)
                    .compose(TransformUtils.defaultSchedulers())
                    .subscribe(object : HttpResultSubscriber<HttpResult<CheckVinBean>>() {
                        override fun onNext(t: HttpResult<CheckVinBean>) {
                            super.onNext(t)

                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                            ToastUtil.showToast(getString(R.string.vin_query_error))
                        }
                    })
        }
    }

}