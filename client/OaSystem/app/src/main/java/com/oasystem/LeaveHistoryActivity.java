package com.oasystem;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
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
import bean.LeaveTable;
import bean.User;
import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class LeaveHistoryActivity extends Activity {
    final int checkLeaveTableWhat=0x501,sendToastWhat=0x502;
    final String checkLeaveKey="checkLeaveKey",sendToastKey="sendToastKey";
    ListView list;
    List<LeaveTable> leaveTables;
    boolean ifAccess=true;
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
                            leaveTables=new ArrayList<LeaveTable>();
                            JSONObject jsonObject=new JSONObject(result);
                            JSONArray jsonArray=jsonObject.getJSONArray("leaves");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject leaveObject=jsonArray.getJSONObject(i);
                                LeaveTable leaveTable=new LeaveTable();
                                leaveTable.setId(leaveObject.getInt("id"));
                                leaveTable.setTopic(leaveObject.getString("topic"));
                                leaveTable.setContent(leaveObject.getString("content"));
                                leaveTable.setBeginTime(leaveObject.getString("beginTime"));
                                leaveTable.setEndTime(leaveObject.getString("endTime"));
                                leaveTable.setCreateTime(leaveObject.getString("createTime"));
                                User user=new User();
                                user.setId(leaveObject.getInt("userId"));
                                user.setName(leaveObject.getString("userName"));
                                user.setPhone(leaveObject.getString("phone"));
                                leaveTable.setUser(user);
                                leaveTables.add(leaveTable);
                            }
                            LeaveAdapter adapter=new LeaveAdapter(LeaveHistoryActivity.this,leaveTables);
                            list.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(LeaveHistoryActivity.this, toast, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_history);
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
                String result=netWork.doGet(map, URLValues.getLeaveServlet);
                if (result==null) result="";
                sendMessage(checkLeaveTableWhat, checkLeaveKey, result);
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
