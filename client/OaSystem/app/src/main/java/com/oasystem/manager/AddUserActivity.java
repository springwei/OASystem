package com.oasystem.manager;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.oasystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import network.NetWork;
import value.URLValues;

public class AddUserActivity extends ActionBarActivity {

    EditText nameEdit,passwordEdit,departmentEdit,phoneEdit;
    Spinner roleSpinner;
    String[] roles={"员工","经理","管理员"};
    String role;
    final int addResultWhat=0x701,sendToastWhat=0x702;
    final String addResultKey="addResultKey",sendToastKey="sendToastKey";
    Handler handler=new Handler(){
        public void handleMessage(Message message){
        switch (message.what){
            case addResultWhat:
                String addResult=message.getData().getString(addResultKey);
                if (!addResult.equals("")){
                    try {
                        JSONObject jsonObject=new JSONObject(addResult);
                        String code=jsonObject.getString("code");
                        if (code.equals("success")){
                            setResult(1);
                            finish();
                        }
                        else{
                            sendToast("添加失败");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    sendToast("数据失败");
                }
                break;
            case sendToastWhat:
                String toast=message.getData().getString(sendToastKey);
                Toast.makeText(AddUserActivity.this,toast,Toast.LENGTH_SHORT).show();
                break;
        }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initView();


    }
    public void initView(){
        nameEdit=(EditText)findViewById(R.id.name);
        passwordEdit=(EditText)findViewById(R.id.password);
        departmentEdit=(EditText)findViewById(R.id.department);
        phoneEdit=(EditText)findViewById(R.id.phone);
        roleSpinner=(Spinner)findViewById(R.id.role);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//绑定 Adapter到控件
        roleSpinner .setAdapter(adapter);
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = roles[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button submite=(Button)findViewById(R.id.submite);
        submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString().trim();
                String password = passwordEdit.getText().toString().trim();
                String phone = phoneEdit.getText().toString().trim();
                String department = departmentEdit.getText().toString().trim();
                addUser(name, password, phone, department);
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
    public void addUser(final String name, final String password, final String phone, final String department){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String,String> map=new HashMap<String,String>();
                map.put("name",name);
                map.put("password",password);
                map.put("phone",phone);
                map.put("department",department);
                map.put("role",role);
                NetWork netWork=new NetWork();
                String addResult=netWork.doPost(map, URLValues.addUser);
                if (addResult==null) addResult="";
                sendMessage(addResultWhat,addResultKey,addResult);
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
