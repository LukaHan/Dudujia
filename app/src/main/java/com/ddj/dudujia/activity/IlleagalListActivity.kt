package com.ddj.dudujia.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseRecyclerAdapter
import com.ddj.dudujia.bean.CarDetailBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.http.HttpResult
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.LogUtils
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.activity__list_illeagal.*
import kotlinx.android.synthetic.main.item_recycler_illeagal.view.*

/**
 * Created by hanshaobo on 18/01/2018.
 */
class IlleagalListActivity : BaseActivity() {
    private var mDatas = ArrayList<CarDetailBean.DataBean.WeizhangBean>()
    private lateinit var mAdapter: BaseRecyclerAdapter<CarDetailBean.DataBean.WeizhangBean, BaseRecyclerAdapter.ViewHolder<CarDetailBean.DataBean.WeizhangBean>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__list_illeagal)
        initView()
        initData()
    }

    private fun initData() {
        var licenseplate = intent.getStringExtra("licenseplate")
        var vin = intent.getStringExtra("vin")
        titleView.setTitle(licenseplate)

        HttpMethods.createService().getMyCarDetail("get_mycardetail", CommonMethod.getUserId(), licenseplate, vin)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<CarDetailBean>>() {
                    override fun onNext(t: HttpResult<CarDetailBean>) {
                        super.onNext(t)
                        LogUtils.d("ttt:" + t.result.data.weizhang.size)
                        mDatas.clear()
                        mDatas.addAll(t.result.data.weizhang)
                        mAdapter.notifyDataSetChanged()
                    }
                })

    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = BaseRecyclerAdapter(R.layout.item_recycler_illeagal, mDatas) { view, weizhangBean ->
            view.tvAddress.text = weizhangBean.address
            view.tvReason.text = weizhangBean.reason
            view.tvDate.text = weizhangBean.time

            view.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    ToastUtil.showToast("进入下一页面")

                }
            })
        }
        recyclerView.adapter = mAdapter
    }

}