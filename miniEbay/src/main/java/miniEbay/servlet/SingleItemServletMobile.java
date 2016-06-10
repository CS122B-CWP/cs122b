package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import miniEbay.jdbc.dao.SingleItemDAO;
import miniEbay.object.DetailItem;

public class SingleItemServletMobile extends HttpServlet {
	private static final long serialVersionUID = 4L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String item_id = request.getParameter("item_id");
			DetailItem item = null;
			if (item_id != null)
				item = SingleItemDAO.itemDetail(item_id);
			// System.out.println(item.toString());
			if (item != null) {
				response.getWriter().write(item.toString());
			} else {
				response.sendRedirect("search_mobile");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
