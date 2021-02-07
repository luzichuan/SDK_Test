package com.example.sdk_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity{

    private Button btn_startAct;
    private Spinner pro_spinner;
    private Spinner class_spinner;

    private int pro_position = -1;
    private int cls_position = -1;

    ArrayAdapter<String> proAdapter;
    ArrayAdapter<String> clsAdapter;

    private String[] pro = new String[]{
            "",
            "com.tencentcs.iotvideo",
            "com.tencentcs.iotvideo.httpviap2p",
            "com.tencentcs.iotvideo.iotvideoplayer",
            "com.tencentcs.iotvideo.iotvideoplayer.capture",
            "com.tencentcs.iotvideo.iotvideoplayer.codec",
            "com.tencentcs.iotvideo.iotvideoplayer.mediacodec",
            "com.tencentcs.iotvideo.iotvideoplayer.player",
            "com.tencentcs.iotvideo.iotvideoplayer.render",
            "com.tencentcs.iotvideo.messagemgr",
            "com.tencentcs.iotvideo.netconfig",
            "com.tencentcs.iotvideo.netconfig.ap",
            "com.tencentcs.iotvideo.netconfig.bluetooth",
            "com.tencentcs.iotvideo.netconfig.data",
            "com.tencentcs.iotvideo.netconfig.qr",
            "com.tencentcs.iotvideo.netconfig.smartlink",
            "com.tencentcs.iotvideo.netconfig.wired",
            "com.tencentcs.iotvideo.utils",
            "com.tencentcs.iotvideo.utils.qrcode",
            "com.tencentcs.iotvideo.utils.rxjava"
    };

    private String[][] cls = new String[][]{
            {},
            {"IoTVideoError","IoTVideoSdk"},
            {"HttpResultAdapter","HttpViaP2PProxy"},
            {"AECManager","CallTypeEnum","IoTVideoPlayer","IoTVideoView","PlayerStateEnum"},
            {}
    };

    private String[][] proPackPath = new String[][]{
            {},
            {"","com.example.sdk_test.IoTVideoSdkTest"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSpinner();
        btn_startAct = (Button)findViewById(R.id.btn_activityStart);
        btn_startAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("iotVideo", "点击了启动");
                if (pro_position != -1 && cls_position != -1){
                    Log.e("iotVideo", "类名：" + cls[pro_position][cls_position]);
                    Intent intent = null;
                    try {
                        intent = new Intent(getApplicationContext(), Class.forName(proPackPath[pro_position][cls_position]));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        Log.e("iotVideo", "找不到此类：" + proPackPath[pro_position][cls_position]);
                        Log.e("iotVideo", "完整路径此类：" + IoTVideoSdkTest.class.getName());
                    }
                    if (intent != null){
                        startActivity(intent);
                    }
                }
            }
        });
    }

    //动态设置下拉框内容
    private void setSpinner() {
        pro_spinner = (Spinner)findViewById(R.id.pro_spinner);
        class_spinner = (Spinner)findViewById(R.id.cls_spinner);


        proAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, pro);
        pro_spinner.setAdapter(proAdapter);
        clsAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, cls[0]);
        class_spinner.setAdapter(clsAdapter);


        pro_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                clsAdapter = new ArrayAdapter<String>(
                        MainActivity.this, android.R.layout.simple_spinner_dropdown_item, cls[position]);
                class_spinner.setAdapter(clsAdapter);
                pro_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        class_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                cls_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}