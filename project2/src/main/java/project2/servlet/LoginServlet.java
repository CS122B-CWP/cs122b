package project2.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import project2.jdbc.dao.LoginDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		loginDao = new LoginDao();
	}

	@Override
	public void destroy() {
		loginDao.close();
	}

}
