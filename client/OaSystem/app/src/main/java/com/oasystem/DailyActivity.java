package com.oasystem;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.DalyAdapter;
import bean.Daly;
import bean.User;
import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class DailyActivity extends ActionBarActivity {

    List<Daly> dalys;
    ListView dalyList;
    public final int getDalyWhat=0x901,sendToastWhat=0x902;
    public final String getDalyKey="getDalyKey",sendToastKey="sendToastKey";
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case getDalyWhat:
                    String dalyValue=message.getData().getString(getDalyKey);
                    try {

                        JSONObject jsonObject=new JSONObject(dalyValue);
                        JSONArray jsonArray=jsonObject.getJSONArray("dalys");
                        dalys=new ArrayList<Daly>();
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            Daly daly=new Daly();
                            daly.setId(jsonObject1.getInt("id"));
                            daly.setUserId(jsonObject1.getInt("userId"));
                            daly.setTopic(jsonObject1.getString("topic"));
                            daly.setContent(jsonObject1.getString("content"));
                            daly.setTime(jsonObject1.getString("time"));
                            User user=new User();
                            user.setName(jsonObject1.getString("userName"));
                            user.setDepartment(jsonObject1.getString("userDepartment"));
                            user.setRole(jsonObject1.getString("role"));
                            daly.setUser(user);
                            dalys.add(daly);

                        }
                        DalyAdapter adapter=new DalyAdapter(dalys,DailyActivity.this);
                        dalyList.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(DailyActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        r();

        initView();
        initData();
    }

    public void initView(){
        dalyList=(ListView)findViewById(R.id.daly_list);
        ImageView add=(ImageView)findViewById(R.id.add);//添加工作日志
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(DailyActivity.this,AddDalyActivity.class);
                startActivityForResult(intent, 1);
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
                String dalyResult=netWork.doGet(map, URLValues.getDalyList);
                sendMessage(getDalyWhat,getDalyKey,dalyResult);
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
    private void r() {
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyActivity.this.finish();
            }
        });

    }
}
