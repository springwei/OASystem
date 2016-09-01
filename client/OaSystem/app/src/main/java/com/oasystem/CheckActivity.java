package com.oasystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class CheckActivity extends Activity {
final int signWhat=0x901,sendToastWhat=0x902;
    final String signKey="signKey",sendToastKey="sendToastKey";
    ListView list;
    ImageView sign;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public mLocationListener1 mLocationListener = new mLocationListener1();
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case signWhat:
                    String signResult=message.getData().getString(signKey);
                    if (signResult.equals("")){
                        sendToast("失败");
                    }else{
                        try {
                            JSONObject jsonObject=new JSONObject(signResult);
                            String code=jsonObject.getString("code");
                            if (code.equals("success")){
                                sendToast("签到成功");
                                finish();
                            }
                            else{
                                sendToast("签到失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(CheckActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        initView();
        r();
    }
    public void initView(){
        list=(ListView)findViewById(R.id.list);
        sign=(ImageView)findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startLocation();
            }
        });
    }
    public class mLocationListener1 implements AMapLocationListener{
        //实现接口
        //AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {

                    final String address=amapLocation.getAddress();
                    AlertDialog.Builder dialog=new AlertDialog.Builder(CheckActivity.this);
                    dialog.setTitle("确认");
                    dialog.setMessage("当前位置：\n" + address + "\n是否进行签到？");
                    dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            submiteAddress(address);
                        }
                    });
                    dialog.setNegativeButton("否",null);
                    dialog.show();
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Toast.makeText(CheckActivity.this,amapLocation.getErrorInfo(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void submiteAddress(final String address){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String,String>();
                map.put("id", UserInfo.id+"");
                map.put("address",address);
                map.put("department",UserInfo.department);
                String result=netWork.doPost(map, URLValues.sign);
                if (result==null)result="";
                sendMessage(signWhat,signKey,result);
            }
        }).start();
    }
    public void sendMessage(int what,String key,String value){
        Message message=new Message();
        message.what=what;
        Bundle bundle=new Bundle();
        bundle.putString(key,value);
        message.setData(bundle);
        handler.sendMessage(message);
    }
    public void sendToast(String toast){
        sendMessage(sendToastWhat,sendToastKey,toast);
    }
    private void startLocation() {
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位模式为高精度模式，Battery_Saving为低功耗模式模，Device_Sensors是仅设备式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
//设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
//设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
//设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
//设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
//给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();

    }

    private void r() {
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位
    }
}
