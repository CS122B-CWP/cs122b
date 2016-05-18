package project4.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import project4.jdbc.bean.OrderHistoryBean;
import project4.jdbc.bean.OrderSingleBean;
import project4.jdbc.dao.SalesDAO;

public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 9L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			int customer_id = (int) request.getSession().getAttribute("customer_id");
			String sale_date = request.getParameter("date");
			if (sale_date != null && sale_date != "") {
				List<OrderSingleBean> orders_day = SalesDAO.orderSingle(customer_id, sale_date);
				JSONObject historySinglePage = new JSONObject();
				JSONArray orders_day_array = new JSONArray();
				for (OrderSingleBean order_day : orders_day) {
					orders_day_array.put(order_day.toJson());
				}
				historySinglePage.put("orders_day", orders_day_array);
				request.getSession().setAttribute("historySinglePage", historySinglePage.toString());
				request.getRequestDispatcher("ordersingle.jsp").forward(request, response);
			} else {
				List<OrderHistoryBean> orders = SalesDAO.orderhistory(customer_id);
				JSONObject historyPage = new JSONObject();
				JSONArray order_array = new JSONArray();
				for (OrderHistoryBean order : orders) {
					order_array.put(order.toJson());
				}
				historyPage.put("orders", order_array);
				request.getSession().setAttribute("orderhistoryPage", historyPage.toString());
				response.sendRedirect("orderhistory.jsp");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
