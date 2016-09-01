package com.oasystem.manager;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oasystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import network.NetWork;
import value.URLValues;
import value.UserInfo;

public class AddNewsActivity extends ActionBarActivity {
    String role;
    EditText topicEdit,contentEdit;
    TextView submiteTV;
    final int addNewsWhat=0x501,sendToastWhat=0x502;
    final String addNewsKey="addNewsKey",sendToastKey="sendToastKey";
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case addNewsWhat:
                    String result=message.getData().getString(addNewsKey);
                    if (result.equals("")){
                        //接收服务器数据失败
                    }
                    else{
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String code=jsonObject.getString("code");
                            if (code.equals("success")){
                                sendToast("发布成功");
                                finish();
                            }
                            else{
                                sendToast("发布失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(AddNewsActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        initView();
    }

    public void initView(){
        topicEdit=(EditText)findViewById(R.id.topic);
        contentEdit=(EditText)findViewById(R.id.content);
        submiteTV=(TextView)findViewById(R.id.submite);
        submiteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = topicEdit.getText().toString().trim();
                String content = contentEdit.getText().toString().trim();
                String name = UserInfo.name;
                submite(topic, content, name);
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

    public void submite(final String topic,final String content,final String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String ,String> map=new HashMap<String, String>();
                map.put("topic",topic);
                map.put("content",content);
                map.put("name",name);
                NetWork netWork=new NetWork();
                String result=netWork.doPost(map, URLValues.addNews);
                if (result==null) result="";
                sendMessage(addNewsWhat,addNewsKey,result);
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
