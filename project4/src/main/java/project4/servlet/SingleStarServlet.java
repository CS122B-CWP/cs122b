package project4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project4.jdbc.dao.SingleStarDAO;
import project4.object.Star;

public class SingleStarServlet extends HttpServlet {
	private static final long serialVersionUID = 5L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String star_id = request.getParameter("id");
			Star star = null;
			if (star_id != null && star_id.matches("[0-9][0-9]*"))
				star = SingleStarDAO.starcontent(Integer.parseInt(star_id));
			if (star != null) {
				HttpSession session = request.getSession();
				session.setAttribute("StarPage", star.toString());
			}
			// System.out.println(star.toString());
			request.getRequestDispatcher("singlestar.jsp").forward(request, response);
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
