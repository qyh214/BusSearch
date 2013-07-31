/*
 * @(#) ListViewAdapter.java
 * @Author:della(mail) 2013-7-31
 * @Copyright (c) 2002-2013 Travelsky Limited. All rights reserved.
 */
package com.chenwg.bussearch.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.chenwg.bussearch.R;
import com.chenwg.bussearch.activity.BusRouteArrayActivity;
import com.chenwg.bussearch.model.BusRoute;


/**
  * @author della(mail) 2013-7-31
  * @version 1.0
  * @Function 类功能说明
  */
public class ListViewAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList data;
    private int id;
    private Context context;
    private LayoutInflater inflater;

    public ListViewAdapter(int item, BusRouteArrayActivity mainActivity, ArrayList data) {
        this.data = data;
        this.context = mainActivity;
        this.id = item;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        TextView name = null;
  
        if (view == null) {
            view = inflater.inflate(id, null);
            name = (TextView) view.findViewById(R.id.title);
           
            // 保存view对象到ObjectClass类中
            view.setTag(new ObjectClass(name));
        } else {
            // 得到保存的对象
            ObjectClass objectclass = (ObjectClass) view.getTag();
            name = objectclass.name;
 
        }
        BusRoute busRoute = (BusRoute) data.get(position);
        // 帮数据绑定到控件上
        name.setText(busRoute.getName().toString());

        // 加载图片资源
        return view;
    }

    private final class ObjectClass {
        TextView name = null;

        public ObjectClass(TextView name) {
            this.name = name;
        }
    }

//    private final class ObjectClass {
//        TextView name = null;
//        TextView sex = null;
//        TextView age = null;
//        ImageView img = null;
//
//        public ObjectClass(TextView name, TextView sex, TextView age, ImageView img) {
//            this.name = name;
//            this.sex = sex;
//            this.age = age;
//            this.img = img;
//        }
//    }

}
