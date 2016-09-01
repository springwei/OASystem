package com.oasystem;
//检查考勤
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.oasystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.SignAdapter;
import bean.Sign;
import network.NetWork;
import value.URLValues;
import value.UserInfo;

public class ManagerCheckActivity extends ActionBarActivity {
    List<Sign> signs;
    ListView listView;

    final int getSignWhat=0x301,sendToastWhat=0x302;
    final String getSignKey="getSignKey",sendToastKey="sendToastKey";

    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case getSignWhat:
                    String result=message.getData().getString(getSignKey);
                    if (!result.equals("")){
                        signs=new ArrayList<Sign>();
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            JSONArray jsonArray=jsonObject.getJSONArray("signs");
                            for(int i=0;i<jsonArray.length();i++){
                                Sign sign=new Sign();
                                JSONObject newsObject=jsonArray.getJSONObject(i);
                                sign.setId(newsObject.getInt("id"));
                                sign.setUser_id(newsObject.getInt("userId") + "");
                                sign.setAddress(newsObject.getString("address"));
                                sign.setDepartment(newsObject.getString("department"));
                                sign.setTime(newsObject.getString("time"));
                                sign.setUsername(newsObject.getString("userName"));
                                signs.add(sign);
                            }
                            SignAdapter signAdapter=new SignAdapter(signs,ManagerCheckActivity.this);
                            listView.setAdapter(signAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(ManagerCheckActivity.this,toast,Toast.LENGTH_SHORT).show();

                    break;
            };
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_check);
        initView();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String, String>();
                map.put("department", UserInfo.department);
                String result=netWork.doGet(map, URLValues.getSign);
                if (result==null){
                    result="";
                }
                sendMessage(getSignWhat,getSignKey,result);
            }
        }).start();

    }

    public void sendMessage(int what ,String key,String value){
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
    private void initView() {
        listView=(ListView)findViewById(R.id.list);
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manager_check, menu);
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
}
