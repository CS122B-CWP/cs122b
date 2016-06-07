package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniEbay.jdbc.bean.SearchPageBean;
import miniEbay.jdbc.dao.SearchDAO;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 6L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			SearchPageBean pg = new SearchPageBean();
			// title
			String title = request.getParameter("title");
			if (title != null)
				pg.setTitle(title);
			// search type
			String type = request.getParameter("normal");
			if (type != null)
				pg.setType("n");
			else
				pg.setType("r");
			// director
			String director = request.getParameter("director");
			if (director != null)
				pg.setDirector(director);
			// start year
			String syear = request.getParameter("syear");
			if (syear != null && syear.matches("[0-9][0-9]*"))
				pg.setSyear(Integer.parseInt(syear));
			// end year
			String eyear = request.getParameter("eyear");
			if (eyear != null && syear.matches("[0-9][0-9]*"))
				pg.setEyear(Integer.parseInt(eyear));
			// star first name
			String fname = request.getParameter("fname");
			if (fname != null)
				pg.setFname(fname);
			// star last name
			String lname = request.getParameter("lname");
			if (lname != null)
				pg.setLname(lname);
			// page
			String page = request.getParameter("page");
			if (page != null && page.matches("[0-9][0-9]*"))
				pg.setCurPage(Integer.parseInt(page));
			if (pg.getType().equals("n")) {
				pg.setMovies(SearchDAO.nsearchContent(pg));
				pg.setMaxPage(SearchDAO.nsearchPages(pg));
			} else {
				pg.setMovies(SearchDAO.rsearchContent(pg));
				pg.setMaxPage(SearchDAO.rsearchPages(pg));
			}
			// System.out.println(pg.toString());
			HttpSession session = request.getSession();
			session.setAttribute("searchPage", pg.toString());
			request.getRequestDispatcher("search.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

}
