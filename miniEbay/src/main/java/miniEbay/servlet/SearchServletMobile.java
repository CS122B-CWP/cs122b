package miniEbay.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniEbay.jdbc.bean.SearchPageBean;
import miniEbay.jdbc.dao.SearchDAO;

public class SearchServletMobile extends HttpServlet {
	private static final long serialVersionUID = 6L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			SearchPageBean pg = new SearchPageBean();
			// title
			String title = request.getParameter("title");
			if (title != null)
				pg.setSearch_title(title);
			// category id
			String search_category_id = request.getParameter("search_category_id");
			if (search_category_id != null)
				pg.setSearch_category_id(search_category_id);
			// low price
			String low = request.getParameter("lowPrice");
			if (low != null && low != "")
				pg.setLowPrice(Double.parseDouble(low));
			// high price
			String high = request.getParameter("highPrice");
			if (high != null && high != "")
				pg.setHighPrice(Double.parseDouble(high));
			// page
			String page = request.getParameter("page");
			if (page != null && page.matches("[0-9][0-9]*"))
				pg.setCurPage(Integer.parseInt(page));

			pg.setBrief_items(SearchDAO.searchContent(pg));
			pg.setMaxPage(SearchDAO.searchPages(pg));
			// System.out.println(pg.toString());
			response.getWriter().write(pg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
