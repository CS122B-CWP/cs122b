package miniEbay.jdbc;

import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.dao.SingleItemDAO;

public class SingleItemDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(SingleItemDAO.itemDetail("111178375017").toString());
		pool.closePool();
	}
}
