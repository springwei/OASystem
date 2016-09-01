package com.oasystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.NoteListAdapter;
import bean.Note;
import network.NetWork;
import value.URLValues;
import value.UserInfo;


public class NoteActivity extends ActionBarActivity {

    ListView noteList;
    final int getNoteWhat=0x901,sendToastWhat=0x902,deleteWhat=0x903;
    final String getNoteKey="getNoteKey",sendToastKey="sendToastKey",deleteKey="deleteKey";
    List<Note> notes;
    Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case getNoteWhat:
                    String result=message.getData().getString(getNoteKey);
                    if (!result.equals("")){
                        try {
                            notes=new ArrayList<Note>();
                            JSONObject jsonObject=new JSONObject(result);
                            JSONArray jsonArray=jsonObject.getJSONArray("notes");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject noteObject=jsonArray.getJSONObject(i);
                                Note note=new Note();
                                note.setId(noteObject.getInt("id"));
                                note.setUserId(noteObject.getInt("userId"));
                                note.setContent(noteObject.getString("content"));
                                note.setCreateTime(noteObject.getString("createTime"));
                                note.setChangeTime(noteObject.getString("changeTime"));
                                notes.add(note);
                            }
                            NoteListAdapter adapter=new NoteListAdapter(NoteActivity.this,notes);
                            noteList.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        sendToast("连接失败");
                    }
                    break;
                case sendToastWhat:
                    String toast =message.getData().getString(sendToastKey);
                    Toast.makeText(NoteActivity.this,toast,Toast.LENGTH_SHORT).show();
                    break;
                case deleteWhat:
                    String deleteResult=message.getData().getString(deleteKey);
                    if (!deleteResult.equals("")){
                        try {
                            JSONObject jsonObject=new JSONObject(deleteResult);
                            String result1=jsonObject.getString("code");
                            if (result1.equals("success")){
                                sendToast("删除成功");
                                initData();
                            }
                            else{
                                sendToast("删除失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        sendToast("连接错误");
                    }
                    break;

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initView();
        initData();
        r();
    }

    public void initView(){
        ImageView add=(ImageView) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NoteActivity.this, NewNoteActivity.class);
                intent.putExtra("content", "");
                startActivityForResult(intent, 1);
            }
        });
        noteList=(ListView)findViewById(R.id.note_list);
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(NoteActivity.this, NewNoteActivity.class);
                intent.putExtra("noteId", notes.get(position).getId());
                intent.putExtra("content", notes.get(position).getContent());
                startActivityForResult(intent, 1);
            }
        });
        noteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new  AlertDialog.Builder(NoteActivity.this)
                        .setTitle("提示" )
                        .setMessage("确认删除？" )

                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteNote(notes.get(position).getId());
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
                return false;
            }
        });
    }

    public void deleteNote(final int noteId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String, String>();
                map.put("noteId", noteId + "");
                String deleteResult=netWork.doPost(map,URLValues.deleteNoteURL);
                sendMessage(deleteWhat,deleteKey,deleteResult);
            }
        }).start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            initData();
        }
    }

    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetWork netWork=new NetWork();
                Map<String,String> map=new HashMap<String,String>();
                map.put("userId", UserInfo.id+"");
                String result=netWork.doGet(map, URLValues.getNote);
                if (result==null){
                    result="";
                }
                sendMessage(getNoteWhat,getNoteKey,result);
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
    }
}
