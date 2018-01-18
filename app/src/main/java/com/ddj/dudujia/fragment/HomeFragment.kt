package com.ddj.dudujia.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.MainActivity
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.base.BaseRecyclerAdapter
import com.ddj.dudujia.bean.HomeBean
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.utils.ImageUtils
import com.first.basket.http.HttpMethods
import com.first.basket.http.HttpResultSubscriber
import com.first.basket.http.TransformUtils
import com.first.basket.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_item_home.view.*

/**
 * Created by hanshaobo on 01/12/2017.
 */
class HomeFragment : BaseFragment() {
    private var mDatas = ArrayList<HomeBean.DataBean.NewsBean>()
    private lateinit var mAdapter: BaseRecyclerAdapter<HomeBean.DataBean.NewsBean, BaseRecyclerAdapter.ViewHolder<HomeBean.DataBean.NewsBean>>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initData() {
        mAdapter = BaseRecyclerAdapter(R.layout.layout_item_home, mDatas) { view, newsBean ->
            view.tvTitle.text = newsBean.title
            view.tvHeat.text = newsBean.heat
            ImageUtils.showImg(activity, newsBean.image, view.ivImg)
        }
        recyclerView.adapter = mAdapter

        getData()

        refreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                getData()
            }

        })
    }

    private fun getData() {
        HttpMethods.createService().getMainpage("get_mainpage")
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<HomeBean>>() {
                    override fun onNext(t: HttpResult<HomeBean>) {
                        super.onNext(t)
                        LogUtils.d(t.result.data.mainTitle + ",,," + t.result.data.news.size)
                        mDatas.clear()
                        mDatas.addAll(t.result.data.news)
                        mAdapter.notifyDataSetChanged()
                    }

                    override fun onCompleted() {
                        super.onCompleted()
                        (activity as MainActivity).hideLoading()
                        refreshLayout.isRefreshing = false
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        LogUtils.d(e.message!!)
                    }
                })
    }
}