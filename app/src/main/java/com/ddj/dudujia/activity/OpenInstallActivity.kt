package com.ddj.dudujia.activity

import android.content.Intent
import android.os.Bundle
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.fm.openinstall.OpenInstall
import com.fm.openinstall.listener.AppInstallListener
import com.fm.openinstall.listener.AppWakeUpAdapter
import com.fm.openinstall.model.AppData
import kotlinx.android.synthetic.main.activity_openinstall.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 27/02/2018.
 */
class OpenInstallActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_openinstall)
        initData()

        OpenInstall.getInstall(AppInstallListener { appData, error ->
            tvData.text = "获取到渠道包参数:"+appData.data
        })
    }

    private fun initData() {
        OpenInstall.getWakeUp(intent, wakeUpAdapter)
        btRegister.onClick {
            OpenInstall.reportRegister()
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        OpenInstall.getWakeUp(intent, wakeUpAdapter)
    }

    private var wakeUpAdapter: AppWakeUpAdapter = object : AppWakeUpAdapter() {
        override fun onWakeUp(appData: AppData) {
            //获取渠道数据
            val channelCode = appData.getChannel()
            //获取绑定数据
            val bindData = appData.getData()
            tvData.text = "getWakeUp : wakeupData = " + appData.toString()
        }
    }
}