package com.oasystem;
//报销详情 同意 拒绝
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bean.LeaveTable;
import bean.Money;
import network.NetWork;
import value.URLValues;


public class MoneyDetailsActivity extends Activity {
    Money money;
    final int sendManagerWhat=0x401,sendToastWhat=0x402;
    final String sendMessageKey="sendMessageKey",sendToastKey="sendToastKey";

    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case sendManagerWhat:
                    String result=message.getData().getString(sendMessageKey);
                    if (result.equals("")){
                        sendToast("连接服务器失败");
                    }
                    else{
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String code=jsonObject.getString("code");
                            if (code.equals("success")){
                                sendToast("处理成功");
                                setResult(2);
                                finish();
                            }
                            else{
                                sendToast("处理失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case sendToastWhat:

                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(MoneyDetailsActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_details);

        initView();
        money=(Money)(getIntent().getSerializableExtra("check"));
        initData();
    }
    public void initView(){
        Button submite=(Button)findViewById(R.id.submite);
        Button cancel=(Button)findViewById(R.id.refuse);
        submite.setOnClickListener(new BTClickListener());
        cancel.setOnClickListener(new BTClickListener());
    }

    public class BTClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.submite:
                  manage("access");
                    break;
                case R.id.refuse:
                    manage("refuse");
                            break;
            }
        }
    }

    public void manage(final String managerText){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String ,String>();
                map.put("content",managerText);
                map.put("moneyid", money.getId()+"");
                String result=netWork.doPost(map, URLValues.managerMoneyURL);
                if (result==null)result="";
                sendMessage(sendManagerWhat,sendMessageKey,result);
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
    private void initData() {
        TextView name=(TextView)findViewById(R.id.name);
        name.setText(money.getUser().getName());
        TextView time=(TextView)findViewById(R.id.time);
        time.setText(money.getCreatetime());
        TextView content=(TextView)findViewById(R.id.content);
        content.setText(money.getContent());

    }
}