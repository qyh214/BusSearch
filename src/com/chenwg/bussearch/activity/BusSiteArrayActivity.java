package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.open189.api.http.Callback;
import com.chenwg.bussearch.R;
import com.chenwg.bussearch.adapter.ListViewAdapter;
import com.chenwg.bussearch.model.BusRoute;
import com.chenwg.bussearch.model.BusSite;
import com.chenwg.bussearch.service.SearchService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by della on 13-8-1.
 */
public class BusSiteArrayActivity extends Activity {
    public static final String TAG="BusSiteArrayActivity";
    ImageButton backButton;
    ImageButton homeButton;
    private TextView tx;
    private TextView tx2;
    ProgressBar progressBar;
    JSONArray jsonArray;
    BusRoute busRoute;
    ArrayList arrayList = new ArrayList();
    JSONObject jsonObject1;
    String name;


    ListView list;
    private ListAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bussitearray);

        tx = (TextView)findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        tx2 = (TextView)findViewById(R.id.textView2);
        homeButton = (ImageButton)findViewById(R.id.home);
        backButton = (ImageButton)findViewById(R.id.back);

        Bundle bundle = this.getIntent().getExtras();
        String stationName = bundle.getString("stationName");

        String city = "020";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        try {
            Bundle params = new Bundle();
            params.putString("app_id", "405410020000031174");
            params.putString("access_token", "fa63d346ab78af2225cf7597de0973551375092994988");
            params.putString("city", city);
            params.putString("stationName", URLDecoder.decode(stationName, "utf-8"));
            params.putString("encode", "utf-8");
            // params.putString("batch", "2");
            // params.putString("number", "5");
            params.putString("timestamp", formater.format(date));
            // System.out.println("params===" + params.toString());
            SearchService.getBusSiteNameInfo(SearchService.busSiteNameInfoUrl, params, new Callback() {
                @Override
                public void onSuccess(final Object o) {

                    BusSiteArrayActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, o.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(o.toString());
                                String res_code = jsonObject.getString("res_code");
                                if ("0".equals(res_code)) {
                                    jsonArray = jsonObject.getJSONObject("response").getJSONArray("list");

                                    try {

                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            busRoute = new BusRoute();
                                            jsonObject1 = new JSONObject();
                                            jsonObject1 = jsonArray.getJSONObject(i);
                                            name = jsonObject1.getString("key_name");
                                            System.out.println("name=" + name);
                                            busRoute.setName(name);
                                            arrayList.add(busRoute);
                                        }
                                        System.out.println("arrayList.size()====" + arrayList.size());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    progressBar.setVisibility(View.INVISIBLE);
                                    tx2.setVisibility(View.INVISIBLE);

                                    BinderListData(arrayList);
                                    int i = jsonArray.length();
                                    System.out.println("i====" + i);
                                    Toast.makeText(getApplicationContext(), "总共" + i + "条路线", Toast.LENGTH_SHORT).show();

                                } else {

                                }

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

        list = (ListView) findViewById(R.id.list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(BusSiteArrayActivity.this, BusRouteDetailActivity.class);
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("jsonObject", jsonArray.getJSONObject(i).toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusSiteArrayActivity.this.finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(BusSiteArrayActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // 绑定数据
    public void BinderListData(ArrayList arrayListRoute) {

        // 创建adapter对象
        adapter = new ListViewAdapter(R.layout.list_item, this, arrayListRoute);
        // 将Adapter绑定到listview中
        list.setAdapter(adapter);
    }
}