package com.chenwg.bussearch.activity;

import com.chenwg.bussearch.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = getTabHost();
        setTabs();
    }

    private void setTabs()
    {
        addTab("站点", R.drawable.tab_home, FirstActivity.class);
        addTab("线路", R.drawable.tab_search, SecondActivity.class);
        addTab("换乘", R.drawable.tab_search, ThirdActivity.class);
        addTab("关于", R.drawable.tab_home, FouthActivity.class);


    }
    private void addTab(String labelId, int drawableId, Class<?> c)
    {
        Intent intent = new Intent(this, c);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);


        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
        tabIndicator.setBackgroundColor(R.color.lightblue);
        TextView title = (TextView) tabIndicator.findViewById(R.id.title);
        title.setText(labelId);
        ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
        icon.setImageResource(drawableId);
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
