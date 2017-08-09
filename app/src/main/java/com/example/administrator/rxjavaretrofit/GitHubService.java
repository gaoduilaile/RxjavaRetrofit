package com.example.administrator.rxjavaretrofit;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/27.
 */

public interface GitHubService {

    /*
      请求该接口：https://api.github.com/users/baiiu
    */

    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("user/login")
    Observable<ResponseBody> login(@Field("account") String count, @Field("password") String password);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("user/register")
    Observable<ResponseBody> register(@Field("account") String account, @Field("password") String password);


    @Headers({
            "osType: 3",
    })
    @POST("user/getInfo")
    Observable<ResponseBody> getInfo(@Header("token") String token);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("user/update")
    Observable<ResponseBody> update(
            @Header("token") String token,
            @Field("avaterUrl") String avaterUrl,
            @Field("name") String name,
            @Field("age") String age,
            @Field("sex") String sex,
            @Field("stature") String stature,
            @Field("region") String region,
            @Field("visionStatus") String visionStatus,
            @Field("introduction") String introduction);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("activity/getList")
    Observable<ResponseBody> getHotActivity(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("activity/getInfo")
    Observable<ResponseBody> getHotActivityInfo(
            @Header("token") String token,
            @Field("id") String id);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("activity/apply")
    Observable<ResponseBody> activityApply(
            @Header("token") String token,
            @Field("activityId") String activityId,
            @Field("mobile") String mobile,
            @Field("name") String name,
            @Field("age") String age);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("dynamic/getList")
    Observable<ResponseBody> getActivityTopic(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type);



    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("user/getList")
    Observable<ResponseBody> getRecommend(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type);


    /*dynamic/getList   page=1&pageSize=10&type=1*/

    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("dynamic/getList")
    Observable<ResponseBody> getFocusTopic(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("dynamic/getList")
    Observable<ResponseBody> getMyTopic(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("dynamicComment/add")
    Observable<ResponseBody> addTopicComment(
            @Header("token") String token,
            @Field("dynamicId") String dynamicId,
            @Field("content") String content);


    /*/*dynamicComment/getList  评论列表    用户标识(如token)  dynamicId=2&page=1&pageSize=10*/
    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("dynamicComment/getList")
    Observable<ResponseBody> getTopicCommentList(
            @Header("token") String token,
            @Field("dynamicId") String dynamicId,
            @Field("page") String page,
            @Field("pageSize") String pageSize);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("user/getList")
    Observable<ResponseBody> getFocusMe(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("user/getList")
    Observable<ResponseBody> getMeFocus(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("user/getList")
    Observable<ResponseBody> getSearch(
            @Header("token") String token,
            @Field("page") String page,
            @Field("pageSize") String pageSize,
            @Field("type") String type,
            @Field("keywords") String keywords);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("userAttention/payAttentionOrCancel")
    Observable<ResponseBody> focusPerson(
            @Header("token") String token,
            @Field("otherId") String id,
            @Field("type") String type);


    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("dynamic/addOrUpdate")
    Observable<ResponseBody> updateTopic(
            @Header("token") String token,
            @Field("content") String content,
            @Field("title") String title,
            @Field("mediaType") String mediaType,
            @Field("mediaUrl") String mediaUrl);


    /*删除帖子*/
    @Headers({
            "osType: 3"
    })
    @FormUrlEncoded
    @POST("dynamic/delete")
    Observable<ResponseBody> deleteTopic(
            @Header("token") String token,
            @Field("id") String id);

    /*多媒体上传token*/
    @Headers({
            "osType: 3"
    })
    @POST("pay/getUploadToken")
    Observable<ResponseBody> getUploadToken(
            @Header("token") String token);


}
