package project5.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project5.jdbc.bean.SearchPageBean;
import project5.object.Movie;

public class SearchDAO3 {

	public static List<Movie> nsearchContent(SearchPageBean pagebean) {
		List<Movie> movies = new ArrayList<Movie>();
		int start = (pagebean.getCurPage() - 1) * pagebean.getRowsPerPage();
		Object[] para = new Object[] { "%" + pagebean.getTitle() + "%", "%" + pagebean.getDirector() + "%",
				pagebean.getSyear(), pagebean.getEyear(), "%" + pagebean.getFname() + "%",
				"%" + pagebean.getLname() + "%", start, pagebean.getRowsPerPage() };
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://54.183.102.98:3306/moviedb", "caoyh", "cyhwwq");
			String sql_str = "select movies.id, title, year, dirctor, banner_url from stars, movies, stars_in_movies "
					+ "where stars.id=stars_in_movies.stars_id and movies.id=stars_in_movies.movie_id "
					+ "and movies.title like ? and dirctor like ? and year >= ? and year <=? "
					+ "and first_name like ? and last_name like ? group by movies.id limit ?,?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setObject(1, para[0]);
			sql.setObject(2, para[1]);
			sql.setObject(3, para[2]);
			sql.setObject(4, para[3]);
			sql.setObject(5, para[4]);
			sql.setObject(6, para[5]);
			sql.setObject(7, para[6]);
			sql.setObject(8, para[7]);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				Movie mv = new Movie();
				mv.setId(rs.getInt(1));
				mv.setTitle(rs.getString(2));
				mv.setYear(rs.getInt(3));
				mv.setDirctor(rs.getString(4));
				mv.setBanner_url(rs.getString(5));
				movies.add(mv);
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}

	public static int nsearchPages(SearchPageBean pagebean) {
		int total_pages = 0;
		Object[] para = new Object[] { "%" + pagebean.getTitle() + "%", "%" + pagebean.getDirector() + "%",
				pagebean.getSyear(), pagebean.getEyear(), "%" + pagebean.getFname() + "%",
				"%" + pagebean.getLname() + "%" };
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://54.183.102.98:3306/moviedb", "caoyh", "cyhwwq");
			String sql_str = "select count(distinct movies.id) from stars, movies, stars_in_movies "
					+ "where stars.id=stars_in_movies.stars_id and movies.id=stars_in_movies.movie_id "
					+ "and movies.title like ? and dirctor like ? and year >= ? and year <=? "
					+ "and first_name like ? and last_name like ?";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setObject(1, para[0]);
			sql.setObject(2, para[1]);
			sql.setObject(3, para[2]);
			sql.setObject(4, para[3]);
			sql.setObject(5, para[4]);
			sql.setObject(6, para[5]);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				total_pages = rs.getInt(1);
				// System.out.println(total_pages);
			}
			rs.close();
			sql.close();
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static List<Movie> rsearchContent(SearchPageBean pagebean) {
		List<Movie> movies = new ArrayList<Movie>();
		int start = (pagebean.getCurPage() - 1) * pagebean.getRowsPerPage();
		Object[] para = new Object[] { pagebean.getTitle(), "%" + pagebean.getDirector() + "%", pagebean.getSyear(),
				pagebean.getEyear(), "%" + pagebean.getFname() + "%", "%" + pagebean.getLname() + "%", start,
				pagebean.getRowsPerPage() };
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://54.183.102.98:3306/moviedb", "caoyh", "cyhwwq");
			String sql_str = "select movies.id, title, year, dirctor, banner_url from stars, movies, stars_in_movies "
					+ "where stars.id=stars_in_movies.stars_id and movies.id=stars_in_movies.movie_id "
					+ "and movies.title rlike ? and dirctor like ? and year >= ? and year <=? "
					+ "and first_name like ? and last_name like ? group by movies.id limit ?,?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, para[0].toString());
			sql.setObject(2, para[1]);
			sql.setObject(3, para[2]);
			sql.setObject(4, para[3]);
			sql.setObject(5, para[4]);
			sql.setObject(6, para[5]);
			sql.setObject(7, para[6]);
			sql.setObject(8, para[7]);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				Movie mv = new Movie();
				mv.setId(rs.getInt(1));
				mv.setTitle(rs.getString(2));
				mv.setYear(rs.getInt(3));
				mv.setDirctor(rs.getString(4));
				mv.setBanner_url(rs.getString(5));
				movies.add(mv);
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}

	public static int rsearchPages(SearchPageBean pagebean) {
		int total_pages = 0;
		Object[] para = new Object[] { pagebean.getTitle(), "%" + pagebean.getDirector() + "%", pagebean.getSyear(),
				pagebean.getEyear(), "%" + pagebean.getFname() + "%", "%" + pagebean.getLname() + "%" };
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://54.183.102.98:3306/moviedb", "caoyh", "cyhwwq");
			String sql_str = "select count(distinct movies.id) from stars, movies, stars_in_movies "
					+ "where stars.id=stars_in_movies.stars_id and movies.id=stars_in_movies.movie_id "
					+ "and movies.title rlike ? and dirctor like ? and year >= ? and year <=? "
					+ "and first_name like ? and last_name like ?";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, para[0].toString());
			sql.setObject(2, para[1]);
			sql.setObject(3, para[2]);
			sql.setObject(4, para[3]);
			sql.setObject(5, para[4]);
			sql.setObject(6, para[5]);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				total_pages = rs.getInt(1);
			}
			rs.close();
			sql.close();
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
