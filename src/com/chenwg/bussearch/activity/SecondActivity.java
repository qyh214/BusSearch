package com.chenwg.bussearch.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
            @Override
            public void onClick(View view) {
                String station = editText.getText().toString();
                if ("".equals(station)){
                    showDialog("线路不能为空！");
                }else{
                    Intent intent = new Intent(SecondActivity.this,BusRouteArrayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("station",station);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }


        });
    }

    protected void showDialog(String text){
        //创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("温馨提示");
        builder.setMessage(text);
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }


}