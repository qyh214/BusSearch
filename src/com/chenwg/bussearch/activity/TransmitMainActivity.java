package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import cn.open189.api.http.Callback;
import com.chenwg.bussearch.R;
import com.chenwg.bussearch.model.BusRoute;
import com.chenwg.bussearch.service.SearchService;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by della on 13-8-1.
 */
public class TransmitMainActivity extends Activity {
    public static final String TAG="TransmitMainActivity";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transmitmain);

        Bundle bundle = this.getIntent().getExtras();
        String startText = bundle.getString("startText");
        String endText = bundle.getString("endText");


        String city = "020";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        try {
            Bundle params = new Bundle();
            params.putString("app_id", "405410020000031174");
            params.putString("access_token", "fa63d346ab78af2225cf7597de0973551375092994988");
            params.putString("city", city);
            //途经点坐标集合
            params.putString("xys","");
            /**
             * 行驶类型
             * 0表示最快捷模式，尽可能乘坐轨道交通和快速公交线路；
             * 2表示最少换乘模式，尽可能减少换乘次数；
             * 3表示最少步行模式，尽可能减少步行距离；
             * 4表示最舒适模式，；乘坐有空调的车线；
             * 5表示纯地铁模式，只选择地铁换乘；
             */
            params.putString("type","");
            //params.putString("busName", URLDecoder.decode(station, "utf-8"));
            params.putString("encode", "utf-8");
            // params.putString("batch", "2");
            // params.putString("number", "5");
            params.putString("timestamp", formater.format(date));
            // System.out.println("params===" + params.toString());
            SearchService.getBusRouteInfo(SearchService.busRouteInfoUrl, params, new Callback() {
                @Override
                public void onSuccess(final Object o) {

                    TransmitMainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Log.i(TAG, o.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(o.toString());
                                String res_code = jsonObject.getString("res_code");
//                                if ("0".equals(res_code)) {
//                                    jsonArray = jsonObject.getJSONObject("response").getJSONArray("list");
//
//                                    try {
//                                        // jsonArray1 = new JSONArray(jsonArray.toString());
//                                        for (int i = 0; i < jsonArray.length(); i++) {
//                                            busRoute = new BusRoute();
//                                            jsonObject1 = new JSONObject();
//                                            jsonObject1 = jsonArray.getJSONObject(i);
//                                            name = jsonObject1.getString("name");
//                                            System.out.println("name=" + name);
//                                            busRoute.setName(name);
//                                            arrayList.add(busRoute);
//                                        }
//                                        System.out.println("arrayList.size()====" + arrayList.size());
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                    progressBar.setVisibility(View.INVISIBLE);
//                                    tx2.setVisibility(View.INVISIBLE);
//
//                                    BinderListData(arrayList);
//                                    int i = jsonArray.length();
//                                    System.out.println("i====" + i);
//                                    Toast.makeText(getApplicationContext(), "总共" + i + "条路线", Toast.LENGTH_SHORT).show();
//
//                                } else {
//
//                                }
                            } catch (JSONException e) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}