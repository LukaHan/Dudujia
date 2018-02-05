package com.ddj.dudujia.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.LoginActivity
import com.ddj.dudujia.activity.MainActivity
import com.ddj.dudujia.activity.OfflineReportActivity
import com.ddj.dudujia.activity.SettingActivity
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.utils.SPUtil
import kotlinx.android.synthetic.main.fragment_mine.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 01/12/2017.
 */
class MineFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_mine, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        tvNickname.onClick {
            if (CommonMethod.isLogin()) {
                var intent = Intent(activity, SettingActivity::class.java)
                startActivityForResult(intent, 103)
            } else {
                var intent = Intent(activity, LoginActivity::class.java)
                startActivityForResult(intent, 102)
            }
        }
        rlMyReport.onClick {
            if (CommonMethod.isLogin()) {
                startActivity(Intent(activity, OfflineReportActivity::class.java))
            } else {
                var intent = Intent(activity, LoginActivity::class.java)
                startActivityForResult(intent, 102)
            }
        }
        rlCar.onClick {
            if (CommonMethod.isLogin()) {
                (activity as MainActivity).setCurrentPage(2)
            } else {
                var intent = Intent(activity, LoginActivity::class.java)
                startActivityForResult(intent, 102)
            }
        }

        aimvAccount.onClick {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && (requestCode == 102 || requestCode == 103)) {
            setLoginStatus()
        }
    }

    private fun setLoginStatus() {
        (activity as MainActivity).setLoginStatus()

        if (SPUtil.getBoolean(StaticValue.SP_LOGIN_STATUS, false)) {
            val username = SPUtil.getString(StaticValue.SP_LOGIN_USERNAME, "")
            tvNickname.text = username
            tvUserType.visibility = View.VISIBLE
            tvUserType.text = SPUtil.getString(StaticValue.SP_LOGIN_USERTYPE, "")
            tvReportNum.text = SPUtil.getString(StaticValue.SP_LOGIN_USER_NUM_REPORT, "")
            tvCarNum.text = SPUtil.getString(StaticValue.SP_LOGIN_USER_NUM_CAR, "")
        } else {
            tvNickname.text = "登录/注册"
            tvUserType.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        setLoginStatus()
    }
}