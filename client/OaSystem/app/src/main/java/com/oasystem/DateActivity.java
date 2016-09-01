package com.oasystem;

import android.content.Intent;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.View;
;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.DateAdapter;
import bean.Date;
import bean.User;
import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class DateActivity extends ActionBarActivity {


    List<Date> dates;
    ListView dateList;
    public final int getDateWhat=0x901,sendToastWhat=0x902;
    public final String getDateKey="getDalyKey",sendToastKey="sendToastKey";
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case getDateWhat:
                    String dateValue=message.getData().getString(getDateKey);
                    try {

                        JSONObject jsonObject=new JSONObject(dateValue);
                        JSONArray jsonArray=jsonObject.getJSONArray("dates");
                        dates=new ArrayList<Date>();
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            Date date=new Date();
                            date.setId(jsonObject1.getInt("id"));
                            date.setUserId(jsonObject1.getInt("userId"));
                            date.setTopic(jsonObject1.getString("topic"));
                            date.setContent(jsonObject1.getString("content"));
                            date.setTime(jsonObject1.getString("time"));
                            User user=new User();
                            user.setName(jsonObject1.getString("userName"));
                            user.setDepartment(jsonObject1.getString("userDepartment"));
                            user.setRole(jsonObject1.getString("role"));
                            date.setUser(user);
                            dates.add(date);

                        }
                        DateAdapter adapter=new DateAdapter(dates,DateActivity.this);
                        dateList.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(DateActivity.this, toast, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        r();

        initView();
        initData();
    }

    public void initView(){
        dateList=(ListView)findViewById(R.id.date_list);
        ImageView add=(ImageView)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(DateActivity.this,AddDateActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        ImageView r=(ImageView)findViewById(R.id.Return);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==1){
            initData();
        }
    }

    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String,String>();
                map.put("id", UserInfo.id+"");
                String dateResult=netWork.doGet(map, URLValues.getDateList);
                sendMessage(getDateWhat,getDateKey,dateResult);
            }
        }).start();
    }
    public void sendMessage(int what,String key,String value){
        Message message=new Message();
        message.what=what;
        Bundle bundle=new Bundle();
        bundle.putString(key, value);
        message.setData(bundle);
        handler.sendMessage(message);
    }
    public void sendToast(String toast){
        sendMessage(sendToastWhat,sendToastKey,toast);
    }
    private void r() {
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateActivity.this.finish();
            }
        });

    }
}
