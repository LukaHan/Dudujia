package com.ddj.dudujia.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ddj.dudujia.R
import com.ddj.dudujia.R.id.llCheckOrder
import com.ddj.dudujia.activity.CheckYuyueActivity
import com.ddj.dudujia.base.BaseFragment

class HomeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        llCheckOrder.onClick {
//            startActivity(Intent(activity, CheckYuyueActivity::class.java))
//        }
    }
}
