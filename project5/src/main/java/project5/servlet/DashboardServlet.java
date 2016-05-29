package project5.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 11L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			if (session.getAttribute("e_login_name") != null && session.getAttribute("e_login_name") != "") {
				response.sendRedirect("dashapp/main.html");
			} else
				response.sendRedirect("employee_login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
