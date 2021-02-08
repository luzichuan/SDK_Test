package com.example.sdk_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencentcs.iotvideo.IoTVideoSdk;
import com.tencentcs.iotvideo.messagemgr.IAppLinkListener;
import com.tencentcs.iotvideo.messagemgr.MessageMgr;
import com.tencentcs.iotvideo.utils.LogUtils;

import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IoTVideoSdkTest extends AppCompatActivity implements View.OnClickListener{

    private Button btn_init;
    private Button btn_register;
    private Button btn_unregister;
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

        btn_init = (Button)findViewById(R.id.btn_init);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_unregister = (Button)findViewById(R.id.btn_unregister);
        btn_getTerminalId = (Button)findViewById(R.id.btn_getTerminalId);
        btn_getLocalIPAddress = (Button)findViewById(R.id.btn_getLocalIPAddress);

        btn_init.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_unregister.setOnClickListener(this);
        btn_getTerminalId.setOnClickListener(this);
        btn_getLocalIPAddress.setOnClickListener(this);

        AccessId = Long.parseLong(getApplicationContext().getString(R.string.AccessId));
        AccessToken = getApplicationContext().getString(R.string.AccessToken);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_init:
                IoTVideoSdk.init(getApplication(),null);
                setTextView(tv_print, "初始化SDK");
                break;

            //注册sdk
            case R.id.btn_register:
                IoTVideoSdk.register(AccessId, AccessToken);

                p2pVersion = -1;
                IoTVideoSdk.getMessageMgr().addAppLinkListener(new IAppLinkListener(){
                    @Override
                    public void onAppLinkStateChanged(int state) {
                        //上线监听回调
                        if (IoTVideoSdk.APP_LINK_ONLINE == state){
                            setTextView(tv_print, "注册SDK成功");
                        }
                    }
                });
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
                p2pVersion = -1;
                p2pVersion = IoTVideoSdk.getP2PVersion();
                if (p2pVersion == -1){
                    setTextView(tv_print, "反注册SDK成功" + ",P2P版本为" + p2pVersion);
                }
                else{
                    setTextView(tv_print, "反注册SDK失败" + ",P2P版本为" + p2pVersion);
                }
                break;

            //获取终端ID
            case R.id.btn_getTerminalId:
                setTextView(tv_print, "终端ID为：" + String.valueOf(IoTVideoSdk.getTerminalId()));
                break;
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