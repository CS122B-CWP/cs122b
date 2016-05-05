package project2.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project2.jdbc.JDBCPool;
import project2.object.StarMovie;

public class StarMoviesDAO {
	public static List<StarMovie> star_movies(int star_id) {
		List<StarMovie> movies = new ArrayList<StarMovie>();

		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select movies.id, title " + "from stars, movies, stars_in_movies "
					+ "where stars.id=stars_in_movies.stars_id and movies.id=stars_in_movies.movie_id and "
					+ "stars.id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, star_id);

			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				StarMovie mv = new StarMovie();
				mv.setId(rs.getInt(1));
				mv.setTitle(rs.getString(2));
				movies.add(mv);
				// System.out.println(star.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return movies;
	}
}
