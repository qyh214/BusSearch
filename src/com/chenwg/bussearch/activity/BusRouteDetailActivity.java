package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.os.Bundle;
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
public class BusRouteDetailActivity extends Activity {

    ListView list;

    ArrayList arrayList = new ArrayList();
    JSONObject jsonObject ;
    JSONObject jsonObjectSta;

    String name;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busroutedetail);

        Bundle bundle = this.getIntent().getExtras();
        String jsonObject1= bundle.getString("jsonObject");

        try{
            jsonObject = new JSONObject(jsonObject1);
            JSONArray jsonArray = jsonObject.getJSONArray("stationdes");
            for (int i=0;i<jsonArray.length();i++){
                jsonObjectSta = new JSONObject();
                jsonObjectSta = jsonArray.getJSONObject(i);
                name = jsonObjectSta.getString("name");
                arrayList.add(name);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        list =(ListView) findViewById(R.id.list);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.list_item,
                new String[]{"title"}, new int[]{R.id.title});

        list.setAdapter(adapter);
        System.out.println("=======jsonObject===="+jsonObject);
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

















