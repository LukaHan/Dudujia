package com.ddj.dudujia.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.LoginActivity
import com.ddj.dudujia.activity.OfflineReportActivity
import com.ddj.dudujia.base.BaseFragment
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
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        rlMyReport.onClick {
            startActivity(Intent(activity, OfflineReportActivity::class.java))
        }
    }
}