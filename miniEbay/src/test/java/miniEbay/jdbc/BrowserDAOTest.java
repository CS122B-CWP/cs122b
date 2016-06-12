package miniEbay.jdbc;

import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.bean.PageBean;
import miniEbay.jdbc.dao.BrowserDAO;

public class BrowserDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		PageBean pg = new PageBean(1, 20);
		pg.setMaxPage(BrowserDAO.browserPages(pg));
		pg.setBrief_items((BrowserDAO.browserContent(pg)));
		System.out.println(pg.toString());
		pool.closePool();
	}
}
