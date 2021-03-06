package project2.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project2.jdbc.bean.ShoppingCartBean;
import project2.jdbc.dao.LoginDAO;
import project2.jdbc.dao.ShoppingCartDAO;
import project2.object.LoginInfo;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginInfo user = LoginDAO.validate(request.getParameter("username"), request.getParameter("password"));
			// System.out.println(login_name);
			// System.out.println(request.getHeader("referer"));
			if (request.getSession().getAttribute("origin_url") == null) {
				request.getSession().setAttribute("origin_url", request.getHeader("referer"));
			}

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("login_name", user.getLname());
				session.setAttribute("customer_id", user.getUser_id());
				List<ShoppingCartBean> items = ShoppingCartDAO.items(user.getUser_id());
				int total = 0;
				for (ShoppingCartBean item : items)
					total += item.getQty();
				session.setAttribute("item_nums", total);
				String origin_url = (String) session.getAttribute("origin_url");
				// System.out.println(origin_url);
				if (origin_url != null) {
					session.removeAttribute("origin_url");
					// System.out.println(origin_url);
					response.sendRedirect(origin_url);
				} else {
					response.sendRedirect("main.jsp");
				}
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
