package project5.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project5.jdbc.dao.CheckoutDAO;

public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String credit_card_id = request.getParameter("credit_card");
			String first_name = request.getParameter("fname");
			String last_name = request.getParameter("lname");
			String exp = request.getParameter("exp");
			if (CheckoutDAO.validate(credit_card_id, first_name, last_name, exp)) {
				int customer_id = (int) request.getSession().getAttribute("customer_id");

				// TODO add more robust operation if add sale failed
				CheckoutDAO.addsales(customer_id);
				request.getSession().setAttribute("item_nums", 0);
				response.sendRedirect("ordersuccess.html");
			} else
				response.sendRedirect("orderfail.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
