package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chenwg.bussearch.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by della on 13-7-30.
 */
public class BusRouteArrayActivity extends Activity {

    ListView list;

    ArrayList arrayList = new ArrayList();

    JSONObject jsonObject;
    String name;
    JSONArray jsonArray1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busroutearray);

        Bundle bundle = this.getIntent().getExtras();
        String jsonArray = bundle.getString("jsonArray");
        System.out.println("jsonArray============"+jsonArray);
        try{
            jsonArray1 = new JSONArray(jsonArray);
            for (int i=0;i<jsonArray1.length();i++){
                jsonObject = new JSONObject();
                jsonObject = jsonArray1.getJSONObject(i);
                name = jsonObject.getString("name");
                System.out.println("name="+name);
                arrayList.add(name);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        list =(ListView) findViewById(R.id.list);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.list_item,
                new String[]{"title"}, new int[]{R.id.title});

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BusRouteArrayActivity.this,BusRouteDetailActivity.class);
                try{
                    Bundle bundle = new Bundle();
                    bundle.putString("jsonObject",jsonArray1.getJSONObject(i).toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        });

    }


    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> dlist = new ArrayList<HashMap<String,Object>>();

        for(int i =0;i<arrayList.size();i++){
            HashMap<String, Object>map = new HashMap<String, Object>();
            map.put("title", arrayList.get(i));
            //map.put("img", R.drawable.item_left2);
            dlist.add(map);
        }
        return dlist;
    }
}