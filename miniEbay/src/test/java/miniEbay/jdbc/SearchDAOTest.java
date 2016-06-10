package miniEbay.jdbc;

import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.bean.SearchPageBean;
import miniEbay.jdbc.dao.SearchDAO;

public class SearchDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		SearchPageBean pg = new SearchPageBean(1, 20);
		pg.setBrief_items(SearchDAO.searchContent(pg));
		System.out.println(pg.toString());
		pool.closePool();
	}
}
