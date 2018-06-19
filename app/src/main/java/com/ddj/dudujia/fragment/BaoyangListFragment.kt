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
import com.ddj.dudujia.utils.ToastUtil

import kotlinx.android.synthetic.main.fragment_baoyang_list.*
import kotlinx.android.synthetic.main.item_recycler_baoyang.view.*

/**
* Created by hanshaobo on 04/02/2018.
*/
class BaoyangListFragment : BaseFragment() {
    private var mDatas = ArrayList<CarDetailBean.DataBean.CheshiBean>()
    private lateinit var mAdapter: BaseRecyclerAdapter<CarDetailBean.DataBean.CheshiBean, BaseRecyclerAdapter.ViewHolder<CarDetailBean.DataBean.CheshiBean>>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_baoyang_list, container, false)!!
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = BaseRecyclerAdapter(R.layout.item_recycler_baoyang, mDatas) { view, dataBean ->
            view.tvModel.text = dataBean.xsmc
            view.tvVin.text = dataBean.vin
            view.tvDate.text = dataBean.updatedt

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

    fun setData(data: List<CarDetailBean.DataBean.CheshiBean>) {
        mDatas.addAll(data)
        mAdapter.notifyDataSetChanged()
    }
}