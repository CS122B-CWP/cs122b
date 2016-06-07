package project4.jdbc;

import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.dao.SingleMovieDAO;

public class SingleMovieDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(SingleMovieDAO.moviecontent(901).toString());
		pool.closePool();
	}
}
