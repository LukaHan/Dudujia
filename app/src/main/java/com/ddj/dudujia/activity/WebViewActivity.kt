package com.ddj.dudujia.activity

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.text.TextUtils
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ddj.dudujia.R
import com.ddj.dudujia.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*


/**
 * Created by hanshaobo on 10/10/2017.
 */
class WebViewActivity : BaseActivity() {
    private var url: String? = ""
    private var mTitle: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initData() {
        url = intent.getStringExtra("url")
        if (TextUtils.isEmpty(url)) myFinish()
        mTitle = intent.getStringExtra("title")

        titleView.setTitle(mTitle)

        var webSettings = webview.settings

        // 解决某些网址显示异常的问题，4种属性需同时设置
        webview.webViewClient = MyWebViewClient()

//        webview.webChromeClient = object : WebChromeClient() {
//            override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                if (newProgress == 100) {
//                    pb.visibility = View.INVISIBLE
//                    showBtGo()
//
//                } else {
//                    if (View.INVISIBLE == pb.visibility) {
//                        pb.visibility = View.VISIBLE
//                    }
//                    pb.progress = newProgress;
//                }
//                super.onProgressChanged(view, newProgress);
//            }
//
//        }
        webSettings.domStorageEnabled = true;
        webSettings.javaScriptEnabled = true;

        // 设置自适应屏幕
        webSettings.useWideViewPort = true;
        webSettings.loadWithOverviewMode = true;

        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE;

        webview.loadUrl(url)
    }

    private fun showBtGo() {

    }

}


internal class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        handler.proceed()
    }
}