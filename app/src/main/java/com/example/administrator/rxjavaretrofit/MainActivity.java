package com.example.administrator.rxjavaretrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*13783539372 123456
                * MTM3ODM1MzkzNzIxNTAxMDQxNDY0NzA5
                * */
                String token1="MTM3ODM1MzkzNzIxNTAxMDQxNDY0NzA5";

//                HttpMethods.login(mContext, "13783539372", "123456", new HttpMethods.Func() {
//                    @Override
//                    public void onJsonString(String response) {
//                        Log.e("MainActivity",response);
//                    }
//                });

//                HttpMethods.register(mContext, "13783539370", "123456", new HttpMethods.Func() {
//                    @Override
//                    public void onJsonString(String response) {
//                        Log.e("MainActivity",response);
//                    }
//                });

//                HttpMethods.getInfo(mContext,token1, new HttpMethods.Func() {
//                    @Override
//                    public void onJsonString(String response) {
//                        Log.e("MainActivity",response);
//                    }
//                });

////
//                HttpMethods.update(mContext,token1,"url","name","23","2","123-453","hanghzou","其他","个人卫生你建", new HttpMethods.Func() {
//                    @Override
//                    public void onJsonString(String response) {
//                        Log.e("MainActivity",response);
//                    }
//                });

//                HttpMethods.getActivityTopic(mContext, token1, "1", "10", "2", new HttpMethods.Func() {
//                    @Override
//                    public void onJsonString(String response) {
//                        Log.e("MainActivity",response);
//                    }
//                });

//                HttpMethods.getFocusTopic(mContext, token1, "1", "10", "1", new HttpMethods.Func() {
//                    @Override
//                    public void onJsonString(String response) {
//                        Log.e("MainActivity",response);
//                    }
//                });
                HttpMethods.getHotActivity(mContext, token1, "1", "10", "1", new HttpMethods.Func() {
                    @Override
                    public void onJsonString(String response) {
                        Log.e("MainActivity",response);
                    }
                });
            }
        });
    }
}
