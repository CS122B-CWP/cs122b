package project5.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project5.jdbc.dao.SingleMovieDAO;
import project5.object.Movie;

public class SingleMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 4L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String movie_id = request.getParameter("id");
			Movie mv = null;
			if (movie_id != null && movie_id.matches("[0-9][0-9]*"))
				mv = SingleMovieDAO.moviecontent(Integer.parseInt(movie_id));
			if (mv != null) {
				HttpSession session = request.getSession();
				session.setAttribute("moviePage", mv.toString());
			}
			// System.out.println(mv.toString());
			request.getRequestDispatcher("singlemovie.jsp").forward(request, response);
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
