package project2.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project2.jdbc.JDBCPool;
import project2.object.Star;

public class SingleStarDAO {
	public static Star starcontent(int star_id) {
		Star star = new Star();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from stars where id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, star_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				star.setId(rs.getInt(1));
				star.setFname(rs.getString(2));
				star.setLname(rs.getString(3));
				star.setDob(rs.getDate(4));
				star.setPhoto_url(rs.getString(5));
				star.setMovies(StarMoviesDAO.star_movies(star_id));
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return star;
	}
}
