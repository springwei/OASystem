package com.oasystem;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bean.Notice;


public class NoticeDetailsActivity extends ActionBarActivity {

    Notice notice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);
        notice=(Notice)getIntent().getSerializableExtra("notice");
        initData();
    }

    private void initData() {
        TextView name=(TextView)findViewById(R.id.name);
        name.setText(notice.getIssuer());
        TextView time=(TextView)findViewById(R.id.time);
        time.setText(notice.getTime());
        TextView content=(TextView)findViewById(R.id.content);
        content.setText(notice.getContent());

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Return);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}
