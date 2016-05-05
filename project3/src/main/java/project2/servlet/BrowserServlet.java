package project2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import project2.jdbc.bean.BrowserPageBean;
import project2.jdbc.dao.BrowserDAO;

public class BrowserServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			BrowserPageBean pg = new BrowserPageBean();
			String page = request.getParameter("page");
			if (page != null && page.matches("[0-9][0-9]*"))
				pg.setCurPage(Integer.parseInt(page));
			String genre = request.getParameter("genre");
			if (genre != null)
				pg.setGenre(genre);
			else
				genre = "";
			String year = request.getParameter("year");
			if (year != null)
				pg.setYear(year);
			else
				year = "";
			// System.out.println(page + "," + genre + "," + year);
			// System.out.println(pg.toString());

			String preContent = (String) request.getAttribute("browsePage");
			boolean ifChangeMax = true;
			if (preContent != null) {
				JSONObject json = new JSONObject(preContent);
				if (json.getString("genre").equals(genre) && json.getString("year").equals(year))
					if (json.getInt("curPage") == pg.getCurPage()) {
						request.getRequestDispatcher("browse.jsp").forward(request, response);
						return;
					} else
						ifChangeMax = false;
			}

			if (ifChangeMax)
				pg.setMaxPage(BrowserDAO.browserPages(pg));
			pg.setMovies(BrowserDAO.browserContent(pg));

			HttpSession session = request.getSession();
			session.setAttribute("browsePage", pg.toString());
			request.getRequestDispatcher("browse.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
