package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import miniEbay.jdbc.bean.PageBean;
import miniEbay.jdbc.dao.BrowserDAO;

public class BrowserServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			PageBean pg = new PageBean();

			String page = request.getParameter("page");
			if (page != null && page.matches("[0-9][0-9]*"))
				pg.setCurPage(Integer.parseInt(page));

			String search_category_id = request.getParameter("search_category_id");
			if (search_category_id != null)
				pg.setSearch_category_id(search_category_id);

			pg.setMaxPage(BrowserDAO.browserPages(pg));
			pg.setBrief_items(BrowserDAO.browserContent(pg));

			// System.out.println(pg.toString());

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
