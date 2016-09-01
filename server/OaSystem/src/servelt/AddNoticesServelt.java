package servelt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gexin.rp.sdk.template.NotificationTemplate;

import tools.Data;
import tools.PushI;
import tools.push;

public class AddNoticesServelt extends HttpServlet{
	
	PushI p=new push();
	
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
		String topic=request.getParameter("topic");
		String name=request.getParameter("name");
		String content=request.getParameter("content");
	
		
		Data data=new Data();
		
		data.connect();
		boolean result=data.addNotices(topic,content,name);
		
		StringBuffer buffer=new StringBuffer("{\"code\":\"");
		
		if(result){
			buffer.append("success\"}");
			
			p.notificationTemplateDemo();
			p.success();
	
		}
		else{
			buffer.append("fail\"}");
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
