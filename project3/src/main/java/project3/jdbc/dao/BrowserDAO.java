package project3.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project3.jdbc.JDBCPool;
import project3.jdbc.bean.BrowserPageBean;
import project3.object.Movie;

public class BrowserDAO {

	public static List<Movie> browserContent(BrowserPageBean pagebean) {
		List<Movie> movies = new ArrayList<Movie>();
		int start = (pagebean.getCurPage() - 1) * pagebean.getRowsPerPage();
		Object[] para = new Object[] { "%" + pagebean.getGenre() + "%", pagebean.getYear() + "%", start,
				pagebean.getRowsPerPage() };
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select movies.id, title, year, dirctor, banner_url, name from movies, genres_in_movies, genres"
					+ " where movies.id=genres_in_movies.movie_id and genres_in_movies.genre_id = genres.id "
					+ "and name like ? and year like ? group by movies.id limit ?,?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setObject(1, para[0]);
			sql.setObject(2, para[1]);
			sql.setObject(3, para[2]);
			sql.setObject(4, para[3]);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				Movie mv = new Movie();
				mv.setId(rs.getInt(1));
				mv.setTitle(rs.getString(2));
				mv.setYear(rs.getInt(3));
				mv.setDirctor(rs.getString(4));
				mv.setBanner_url(rs.getString(5));
				mv.setGenre(rs.getString(6));
				movies.add(mv);
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return movies;
	}

	public static int browserPages(BrowserPageBean pagebean) {
		int total_pages = 0;
		Object[] para = new Object[] { "%" + pagebean.getGenre() + "%", pagebean.getYear() + "%" };
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select count(distinct movies.id) from movies, genres_in_movies, genres"
					+ " where movies.id=genres_in_movies.movie_id and genres_in_movies.genre_id = genres.id "
					+ "and name like ? and title like ?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setObject(1, para[0]);
			sql.setObject(2, para[1]);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				total_pages = rs.getInt(1);
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		if (total_pages % pagebean.getRowsPerPage() != 0)
			total_pages /= pagebean.getRowsPerPage() + 1;
		else
			total_pages /= pagebean.getRowsPerPage();

		if (total_pages == 0)
			total_pages = 1;
		// System.out.println(total_pages);
		return total_pages;
	}

}
