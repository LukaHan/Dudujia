package com.ddj.dudujia.activity

import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.HealthReportBean
import kotlinx.android.synthetic.main.activity_report_basic.*

class ReportBasicActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_basic)

        initData()
    }

    private fun initData() {
        var data = intent.getSerializableExtra("data") as HealthReportBean.DataBean
        tvName.text = data.cjmc
        tvBrand.text = data.pp
        tvModel.text = data.cx
        tvPL.text = data.displacement
        tvPFBZ.text = data.emission
        tvYear.text = data.productionyear
    }
}