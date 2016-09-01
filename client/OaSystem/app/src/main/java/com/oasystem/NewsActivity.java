package com.oasystem;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import adapter.NewsListAdapter;
import bean.News;
import network.NetWork;
import value.URLValues;

//新闻
public class NewsActivity extends ActionBarActivity {
    List<News> news1;
    ListView newsListView;
    final int getNoticeWhat=0x201,sendToastWhat=0x202;
    final String getNoticeKey="getNoticeKey",sendToastKey="sendToastKey";


    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case getNoticeWhat:
                    String newsResult=message.getData().getString(getNoticeKey);
                    if (newsResult.equals("")){
                        sendToast("获取公告失败");

                    }
                    else{
                        news1=new ArrayList<News>();
                        try {
                            JSONObject jsonObject=new JSONObject(newsResult);
                            String result=jsonObject.getString("code");
                            if (result.equals("success")){
                                JSONArray jsonArray=jsonObject.getJSONArray("newss");
                                for(int i=0;i<jsonArray.length();i++){
                                    News news=new News();
                                    JSONObject newsObject=jsonArray.getJSONObject(i);
                                    news.setId(newsObject.getInt("id"));
                                    news.setIssuer(newsObject.getString("issuer"));
                                    news.setContent(newsObject.getString("content"));
                                    news.setTitle(newsObject.getString("title"));
                                    news.setTime(newsObject.getString("time"));
                                    news1.add(news);
                                }
                                NewsListAdapter adapter=new NewsListAdapter(NewsActivity.this,news1);
                                newsListView.setAdapter(adapter);


                            }
                            else{
                                sendToast("无公告数据");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case sendToastWhat:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        r();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                String result=netWork.doGet(null, URLValues.getNews);
                if (result==null) result="";
                sendMessage(getNoticeWhat,getNoticeKey,result);
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
    private void r() {
        ImageView imageView=(ImageView)findViewById(R.id.Return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        newsListView=(ListView)findViewById(R.id.news);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(NewsActivity.this,NewsDetailsActivity.class);
                intent.putExtra("news",news1.get(position));
                startActivity(intent);
            }
        });
    }
}
