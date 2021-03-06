package com.ddj.dudujia.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.*
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.utils.ImageUtils
import com.ddj.dudujia.utils.SPUtil
import com.ddj.dudujia.view.TitleView
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
        tvVersion.text = "v" + CommonMethod.getVersionName()
        tvLogin.onClick {
            if (CommonMethod.isLogin()) {

            } else {
                if (CommonMethod.isCusVer()) {
                    //客户版
                    var intent = Intent(activity, LoginActivity::class.java)
                    startActivityForResult(intent, 102)
                } else {
                    //员工版
                    var intent = Intent(activity, LoginActivity::class.java)
                    startActivityForResult(intent, 102)
                }
            }


//            if (CommonMethod.isLogin()) {
//                var intent = Intent(activity, SettingActivity::class.java)
//                startActivityForResult(intent, 103)
//            } else {
//                var intent = Intent(activity, LoginActivity::class.java)
//                startActivityForResult(intent, 102)
//            }
        }

        llJKBG.onClick {
            if (CommonMethod.isLogin()) {
                startActivity(Intent(activity, OfflineReportActivity::class.java))
            } else {
                var intent = Intent(activity, LoginActivity::class.java)
                startActivityForResult(intent, 102)
            }
        }

        titleView.setOnMoreClickListenr(object : TitleView.OnItemClick {
            override fun onMoreClick() {
                startActivity(Intent(activity, SettingActivity::class.java))
            }

            override fun onMoreSecondClick() {

            }

        })

        llAbout.onClick { startActivity(Intent(activity, AboutActivity::class.java)) }
        llWDAC.onClick { (activity as MainActivity).setCurrentPage(2) }


//        rlJiance.onClick {
//            if (CommonMethod.isLogin()) {
//                startActivity(Intent(activity, OfflineReportActivity::class.java))
//            } else {
//                var intent = Intent(activity, LoginActivity::class.java)
//                startActivityForResult(intent, 102)
//            }
//        }
//        rlMyReport.onClick {
//            startActivity(Intent(activity, CarHistoryReportActivity::class.java))
//        }
//        rlCar.onClick {
//            if (CommonMethod.isLogin()) {
//                (activity as MainActivity).setCurrentPage(2)
//            } else {
//                var intent = Intent(activity, LoginActivity::class.java)
//                startActivityForResult(intent, 102)
//            }
//        }
//
//        aimvAccount.onClick {
//            startActivity(Intent(activity, HealthReportActivity::class.java))
//        }
//
//
//        aimvAbout.onClick {
//            startActivity(Intent(activity, AboutActivity::class.java))
//        }
//
//        aimvHelp.onClick {
//            (activity as MainActivity).showDialog("联系客服", activity.getString(R.string.service_phone), "拨打", DialogInterface.OnClickListener { p0, p1 ->
//                val intent = Intent(Intent.ACTION_DIAL)
//                val data = Uri.parse("tel:" + activity.getString(R.string.service_phone))
//                intent.data = data
//                startActivity(intent)
//            })
//        }

        isBusVer()
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
            tvLogin.text = username
            tvDes.text = "已认证信誉车商"

//            tvUserType.visibility = View.VISIBLE
//            tvUserType.text = SPUtil.getString(StaticValue.SP_LOGIN_USERTYPE, "")
//            tvReportNum.text = SPUtil.getString(StaticValue.SP_LOGIN_USER_NUM_REPORT, "")
//            tvCarNum.text = SPUtil.getString(StaticValue.SP_LOGIN_USER_NUM_CAR, "")
        } else {
            tvLogin.text = "立即登录"
            tvDes.text = "Hello,欢迎来到车主好伙伴"
        }
    }

    override fun onResume() {
        super.onResume()
        setLoginStatus()
    }


    private fun isBusVer() {
        if (!CommonMethod.isCusVer()) {
            tvGongwei.text = "我的工位"
            tvJCSQ.text = "检测申请"
            tvYYJC.text = "预约检测"
            tvJCGD.text = "检测工单"
            tvXSYJ.text = "销售业绩"
            tvCSLB.text = "车商列表"

            ImageUtils.showImg(activity, R.mipmap.ic_bus_mine_jianceshenqing, ivJCSQ)
            ImageUtils.showImg(activity, R.mipmap.ic_bus_mine_yuyuejiance, ivYYJC)
            ImageUtils.showImg(activity, R.mipmap.ic_bus_mine_jiancegongdan, ivJCGD)
            ImageUtils.showImg(activity, R.mipmap.ic_bus_mine_xiaoshouyeji, ivXSYJ)
            ImageUtils.showImg(activity, R.mipmap.ic_mine_cwcs, ivCSLB)

            tvGRFW.visibility = View.GONE
            llGRFW.visibility = View.GONE
            llVersion.visibility = View.GONE
            rlCSRZ.visibility = View.GONE

            ivJCSQ.onClick {
                startActivity(Intent(activity, CheckDingdanActivity::class.java))
            }
        }
    }

}