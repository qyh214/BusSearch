package com.chenwg.bussearch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.open189.api.EmpAPI;
import cn.open189.api.http.Callback;
import cn.open189.api.util.DateHelper;
import com.chenwg.bussearch.service.SearchService;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by della on 13-7-29.
 */
public class FirstActivity extends Activity {
    private Button button;
    private EditText editText;
    //String station;
    private static final String TAG = "FirstActivity";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            String city="020";

            //String stationName=station;

            //String stationName="师大暨大";
            Date date = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public void onClick(View view) {
                String station = editText.getText().toString();
                try{
                    System.out.println("stationName==="+station);
                    Bundle params=new Bundle();
                    params.putString("app_id", "405410020000031174");
                    params.putString("access_token", "fa63d346ab78af2225cf7597de0973551375092994988");
                    params.putString("city", city);
                    params.putString("stationName", URLDecoder.decode(station,"utf-8"));
                    params.putString("encode", "utf-8");
                    params.putString("batch", "2");
                    params.putString("number", "1");
                    params.putString("timestamp", formater.format(date));
                    System.out.println("params==="+params.toString());
                    SearchService.getBusSiteNameInfo(SearchService.busSiteNameInfoUrl, params,new Callback() {
                        @Override
                        public void onSuccess(final Object o) {
                            FirstActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i(TAG,o.toString());
                                    //System.out.println("ooo==="+o.toString());
                                }
                            });
                        }

                        @Override
                        public void onFail(int i, Object o) {

                        }

                        @Override
                        public void onException(Throwable throwable) {

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    class CCOnClickListener implements View.OnClickListener{
        String city="020";
        String stationName="";

        //String stationName="师大暨大";
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @Override
        public void onClick(View v) {
            try{
                System.out.println("stationName==="+stationName);
                Bundle params=new Bundle();
                params.putString("app_id", "405410020000031174");
                params.putString("access_token", "fa63d346ab78af2225cf7597de0973551375092994988");
                params.putString("city", city);
                params.putString("stationName", URLDecoder.decode(stationName,"utf-8"));
                params.putString("encode", "utf-8");
                params.putString("batch", "2");
                params.putString("number", "1");
                params.putString("timestamp", formater.format(date));
                System.out.println("params==="+params.toString());
                SearchService.getBusSiteNameInfo("http://api.189.cn/v2/besttone/getBusSiteNameInfo", params,new Callback() {
                    @Override
                    public void onSuccess(final Object o) {
                        FirstActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG,o.toString());
                                //System.out.println("ooo==="+o.toString());
                            }
                        });
                    }

                    @Override
                    public void onFail(int i, Object o) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}






















