package com.ddj.dudujia.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_main_bus.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainBusActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_bus)
        initView()
    }

    private fun initView() {
        ivAvatar.onClick {
            if(CommonMethod.isLogin()){
                myStartActivity(Intent(this@MainBusActivity,MineBusActivity::class.java))
            }else{
                myStartActivity(Intent(this@MainBusActivity,LoginActivity::class.java))
            }
        }

    }

    private var exitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (System.currentTimeMillis() - exitTime > 1000) {
                    ToastUtil.showToast("双击退出应用")
                    exitTime = System.currentTimeMillis()
                } else {
                    //保存购物车数据
                    finish()
                }
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}