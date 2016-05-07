package project3.jdbc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		/*
		String origin_url = req.getHeader("referer");
		if (req.getSession().getAttribute("origin_url") == null) {
			req.getSession().setAttribute("origin_url", origin_url);
			// System.out.println(origin_url);
		}
		*/
		if (session.getAttribute("customer_id") != null && session.getAttribute("customer_id") != "") {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(req, res);
		}
	}

	@Override
	public void destroy() {
	}

}
