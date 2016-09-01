package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Daly;
import bean.User;

import tools.Data;

public class GetDalyList extends HttpServlet {

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
		List<Daly> dalys=data.getDaly(id);
		StringBuffer buffer=new StringBuffer("{\"dalys\":[");
		for(int i=0;i<dalys.size();i++){
			Daly daly=dalys.get(i);
			int userId=daly.getUserId();
			User user=data.getUser(userId);
			buffer.append("{\"id\":"+daly.getId());
			buffer.append(",\"userId\":"+daly.getUserId());
			buffer.append(",\"topic\":\""+daly.getTopic()+"\"");
			buffer.append(",\"userName\":\""+user.getName()+"\"");
			buffer.append(",\"userDepartment\":\""+user.getDepartment()+"\"");
			buffer.append(",\"role\":\""+user.getRole()+"\"");
			buffer.append(",\"content\":\""+daly.getContent()+"\"");
			if(i==dalys.size()-1){
			buffer.append(",\"time\":\""+daly.getTime()+"\"}");
			}else{
				buffer.append(",\"time\":\""+daly.getTime()+"\"},");
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
