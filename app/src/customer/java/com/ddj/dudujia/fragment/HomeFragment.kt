package com.ddj.dudujia.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddj.dudujia.R
import com.ddj.dudujia.activity.MainActivity
import com.ddj.dudujia.activity.WebViewActivity
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.base.BaseRecyclerAdapter
import com.ddj.dudujia.bean.HomeBean
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.http.HttpMethods
import com.ddj.dudujia.http.HttpResultSubscriber
import com.ddj.dudujia.http.TransformUtils
import kotlinx.android.synthetic.customer.fragment_home.*
import kotlinx.android.synthetic.customer.item_recycler_home.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

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

        tvGoCarList.onClick {
            (activity as MainActivity).setCurrentPage(2)
        }
    }

    private fun initData() {
        mAdapter = BaseRecyclerAdapter(R.layout.item_recycler_home, mDatas) { view, newsBean ->
            view.tvTitle.text = newsBean.title
            view.tvHeat.text = newsBean.heat + "查看"
//            ImageUtils.showImg(activity, newsBean.image, view.ivImg)

            view.onClick {
                var intent = Intent(activity,WebViewActivity::class.java)
                intent.putExtra("url",mDatas[recyclerView.getChildAdapterPosition(view)].url)
                intent.putExtra("title",mDatas[recyclerView.getChildAdapterPosition(view)].title)
                startActivity(intent)
            }
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
                        tv0.text = t.result.data.mainTitle
                        tv1.text = t.result.data.submainTitle
                        mDatas.clear()
                        mDatas.addAll(t.result.data.news)
                        mAdapter.notifyDataSetChanged()
                    }

                    override fun onCompleted() {
                        super.onCompleted()
                        (activity as MainActivity).hideLoading()
                        refreshLayout.isRefreshing = false
                    }
                })


//        HttpMethods.createService().getMainpageFromJava("1")
//                .compose(TransformUtils.defaultSchedulers())
//                .subscribe(object : HttpResultSubscriber<HttpResult<HomeBean>>() {
//                    override fun onNext(t: HttpResult<HomeBean>) {
//                        super.onNext(t)
//                        tv0.text = t.result.data.mainTitle
//                        tv1.text = t.result.data.submainTitle
//                        mDatas.clear()
//                        mDatas.addAll(t.result.data.news)
//                        mAdapter.notifyDataSetChanged()
//                    }
//
//                    override fun onCompleted() {
//                        super.onCompleted()
//                        (activity as MainActivity).hideLoading()
//                        refreshLayout.isRefreshing = false
//                    }
//                })
    }
}