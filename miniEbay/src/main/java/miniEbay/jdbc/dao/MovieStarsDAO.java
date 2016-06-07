package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miniEbay.jdbc.JDBCPool;
import miniEbay.object.MovieStar;

public class MovieStarsDAO {
	public static List<MovieStar> movie_stars(int movie_id) {
		List<MovieStar> stars = new ArrayList<MovieStar>();

		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select stars.id, first_name, last_name " + "from stars, movies, stars_in_movies where "
					+ "stars.id=stars_in_movies.stars_id and movies.id=stars_in_movies.movie_id and movies.id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);

			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				MovieStar star = new MovieStar();
				star.setId(rs.getInt(1));
				star.setFname(rs.getString(2));
				star.setLname(rs.getString(3));
				stars.add(star);
				// System.out.println(star.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return stars;
	}
}
