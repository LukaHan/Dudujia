package com.ddj.dudujia.activity

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.alipay.sdk.app.PayTask
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.AliBean
import com.ddj.dudujia.bean.CodeBean
import com.ddj.dudujia.bean.ReservationBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.CountDownUtil
import com.ddj.dudujia.utils.SPUtil
import com.ddj.dudujia.utils.alipay.PayResult
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_jiance.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 20/01/2018.
 */
class JianceActivity : BaseActivity() {
    private val SDK_PAY_FLAG: Int = 1
    private lateinit var price: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jiance)
        initData()
        initView()
    }

    private fun initData() {
        HttpMethods.createService().getReservation("get_reservation", CommonMethod.getUserId())
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<ReservationBean>>() {
                    override fun onNext(t: HttpResult<ReservationBean>) {
                        super.onNext(t)
//                        ImageUtils.showImg(this@JianceActivity, t.result.data.image, ivTitle)
                        price = t.result.data.price
                    }
                })
    }

    private fun getLoginVerifyCode(phonenumber: String) {

        HttpMethods.createService()
                .getCode("get_code", phonenumber)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<CodeBean>>() {
                    override fun onNext(t: HttpResult<CodeBean>) {
                        super.onNext(t)
                        countDown()
                    }
                })
    }

    private fun countDown() {
        CountDownUtil.countDown(120, object : CountDownUtil.onCountDownListener {
            override fun onCountDownNext(integer: Int?) {
                tvSend.text = integer.toString() + "秒后重新发送"
            }

            override fun onCountDownError(e: Throwable?) {

            }

            override fun onCountDownComplete() {
//                countDownComplete()
            }
        })
    }

    private fun initView() {
        tvSend.onClick {
            if (!CommonMethod.isMobile(etPhone.text.toString())) {
                ToastUtil.showToast(this@JianceActivity, "请输入正确的手机号")
                return@onClick
            }
            getLoginVerifyCode(etPhone.text.toString())
        }

        btYuyue.onClick {
            var phone = etPhone.text.toString()
            var code = etCode.text.toString()
            var model = etModel.text.toString()
            var vin = etVin.text.toString()
            var time = etTime.text.toString()
            var address = etAddress.text.toString()

            if (!CommonMethod.isMobile(phone)) {
                ToastUtil.showToast(getString(R.string.phone_error))
                return@onClick
            }
            if (vin.length != 17) {
                ToastUtil.showToast(getString(R.string.vin_error))
                return@onClick
            }

            //调支付接口

            var map = HashMap<String, String>()
            map.put("userid", SPUtil.getString(StaticValue.SP_LOGIN_USER_ID, ""))
            map.put("paytype", "APP")
            map.put("productname", getString(R.string.phone_error))
            //
            map.put("totalfee", "APP")
            map.put("phone", phone)
            map.put("code", code)
            map.put("carmodel", model)
            map.put("vin", vin)
            map.put("time", time)
            map.put("address", address)
            //
            map.put("repaystrorder", "repaystrorder")

            HttpMethods.createService().doPayForAlipay("do_payforalipay", map)
                    .compose(TransformUtils.defaultSchedulers())
                    .subscribe(object : HttpResultSubscriber<HttpResult<AliBean>>() {
                        override fun onNext(t: HttpResult<AliBean>) {
                            super.onNext(t)
                            aliPay(t.result.data)
                        }
                    })

        }
    }

    private fun aliPay(data: AliBean.DataBean) {
        val orderInfo = data.preorder_result   // 订单信息

        val payRunnable = Runnable {
            val alipay = PayTask(this@JianceActivity)
            val result = alipay.payV2(orderInfo, true)

            val msg = Message()
            msg.what = SDK_PAY_FLAG
            msg.obj = result
            mHandler.sendMessage(msg)
        }
        // 必须异步调用
        val payThread = Thread(payRunnable)
        payThread.start()
    }

    private var mHandler = Handler(Handler.Callback { msg ->
        var payResult = PayResult(msg.obj as Map<String, String>)
        if ("9000" == payResult.resultStatus) {
            setResult(Activity.RESULT_OK)
        } else {
            setResult(RESULT_FAIL)
        }
        myFinish()
        false
    })
}