package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniEbay.jdbc.dao.SingleItemDAO;
import miniEbay.object.DetailItem;

public class SingleItemServlet extends HttpServlet {
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
				HttpSession session = request.getSession();
				session.setAttribute("itemPage", item.toString());
				request.getRequestDispatcher("singleitem.jsp").forward(request, response);
			} else {
				response.sendRedirect("search.jsp");
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
