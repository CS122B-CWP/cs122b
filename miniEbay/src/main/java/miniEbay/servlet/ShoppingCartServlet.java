package miniEbay.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import miniEbay.jdbc.bean.ShoppingCartBean;
import miniEbay.jdbc.dao.ShoppingCartDAO;

public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 7L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			int customer_id = (int) request.getSession().getAttribute("customer_id");
			String type = request.getParameter("type");
			if (type != null) {
				switch (type) {
				case "add":
					int movie_id = Integer.parseInt(request.getParameter("movie_id"));
					String movie_title = request.getParameter("movie_title");
					// System.out.println(customer_id + "," + movie_id + "," +
					// movie_title);
					ShoppingCartDAO.additem(customer_id, movie_id, movie_title);
					break;
				case "update":
					movie_id = Integer.parseInt(request.getParameter("movie_id"));
					int qty = Integer.parseInt(request.getParameter("qty"));
					// System.out.println(customer_id + "," + movie_id + "," +
					// qty);
					ShoppingCartDAO.updateitem(customer_id, movie_id, qty);
					break;
				case "removeSingle":
					movie_id = Integer.parseInt(request.getParameter("movie_id"));
					ShoppingCartDAO.removeitem(customer_id, movie_id);
					break;
				case "removeAll":
					ShoppingCartDAO.removeAll(customer_id);
					break;
				default:
					break;
				}
			}

			List<ShoppingCartBean> items = ShoppingCartDAO.items(customer_id);
			JSONObject cartPage = new JSONObject();
			JSONArray item_array = new JSONArray();
			int total = 0;
			for (ShoppingCartBean item : items) {
				item_array.put(item.toJson());
				total += item.getQty();
			}
			cartPage.put("items", item_array);
			HttpSession session = request.getSession();
			session.setAttribute("cartPage", cartPage.toString());
			session.setAttribute("item_nums", total);
			response.sendRedirect("shoppingcart.jsp");
			// request.getRequestDispatcher("shoppingcart.jsp").forward(request,
			// response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
