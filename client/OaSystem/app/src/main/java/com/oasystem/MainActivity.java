package com.oasystem;
import com.igexin.sdk.PushManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import value.UserInfo;


public class MainActivity extends Activity {
    private long exitTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        /*个推*/
        PushManager.getInstance().initialize(this.getApplicationContext());


    }
    public void initView(){
        LinearLayout noticeLayout=(LinearLayout)findViewById(R.id.notice);
        noticeLayout.setOnClickListener(new BTClickListener());
        LinearLayout newsLayout=(LinearLayout)findViewById(R.id.news);
        newsLayout.setOnClickListener(new BTClickListener());
        LinearLayout dateLayout=(LinearLayout)findViewById(R.id.date);
        dateLayout.setOnClickListener(new BTClickListener());
        LinearLayout DailyLayout=(LinearLayout)findViewById(R.id.daily);
        DailyLayout.setOnClickListener(new BTClickListener());
        LinearLayout noteLayout=(LinearLayout)findViewById(R.id.note);
        noteLayout.setOnClickListener(new BTClickListener());
        LinearLayout moneyLayout=(LinearLayout)findViewById(R.id.money);
        moneyLayout.setOnClickListener(new BTClickListener());
        LinearLayout leaveLayout=(LinearLayout)findViewById(R.id.leave);
        leaveLayout.setOnClickListener(new BTClickListener());
        LinearLayout checkLayout=(LinearLayout)findViewById(R.id.check);
        checkLayout.setOnClickListener(new BTClickListener());
        LinearLayout weatherLayout=(LinearLayout)findViewById(R.id.weather);
        weatherLayout.setOnClickListener(new BTClickListener());
        //ImageView people=(ImageView)findViewById(R.id.people);
       // people.setOnClickListener(new BTClickListener());

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.out.print("111");
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",

                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public class BTClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            switch (v.getId()){
                case R.id.notice:
                    intent.setClass(MainActivity.this,NoticeActivity.class);

                    startActivity(intent);
                    break;
                case R.id.news:
                    intent.setClass(MainActivity.this,NewsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.date:
                    intent.setClass(MainActivity.this,DateActivity.class);
                    startActivity(intent);
                    break;
                case R.id.daily:
                    intent.setClass(MainActivity.this,DailyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.note:
                    intent.setClass(MainActivity.this,NoteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.money:

                    if(UserInfo.role.equals("经理")){
                        intent.setClass(MainActivity.this,CheckMoneyActivity.class);
                    }
                    if (UserInfo.role.equals("员工")) {
                        intent.setClass(MainActivity.this, MoneyActivity.class);
                    }

                    startActivity(intent);

                    break;
                case R.id.leave:
                    if(UserInfo.role.equals("经理")){
                        intent.setClass(MainActivity.this,CheckLeaveActivity.class);
                    }
                    if (UserInfo.role.equals("员工")) {
                        intent.setClass(MainActivity.this, LeaveActivity.class);
                    }
                    startActivity(intent);
                    break;
                case R.id.check:
                    if(UserInfo.role.equals("经理")){
                        intent.setClass(MainActivity.this, ManagerCheckActivity.class);
                    }
                    else{
                    intent.setClass(MainActivity.this, CheckActivity.class);

                   }
                    startActivity(intent);
                    break;
                case R.id.weather:
                    intent.setClass(MainActivity.this,WeatherActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    }
}
