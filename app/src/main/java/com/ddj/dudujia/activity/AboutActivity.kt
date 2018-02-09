package com.ddj.dudujia.activity

import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.common.CommonMethod
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by hanshaobo on 15/10/2017.
 */
class AboutActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        tvVersion.text = "v"+ CommonMethod.getVersionName()
    }
}