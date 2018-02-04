package com.ddj.dudujia.activity

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import com.alipay.sdk.app.PayTask
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseBean
import com.ddj.dudujia.bean.AliBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.SPUtil
import com.ddj.dudujia.utils.alipay.PayResult
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_jiance.*
import kotlinx.android.synthetic.main.activity_jiance_center.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 20/01/2018.
 */
class JianceCenterActivity : BaseActivity() {
    private val SDK_PAY_FLAG: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jiance_center)
        initView()
    }

    private fun initView() {
        rlBase.onClick {


        }

//        btYuyue.onClick {
//            var phone = etPhone.text.toString()
//            var code = etCode.text.toString()
//            var model = etModel.text.toString()
//            var vin = etVin.text.toString()
//            var time = etTime.text.toString()
//            var address = etAddress.text.toString()
//
//            if (!CommonMethod.isMobile(phone)) {
//                ToastUtil.showToast(getString(R.string.phone_error))
//                return@onClick
//            }
//            if (vin.length != 17) {
//                ToastUtil.showToast(getString(R.string.vin_error))
//                return@onClick
//            }
//
//            //调支付接口
//
//            var map = HashMap<String, String>()
//            map.put("userid", SPUtil.getString(StaticValue.SP_LOGIN_USER_ID, ""))
//            map.put("paytype", "APP")
//            map.put("productname", getString(R.string.phone_error))
//            //
//            map.put("totalfee", "APP")
//            map.put("phone", phone)
//            map.put("code", code)
//            map.put("carmodel", model)
//            map.put("vin", vin)
//            map.put("time", time)
//            map.put("address", address)
//            //
//            map.put("repaystrorder", "repaystrorder")
//
//            HttpMethods.createService().doPayForAlipay("do_payforalipay", map)
//                    .compose(TransformUtils.defaultSchedulers())
//                    .subscribe(object : HttpResultSubscriber<HttpResult<BaseBean>>() {
//                        override fun onNext(t: HttpResult<BaseBean>) {
//                            super.onNext(t)
//
//                        }
//                    })
//
//        }
    }
}