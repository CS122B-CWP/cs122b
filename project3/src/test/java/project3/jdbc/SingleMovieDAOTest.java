package project3.jdbc;

import java.sql.SQLException;

import project3.jdbc.JDBCPool;
import project3.jdbc.dao.SingleMovieDAO;

public class SingleMovieDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(SingleMovieDAO.moviecontent(901).toString());
		pool.closePool();
	}
}
