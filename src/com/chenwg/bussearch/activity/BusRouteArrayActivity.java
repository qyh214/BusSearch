package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.open189.api.http.Callback;
import com.chenwg.bussearch.service.SearchService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chenwg.bussearch.R;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by della on 13-7-30.
 */
public class BusRouteArrayActivity extends Activity {

    private static final String TAG = "BusRouteArrayActivity";
    ListView list;
    private TextView tx;

    ArrayList arrayList = new ArrayList();

    ArrayList arrayList2 = new ArrayList();

    JSONObject jsonObject1;
    String name;
    JSONArray jsonArray1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busroutearray);

//        Bundle bundle = this.getIntent().getExtras();
//        String jsonArray = bundle.getString("jsonArray");
//        System.out.println("jsonArray============" + jsonArray);
//        try {
//            jsonArray1 = new JSONArray(jsonArray);
//            for (int i = 0; i < jsonArray1.length(); i++) {
//                jsonObject = new JSONObject();
//                jsonObject = jsonArray1.getJSONObject(i);
//                name = jsonObject.getString("name");
//                System.out.println("name=" + name);
//                arrayList.add(name);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


//        Bundle bundle = this.getIntent().getExtras();
//        String station = bundle.getString("station");


//        String city="020";
//        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//
//        try {
//            Bundle params = new Bundle();
//            params.putString("app_id", "405410020000031174");
//            params.putString("access_token", "fa63d346ab78af2225cf7597de0973551375092994988");
//            params.putString("city", city);
//            params.putString("busName", URLDecoder.decode(station, "utf-8"));
//            params.putString("encode", "utf-8");
////                    params.putString("batch", "2");
////                    params.putString("number", "5");
//            params.putString("timestamp", formater.format(date));
//            //System.out.println("params===" + params.toString());
//            SearchService.getBusRouteInfo(SearchService.busRouteInfoUrl, params, new Callback() {
//                @Override
//                public void onSuccess(final Object o) {
//
//                    BusRouteArrayActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Log.i(TAG, o.toString());
//                            try {
//                                JSONObject jsonObject = new JSONObject(o.toString());
//                                String res_code = jsonObject.getString("res_code");
//                                if ("0".equals(res_code)) {
//                                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("list");
////
////                                    Intent intent = new Intent(SecondActivity.this, BusRouteArrayActivity.class);
////                                    Bundle bundle = new Bundle();
////                                    bundle.putString("jsonArray", jsonArray.toString());
////                                    intent.putExtras(bundle);
////                                    startActivity(intent);
//                                    try {
//                                        //jsonArray1 = new JSONArray(jsonArray.toString());
//                                        for (int i = 0; i < jsonArray.length(); i++) {
//                                            jsonObject1 = new JSONObject();
//                                            jsonObject1 = jsonArray.getJSONObject(i);
//                                            name = jsonObject1.getString("name");
//                                            System.out.println("name=" + name);
//                                            arrayList.add(name);
//                                        }
//                                        System.out.println("arrayList.size()===="+arrayList.size());
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                    int i = jsonArray.length();
//                                    System.out.println("i====" + i);
//                                    Toast.makeText(getApplicationContext(),  "Update My UI",
//                                            Toast.LENGTH_LONG).show();
//                                    tx.setText(arrayList.toString());
//                                    list = (ListView) findViewById(R.id.list);
//                                    // 通过Handler获得Message对象
////                                    Message msg = new MyHandler().obtainMessage();
////                                    msg.obj = arrayList;
////                                    // 发送到Handler，在UI线程里处理Message
////                                    msg.sendToTarget();
//                                    //Log.i(TAG, jsonArray.toString());
//                                } else {
//
//                                }
//                                //System.out.println("ooo==="+o.toString());
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//                }
//
//                @Override
//                public void onFail(int i, Object o) {
//
//                }
//
//                @Override
//                public void onException(Throwable throwable) {
//
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        tx=(TextView)findViewById(R.id.tx);


        list = (ListView) findViewById(R.id.list);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.list_item,
                new String[]{"title"}, new int[]{R.id.title});

        list.setAdapter(adapter);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(BusRouteArrayActivity.this, BusRouteDetailActivity.class);
