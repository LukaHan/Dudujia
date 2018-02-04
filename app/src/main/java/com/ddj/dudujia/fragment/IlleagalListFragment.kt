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
import kotlinx.android.synthetic.main.item_recycler_illeagal.view.*

/**
* Created by hanshaobo on 04/02/2018.
*/
class IlleagalListFragment : BaseFragment() {

    private var mWeizhangDatas = ArrayList<CarDetailBean.DataBean.WeizhangBean>()
    private lateinit var mWeizhangAdapter: BaseRecyclerAdapter<CarDetailBean.DataBean.WeizhangBean, BaseRecyclerAdapter.ViewHolder<CarDetailBean.DataBean.WeizhangBean>>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_illeagal_list, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mWeizhangAdapter = BaseRecyclerAdapter(R.layout.item_recycler_illeagal, mWeizhangDatas) { view, weizhangBean ->
            view.tvAddress.text = weizhangBean.address
            view.tvReason.text = weizhangBean.reason
            view.tvDate.text = weizhangBean.time

            view.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    ToastUtil.showToast("进入下一页面")
                }
            })
        }
        recyclerView.adapter = mWeizhangAdapter
    }

    private fun initData() {

    }

    fun setData(datas: List<CarDetailBean.DataBean.WeizhangBean>) {
        mWeizhangDatas.clear()
        mWeizhangDatas.addAll(datas)
        mWeizhangAdapter.notifyDataSetChanged()

    }
}