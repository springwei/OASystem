package com.oasystem;

import android.app.DatePickerDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class AddDalyActivity extends ActionBarActivity {

    EditText topic,content;
    Button time;
    String date;
    final int addWhat=0x201,sendToastWhat=0x301;
    final String addKey="addKey",sendToastKey="sendToastKey";
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case addWhat:
                    String addCode=message.getData().getString(addKey);
                    try {
                        JSONObject jsonObject=new JSONObject(addCode);
                        String code=jsonObject.getString("code");
                        if (code.equals("success")){
                            setResult(1);
                            finish();
                        }
                        else{
                            sendToast("发布失败");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case sendToastWhat:
                    Toast.makeText(AddDalyActivity.this,message.getData().getString(sendToastKey),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daly);
        initView();
    }
    public void initView(){
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        topic=(EditText)findViewById(R.id.topic);
        content=(EditText)findViewById(R.id.content);
        time=(Button)findViewById(R.id.select_time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });
        Button button=(Button)findViewById(R.id.submite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topicString=topic.getText().toString().trim();
                String contentText=content.getText().toString().trim();
                submite(UserInfo.id,topicString,contentText,date);
            }
        });
    }

    public void submite(final int id, final String topic, final String content, final String time){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String,String>();
                map.put("id",id+"");
                map.put("topic",topic);
                map.put("content",content);
                map.put("time",time);
               String code= netWork.doPost(map, URLValues.addNewDaly);
                if(code==null)code="";
                sendMessage(addWhat,addKey,code);
            }
        }).start();
    }
    public void sendMessage(int what,String key,String value){
        Message message=new Message();
        message.what=what;
        Bundle bundle=new Bundle();
        bundle.putString(key,value);
      //  bundle.putString("dd","aa");
        message.setData(bundle);
        handler.sendMessage(message);
    }
    public void sendToast(String toast){
        sendMessage(sendToastWhat,sendToastKey,toast);
    }
    public void selectDate(){
        Calendar calendar=Calendar.getInstance();
        new DatePickerDialog(AddDalyActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // TODO Auto-generated method stub
                    date=year+"年"+(month+1)+'月'+day+'日';
                    time.setText(date);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) ).show();
    }

}
