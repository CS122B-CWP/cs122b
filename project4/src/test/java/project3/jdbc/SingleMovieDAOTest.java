package project3.jdbc;

import java.sql.SQLException;

import project4.jdbc.JDBCPool;
import project4.jdbc.dao.SingleMovieDAO;

public class SingleMovieDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(SingleMovieDAO.moviecontent(901).toString());
		pool.closePool();
	}
}
