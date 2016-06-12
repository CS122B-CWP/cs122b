package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniEbay.jdbc.dao.CommentDAO;

public class CommentServletMobile extends HttpServlet {
	private static final long serialVersionUID = 7L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String customer_id = (String) request.getSession().getAttribute("customer_id");
			String item_id = request.getParameter("item_id");
			String comment = request.getParameter("comment");

			response.getWriter().write(CommentDAO.post_comment(customer_id, item_id, comment));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
