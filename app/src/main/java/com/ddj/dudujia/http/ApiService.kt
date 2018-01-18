package com.first.basket.http

import com.ddj.dudujia.base.BaseBean
import com.ddj.dudujia.base.CarListBean
import com.ddj.dudujia.bean.CodeBean
import com.ddj.dudujia.bean.HomeBean
import com.ddj.dudujia.bean.LoginBean
import com.ddj.dudujia.http.HttpResult
import retrofit2.http.Field
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

    //我的爱车
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun getMyCar(@Field("action") action: String,@Field("userid") userid: String): Observable<HttpResult<CarListBean>>

    //添加我的爱车
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun doAddMyCar(@Field("action") action: String, @Field("userid") userid: String, @Field("licenseplate") licenseplate: String, @Field("cartype") cartype: String, @Field("enginenumber") enginenumber: String, @Field("vin") vin: String): Observable<HttpResult<BaseBean>>

    //违章查询
    @FormUrlEncoded
    @POST("ClientAPI.php")
    fun doIlleagalQuery(@Field("action") action: String, @Field("userid") userid: String, @Field("licenseplate") licenseplate: String, @Field("cartype") cartype: String, @Field("enginenumber") enginenumber: String, @Field("vin") vin: String): Observable<HttpResult<BaseBean>>
}