//                try {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("jsonObject", jsonArray1.getJSONObject(i).toString());
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });

    }


    private ArrayList<HashMap<String, Object>> getData() {

        Bundle bundle = this.getIntent().getExtras();
        String station = bundle.getString("station");
        final ArrayList<HashMap<String, Object>> dlist = new ArrayList<HashMap<String, Object>>();


        String city="020";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        try {
            Bundle params = new Bundle();
            params.putString("app_id", "405410020000031174");
            params.putString("access_token", "fa63d346ab78af2225cf7597de0973551375092994988");
            params.putString("city", city);
            params.putString("busName", URLDecoder.decode(station, "utf-8"));
            params.putString("encode", "utf-8");
//                    params.putString("batch", "2");
//                    params.putString("number", "5");
            params.putString("timestamp", formater.format(date));
            //System.out.println("params===" + params.toString());
            SearchService.getBusRouteInfo(SearchService.busRouteInfoUrl, params, new Callback() {
                @Override
                public void onSuccess(final Object o) {

                    BusRouteArrayActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Log.i(TAG, o.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(o.toString());
                                String res_code = jsonObject.getString("res_code");
                                if ("0".equals(res_code)) {
                                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("list");
//
//                                    Intent intent = new Intent(SecondActivity.this, BusRouteArrayActivity.class);
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("jsonArray", jsonArray.toString());
//                                    intent.putExtras(bundle);
//                                    startActivity(intent);
                                    try {
                                        //jsonArray1 = new JSONArray(jsonArray.toString());
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            jsonObject1 = new JSONObject();
                                            jsonObject1 = jsonArray.getJSONObject(i);
                                            name = jsonObject1.getString("name");
                                            System.out.println("name=" + name);
                                            arrayList.add(name);
                                        }
                                        System.out.println("arrayList.size()===="+arrayList.size());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    int i = jsonArray.length();
                                    System.out.println("i====" + i);
                                    Toast.makeText(getApplicationContext(),  "Update My UI",
                                            Toast.LENGTH_LONG).show();


                                    for (int j = 0; j < arrayList.size(); j++) {
                                        HashMap<String, Object> map = new HashMap<String, Object>();
                                        map.put("title", arrayList.get(j));
                                        //map.put("img", R.drawable.item_left2);
                                        dlist.add(map);
                                    }



                                    //tx.setText(arrayList.toString());
                                    //list = (ListView) findViewById(R.id.list);
                                    // 通过Handler获得Message对象
//                                    Message msg = new MyHandler().obtainMessage();
//                                    msg.obj = arrayList;
//                                    // 发送到Handler，在UI线程里处理Message
//                                    msg.sendToTarget();
                                    //Log.i(TAG, jsonArray.toString());
                                } else {

                                }

                                //System.out.println("ooo==="+o.toString());
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


        new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(4000);
                    System.out.println("dlist=="+dlist);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.start();

        return dlist;

    }

    /* 重写handleMessage方法，接受并处理Message消息 */
//    public class MyHandler extends Handler {
//        public void handleMessage(Message msg) {
//            // 处理接受到的Message
//            System.out.println("接受到消息：" + msg.obj + "，并成功处理");
//            arrayList = (ArrayList)msg.obj;
//        }
//
//        private ArrayList<HashMap<String, Object>> getData() {
//            ArrayList<HashMap<String, Object>> dlist = new ArrayList<HashMap<String, Object>>();
//
//            for (int i = 0; i < arrayList.size(); i++) {
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                map.put("title", arrayList.get(i));
//                //map.put("img", R.drawable.item_left2);
//                dlist.add(map);
//            }
//            return dlist;
//        }
//    }
}