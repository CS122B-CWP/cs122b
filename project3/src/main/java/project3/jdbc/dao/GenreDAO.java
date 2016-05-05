package project3.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project3.jdbc.JDBCPool;

public class GenreDAO {

	public static String moviegenre(int movie_id) {
		String genre = "";
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select name from movies, genres_in_movies, genres where "
					+ "movies.id=genres_in_movies.movie_id and genres_in_movies.genre_id = genres.id "
					+ "and movies.id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				genre = rs.getString(1);
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return genre;
	}
}
