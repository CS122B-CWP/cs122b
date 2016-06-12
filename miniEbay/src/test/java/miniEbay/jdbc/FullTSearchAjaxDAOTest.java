package miniEbay.jdbc;

import java.sql.SQLException;
import java.util.List;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.dao.FullTSearchAjaxDAO;

public class FullTSearchAjaxDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();

		// BrowserDAO.browserContent(new PageBean(1, 20), "crime", "s");
		// pg.setMaxPage();
		List<String> titles = FullTSearchAjaxDAO.searchContent("good c");
		System.out.println(titles.toString());
		pool.closePool();
	}
}
