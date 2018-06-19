package com.ddj.dudujia.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseRecyclerAdapter
import com.ddj.dudujia.bean.OfflineReportBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.http.HttpMethods
import com.ddj.dudujia.http.HttpResultSubscriber
import com.ddj.dudujia.http.TransformUtils
import com.ddj.dudujia.utils.ToastUtil

import kotlinx.android.synthetic.main.activity_list_report.*
import kotlinx.android.synthetic.main.item_recycler_jiance.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textColor

/**
 * Created by hanshaobo on 18/01/2018.
 */
class OfflineReportActivity : BaseActivity() {
    private var mDatas = ArrayList<OfflineReportBean.DataBean>()
    private lateinit var mAdapter: BaseRecyclerAdapter<OfflineReportBean.DataBean, BaseRecyclerAdapter.ViewHolder<OfflineReportBean.DataBean>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_report)
        initView()
        initData()
    }

    private fun initData() {
        getData()
    }

    private fun getData() {
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

                    override fun onCompleted() {
                        super.onCompleted()
                        refreshLayout.isRefreshing = false
                    }
                })
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = BaseRecyclerAdapter(R.layout.item_recycler_jiance, mDatas) { view, dataBean ->
            view.tvModel.text = dataBean.carmodel
            view.tvVin.text = dataBean.vin
            view.tvOrderId.text = "订单号：" + dataBean.orderid
            view.tvTime.text = "检测时间：" + dataBean.time
            view.tvDate.text = dataBean.updatedt
            view.tvAddress.text = "检测地址：" + dataBean.address
            view.tvPhone.text = "联系方式：" + dataBean.phone
            if (TextUtils.isEmpty(dataBean.reportid)) {
                view.tvStatus.text = "未检测"
                view.tvStatus.textColor = resources.getColor(R.color.red56)
            } else {
                view.tvStatus.text = "已完成"
                view.tvStatus.textColor = resources.getColor(R.color.gray66)
            }

            view.onClick {
                if (!TextUtils.isEmpty(dataBean.reportid)) {
                    var intent = Intent(this@OfflineReportActivity, HealthReportActivity::class.java)
                    intent.putExtra("orderid", dataBean.orderid)
                    startActivity(intent)
                }else{
                    ToastUtil.showToast("检测时尚未检测，请耐心等候!")
                }
            }
        }
        recyclerView.adapter = mAdapter


        refreshLayout.setOnRefreshListener { getData() }
    }

}