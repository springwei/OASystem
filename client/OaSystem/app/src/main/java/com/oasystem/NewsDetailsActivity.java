package com.oasystem;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import bean.News;


public class NewsDetailsActivity extends ActionBarActivity {
    News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        news=(News)getIntent().getSerializableExtra("news");
        initDate();
    }

    private void initDate() {
        TextView name=(TextView)findViewById(R.id.name);
        name.setText(news.getIssuer());
        TextView time=(TextView)findViewById(R.id.time);
        time.setText(news.getTime());
        TextView content=(TextView)findViewById(R.id.content);
        content.setText(news.getContent());
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.Return);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }


}