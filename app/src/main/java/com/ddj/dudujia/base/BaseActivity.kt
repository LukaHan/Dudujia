package com.ddj.dudujia.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ddj.dudujia.rxjava.RxjavaUtil
import com.ddj.dudujia.rxjava.UITask
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.style.DoubleBounce

/**
 * Created by hanshaobo on 30/08/2017.
 */
open class BaseActivity : AppCompatActivity() {

    val REQUEST_ONE = 101
    val REQUEST_TWO = 102
    val REQUEST_THREE = 103
    val REQUEST_SPE = 100

    val RESULT_FAIL = 1001

    private lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        mProgressDialog = ProgressDialog(this@BaseActivity)
    }

    private fun hideActionBar() {
        //得到当前界面的装饰视图
        if(Build.VERSION.SDK_INT >= 21) {
            var decorView = window.decorView
            //设置让应用主题内容占据状态栏和导航栏
            var option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            //设置状态栏和导航栏颜色为透明
            window.statusBarColor = Color.TRANSPARENT;
            window.navigationBarColor = Color.TRANSPARENT;
        }
        //隐藏标题栏
        var actionBar = supportActionBar
        actionBar?.hide()
    }

    fun replaceContent(fragment: Fragment, fragmentResId: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(fragmentResId, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun switchContent(from: Fragment, to: Fragment, id: Int) {
        if (from !== to) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            if (!to.isAdded) {
                transaction.hide(from).add(id, to).commitAllowingStateLoss()
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss()
            }
        }
    }


    fun showProgressDialog(message: String) {
        RxjavaUtil.doInUIThread(object : UITask<Any>() {
            override fun doInUIThread() {
                mProgressDialog = ProgressDialog(this@BaseActivity)
                mProgressDialog.setCanceledOnTouchOutside(false)
                mProgressDialog.setMessage(message)
                mProgressDialog.show()
            }
        })
    }

//    fun showProgressDialog() {
//        showProgressDialog("加载中...")
//    }
//
//
//    fun hideProgress() {
//        RxjavaUtil.doInUIThread(object : UITask<Any>() {
//            override fun doInUIThread() {
//                if (mProgressDialog.isShowing) {
//                    mProgressDialog.hide()
//                }
//            }
//        })
//    }

    fun myStartActivity(cls: Class<*>, needFinish: Boolean) {
        val intent = Intent(this, cls)
        startActivity(intent)
        if (needFinish) this.finish()
    }

    fun myStartActivity(intent: Intent, needFinish: Boolean) {
        startActivity(intent)
        if (needFinish) this.finish()
    }

    fun myStartActivity(intent: Intent) {
        myStartActivity(intent, false)
    }

    fun myStartActivityForResult(cls: Class<*>, requestCode: Int) {
        val intent = Intent(this, cls)
        startActivityForResult(intent, requestCode)
    }

    fun myStartActivityForResult(intent: Intent, requestCode: Int) {
        startActivityForResult(intent, requestCode)
    }

    fun myFinish() {
        finish()
    }

    fun showDialog(title: String, content: String, positive: String, listener: DialogInterface.OnClickListener) {
        var dialog = AlertDialog.Builder(this@BaseActivity)
        dialog.setTitle(title)
        dialog.setMessage(content)
        dialog.setPositiveButton(positive, listener)
        dialog.setNegativeButton("取消") { p0, p1 -> p0?.dismiss() }
        dialog.show()
    }

    fun showDialog(title: String, listener: DialogInterface.OnClickListener) {
        showDialog(title, "", "确定", listener)
    }

    fun showLoading(loadingView: SpinKitView) {
        loadingView.visibility = View.VISIBLE
        loadingView.setIndeterminateDrawable(DoubleBounce())
    }

    fun hideLoading(loadingView: SpinKitView) {
        loadingView.visibility = View.GONE
    }
}