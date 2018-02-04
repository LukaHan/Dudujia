package com.ddj.dudujia.activity

import android.os.Bundle
import android.widget.EditText
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.LoginBean
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.view.LicenseKeyboardUtil
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
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
        var inputbox1 = this.findViewById(R.id.et_car_license_inputbox1) as EditText
        var inputbox2 = this.findViewById(R.id.et_car_license_inputbox2) as EditText
        var inputbox3 = this.findViewById(R.id.et_car_license_inputbox3) as EditText
        var inputbox4 = this.findViewById(R.id.et_car_license_inputbox4) as EditText
        var inputbox5 = this.findViewById(R.id.et_car_license_inputbox5) as EditText
        var inputbox6 = this.findViewById(R.id.et_car_license_inputbox6) as EditText
        var inputbox7 = this.findViewById(R.id.et_car_license_inputbox7) as EditText

        ll_license_input_boxes_content.onClick {
            var keyboardUtil = LicenseKeyboardUtil(this@IlleagalActivity, arrayOf<EditText>(inputbox1, inputbox2, inputbox3, inputbox4, inputbox5, inputbox6, inputbox7))
            keyboardUtil.showKeyboard()
        }




        btQuery.onClick {

        }

//        HttpMethods.createService()
//                .doIlleagalQuery("do_Illegalinquiry", phonenumber, code)
//                .compose(TransformUtils.defaultSchedulers())
//                .subscribe(object : HttpResultSubscriber<HttpResult<LoginBean>>() {
//                }
    }

}