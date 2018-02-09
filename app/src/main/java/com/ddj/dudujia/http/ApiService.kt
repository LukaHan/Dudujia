package com.first.basket.http

import com.ddj.dudujia.base.BaseBean
import com.ddj.dudujia.base.CarListBean
import com.ddj.dudujia.bean.*
import com.ddj.dudujia.http.HttpResult
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable


/**
 * Created by hanshaobo on 05/09/2017.
 */
interface ApiService {
    //首页
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getMainpage(@Field("action") action: String): Observable<HttpResult<HomeBean>>

    //登录
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun doLogin(@Field("action") action: String, @Field("phonenumber") phonenumber: String, @Field("code") logincode: String): Observable<HttpResult<LoginBean>>

    //发送验证码
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getCode(@Field("action") action: String, @Field("phonenumber") phonenumber: String): Observable<HttpResult<CodeBean>>

    //获取用户信息
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getUserInfo(@Field("action") action: String, @Field("phone") phone: String, @Field("userid") userid: String): Observable<HttpResult<BaseBean>>

    //我的爱车列表
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getMyCar(@Field("action") action: String, @Field("userid") userid: String): Observable<HttpResult<CarListBean>>


    //获取车辆详细信息
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getMyCarDetail(@Field("action") action: String, @Field("userid") userid: String, @Field("licenseplate") licenseplate: String, @Field("vin") vin: String): Observable<HttpResult<CarDetailBean>>

    //添加我的爱车
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun doAddMyCar(@Field("action") action: String, @Field("userid") userid: String, @Field("licenseplate") licenseplate: String, @Field("cartype") cartype: String, @Field("enginenumber") enginenumber: String, @Field("vin") vin: String): Observable<HttpResult<BaseBean>>

    //违章查询
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun doIlleagalQuery(@Field("action") action: String, @Field("userid") userid: String, @Field("licenseplate") licenseplate: String, @Field("cartype") cartype: String, @Field("enginenumber") enginenumber: String, @Field("vin") vin: String): Observable<HttpResult<BaseBean>>


    //立即预约界面
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getReservation(@Field("action") action: String, @Field("userid") userid: String): Observable<HttpResult<ReservationBean>>

    //获取线下检测报告列表
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getOfflineReportList(@Field("action") action: String, @Field("userid") userid: String): Observable<HttpResult<OfflineReportBean>>

    //获取线下检测报告列表
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getOfflineReport(@Field("action") action: String, @Field("offlinereportid") offlinereportid: String): Observable<HttpResult<ReportBasicBean>>

    //支付宝支付线下检测费用
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun doPayForAlipay(@Field("action") action: String, @FieldMap map: HashMap<String, String>): Observable<HttpResult<BaseBean>>

    //修改用户名
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun modifyUsername(@Field("action") action: String, @Field("userid") userid: String, @Field("username") username: String): Observable<HttpResult<LoginBean>>

    //检查VIN是否可查
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun checkVin(@Field("action") action: String, @Field("vinnum") vinnum: String): Observable<HttpResult<CheckVinBean>>
}
