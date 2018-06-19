package com.ddj.dudujia.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SimpleAdapter
import com.ddj.dudujia.R
import com.ddj.dudujia.app.SampleApplicationLike
import com.ddj.dudujia.base.BaseActivity
import com.ddj.dudujia.bean.HealthReportBean
import com.ddj.dudujia.common.CommonMethod
import com.ddj.dudujia.constants.Constants
import com.ddj.dudujia.http.HttpResult
import com.ddj.dudujia.rxjava.CommonRxTask
import com.ddj.dudujia.rxjava.IOTask
import com.ddj.dudujia.rxjava.RxjavaUtil
import com.ddj.dudujia.rxjava.UITask
import com.ddj.dudujia.view.CustomProgressDialog
import com.ddj.dudujia.view.OnlineInfoView
import com.ddj.dudujia.http.HttpMethods
import com.ddj.dudujia.http.HttpResultSubscriber
import com.ddj.dudujia.http.TransformUtils
import com.ddj.dudujia.utils.SPUtil
import com.ddj.dudujia.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_health_report.*
import kotlinx.android.synthetic.main.layout_insurance.*
import kotlinx.android.synthetic.main.layout_report_title.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class HealthReportActivity : BaseActivity() {
    private lateinit var data: HealthReportBean.DataBean
    private lateinit var mBitmap: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_report)

        initData()
        initListener()
    }

    private fun initListener() {
        tvAll.onClick {
            var intent = Intent(this@HealthReportActivity, ReportBasicActivity::class.java)
            intent.putExtra("data", data)
            startActivity(intent)
        }
        tvMoreInsurance.onClick {

        }
        tvMoreOnline.onClick {
            var intent = Intent(this@HealthReportActivity, CarHistoryReportActivity::class.java)
//            intent.putExtra("data",data)
            startActivity(intent)
        }
        tvMore267.onClick {
            var intent = Intent(this@HealthReportActivity, JianceCenterActivity::class.java)
            intent.putExtra("reportid", data.testing.testingid)
            startActivity(intent)
        }

//        titleView.setOnMoreClickListener(View.OnClickListener {
//            //先检查权限，再生成pdf
//            if (CommonMethod.hasPermissionOfWrite(this@HealthReportActivity)) {
//                generate()
//            } else {
//                ActivityCompat.requestPermissions(this@HealthReportActivity,
//                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
//            }
//        })
//
//        titleView.onClick {
//            if (CommonMethod.hasPermissionOfWrite(this@HealthReportActivity)) {
//                share()
//            } else {
//                ActivityCompat.requestPermissions(this@HealthReportActivity,
//                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
//            }
//        }


    }

    //分享
    private fun share() {
//        expandAllItem(false)
        RxjavaUtil.executeRxTask(object : CommonRxTask<Any>() {

            override fun doInIOThread() {
                //生成bitmap
                mBitmap = CommonMethod.getBitmapByView(scrollView)
            }

            override fun doInUIThread() {
                //保存bitmap
                val path = CommonMethod.getDujDir() + "1" + ".jpeg"
                CommonMethod.savePic(mBitmap, path)
                mBitmap.recycle()
//                mBitmap = null
                SampleApplicationLike.getInstance().share(path)
            }

        })
    }

    private val mPath: String = CommonMethod.getDujDir() + "report" + System.currentTimeMillis() / 1000 + ".pdf"

    private fun generate() {
        //先判断文件是否存在
        val file = File(mPath)
        if (file.exists()) {
            val dialog = AlertDialog.Builder(this@HealthReportActivity)
            dialog.setTitle("文件已存在")
            dialog.setIcon(R.mipmap.icon_pdf)
            dialog.setPositiveButton("立即查看") { dialog, which -> openFile(File(mPath), 1) }
            dialog.setNegativeButton("重新生成") { dialog, which ->
                //先删除本地的
                val b = file.delete()
                showProgress()
                doPreCreate()
            }
            dialog.show()
        } else {
            doPreCreate()
        }
    }

    private fun doPreCreate() {
        RxjavaUtil.doInIOThread(object : IOTask<Any>("") {
            override fun doInIOThread() {
                mBitmap = CommonMethod.getBitmapByView(scrollView)
                createPdf(mBitmap)
            }
        })
    }

    private lateinit var loadingDialog: Dialog

    fun showProgress(message: String) {
        RxjavaUtil.doInUIThread(object : UITask<Any>() {
            override fun doInUIThread() {
                if (loadingDialog == null) {
                    loadingDialog = CustomProgressDialog.createLoadingDialog(this@HealthReportActivity, message)
                    loadingDialog.setCanceledOnTouchOutside(false)
                    loadingDialog.setCancelable(true)
                }
                if (!loadingDialog.isShowing()) {
                    loadingDialog.show()
                }
            }
        })
    }

    private fun createPdf(bitmap: Bitmap) {
        // create a new document
        var document: PdfDocument? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                showProgressDialog("正在生成pdf文件...")
                document = PdfDocument()

                val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
//                LogUtil.d(TAG, "width：" + bitmap.width + ",height:" + bitmap.height)
                // start a page
                val page = document.startPage(pageInfo)
                // draw view on the page
                val content = llContent
                //                //将View的UI会知道Canvas上
                content.draw(page.canvas)


                val imageView = ImageView(this)
                imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                //                imageView.setImageBitmap(bitmap);
                imageView.setImageDrawable(resources.getDrawable(R.mipmap.ic_logo))
                imageView.draw(page.canvas)

                // finish the page
                document.finishPage(page)
                if (!File(mPath).exists()) {
                    File(mPath).createNewFile()
                }
                val fos = FileOutputStream(mPath)
                document.writeTo(fos)
                document.close()

                RxjavaUtil.doInUIThread(object : UITask<Any>() {
                    override fun doInUIThread() {
                        hideProgress()
                        ToastUtil.showToast("生成成功")
//                        Snackbar.make(coordinatorLayout, "生成成功", Snackbar.LENGTH_INDEFINITE).setActionTextColor(resources.getColor(R.color.white)).setAction("查看") { openFile(File(mPath), 1) }.show()
                    }
                })
            } catch (e: IOException) {
                e.printStackTrace()
                RxjavaUtil.doInUIThread(object : UITask<Any>() {
                    override fun doInUIThread() {
                        ToastUtil.showToast("生成失败")
//                        SnackbarUtil.showSnackbar(this@ReportDetailActivity, "生成失败" + e.message)
                        hideProgress()
                    }
                })
            } finally {
                RxjavaUtil.doInUIThread(object : UITask<Any>() {
                    override fun doInUIThread() {
//                        for (i in 0 until llItemContainer.getChildCount()) {
//                            val reportItemDetailView = llItemContainer.getChildAt(i) as ReportItemDetailView
//                            reportItemDetailView.setExpandVisible(true)
//                        }
                    }
                })
            }
        } else {
//            SnackbarUtil.showSnackbar(this@ReportDetailActivity, "您的系统版本暂不支持生成pdf文件，请将您的系统更新到Android4.4及以上")
        }
    }

    fun showProgress() {
        showProgress("请稍候...")
    }

    fun hideProgress() {
        RxjavaUtil.doInUIThread(object : UITask<Any>() {
            override fun doInUIThread() {
//                if (mProgressDialog != null && mProgressDialog.isShowing) {
//                    mProgressDialog.hide()
//                }
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.hide()
                }
            }
        })
    }

    /**
     * @param file
     * @param type 0图片 1pdf
     */
    fun openFile(file: File, type: Int) {
        var stype = ""
        when (type) {
            0 -> stype = "image/jpeg"
            1 -> stype = "application/pdf"
        }
        val intent: Intent
        //判断是否是Android N以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val contentUri = FileProvider.getUriForFile(this, "com.chexiang.cheming.fileprovider", file)
            intent.setDataAndType(contentUri, stype)
        } else {
            intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            //设置intent的Action属性
            intent.action = Intent.ACTION_VIEW
            //设置intent的data和Type属性。
            intent.setDataAndType(/*uri*/Uri.fromFile(file), stype)
        }
        startActivity(intent)
    }

    private fun initData() {
        var orderid  = intent.getStringExtra("orderid")
        HttpMethods.createService().getHealthReport("get_healthreport", CommonMethod.getUserId(), orderid)
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(object : HttpResultSubscriber<HttpResult<HealthReportBean>>() {
                    override fun onNext(t: HttpResult<HealthReportBean>) {
                        super.onNext(t)
                        setData(t.result.data)
                    }
                })
    }


    private fun setData(data: HealthReportBean.DataBean) {
        this.data = data
        tvBrand.text = data.pp + " " + data.cx
        tvVin.text = data.vin
        tvLicense.text = data.license

        tvXSLC.text = data.mileage + "km"
        tvCSYS.text = data.color
        tvPFBZ.text = data.emission
        tvPL.text = data.displacement


        setSummaryData(data)
        setInsurance(data.insurance)

        for (i in 0 until data.carhistory.summaryitems.size) {
            var item = data.carhistory.summaryitems[i]
            var onlineInfo = OnlineInfoView(this)
            onlineInfo.setItemData(item.item, item.level, item.project)
            llOnlineContainer.addView(onlineInfo)
        }
        for (i in 0 until data.testing.testingitems.size) {
            var item = data.testing.testingitems[i]
            var onlineInfo = OnlineInfoView(this)
            onlineInfo.setItemData(item.item, item.level, item.project)
            ll267Container.addView(onlineInfo)
        }
    }

    private fun setInsurance(data: HealthReportBean.DataBean.InsuranceBean) {
        tvCompensation.text = data.compensation
        tvRecording.text = data.recording
        tvCommercial.text = data.commercial
        tvCommercialNumber.text = data.commercialnumber
        tvCommercialTime.text = data.commercialtime
        tvCompulsory.text = data.compulsory
        tvCompulsoryNumber.text = data.compulsorynumber
        tvCompulsoryTime.text = data.compulsorytime
        tvInsuranceReportTime.text = data.insurancereporttime

    }

    private fun setSummaryData(data: HealthReportBean.DataBean) {
        var dataList = ArrayList<Map<String, Any>>()
        for (i in 0 until data.summary.size) {
            var map = HashMap<String, Any>()
            map.put("t", data.summary[i].times as Any)
            map.put("item", data.summary[i].item as Any)
            when (data.summary[i].level) {
                "1" -> {
                    map.put("img", R.mipmap.ic_rep_tan1)
                }
                "2" -> {
                    map.put("img", R.mipmap.ic_rep_tan2)
                }
                "3" -> {
                    map.put("img", R.mipmap.ic_rep_tan3)
                }
            }
            dataList.add(map)
        }
        val from = arrayOf("item", "img")
        val to = intArrayOf(R.id.tvDes, R.id.ivSummaryLevel)

        gvSummary.adapter = SimpleAdapter(this, dataList, R.layout.item_grid_summary, from, to)
    }


}