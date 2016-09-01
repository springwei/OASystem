package com.oasystem.manager;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oasystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.UserListAdapter;
import bean.User;
import network.NetWork;
import value.URLValues;

public class UserManagerActivity extends ActionBarActivity {

    ListView list;
    final int getUsersWhat=0x601,sendToastWhat=0x602;
    final String getUsersKey="getUsersKey",sendToastKey="sendToastKey";
    List<User> userList;
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case getUsersWhat:
                    String result=message.getData().getString(getUsersKey);
                    if (result.equals("")){

                    }
                    else{
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String code=jsonObject.getString("code");
                            if (code.equals("success")){
                                JSONArray jsonArray=jsonObject.getJSONArray("users");
                                userList=new ArrayList<User>();
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject userObject=jsonArray.getJSONObject(i);
                                    User user=new User();
                                    user.setId(userObject.getInt("id"));
                                    user.setDepartment(userObject.getString("department"));
                                    user.setName(userObject.getString("name"));
                                    user.setPhone(userObject.getString("phone"));
                                    user.setRole(userObject.getString("role"));
                                    user.setPassword(userObject.getString("password"));
                                    userList.add(user);
                                }
                                UserListAdapter adapter=new UserListAdapter(UserManagerActivity.this,userList);
                                list.setAdapter(adapter);
                            }
                        } catch (JSONException e) {


                        }
                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(UserManagerActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        initView();
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if (true){
            initData();
            initView();
        }*/
        if(requestCode==1&&resultCode==1){
            initData();
        }
        if(requestCode==2&&resultCode==1){
            initData();
        }

    }

    public void initView(){
        list=(ListView)findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UserManagerActivity.this, ChangeUserInfoActivity.class);
                intent.putExtra("user", userList.get(position));
                startActivityForResult(intent, 1);
            }
        });
        ImageView add=(ImageView)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserManagerActivity.this,AddUserActivity.class);
                startActivityForResult(intent,2);
            }
        });
        ImageView r=(ImageView)findViewById(R.id.back);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
    }

    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                String userResult=netWork.doGet(null, URLValues.getUsers);
                if (userResult==null){
                    userResult="";
                }
                sendMessage(getUsersWhat,getUsersKey,userResult);
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
