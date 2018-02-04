package com.ddj.dudujia.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.base.BaseRecyclerAdapter
import com.ddj.dudujia.bean.CarDetailBean
import com.first.basket.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_illeagal_list.*
import kotlinx.android.synthetic.main.item_recycler_jiance.view.*

/**
 * Created by hanshaobo on 04/02/2018.
 */
class JianceListFragment : BaseFragment() {

    private var mDatas = ArrayList<CarDetailBean.DataBean.JianceBean>()
    private lateinit var mAdapter: BaseRecyclerAdapter<CarDetailBean.DataBean.JianceBean, BaseRecyclerAdapter.ViewHolder<CarDetailBean.DataBean.JianceBean>>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_baoyang_list, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = BaseRecyclerAdapter(R.layout.item_recycler_jiance, mDatas) { view, dataBean ->
            view.tvModel.text = dataBean.carmodel
            view.tvDate.text = dataBean.updatedt
            view.tvVin.text = dataBean.vin
            view.tvAddress.text = dataBean.address
            view.tvTime.text = dataBean.time

            view.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    ToastUtil.showToast("进入下一页面")
                }
            })
        }
        recyclerView.adapter = mAdapter
    }

    private fun initData() {

    }

    fun setData(data: List<CarDetailBean.DataBean.JianceBean>) {
        mDatas.addAll(data)
        mAdapter.notifyDataSetChanged()
    }
}