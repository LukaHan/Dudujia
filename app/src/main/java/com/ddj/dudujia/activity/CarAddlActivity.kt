package com.ddj.dudujia.activity

import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseBean
import com.ddj.dudujia.bean.LoginBean
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.SPUtil
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_car_add.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 17/01/2018.
 */
class CarAddlActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_add)
        initView()
    }

    private fun initView() {
        btQuery.onClick {
            var plate = etLicensePlate.text.toString()
            var type: String = if (cbCarSamll.isChecked) {
                "0"
            } else {
                "1"
            }
            var engine = etEngineNumber.text.toString()
            var vin = etVin.text.toString()

            HttpMethods.createService()
                    .doAddMyCar("do_addmycar", SPUtil.getString(StaticValue.USER_ID, ""), plate, type, engine, vin)
                    .compose(TransformUtils.defaultSchedulers())
                    .subscribe(object : HttpResultSubscriber<HttpResult<BaseBean>>() {
                        override fun onNext(t: HttpResult<BaseBean>) {
                            super.onNext(t)
                            if (t.status == 0) {
                                ToastUtil.showToast("添加成功")
                            }
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                            ToastUtil.showToast("添加失败")
                        }
                    })
        }
    }

}