package project2.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project2.jdbc.JDBCPool;
import project2.object.Movie;

public class SingleMovieDAO {

	public static Movie moviecontent(int movie_id) {
		Movie mv = new Movie();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from movies where id=?";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				mv.setId(rs.getInt(1));
				mv.setTitle(rs.getString(2));
				mv.setYear(rs.getInt(3));
				mv.setDirctor(rs.getString(4));
				mv.setBanner_url(rs.getString(5));
				mv.setTrailer(rs.getString(6));
				mv.setPrice(rs.getDouble(7));
				mv.setStars(MovieStarsDAO.movie_stars(movie_id));
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return mv;
	}
}
