package project4.jdbc;

import java.sql.SQLException;

import project5.jdbc.JDBCPool;
import project5.jdbc.dao.MovieStarsDAO;

public class MovieStarsDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(MovieStarsDAO.movie_stars(908));
		pool.closePool();
	}
}
