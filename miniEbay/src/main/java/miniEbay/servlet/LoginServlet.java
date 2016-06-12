package miniEbay.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import miniEbay.jdbc.bean.ShoppingCartBean;
import miniEbay.jdbc.dao.LoginDAO;
import miniEbay.jdbc.dao.ShoppingCartDAO;
import miniEbay.object.LoginInfo;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginInfo user = LoginDAO.validate(request.getParameter("email"), request.getParameter("password"));
			if (user != null) {
				List<ShoppingCartBean> items = ShoppingCartDAO.items(user.getCustomer_id());

				HttpSession session = request.getSession();
				session.setAttribute("customer_id", user.getCustomer_id());
				session.setAttribute("item_nums", items.size());
				response.sendRedirect("/miniEbay/main.jsp");
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
