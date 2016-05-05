package project2.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import project2.jdbc.JDBCPool;

public class JDBCPoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JDBCPool pool = null;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		pool = JDBCPool.getInstance();
	}

	@Override
	public void destroy() {
		pool.closePool();
	}
}
