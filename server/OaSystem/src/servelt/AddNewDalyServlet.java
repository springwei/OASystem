package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gexin.rp.sdk.template.NotificationTemplate;

import tools.Data;
import bean.Daly;
import bean.User;

public class AddNewDalyServlet extends HttpServlet {
	//采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "E4XM7ti8sp8X71HKhnTMA7";
    private static String appKey = "6YxioUrZtd59z5vkoonMA7";
    private static String masterSecret = "42LP2pRF9f5Rm9ak1l3dr5";
	static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	static String CID1 ="91ebd7f26b1b17d70886b1d8efd1228b";//联想
	static String CID2= "88cd31609db746fc65dc4298ab1a29bb";//note3
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public static NotificationTemplate notificationTemplateDemo() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle("有新的新闻发布");
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
        template.setTransmissionType(1);
        template.setTransmissionContent("通知");
        return template;
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		Data data=new Data();
		data.connect();
		String id=request.getParameter("id");
		String topic=request.getParameter("topic");
		String content=request.getParameter("content");
		String time=request.getParameter("time");
		boolean result=data.addDaly(id,topic,content,time);
		StringBuffer buffer=new StringBuffer("{\"code\":");
		if(result){
			buffer.append("\"success\"}");
			System.out.println(buffer);
		}
		else{
			buffer.append("\"fail\"}");
		}
		out.print(buffer);
		out.flush();
		out.close();
		data.closeSql();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
