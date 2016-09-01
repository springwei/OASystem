package com.oasystem;
//报销管理
import android.app.DatePickerDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class MoneyActivity extends ActionBarActivity {

    EditText topic,content;
    TextView submite;
    final int sendMoneyWhat=0x301,sendToastWhat=0x302;
    final String sendMoneyKey="sendLeaveKey",sendToastKey="sendToastKey";
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case sendMoneyWhat:
                    String result=message.getData().getString(sendMoneyKey);
                    if (result.equals("")){
                        sendToast("提交失败");

                    }
                    else{
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String code=jsonObject.getString("code");
                            if(code.equals("success")){
                                sendToast("提交成功");
                                finish();
                            }
                            else{
                                sendToast("提交失败");
                            }


                        } catch (JSONException e) {


                        }

                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(MoneyActivity.this, toast, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        initView();
        r();
    }
    public void initView(){
        topic=(EditText)findViewById(R.id.topic);
        content=(EditText)findViewById(R.id.content);
        submite=(TextView)findViewById(R.id.submite);
        submite.setOnClickListener(new TextClickListener());

    }

    private void r() {
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Return);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoneyActivity.this.finish();
            }
        });

    }

    public class TextClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.submite:

                    submite();
                    break;
            }
        }
    }
    public void submite(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String,String>();
                map.put("userId", UserInfo.id+"");
                map.put("topic",topic.getText().toString());
                map.put("content",content.getText().toString());
                map.put("department",UserInfo.department);
                String result=netWork.doPost(map, URLValues.sendMoney);
                if (result==null) result="";
                sendMessage(sendMoneyWhat,sendMoneyKey,result);
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
        sendMessage(sendToastWhat, sendToastKey, toast);
    }
}

