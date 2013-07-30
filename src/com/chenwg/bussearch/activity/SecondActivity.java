package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.open189.api.http.Callback;

import com.chenwg.bussearch.R;
import com.chenwg.bussearch.service.SearchService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by della on 13-7-29.
 */
public class SecondActivity extends Activity {
    private Button button;
    private EditText editText;
    //String station;
    private static final String TAG = "FirstActivity";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

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
                    params.putString("busName", URLDecoder.decode(station, "utf-8"));
                    params.putString("encode", "utf-8");
//                    params.putString("batch", "2");
//                    params.putString("number", "5");
                    params.putString("timestamp", formater.format(date));
                    System.out.println("params==="+params.toString());
                    SearchService.getBusRouteInfo(SearchService.busRouteInfoUrl, params, new Callback() {
                        @Override
                        public void onSuccess(final Object o) {
                            SecondActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Log.i(TAG, o.toString());
                                    try{
                                        JSONObject jsonObject = new JSONObject(o.toString());
                                        String res_code = jsonObject.getString("res_code");
                                        if ("0".equals(res_code)){
                                            JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("list");

                                            Intent intent = new Intent(SecondActivity.this,BusRouteArrayActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("jsonArray",jsonArray.toString());
                                            intent.putExtras(bundle);
                                            startActivity(intent);

                                            int i=jsonArray.length();
                                            System.out.println("i===="+i);
                                            Log.i(TAG, jsonArray.toString());
                                        }else {

                                        }
                                        //System.out.println("ooo==="+o.toString());
                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }

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


}