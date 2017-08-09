package com.example.administrator.rxjavaretrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/27.
 */

public class HttpMethods {
    private static ProgressDialog progressDialog;

    private static String BASE_URL = "http://vc.krvision.cn:8080/VisionCircle/";
//    public static final String BASE_URL = "http://10.10.10.46:8080/VisionCircle/";

    private static final int DEFAULT_TIMEOUT = 8;
    public static void startProgress(Context context, String string) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(string);
        View view = View.inflate(context, R.layout.loading_dialog, null);
        progressDialog.setView(view);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public static void cancelProgress(Context context) {
        if (progressDialog.isShowing()) {
            progressDialog.cancel();
            progressDialog.dismiss();
        }
    }

    private static GitHubService getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置baseUrl,注意，baseUrl必须后缀"/"
                .baseUrl(BASE_URL)
                //添加Gson转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService mGitHubService = retrofit.create(GitHubService.class);
        return mGitHubService;
    }

    private static void subscribe(final Context context, final Func func, Observable<ResponseBody> observable) {
        observable.subscribeOn(Schedulers.io())//这里需要注意的是，网络请求在非ui线程。如果返回结果是依赖于Rxjava的，则需要变换线程
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        Log.e("onStart", " ");
                        HttpMethods.startProgress(context,"正在加载...");
                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.e("onNext", string);
                            func.onJsonString(string);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", " ");
//                        func.onCompleted();
                        HttpMethods.cancelProgress(context);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", e.toString());
//                        func.onError();
                        HttpMethods.cancelProgress(context);
                    }
                });
    }


    public static void login(final Context context, String account, String password, final Func func) {
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.login(account, password);
        subscribe(context, func, observable);

    }

    public static void register(final Context context, String account, String password, final Func func) {
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.register(account, password);
        subscribe(context, func, observable);
    }

    public static void getInfo(final Context context, String token1, final Func func) {
        String str4 = getString(token1);
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.getInfo(str4);
        subscribe(context, func, observable);
    }

    public static void update(final Context context,String token1, String avaterUrl, String name, String age, String sex
            , String stature, String region, String visionStatus, String introduction, final Func func) {
        String str4 = getString(token1);
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.update(str4, avaterUrl, name, age, sex, stature, region, visionStatus, introduction);
        subscribe(context, func, observable);

    }

    public static void getHotActivity(final Context context, String token1, String page, String pageSize, String type, final Func func) {
        String str4 = getString(token1);
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.getHotActivity(str4.trim(), "1", "10", "1");
        subscribe(context, func, observable);
    }

    /*热门活动  activity/getList   type1 page=1&pageSize=10     状态，1：报名中，2：进行中，0：已结束*   */
    public static void getHotActivityInfo(final Context context,String token1, String id,final Func func) {
        String str4 = getString(token1);
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.getHotActivityInfo(str4.trim(), id);
        subscribe(context, func, observable);

    }

    /*热门活动报名   activity/apply   activityId=4&mobile=18510770174&name=张三&age=18  */
    public static void activityApply(final Context context, String token1, String activityId,String mobile,String name,String age,final Func func) {
        String str4 = getString(token1);
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.activityApply(str4.trim(), activityId,mobile,name,age);
        subscribe(context, func, observable);
    }

    /*热门动态列表      dynamic/getList   page=1&pageSize=10&type=2 **/
    public static void getActivityTopic(final Context context, String token1, String page, String pageSize, String type, final Func func) {
        String str4 = getString(token1);
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable  = mGitHubService.getActivityTopic(str4.trim(), "1", "10", "2");
        subscribe(context, func, observable);
    }
    /* dynamic/getList   page=1&pageSize=10&type=1   关注列表*/
    public static void getFocusTopic(final Context context, String token1, String page, String pageSize, String type, final Func func) {
        String str4 = getString(token1);
        GitHubService mGitHubService = HttpMethods.getInstance();
        Observable<ResponseBody> observable = mGitHubService.getFocusTopic(str4.trim(), "1", "10", "1");
        subscribe(context, func, observable);
    }

    /*将toke值再次加密*/
    private static String getString(String token1) {
        String token2 = "kr_v%88";
        StringBuffer buffer = new StringBuffer();
        String tokenStr = buffer.append(token1.trim()).append(token2.trim()).toString().trim();
        String encodedString = Base64.encodeToString(tokenStr.getBytes(), Base64.NO_WRAP);
        String str4 = encodedString.replaceAll("\\s*", "");
        return str4;
    }
    public interface Func {
        void onJsonString(String response);
//        void onError();
//        void onCompleted();
    }
}
