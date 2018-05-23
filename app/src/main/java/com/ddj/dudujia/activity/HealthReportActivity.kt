package com.ddj.dudujia.activity

import android.content.Intent
import android.os.Bundle
import android.widget.SimpleAdapter
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.HealthReportBean
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.view.OnlineInfoView
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import kotlinx.android.synthetic.main.activity_health_report.*
import kotlinx.android.synthetic.main.layout_insurance.*
import kotlinx.android.synthetic.main.layout_report_title.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class HealthReportActivity : BaseActivity() {
    private lateinit var data: HealthReportBean.DataBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_report)

        initData()
        initListener()
    }

    private fun initListener() {
        tvAll.onClick {
            var intent = Intent(this@HealthReportActivity, ReportBasicActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
        tvMoreInsurance.onClick {

        }
        tvMoreOnline.onClick {
            var intent = Intent(this@HealthReportActivity, CarHistoryReportActivity::class.java)
//            intent.putExtra("data",data)
            startActivity(intent)
        }
        tvMore267.onClick {
            var intent = Intent(this@HealthReportActivity, JianceCenterActivity::class.java)
            intent.putExtra("reportid",data.testing.testingid)
            startActivity(intent)
        }

    }

    private fun initData() {
        HttpMethods.createService().getHealthReport("get_healthreport", "100000", "100000")
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<HealthReportBean>>() {
                    override fun onNext(t: HttpResult<HealthReportBean>) {
                        super.onNext(t)
                        setData(t.result.data)
                    }
                })
    }


    private fun setData(data: HealthReportBean.DataBean) {
        this.data = data
        tvBrand.text = data.pp + " " + data.cx
        tvVin.text = data.vin
        tvLicense.text = data.license

        tvXSLC.text = data.mileage + "km"
        tvCSYS.text = data.color
        tvPFBZ.text = data.emission
        tvPL.text = data.displacement


        setSummaryData(data)
        setInsurance(data.insurance)

        for (i in 0 until data.carhistory.summaryitems.size) {
            var item = data.carhistory.summaryitems[i]
            var onlineInfo = OnlineInfoView(this)
            onlineInfo.setItemData(item.item, item.level, item.project)
            llOnlineContainer.addView(onlineInfo)
        }
        for (i in 0 until data.testing.testingitems.size) {
            var item = data.testing.testingitems[i]
            var onlineInfo = OnlineInfoView(this)
            onlineInfo.setItemData(item.item, item.level, item.project)
            ll267Container.addView(onlineInfo)
        }
    }

    private fun setInsurance(data: HealthReportBean.DataBean.InsuranceBean) {
        tvCompensation.text = data.compensation
        tvRecording.text = data.recording
        tvCommercial.text = data.commercial
        tvCommercialNumber.text = data.commercialnumber
        tvCommercialTime.text = data.commercialtime
        tvCompulsory.text = data.compulsory
        tvCompulsoryNumber.text = data.compulsorynumber
        tvCompulsoryTime.text = data.compulsorytime
        tvInsuranceReportTime.text = data.insurancereporttime

    }

    private fun setSummaryData(data: HealthReportBean.DataBean) {
        var dataList = ArrayList<Map<String, Any>>()
        for (i in 0 until data.summary.size) {
            var map = HashMap<String, Any>()
            map.put("t", data.summary[i].times as Any)
            map.put("item", data.summary[i].item as Any)
            when (data.summary[i].level) {
                "1" -> {
                    map.put("img", R.mipmap.ic_rep_tan1)
                }
                "2" -> {
                    map.put("img", R.mipmap.ic_rep_tan2)
                }
                "3" -> {
                    map.put("img", R.mipmap.ic_rep_tan3)
                }
            }
            dataList.add(map)
        }
        val from = arrayOf("item", "img")
        val to = intArrayOf(R.id.tvDes, R.id.ivSummaryLevel)

        gvSummary.adapter = SimpleAdapter(this, dataList, R.layout.item_grid_summary, from, to)
    }
}