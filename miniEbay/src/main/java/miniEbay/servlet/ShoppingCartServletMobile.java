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

public class ShoppingCartServletMobile extends HttpServlet {
	private static final long serialVersionUID = 7L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String customer_id = request.getParameter("customer_id");
			String type = request.getParameter("type");
			if (type != null) {
				switch (type) {
				case "add":
					String item_id = request.getParameter("item_id");
					String movie_title = request.getParameter("title");
					// System.out.println(customer_id + "," + movie_id + "," +
					// movie_title);
					ShoppingCartDAO.additem(customer_id, item_id, movie_title);
					break;
				case "update":
					break;
				case "removeSingle":
					item_id = request.getParameter("item_id");
					ShoppingCartDAO.removeitem(customer_id, item_id);
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
				total++;
			}
			cartPage.put("items", item_array);
			HttpSession session = request.getSession();
			session.setAttribute("item_nums", total);
			response.getWriter().write(cartPage.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
