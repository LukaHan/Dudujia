package com.ddj.dudujia.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseRecyclerAdapter
import com.ddj.dudujia.bean.OfflineReportBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.http.HttpResult
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity__list_report.*
import kotlinx.android.synthetic.main.item_recycler_report.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by hanshaobo on 18/01/2018.
 */
class OfflineReportActivity : BaseActivity() {
    private var mDatas = ArrayList<OfflineReportBean.DataBean>()
    private lateinit var mAdapter: BaseRecyclerAdapter<OfflineReportBean.DataBean, BaseRecyclerAdapter.ViewHolder<OfflineReportBean.DataBean>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__list_report)
        initView()
        initData()
    }

    private fun initData() {

        HttpMethods.createService().getOfflineReportList("get_offlineapplication", CommonMethod.getUserId())
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<OfflineReportBean>>() {
                    override fun onNext(t: HttpResult<OfflineReportBean>) {
                        super.onNext(t)
                        if (t.status == 0) {
                            mDatas.clear()
                            mDatas.addAll(t.result.data)
                            mAdapter.notifyDataSetChanged()
                        } else {
                            ToastUtil.showToast(t.info)
                        }
                    }
                })

    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = BaseRecyclerAdapter(R.layout.item_recycler_report, mDatas) { view, dataBean ->
            view.tvModel.text = dataBean.carmodel
            view.tvVin.text = dataBean.vin
            view.tvPlate.text = dataBean.address
            view.onClick {
                var intent = Intent(this@OfflineReportActivity, CarBasicActivity::class.java)
                intent.putExtra("reportid", dataBean.reportid)
                startActivity(intent)
            }
        }
        recyclerView.adapter = mAdapter
    }

}