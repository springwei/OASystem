package servelt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Data;

public class ManagerLeaveServlet extends HttpServlet {


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String leaveid=request.getParameter("leaveid");
		String content=request.getParameter("content");
		
		Data data=new Data();
		
		data.connect();
		boolean result=data.updataLeave(leaveid,content);
		StringBuffer buffer=new StringBuffer("{\"code\":\"");
		if(result){
			buffer.append("success\"}");
		}
		else{
			buffer.append("fail\"}");
		}
		out.print(buffer);
		out.flush();
		out.close();
		data.closeSql();
	}


	public void init() throws ServletException {
		// Put your code here
	}

}
