package com.oasystem;
//处理记录
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.LeaveAdapter;
import adapter.MoneyAdapter;
import bean.LeaveTable;
import bean.Money;
import bean.User;
import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class MoneyHistoryActivity extends Activity {
    final int checkMoneyTableWhat=0x501,sendToastWhat=0x502;
    final String checkMoneyKey="checkMoneyKey",sendToastKey="sendToastKey";
    ListView list;
    List<Money> Moneys;
    boolean ifAccess=true;
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case checkMoneyTableWhat:
                    String result=message.getData().getString(checkMoneyKey);

                    if (result.equals("")){
                        sendToast("获取失败");
                    }
                    else{
                        try {
                            Moneys=new ArrayList<Money>();
                            JSONObject jsonObject=new JSONObject(result);
                            JSONArray jsonArray=jsonObject.getJSONArray("moneys");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject moneyObject=jsonArray.getJSONObject(i);
                                Money money=new Money();
                                money.setId(moneyObject.getInt("id"));
                                money.setTopic(moneyObject.getString("topic"));
                                money.setContent(moneyObject.getString("content"));
                                money.setCreatetime(moneyObject.getString("createTime"));
                                User user=new User();
                                user.setId(moneyObject.getInt("userId"));
                                user.setName(moneyObject.getString("userName"));
                                user.setPhone(moneyObject.getString("phone"));
                                money.setUser(user);
                                Moneys.add(money);
                            }
                            MoneyAdapter adapter=new MoneyAdapter(MoneyHistoryActivity.this,Moneys);
                            list.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(MoneyHistoryActivity.this, toast, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_history);//处理记录
        initView();

        initData("access");

    }


    public void initView(){
        list=(ListView)findViewById(R.id.list);
        final TextView topic=(TextView)findViewById(R.id.topic);
        final TextView flagText=(TextView)findViewById(R.id.flag);
        flagText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ifAccess) {
                    initData("refuse");
                    topic.setText("已拒绝");
                    flagText.setText("已同意");
                    ifAccess=false;
                }
                else{
                    initData("access");
                    topic.setText("已同意");
                    flagText.setText("已拒绝");
                    ifAccess=true;
                }
            }
        });

    }

    public void initData(final String content){
        new Thread(new Runnable() {
            @Override
            public void run() {

                NetWork netWork=new NetWork();
                Map<String,String >map=new HashMap<String,String>();
                map.put("department", UserInfo.department);
              //  map.put("status","access");
                map.put("status",content);
                String result=netWork.doGet(map, URLValues.getMoneyServlet);
                if (result==null) result="";
                sendMessage(checkMoneyTableWhat, checkMoneyKey, result);
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
}
