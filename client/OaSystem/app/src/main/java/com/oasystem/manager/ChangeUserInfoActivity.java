package com.oasystem.manager;

import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.oasystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bean.User;
import network.NetWork;
import value.URLValues;

public class ChangeUserInfoActivity extends ActionBarActivity {
    final int changeResultWhat=0x701,sendToastWhat=0x702;
    final String changeResultKey="changeResultKey",sendToastKey="sendToastKey";
    EditText nameEdit,departmentEdit,phoneEdit,passwordEdit;
    Spinner spinner;
    User user;
    String[] roles={"员工","经理","管理员"};
    String role;

    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case changeResultWhat:
                    String result=message.getData().getString(changeResultKey);
                    if (result.equals("")){
                        //错误
                    }
                    else{
                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            String code=jsonObject.getString("code");
                            if (code.equals("success")){
                                sendToast("修改成功");
                                setResult(1);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case sendToastWhat:
                    String toast=message.getData().getString(sendToastKey);
                    Toast.makeText(ChangeUserInfoActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);
       user=(User)(getIntent().getSerializableExtra("user"));
        initView();

    }

    public void initView(){
        nameEdit=(EditText)findViewById(R.id.name);
        departmentEdit=(EditText)findViewById(R.id.department);
        phoneEdit=(EditText)findViewById(R.id.phone);
        passwordEdit=(EditText)findViewById(R.id.password);
        spinner=(Spinner)findViewById(R.id.role_spinner);
        nameEdit.setText(user.getName());
        departmentEdit.setText(user.getDepartment());
        phoneEdit.setText(user.getPhone());
        passwordEdit.setText(user.getPassword());
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//绑定 Adapter到控件
        spinner .setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role=roles[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        role=user.getRole();
        if(role.equals("员工")){
            spinner.setSelection(0);
        }
        if (role.equals("经理")){
            spinner.setSelection(1);
        }
        if (role.equals("管理员")){
            spinner.setSelection(2);
        }

        Button submiteBT=(Button)findViewById(R.id.submite);
        submiteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEdit.getText().toString().trim();
                String department=departmentEdit.getText().toString().trim();
                String phone=phoneEdit.getText().toString().trim();
                String password=passwordEdit.getText().toString().trim();
                submite(name,department,phone,password);
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

    public void submite(final String name, final String department, final String phone, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id=user.getId()+"";
                Map<String,String> map=new HashMap<String,String>();
                map.put("userid",id);
                map.put("name",name);
                map.put("department",department);
                map.put("phone",phone);
                map.put("password",password);
                map.put("role",role);
                NetWork netWork=new NetWork();
                String result=netWork.doPost(map, URLValues.changeUser);
                if (result==null) result="";
                sendMessage(changeResultWhat,changeResultKey,result);
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
