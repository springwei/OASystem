package com.oasystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.LeaveAdapter;
import adapter.MoneyAdapter;
import bean.Money;
import bean.User;
import network.NetWork;
import value.URLValues;
import value.UserInfo;

public class CheckMoneyActivity extends ActionBarActivity {
    final int checkLeaveTableWhat=0x601,sendToastWhat=0x602;
    final String checkLeaveKey="checkLeaveKey",sendToastKey="sendToastKey";
    ListView moneyList;
    List<Money> moneysTables;
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case checkLeaveTableWhat:
                    String result=message.getData().getString(checkLeaveKey);
                    if (result.equals("")){
                        sendToast("获取失败");
                    }
                    else{
                        try {
                            moneysTables=new ArrayList<Money>();
                            JSONObject jsonObject=new JSONObject(result);
                            JSONArray jsonArray=jsonObject.getJSONArray("moneys");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject leaveObject=jsonArray.getJSONObject(i);
                                Money leaveTable=new Money();
                                leaveTable.setId(leaveObject.getInt("id"));
                                leaveTable.setTopic(leaveObject.getString("topic"));
                                leaveTable.setContent(leaveObject.getString("content"));
                                leaveTable.setCreatetime(leaveObject.getString("createTime"));
                                User user=new User();
                                user.setId(leaveObject.getInt("userId"));
                                user.setName(leaveObject.getString("userName"));
                                user.setPhone(leaveObject.getString("phone"));
                                leaveTable.setUser(user);
                                moneysTables.add(leaveTable);
                            }
                            MoneyAdapter adapter=new MoneyAdapter(CheckMoneyActivity.this,moneysTables);
                            moneyList.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(CheckMoneyActivity.this, toast, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_money);
        initView();
        initData();
    }
    public void initView(){
        TextView check =(TextView)findViewById(R.id.check);//处理记录
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(CheckMoneyActivity.this,MoneyHistoryActivity.class);
                startActivity(intent);
            }
        });
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        moneyList=(ListView)findViewById(R.id.money_list);
        moneyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CheckMoneyActivity.this, MoneyDetailsActivity.class);
                intent.putExtra("check", moneysTables.get(position));
                startActivityForResult(intent, 1);

            }
        });

    }
    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String > map=new HashMap<String,String>();
                map.put("department", UserInfo.department);
                map.put("status","wait");
                String result=netWork.doGet(map, URLValues.getMoneyServlet);
                if (result==null) result="";
                sendMessage(checkLeaveTableWhat,checkLeaveKey,result);
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==2){
            initData();
        }
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
}
