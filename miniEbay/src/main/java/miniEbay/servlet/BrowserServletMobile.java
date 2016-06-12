package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniEbay.jdbc.bean.PageBean;
import miniEbay.jdbc.dao.BrowserDAO;

public class BrowserServletMobile extends HttpServlet {
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

			response.getWriter().write(pg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
