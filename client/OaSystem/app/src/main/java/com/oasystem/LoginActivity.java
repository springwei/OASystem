package com.oasystem;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oasystem.manager.AdminActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener{
    EditText phoneEdit,passwordEdit;
    final int LOGINRESULTWHAT=0x101;
    final String LOGINKEY="loginResult";

    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case LOGINRESULTWHAT:
                    String result=message.getData().getString(LOGINKEY);
                    if (result.equals("")){
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();

                    }else{
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String code=jsonObject.getString("code");
                            if(code.equals("success")){
                                UserInfo.id=jsonObject.getInt("id");
                                UserInfo.role=jsonObject.getString("role");
                                UserInfo.name=jsonObject.getString("name");
                                UserInfo.department=jsonObject.getString("department");
                                Toast.makeText(LoginActivity.this,UserInfo.role+"登录成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent();
                                if(UserInfo.role.equals("员工")||UserInfo.role.equals("经理")) {
                                    intent.setClass(LoginActivity.this, MainActivity.class);
                                }
                                if(UserInfo.role.equals("管理员")){
                                    intent.setClass(LoginActivity.this,AdminActivity.class);
                                }
                                startActivity(intent);
                                finish();
                            }
                            if(code.equals("fail")){
                                Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                            }
                            if(code.equals("error")){
                                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

   public void initView(){
       phoneEdit=(EditText)findViewById(R.id.phone);
       passwordEdit=(EditText)findViewById(R.id.password);
       Button submite=(Button)findViewById(R.id.submite);
       submite.setOnClickListener(this);
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submite:
                String phone=phoneEdit.getText().toString().trim();
                String password=passwordEdit.getText().toString().trim();
                if(phone.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this,"请填写完整",Toast.LENGTH_SHORT).show();
                }
                else{
                    login(phone,password);
                }

                break;
        }
    }


    public void login(final String phone,final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String,String>();
                map.put("phone",phone);
                map.put("password",password);
                String loginResult=netWork.doGet(map, URLValues.loginURL);
                sendMessage(LOGINRESULTWHAT,LOGINKEY,loginResult);
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

}
