package com.ddj.dudujia.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.CarAddlActivity
import com.ddj.dudujia.activity.IlleagalListActivity
import com.ddj.dudujia.adapter.CarListAdapter
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.base.CarListBean
import com.ddj.dudujia.common.StaticValue
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.SPUtil
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import kotlinx.android.synthetic.main.fragment_car.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 01/12/2017.
 */
class CarFragment : BaseFragment() {
    private var mData = ArrayList<CarListBean.CarBean>()
    private lateinit var mAdapter: CarListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_car, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initData() {
        mAdapter = CarListAdapter(activity, mData)
        recyclerView.adapter = mAdapter

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

                            mData.clear()
                            mData.addAll(t.result.data)
                            mAdapter.notifyDataSetChanged()
                        }

                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)

                    }
                })

        mAdapter.setOnItemClickListener(object : CarListAdapter.OnItemClickListener {
            override fun onBaoyangItemClick(view: View?, data: CarListBean.CarBean?, position: Int) {

            }

            override fun onJianceItemClick(view: View?, data: CarListBean.CarBean?, position: Int) {
                return
            }

            override fun onItemClick(view: View?, data: CarListBean.CarBean?, position: Int) {
                var intent = Intent(activity, IlleagalListActivity::class.java)
                intent.putExtra("licenseplate", data?.licenseplate)
                intent.putExtra("vin", data?.vinnum)
                startActivity(intent)
            }

            override fun onWeizhangItemClick(view: View?, data: CarListBean.CarBean?, position: Int) {
                return
            }
        })
    }


    private fun showNoCar() {

    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        btAdd.onClick {
            startActivity(Intent(activity, CarAddlActivity::class.java))
        }

    }
}