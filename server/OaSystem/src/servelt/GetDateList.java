package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Data;
import bean.Daly;
import bean.Date;
import bean.User;

public class GetDateList extends HttpServlet {

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

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		Data data=new Data();
		data.connect();
		String id=request.getParameter("id");
		List<Date> dates=data.getDate(id);
		StringBuffer buffer=new StringBuffer("{\"dates\":[");
		for(int i=0;i<dates.size();i++){
			Date date=dates.get(i);
			int userId=date.getUserId();
			User user=data.getUser(userId);
			buffer.append("{\"id\":"+date.getId());
			buffer.append(",\"userId\":"+date.getUserId());
			buffer.append(",\"topic\":\""+date.getTopic()+"\"");
			buffer.append(",\"userName\":\""+user.getName()+"\"");
			buffer.append(",\"userDepartment\":\""+user.getDepartment()+"\"");
			buffer.append(",\"role\":\""+user.getRole()+"\"");
			buffer.append(",\"content\":\""+date.getContent()+"\"");
			if(i==dates.size()-1){
			buffer.append(",\"time\":\""+date.getTime()+"\"}");
			}else{
				buffer.append(",\"time\":\""+date.getTime()+"\"},");
			}
		}
		buffer.append("]}");
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
