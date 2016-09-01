package servelt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Data;
import bean.User;

public class SendLeave extends HttpServlet {
	
	private static String appId = "E4XM7ti8sp8X71HKhnTMA7";
    private static String appKey = "6YxioUrZtd59z5vkoonMA7";
    private static String masterSecret = "42LP2pRF9f5Rm9ak1l3dr5";
	static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	static String CID1 ="91ebd7f26b1b17d70886b1d8efd1228b";//联想
	static String CID2= "88cd31609db746fc65dc4298ab1a29bb";//note3

	public void destroy() {
		super.destroy(); 
	}
	public static NotificationTemplate notificationTemplateDemo() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle("有新的请假通知");
        template.setText("OaSystem有新的消息");
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("leave");
        return template;
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		String topic=request.getParameter("topic");
		String content=request.getParameter("content");
		String startTime=request.getParameter("beginTime");
		String endTime=request.getParameter("endTime");
		String department=request.getParameter("department");
		
		Data data=new Data();
		
		data.connect();
		boolean result=data.createLeave(Integer.parseInt(userId),topic,content,startTime,endTime,department);
		StringBuffer buffer=new StringBuffer("{\"code\":\"");
		if(result){
			buffer.append("success\"}");
			// 配置返回每个用户返回用户状态，可选
	        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
	        IGtPush push = new IGtPush(url, appKey, masterSecret);
	        // 通知透传模板
	        NotificationTemplate template = notificationTemplateDemo();
	        ListMessage message = new ListMessage();
	        message.setData(template);
	        // 设置消息离线，并设置离线时间
	        message.setOffline(true);
	        // 离线有效时间，单位为毫秒，可选
	        message.setOfflineExpireTime(24 * 1000 * 3600);
	        // 配置推送目标
	        List targets = new ArrayList();
	        Target target1 = new Target();
	        Target target2 = new Target();
	        target1.setAppId(appId);
	     //   target1.setClientId(CID1);
	   //     target1.setAlias(Alias1);
	        target2.setAppId(appId);
	        target2.setClientId(CID1);
	   //     target2.setAlias(Alias2);
	        targets.add(target1);
	        targets.add(target2);
	        // taskId用于在推送时去查找对应的message
	        String taskId = push.getContentId(message);
	        IPushResult ret = push.pushMessageToList(taskId, targets);
	        System.out.println(ret.getResponse().toString());
			
		}
		else{
			buffer.append("fail\"}");
		}
		out.print(buffer);
		out.flush();
		out.close();
		data.closeSql();
	}
}
