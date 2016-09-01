package com.oasystem;
//添加便签
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import network.NetWork;
import value.URLValues;
import value.UserInfo;
import view.MyNote;


public class NewNoteActivity extends ActionBarActivity {

    MyNote noteContent;
    int noteId;
    String content;
    final int newNoteWhat=0x801,sendToastWhat=0x802;
    final String newNoteKey="createNoteKey",sendToastKey="sendToastKey";

    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case newNoteWhat:
                    String result=message.getData().getString(newNoteKey);
                    if (!result.equals("")){
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String code=jsonObject.getString("code");
                            if (code.equals("create_success")){
                                sendToast("添加成功");
                                setResult(1);
                                finish();
                            }
                            if(code.equals("change_success")){
                                sendToast("修改成功");
                                setResult(1);
                                finish();
                            }
                            if(code.equals("create_fail")) {
                                sendToast("添加失败");
                            }
                            if (code.equals("change_fail")){
                                sendToast("修改失败");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        sendToast("连接失败");
                    }
                    break;
                case sendToastWhat:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        noteId=getIntent().getIntExtra("noteId",-1);
        content=getIntent().getStringExtra("content");
        initView();
    }

    public void initView(){
        noteContent=(MyNote)findViewById(R.id.content);
        noteContent.setText(content);
        TextView submite=(TextView)findViewById(R.id.submite);
        submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = noteContent.getText().toString();
                submiteNote(content, noteId);
            }
        });
    }

    public void submiteNote(final String content, final int noteId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,String> map=new HashMap<String,String>();
                NetWork netWork=new NetWork();
                map.put("id", UserInfo.id+"");
                map.put("content",content);
                map.put("noteId",noteId+"");
                String result=netWork.doPost(map, URLValues.newNote);
                if (result==null){
                    result="";
                }
                sendMessage(newNoteWhat,newNoteKey,result);
            }
        }).start();
    }
    public void sendMessage(int what,String key,String value){
        Message message =new Message();
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
