package com.oasystem;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import adapter.NoticeListAdapter;
import bean.Notice;
import network.NetWork;
import value.URLValues;


public class NoticeActivity extends Activity {
    List<Notice> notices;
    ListView noticeListView;
    final int getNoticeWhat=0x201,sendToastWhat=0x202;
    final String getNoticeKey="getNoticeKey",sendToastKey="sendToastKey";


    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case getNoticeWhat:
                    String noticeResult=message.getData().getString(getNoticeKey);
                    if (noticeResult.equals("")){
                        sendToast("获取公告失败");

                    }
                    else{
                        notices=new ArrayList<Notice>();
                        try {
                            JSONObject jsonObject=new JSONObject(noticeResult);
                            String result=jsonObject.getString("code");
                            if (result.equals("success")){
                                JSONArray jsonArray=jsonObject.getJSONArray("notices");
                                for(int i=0;i<jsonArray.length();i++){
                                    Notice notice=new Notice();
                                    JSONObject noticeObject=jsonArray.getJSONObject(i);
                                    notice.setId(noticeObject.getInt("id"));
                                    notice.setIssuer(noticeObject.getString("issuer"));
                                    notice.setContent(noticeObject.getString("content"));
                                    notice.setTitle(noticeObject.getString("title"));
                                    notice.setTime(noticeObject.getString("time"));
                                    notices.add(notice);
                                }
                                NoticeListAdapter adapter=new NoticeListAdapter(NoticeActivity.this,notices);
                                noticeListView.setAdapter(adapter);


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
        setContentView(R.layout.activity_notice);
         r();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                String result=netWork.doGet(null, URLValues.getNotice);
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
                NoticeActivity.this.finish();
            }
        });
        noticeListView=(ListView)findViewById(R.id.notice);
       /* noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/

        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(NoticeActivity.this,NoticeDetailsActivity.class);
                intent.putExtra("notice",notices.get(position));
                startActivity(intent);
            }
        });

    }
}
