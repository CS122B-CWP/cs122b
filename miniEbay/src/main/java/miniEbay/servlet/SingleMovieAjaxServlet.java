package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniEbay.jdbc.dao.SingleItemDAO;
import miniEbay.object.DetailItem;

public class SingleMovieAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 15L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String movie_id = request.getParameter("id");
			DetailItem mv = null;
			if (movie_id != null && movie_id.matches("[0-9][0-9]*"))
				mv = SingleItemDAO.itemDetail(movie_id);
			if (mv != null) {
				// System.out.println(mv.toString());
				response.getWriter().write(mv.toString());
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
