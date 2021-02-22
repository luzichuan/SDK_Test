package com.example.sdk_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencentcs.iotvideo.IoTVideoSdk;
import com.tencentcs.iotvideo.messagemgr.IAppLinkListener;

import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IoTVideoSdkTest extends AppCompatActivity implements View.OnClickListener{

    private Button btn_register;
    private Button btn_unregister;
    private Button btn_getP2PVersion;
    private Button btn_getTerminalId;
    private Button btn_getLocalIPAddress;
    private TextView tv_print;

    private int p2pVersion;
    private long AccessId;
    private String AccessToken;
    private final static String TAG = "IoTVideoSdkTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot_video_sdk);

        tv_print = (TextView)findViewById(R.id.tv_print);
        tv_print.setMovementMethod(ScrollingMovementMethod.getInstance());


        btn_register = (Button)findViewById(R.id.btn_register);
        btn_unregister = (Button)findViewById(R.id.btn_unregister);
        btn_getP2PVersion = (Button)findViewById(R.id.btn_getP2PVersion);
        btn_getTerminalId = (Button)findViewById(R.id.btn_getTerminalId);
        btn_getLocalIPAddress = (Button)findViewById(R.id.btn_getLocalIPAddress);

        btn_register.setOnClickListener(this);
        btn_unregister.setOnClickListener(this);
        btn_getP2PVersion.setOnClickListener(this);
        btn_getTerminalId.setOnClickListener(this);
        btn_getLocalIPAddress.setOnClickListener(this);

        AccessId = Long.parseLong(getApplicationContext().getString(R.string.AccessId));
        AccessToken = getApplicationContext().getString(R.string.AccessToken);

        IoTVideoSdk.init(getApplication(),null);
        setTextView(tv_print, "已自动初始化SDK");

        setMessageMgr();

    }

    /*
    *   添加App上线监听
    * */
    private void setMessageMgr() {
        IoTVideoSdk.getMessageMgr().addAppLinkListener(new IAppLinkListener(){
            @Override
            public void onAppLinkStateChanged(int state) {
                switch (state){
                    case IoTVideoSdk.APP_LINK_ONLINE:
                        setTextView(tv_print, "注册SDK成功，APP已上线");
                        break;
                    case IoTVideoSdk.APP_LINK_OFFLINE:
                        setTextView(tv_print, "反注册SDK成功，APP已离线");
                        break;
                }
            }
        });
    }

    /*
    *   设置按键响应事件
    * */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //注册sdk
            case R.id.btn_register:
                IoTVideoSdk.register(AccessId, AccessToken);
                break;

            //获取局域网IP地址
            case R.id.btn_getLocalIPAddress:

                String strIp = "";
                try {
                    strIp = IoTVideoSdk.getLocalIPAddress();
                } catch (SocketException e) {
                    e.printStackTrace();
                    Log.d(TAG, e.toString());
                }
                setTextView(tv_print, "获取本地IP为 : " + strIp);
                break;

            //反注册
            case R.id.btn_unregister:
                IoTVideoSdk.unregister();
                break;

            //获取终端ID
            case R.id.btn_getTerminalId:
                setTextView(tv_print, "终端ID为：" + String.valueOf(IoTVideoSdk.getTerminalId()));
                break;

            case R.id.btn_getP2PVersion:
                setTextView(tv_print, "P2P版本为：" + IoTVideoSdk.getP2PVersion());
        }
    }

    /*
     *   输出到文本框中
     *
     * */

    public void setTextView(TextView textView, String str){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);

        textView.append(dateString + " --- ");
        textView.append(str);
        textView.append("\n");

        int offset = 0;
        offset = tv_print.getLineCount() * tv_print.getLineHeight();
        if (offset > tv_print.getHeight()) tv_print.scrollTo(0, offset - tv_print.getHeight());

        Log.d(TAG, "-------" + str);
    }
}