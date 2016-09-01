
package com.oasystem;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;

/**
 * Created by lqs on 2016/4/24.
 */

public class PushDemoReceiver extends BroadcastReceiver {
    //个推跳转
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {

            case PushConsts.GET_MSG_DATA:
                // 获取透传数据
                // String appid = bundle.getString("appid");
                byte[] payload = bundle.getByteArray("payload");
                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");
                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
                // 透传消息,根据事件id改变事件
                boolean result = PushManager.getInstance().sendFeedbackMessage(
                        context, taskid, messageid, 90001);
                String d=null;
                if (payload != null) {
                    d = new String(payload);

//				Log.d("GetuiSdkDemo", "Got Payload:" + data);
//				if (MainActivity.tLogView != null)
//					MainActivity.tLogView.append(data + "\n");
                }
                if(result){
                    if(d.equals("notice")) {
                            Intent intentTemp = new Intent(context.getApplicationContext(), NoticeActivity.class);
                        intentTemp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        context.getApplicationContext().startActivity(intentTemp);
                    }
                    else if (d.equals("news"))
                    {
                        Intent intentTemp = new Intent(context.getApplicationContext(), NewsActivity.class);
                        intentTemp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        context.getApplicationContext().startActivity(intentTemp);
                    }
                    else if (d.equals("leave")){
                        Intent intentTemp = new Intent(context.getApplicationContext(), CheckLeaveActivity.class);
                        intentTemp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        context.getApplicationContext().startActivity(intentTemp);
                    }
                    else if (d.equals("money")){
                        Intent intentTemp = new Intent(context.getApplicationContext(), CheckMoneyActivity.class);
                        intentTemp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        context.getApplicationContext().startActivity(intentTemp);
                    }
                    else if(d.equals("s1")||d.equals("s2")){
                        Intent intentTemp = new Intent(context.getApplicationContext(), MainActivity.class);
                        intentTemp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        context.getApplicationContext().startActivity(intentTemp);
                    }

                }
                break;
            default:
                break;
        }
    }
}
