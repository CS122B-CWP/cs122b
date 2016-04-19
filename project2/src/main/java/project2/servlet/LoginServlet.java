package project2.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project2.jdbc.dao.LoginDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String login_name = LoginDao.validate(request.getParameter("username"), request.getParameter("password"));
			// System.out.println(login_name);
			if (!login_name.equals("")) {
				HttpSession session = request.getSession();
				session.setAttribute("login_name", login_name);
				response.sendRedirect("main.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
