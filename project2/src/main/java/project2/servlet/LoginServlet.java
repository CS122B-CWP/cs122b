package project2.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project2.jdbc.dao.LoginDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		loginDao = new LoginDao();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println(request.getParameter("username"));
			System.out.println(request.getParameter("password"));
			HttpSession session = request.getSession();
			session.setAttribute("login_name", "");
			response.sendRedirect("main.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		loginDao.close();
	}

}
