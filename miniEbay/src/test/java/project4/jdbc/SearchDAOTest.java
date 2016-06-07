package project4.jdbc;

import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.bean.SearchPageBean;
import miniEbay.jdbc.dao.SearchDAO;

public class SearchDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		SearchPageBean pg = new SearchPageBean(1, 20);
		// BrowserDAO.browserContent(new PageBean(1, 20), "crime", "s");
		// pg.setMaxPage();
		pg.setMovies(SearchDAO.nsearchContent(pg));
		System.out.println(pg.toString());
		pool.closePool();
	}
}