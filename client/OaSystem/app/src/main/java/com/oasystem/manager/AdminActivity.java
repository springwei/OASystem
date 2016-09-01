package com.oasystem.manager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.oasystem.R;


public class AdminActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
    }
    public void initView(){
        LinearLayout newsLayout=(LinearLayout)findViewById(R.id.news_layout);
        LinearLayout noticeLayout=(LinearLayout)findViewById(R.id.notice_layout);
        LinearLayout userManagerLayout=(LinearLayout)findViewById(R.id.user_manager);

        newsLayout.setOnClickListener(new LayoutClickListener());
        noticeLayout.setOnClickListener(new LayoutClickListener());
        userManagerLayout.setOnClickListener(new LayoutClickListener());
    }

    public class LayoutClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            switch (v.getId()){
                case R.id.news_layout:
                    intent.setClass(AdminActivity.this,AddNewsActivity.class);
                    break;
                case R.id.notice_layout:
                    intent.setClass(AdminActivity.this,AddNoticesActivity.class);
                    break;
                case R.id.user_manager:
                    intent.setClass(AdminActivity.this,UserManagerActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
