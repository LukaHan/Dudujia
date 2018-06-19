package com.ddj.dudujia.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.bean.CarDetailBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.fragment.BaoyangListFragment
import com.ddj.dudujia.fragment.IlleagalListFragment
import com.ddj.dudujia.fragment.JianceListFragment
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.http.HttpMethods
import com.ddj.dudujia.http.HttpResultSubscriber
import com.ddj.dudujia.http.TransformUtils
import com.ddj.dudujia.utils.ToastUtil

import kotlinx.android.synthetic.main.activity_car_detail_list.*

/**
 * Created by hanshaobo on 12/09/2017.
 */
class CarDetailListActivity : BaseActivity() {
    var baoyangListFragment = BaoyangListFragment()
    var weizhangFragment = IlleagalListFragment()
    var jianceFragment = JianceListFragment()

    lateinit var licenseplate: String
    lateinit var vin: String

    private var fragmentList = ArrayList<BaseFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail_list)
        initView()
        initData()
        initListener()
    }

    private fun initListener() {
        var currentItem = 0
        var listener = View.OnClickListener { p0 ->
            when (p0.id) {
                R.id.rlBaoyang -> {
                    currentItem = 0

                }
                R.id.rlWeizhang -> {
                    currentItem = 1

                }
                R.id.rlJiance -> {
                    currentItem = 2
                }
            }
            setItem(currentItem)
            viewPager.currentItem = currentItem
        }
        rlWeizhang.setOnClickListener(listener)
        rlBaoyang.setOnClickListener(listener)
        rlJiance.setOnClickListener(listener)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                setItem(position)
            }

        })
    }

    private fun setItem(currentItem: Int) {
        when (currentItem) {
            0 -> {
                ivBaoyang.visibility = View.VISIBLE
                ivWeizhang.visibility = View.GONE
                ivJiance.visibility = View.GONE
            }
            1 -> {
                ivBaoyang.visibility = View.GONE
                ivWeizhang.visibility = View.VISIBLE
                ivJiance.visibility = View.GONE
            }
            2 -> {
                ivBaoyang.visibility = View.GONE
                ivWeizhang.visibility = View.GONE
                ivJiance.visibility = View.VISIBLE
            }
        }
    }


    private fun initView() {
        setItem(0)
    }

    private fun initData() {
        licenseplate = intent.getStringExtra("licenseplate")
        vin = intent.getStringExtra("vin")
        titleView.setTitle(licenseplate)

        fragmentList.add(baoyangListFragment)
        fragmentList.add(weizhangFragment)
        fragmentList.add(jianceFragment)

        var mAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
        viewPager.adapter = mAdapter

        mAdapter.notifyDataSetChanged()

        HttpMethods.createService().getMyCarDetail("get_mycardetail", CommonMethod.getUserId(), licenseplate, vin)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<CarDetailBean>>() {
                    override fun onNext(t: HttpResult<CarDetailBean>) {
                        super.onNext(t)
                        if (t.status == 0) {
                            setData(t.result.data)
                        } else {
                            ToastUtil.showToast(t.info)
                        }
                    }
                })
    }

    private fun setData(datas: CarDetailBean.DataBean) {
        baoyangListFragment.setData(datas.cheshi)
        weizhangFragment.setData(datas.weizhang)
        jianceFragment.setData(datas.jiance)
    }

    inner class ViewPagerAdapter(fm: FragmentManager, fragments: List<Fragment>) : FragmentPagerAdapter(fm) {
        private var mFragments = fragments
        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }
    }
}