package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Data;
import bean.News;
import bean.Notice;

public class GetNewsServlet extends HttpServlet {

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
		StringBuffer buffer = new StringBuffer("{\"code\":");
		PrintWriter out = response.getWriter();
		Data data = new Data();
		data.connect();
		List<News> newss = data.getNews();
		if (newss.size() == 0) {
			buffer.append("\"fail\"}");
		} else {
			buffer.append("\"success\",\"newss\":[");
			for (int i = 0; i < newss.size() - 1; i++) {
				News news = newss.get(i);
				buffer.append("{\"id\":" + news.getId() + ",\"issuer\":\""
						+ news.getIssuer() + "\"," + "\"content\":\""
						+ news.getContent() + "\",\"time\":\""
						+ news.getTime() + "\",\"title\":\""
						+ news.getTitle() + "\"},");
				System.out.println(buffer);
			}
			buffer.append("{\"id\":" + newss.get(newss.size()-1).getId()
					+ ",\"issuer\":\""+ newss.get(newss.size()-1).getIssuer()
					+ "\","+ "\"content\":\""+ newss.get(newss.size()-1).getContent()
					+ "\","+ "\"title\":\""+newss.get(newss.size()-1).getTitle()
					+ "\",\"time\":\"" + newss.get(newss.size()-1).getTime()
					+ "\"}]}");
			/*
			 * {"code":"success","notices":[{"id":1,"issuer":"����"},{"id":1,"issuer"
			 * :"����"}]}
			 */
			System.out.println(buffer);

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
