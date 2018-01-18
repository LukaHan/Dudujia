package com.ddj.dudujia.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.CarAddlActivity
import com.ddj.dudujia.activity.LoginActivity
import com.ddj.dudujia.adapter.CarListAdapter
import com.ddj.dudujia.base.BaseBean
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.base.CarListBean
import com.ddj.dudujia.bean.LoginBean
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.SPUtil
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_car.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 01/12/2017.
 */
class CarFragment : BaseFragment() {
    var mData = ArrayList<CarListBean.CarBean>()
    var mAdapter = CarListAdapter(activity, mData)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_car, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {

        HttpMethods.createService()
                .getMyCar("get_mycar", SPUtil.getString(StaticValue.USER_ID, ""))
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<CarListBean>>() {
                    override fun onNext(t: HttpResult<CarListBean>) {
                        super.onNext(t)

                        if (t.result.data.size == 0) {
                            llNoCar.visibility = View.VISIBLE
                            refreshLayout.visibility = View.GONE
                        } else {
                            llNoCar.visibility = View.GONE
                            refreshLayout.visibility = View.VISIBLE

                            mData.addAll(t.result.data)
                            mAdapter.notifyDataSetChanged()
                        }

                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })
    }


    private fun showNoCar() {

    }

    private fun initView() {
        btAdd.onClick {
            startActivity(Intent(activity, CarAddlActivity::class.java))
        }
    }
}