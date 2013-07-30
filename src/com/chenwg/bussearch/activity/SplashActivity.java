package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import cn.open189.api.EmpAPI;
import cn.open189.api.http.Callback;
import org.json.JSONObject;

import com.chenwg.bussearch.R;

/**
 * Created by della on 13-7-29.
 */
public class SplashActivity extends Activity implements Runnable{

    private static final String TAG = "SplashActivity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        // 启动一个线程
        new Thread(this).start();




    }

    /**
     * 加载主界面
     */
    private void loadMainUI() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();// 把当前的Activity从任务栈里面移除
    }

    @Override
    public void run() {
        try {
            // 一秒后跳转到登录界面
            Thread.sleep(1000);

            EmpAPI.getAccessToken("405410020000031174","8a84db54d38abf2d8d1b4b8f78190338",null,null,new Callback() {
                @Override
                public void onSuccess(Object o) {
                    System.out.println("o============="+o.toString());

                    JSONObject jsonObject=(JSONObject)o;
                    try{
                        String token=jsonObject.getString("access_token");
                        System.out.println("token============="+token);
//                        if ("".equals(token)){
//                            SharedPreferences preferences=getSharedPreferences("token", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor=preferences.edit();
//                            editor.putString("token", token);
//                            editor.commit();
//                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }

                @Override
                public void onFail(int i, Object o) {

                }

                @Override
                public void onException(Throwable throwable) {

                }
            });



            loadMainUI();




        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}










































