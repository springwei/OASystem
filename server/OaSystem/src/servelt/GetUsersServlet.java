package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;

import tools.Data;

public class GetUsersServlet extends HttpServlet {

	/**
	 * 管理员获取用户
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
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Data data=new Data();
		data.connect();
		List<User> users=data.getUsers();
		StringBuffer buffer=new StringBuffer("{\"code\":\"");
		if(users.size()>0){
			buffer.append("success\",\"users\":[");
			for(int i=0;i<users.size()-1;i++){
				User user=users.get(i);
				buffer.append("{\"id\":"+user.getId());
				buffer.append(",\"name\":\""+user.getName()+"\"");
				buffer.append(",\"department\":\""+user.getDepartment()+"\"");
				buffer.append(",\"role\":\""+user.getRole()+"\"");
				buffer.append(",\"password\":\""+user.getPassword()+"\"");
				buffer.append(",\"phone\":\""+user.getPhone()+"\"},");
			}
			User user=users.get(users.size()-1);
			buffer.append("{\"id\":"+user.getId());
			buffer.append(",\"name\":\""+user.getName()+"\"");
			buffer.append(",\"department\":\""+user.getDepartment()+"\"");
			buffer.append(",\"role\":\""+user.getRole()+"\"");
			buffer.append(",\"password\":\""+user.getPassword()+"\"");
			buffer.append(",\"phone\":\""+user.getPhone()+"\"}");
			buffer.append("]}");
			
		}
		else
		{
			buffer.append("fail\"}");
		}
		out.print(buffer);
		out.flush();
		out.close();
		data.closeSql();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
