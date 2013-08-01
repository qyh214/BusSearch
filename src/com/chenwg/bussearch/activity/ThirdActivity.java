package com.chenwg.bussearch.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.chenwg.bussearch.R;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by della on 13-7-29.
 */
public class ThirdActivity extends Activity {

    private EditText start;
    private EditText end;
    private Button button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        start = (EditText)findViewById(R.id.editTextStart);
        end = (EditText)findViewById(R.id.editTextEnd);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String startText = start.getText().toString().trim();
                String endText = end.getText().toString().trim();

                if ("".equals(startText)&&"".equals(endText)){
                    showDialog("起点和终点不能为空！");

                }else if ("".equals(startText)){
                    showDialog("起点不能为空！");
                }else if ("".equals(endText)){
                    showDialog("终点不能为空！");
                }else{
                    Intent intent = new Intent(ThirdActivity.this,BusRouteArrayActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("startText",startText);
                    bundle.putString("endText",endText);
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






















