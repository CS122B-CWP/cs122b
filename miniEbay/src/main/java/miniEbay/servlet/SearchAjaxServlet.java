package miniEbay.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import miniEbay.jdbc.dao.FullTSearchAjaxDAO;

public class SearchAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 14L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<String> titles = null;
			// title
			String titles_str = request.getParameter("title");
			if (titles_str != null)
				titles = FullTSearchAjaxDAO.searchContent(titles_str);

			if (titles != null) {
				JSONArray res_array = new JSONArray();
				for (int i = 0; i < titles.size(); i++) 
					res_array.put(titles.get(i));
				//System.out.println(res_array.toString());
				response.getWriter().write(res_array.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
