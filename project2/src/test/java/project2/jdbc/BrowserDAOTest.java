package project2.jdbc;

import java.sql.SQLException;

import project2.jdbc.bean.BrowserPageBean;
import project2.jdbc.dao.BrowserDAO;

public class BrowserDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		BrowserPageBean pg = new BrowserPageBean(1, 20);
		// BrowserDAO.browserContent(new PageBean(1, 20), "crime", "s");
		pg.setMaxPage(BrowserDAO.browserPages(pg));
		pg.setMovies(BrowserDAO.browserContent(pg));
		System.out.println(pg.toString());
		pool.closePool();
	}
}
