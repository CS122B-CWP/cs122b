package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniEbay.jdbc.dao.BidDAO;

public class BidServletMobile extends HttpServlet {
	private static final long serialVersionUID = 7L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String customer_id = request.getParameter("customer_id");
			String item_id = request.getParameter("item_id");
			double bid_price = Double.parseDouble(request.getParameter("bid_price"));

			response.getWriter().write(BidDAO.bid(customer_id, item_id, bid_price));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
