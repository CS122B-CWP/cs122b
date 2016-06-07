package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniEbay.jdbc.dao.InsertMovieDAO;
import miniEbay.object.IMoviePara;

public class InsertMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 13L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String year = request.getParameter("year");
			String director = request.getParameter("director");
			String banner_url = request.getParameter("banner_url");
			String trailer = request.getParameter("trailer");
			String price = request.getParameter("price");
			String star_name = request.getParameter("star_name");
			String genre = request.getParameter("genre");

			IMoviePara insert_para = new IMoviePara();
			if (id != "")
				insert_para.setId(Integer.parseInt(id));
			if (title != "")
				insert_para.setTitle(title);
			if (year != "")
				insert_para.setYear(Integer.parseInt(year));
			if (director != "")
				insert_para.setDirctor(director);
			if (banner_url != "")
				insert_para.setBanner_url(banner_url);
			if (trailer != "")
				insert_para.setTrailer(trailer);
			if (price != "")
				insert_para.setPrice(Double.parseDouble(price));
			if (star_name != "")
				insert_para.setStar_name(star_name);
			if (genre != "")
				insert_para.setGenre(genre);
			String insert_movie_res = InsertMovieDAO.insertmovie(insert_para);
			if (insert_movie_res == "")
				insert_movie_res = "Nothing Changed!";

			request.getSession().setAttribute("insert_movie_res", String.valueOf(insert_movie_res));
			response.sendRedirect("insert_movie_result.jsp");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("insert_movie.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
