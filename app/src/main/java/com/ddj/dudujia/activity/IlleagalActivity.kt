package com.ddj.dudujia.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.http.HttpMethods
import com.ddj.dudujia.http.HttpResultSubscriber
import com.ddj.dudujia.http.TransformUtils
import com.ddj.dudujia.utils.ToastUtil

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
        btSelProvince.onClick {
            myStartActivityForResult(Intent(this@IlleagalActivity, ProvinceActivity::class.java), 100)
        }

        btQuery.onClick {
            var licensePlate = btSelProvince.text.toString() + etLicensePlate.text.toString()
            var engineNumber = etEngineNumber.text.toString()
            var vin = etVin.text.toString()
            if (licensePlate.length < 6) {
                ToastUtil.showToast(getString(R.string.plate_error))
                return@onClick
            }
            if (vin.length < 17) {
                ToastUtil.showToast(getString(R.string.vin_error))
                return@onClick
            }

            var carType = if (rbCarSmall.isSelected) {
                "0"
            } else {
                "1"
            }

            HttpMethods.createService()
                    .doIlleagalQuery("do_Illegalinquiry", CommonMethod.getUserId(), licensePlate, carType, engineNumber, vin)
                    .compose(TransformUtils.defaultSchedulers())
                    .subscribe(object : HttpResultSubscriber<HttpResult<BaseBean>>() {
                        override fun onNext(t: HttpResult<BaseBean>) {
                            super.onNext(t)
                            ToastUtil.showToast("查询成功")
                        }

                        override fun onError(e: Throwable) {
                            super.onError(e)
                            ToastUtil.showToast(e.message)
                        }
                    })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            var region = data?.getStringExtra("region")
            btSelProvince.text = region
        }
    }

}