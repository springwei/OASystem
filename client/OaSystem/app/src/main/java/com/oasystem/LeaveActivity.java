package com.oasystem;


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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class LeaveActivity extends ActionBarActivity {

    EditText topic,content;
    String beginTime,endTime;
    TextView beginTimeText,endTimeText,submite;
    Calendar calendar;
    final int sendLeaveWhat=0x301,sendToastWhat=0x302;
    final String sendLeaveKey="sendLeaveKey",sendToastKey="sendToastKey";


    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case sendLeaveWhat:
                    String result=message.getData().getString(sendLeaveKey);
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
                    Toast.makeText(LeaveActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        calendar=Calendar.getInstance();
        beginTime=calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH))+"月"+calendar.get(Calendar.DAY_OF_MONTH)+"日";
        endTime=beginTime;
        initView();
        r();
    }
    public void initView(){
        topic=(EditText)findViewById(R.id.topic);
        content=(EditText)findViewById(R.id.content);
        beginTimeText=(TextView)findViewById(R.id.begin_text);
        endTimeText=(TextView)findViewById(R.id.end_text);
        submite=(TextView)findViewById(R.id.submite);//提交
        submite.setOnClickListener(new TextClickListener());
        beginTimeText.setOnClickListener(new TextClickListener());
        endTimeText.setOnClickListener(new TextClickListener());
    }
    public class TextClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.begin_text:
                    showDataDialog(1);
                    break;
                case R.id.end_text:
                    showDataDialog(2);
                    break;
                case R.id.submite:

                    submite();
                    break;
            }
        }
    }
    public void showDataDialog(final int i){

            new DatePickerDialog(LeaveActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // TODO Auto-generated method stub
                if(i==1) {
                    beginTime = year + "年" + (month +1)+ "月" + day+"日";
                    //更新EditText控件日期 小于10加0
                    beginTimeText.setText(beginTime);
                }
                if(i==2){
                    endTime=year+"年"+(month+1)+"月"+day+"日";
                    endTimeText.setText(endTime);
                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) ).show();
    }

    private void r() {
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
               map.put("beginTime",beginTime);
               map.put("endTime",endTime);
               map.put("department",UserInfo.department);
               String result=netWork.doPost(map, URLValues.sendLeave);
               if (result==null) result="";
                sendMessage(sendLeaveWhat,sendLeaveKey,result);

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
