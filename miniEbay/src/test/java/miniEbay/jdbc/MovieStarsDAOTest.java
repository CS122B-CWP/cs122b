package miniEbay.jdbc;

import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.dao.MovieStarsDAO;

public class MovieStarsDAOTest {
	public static void main(String[] args) throws SQLException {
		JDBCPool pool = JDBCPool.getInstance();
		System.out.println(MovieStarsDAO.movie_stars(908));
		pool.closePool();
	}
}