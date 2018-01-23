package com.ddj.dudujia.activity

import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.ReportBasicBean
import com.ddj.dudujia.http.HttpResult
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.LogUtils
import kotlinx.android.synthetic.main.activity_car_basic.*

/**
 * Created by hanshaobo on 20/01/2018.
 */
class CarBasicActivity : BaseActivity() {
    private lateinit var reportid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_basic)
        initView()
        initData()
    }

    private fun initData() {

        HttpMethods.createService().getOfflineReport("get_offlinereport", reportid)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<ReportBasicBean>>() {
                    override fun onNext(t: HttpResult<ReportBasicBean>) {
                        super.onNext(t)
                        LogUtils.d("ttt:" + t.result.data.toString())
                        setData(t.result.data)
                    }
                })

    }

    private fun setData(data: ReportBasicBean.DataBean) {
        tvVin.text = data.vin
        tvPlate.text = data.licenseplate
        tv_registerdt.text = data.registerdt
        tv_limiteddt.text = data.limiteddt
        tv_manufacturer.text = data.manufacturer
        tv_vehiclename.text = data.vehiclemodel
        tv_level.text = data.level
        tv_vehiclemodel.text = data.vehiclemodel
        tv_mileage.text = data.mileage
        tv_displacement.text = data.displacement
        tv_vehicletype.text = data.vehicletype
        tv_passenger.text = data.passenger
        tv_bodycolore.text = data.bodycolor
        tv_interiorcolor.text = data.interiorcolor

        if ("0" == data.transmissionmode) {
            rb_transmissionmode.isChecked = true
        } else {
            rb_transmissionmode1.isChecked = true
        }
        if ("0" == data.drivingmode) {
            rb_drivingmode.isChecked = true
        } else {
            rb_drivingmode1.isChecked = true
        }
        if ("0" == data.useproperty) {
            rb_useproperty.isChecked = true
        } else {
            rb_useproperty1.isChecked = true
        }
        if ("0" == data.airsystem) {
            rb_airsystem.isChecked = true
        } else {
            rb_airsystem1.isChecked = true
        }

    }

    private fun initView() {
        reportid = intent.getStringExtra("reportid")


    }
}