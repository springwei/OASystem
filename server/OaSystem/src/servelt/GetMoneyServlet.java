package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Data;
import bean.MoneyTable;
import bean.User;

public class GetMoneyServlet extends HttpServlet {

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
		StringBuffer buffer=new StringBuffer("{\"moneys\":[");
		PrintWriter out = response.getWriter();
		Data data=new Data();
		data.connect();
		String department=request.getParameter("department");
		String status=request.getParameter("status");
		//
		List<MoneyTable> moneyTables=data.getMoneyTable(department,status);
		
		int length=moneyTables.size();
		for(int i=0;i<length-1;i++){
			User user=data.getUser(Integer.parseInt(moneyTables.get(i).getUserId()));
			MoneyTable moneyTable=moneyTables.get(i);
			buffer.append("{\"id\":"+moneyTable.getId()+",\"topic\":\""+moneyTable.getTopic()+"\",\"content\":\""+moneyTable.getContent()+"\",\"createTime\":\""+moneyTable.getCreateTime()+"\",\"userId\":"+user.getId()+",\"userName\":\""+user.getName()+"\",\"phone\":\""+user.getPhone()+"\"},");
		}
		if(moneyTables.size()>0){
		MoneyTable moneyTable=moneyTables.get(length-1);
		User user=data.getUser(Integer.parseInt(moneyTable.getUserId()));
		buffer.append("{\"id\":"+moneyTable.getId()+",\"topic\":\""+moneyTable.getTopic()+"\",\"content\":\""+moneyTable.getContent()+"\",\"createTime\":\""+moneyTable.getCreateTime()+"\",\"userId\":"+user.getId()+",\"userName\":\""+user.getName()+"\",\"phone\":\""+user.getPhone()+"\"}]}");
		}
		else{
			buffer.append("{}]}");
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
