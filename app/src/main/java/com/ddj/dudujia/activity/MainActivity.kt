package com.ddj.dudujia.activity

import android.app.ActionBar
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.base.BaseFragment
import com.ddj.dudujia.fragment.CarFragment
import com.ddj.dudujia.fragment.HomeFragment
import com.ddj.dudujia.fragment.MineFragment
import com.ddj.dudujia.fragment.ServiceFragment
import com.first.basket.utils.ToastUtil
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.roughike.bottombar.BottomBar
import com.roughike.bottombar.BottomBarTab
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_loading.*
import java.util.*


class MainActivity : BaseActivity(){
    companion object {
        private lateinit var instance: MainActivity
        fun getInstance1(): MainActivity {
            return instance
        }
    }

    private lateinit var bottomBar: BottomBar
    private var baseFragment = BaseFragment()
    private var fragmentList = ArrayList<BaseFragment>()

    private var homeFragment = HomeFragment()
    private var classifyFragment = ServiceFragment()
    private var activeFragment = CarFragment()
    private var mineFragment = MineFragment()

    private lateinit var nearby: BottomBarTab
    var mChannel: Int = 1   //菜市，默认为社区菜市
    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this
        initView()
        initData()
    }

    private fun initView() {
        bottomBar = findViewById(R.id.bottombar)
        bottomBar.setTabTitleTextAppearance(10)
        nearby = bottomBar.getTabWithId(R.id.tab_car)

        fragmentList = ArrayList()
        homeFragment = HomeFragment()
        fragmentList.add(homeFragment)
        fragmentList.add(classifyFragment)
        fragmentList.add(activeFragment)
        fragmentList.add(mineFragment)
    }

    private fun initData() {
        var fragment = BaseFragment()
        bottomBar.setOnTabSelectListener { l ->
            when (l) {
                R.id.tab_home
                -> fragment = fragmentList.get(0)
                R.id.tab_service
                -> fragment = fragmentList.get(1)
                R.id.tab_car
                -> fragment = fragmentList.get(2)
                R.id.tab_mine
                -> fragment = fragmentList.get(3)
            }

            if (baseFragment == null) {
                replaceContent(fragment, R.id.fragmentContainer);
                baseFragment = fragment;
            } else {
                if (fragment != null) {
                    switchContent(baseFragment, fragment, R.id.fragmentContainer);
                    baseFragment = fragment;
                }
            }
        }
    }

    private var exitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (getCurrentPage() == 2 && (fragmentList[2] as ActiveFragment).setBack()) {
//                return true
//            } else
                if (event.action == KeyEvent.ACTION_DOWN) {
                if (System.currentTimeMillis() - exitTime > 1000) {
                    ToastUtil.showToast("双击退出应用")
                    exitTime = System.currentTimeMillis()
                } else {
                    //保存购物车数据
                    finish()
                }
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }


    fun switchFragment(index: Int) {
        var fragment: BaseFragment? = null
        when (index) {
            0 -> fragment = homeFragment
            1 -> fragment = classifyFragment
            2 -> fragment = activeFragment
            3 -> fragment = mineFragment
        }
        if (baseFragment == null) {
            replaceContent(fragment!!, R.id.fragmentContainer)
            baseFragment = fragment
        } else {
            if (fragment != null) {
                switchContent(baseFragment, fragment, R.id.fragmentContainer)
                baseFragment = fragment
            }
        }
    }

    fun setCurrentPage(index: Int) {
        if (fragmentList[index].isAdded) {
            switchFragment(index)
            bottomBar.selectTabAtPosition(index)
        }
    }

    fun showLogin() {
//        showDialog("提示", "您尚未登录，请先登录", "去登录", DialogInterface.OnClickListener { p0, p1 -> myStartActivity(Intent(this@MainActivity, LoginActivity::class.java)) })
    }

    fun showLoading() {
        loadingView.visibility = View.VISIBLE
        loadingView.setIndeterminateDrawable(DoubleBounce())
    }

    fun hideLoading() {
        loadingView.visibility = View.GONE
    }


    fun goClassify(channel: Int) {
        mChannel = channel
        bottombar.selectTabAtPosition(1)
    }

    private fun getCurrentPage(): Int {
        return bottomBar.currentTabPosition
    }
}
