package com.ddj.dudujia.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.*
import com.ddj.dudujia.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_service.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 01/12/2017.
 */
class ServiceFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_service, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initListener() {
        rlWeizhang.onClick {
            startActivity(Intent(activity, IlleagalActivity::class.java))
        }
        rlHistory.onClick {
            startActivity(Intent(activity, CarHistoryActivity::class.java))
        }
        rlJiance.onClick {
            startActivity(Intent(activity, JianceActivity::class.java))
        }
        btYuyue.onClick {
            startActivity(Intent(activity, JianceActivity::class.java))
        }
    }

    private fun initView() {
    }

}