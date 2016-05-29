package project4.jdbc;

import java.sql.SQLException;

import project5.jdbc.JDBCPool;
import project5.jdbc.dao.SingleMovieDAO;

public class SingleMovieDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(SingleMovieDAO.moviecontent(901).toString());
		pool.closePool();
	}
}
