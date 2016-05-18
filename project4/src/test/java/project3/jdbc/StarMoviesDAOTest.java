package project3.jdbc;

import java.sql.SQLException;

import project4.jdbc.JDBCPool;
import project4.jdbc.dao.StarMoviesDAO;

public class StarMoviesDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(StarMoviesDAO.star_movies(671105));
		pool.closePool();
	}
}
