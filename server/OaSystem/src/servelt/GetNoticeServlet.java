package servelt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Notice;
import bean.User;

import tools.Data;

public class GetNoticeServlet extends HttpServlet {

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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StringBuffer buffer = new StringBuffer("{\"code\":");
		PrintWriter out = response.getWriter();
		Data data = new Data();
		data.connect();
		List<Notice> notices = data.getNotice();
		if (notices.size() == 0) {
			buffer.append("\"fail\"}");
		} else {

			buffer.append("\"success\",\"notices\":[");
			for (int i = 0; i < notices.size() - 1; i++) {
				Notice notice = notices.get(i);
				buffer.append("{\"id\":" + notice.getId() + ",\"issuer\":\""
						+ notice.getIssuer() + "\"," + "\"content\":\""
						+ notice.getContent() + "\",\"time\":\""
						+ notice.getTime() + "\",\"title\":\""
						+ notice.getTitle() + "\"},");
				System.out.println(buffer);
			}
			buffer.append("{\"id\":" + notices.get(notices.size()-1).getId()
					+ ",\"issuer\":\""+ notices.get(notices.size()-1).getIssuer()
					+ "\","+ "\"content\":\""+ notices.get(notices.size()-1).getContent()
					+ "\","+ "\"title\":\""+notices.get(notices.size()-1).getTitle()
					+ "\",\"time\":\"" + notices.get(notices.size()-1).getTime()
					+ "\"}]}");
			/*
			 * {"code":"success","notices":[{"id":1,"issuer":"张三"},{"id":1,"issuer"
			 * :"张三"}]}
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
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
