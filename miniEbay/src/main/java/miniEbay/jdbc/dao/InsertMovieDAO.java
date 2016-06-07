package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import miniEbay.jdbc.JDBCPool;
import miniEbay.object.IMoviePara;

public class InsertMovieDAO {

	public static String insertmovie(IMoviePara para) {
		// System.out.println(para.toString());
		String resultStr = "";

		// check genre
		int genre_id = -1;
		if (para.getGenre() != "") {
			genre_id = validGenre(para.getGenre());
			// System.out.println(genre_id);
			if (genre_id == -1) {
				resultStr = "The genre " + para.getGenre() + " is not a valid genre!!";
				return resultStr;
			}
		}

		// check star
		int star_id = -1;
		if (para.getStar_name() != "") {
			String[] names = para.getStar_name().split(",");
			if (names.length > 2 || names.length < 1) {
				resultStr = "The star name your provide is not valid!!";
				return resultStr;
			}

			star_id = validStar(names);
			if (star_id == -1) {
				resultStr = "The star " + para.getStar_name() + " is not in records!!";
				return resultStr;
			}

		}

		// check movie id
		if (para.getId() != -1) {
			boolean valid_id = validMovie_id(para);
			if (!valid_id) {
				resultStr = "The ID " + para.getId() + " your provided is not in database record!!";
				return resultStr;
			}
		} else {
			// find if exist, otherwise, insert
			int movie_id = findMovie_id(para);
			// System.out.println(movie_id);
			if (movie_id == -1) {
				movie_id = insertMovie(para);
				para.setId(movie_id);
				resultStr = "Insert new movie, new movie_id is " + movie_id + ".\n";
			} else {
				para.setId(movie_id);
			}
		}

		// System.out.println(para.getId());
		// check movie_genre
		if (genre_id != -1) {
			// find if exist, otherwise, insert
			boolean movie_genre = findMovie_Genre(para.getId(), genre_id);
			if (!movie_genre) {
				insertMovie_Genre(para.getId(), genre_id);
				resultStr += "movie id: " + para.getId() + " linked to genre: " + para.getGenre() + ".\n";
			}
		}

		// check movie_star
		if (star_id != -1) {
			// find if exist, otherwise, insert
			boolean movie_star = findMovie_Star(para.getId(), star_id);
			if (!movie_star) {
				insertMovie_Star(para.getId(), star_id);
				resultStr += "movie id: " + para.getId() + " linked to star: " + para.getStar_name() + ".\n";
			}
		}
		return resultStr;
	}

	private static boolean validMovie_id(IMoviePara para) {
		boolean valid = false;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from movies where id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, para.getId());

			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				valid = true;
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return valid;
	}

	private static int validGenre(String genre) {
		int genre_id = -1;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select id from genres where name=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, genre);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				genre_id = rs.getInt(1);
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return genre_id;
	}

	private static int validStar(String[] star_names) {
		int star_id = -1;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str;
			PreparedStatement sql;

			if (star_names.length == 2) {
				sql_str = "select id from stars where first_name=? and last_name=?;";
				sql = conn.prepareStatement(sql_str);
				sql.setString(1, star_names[1]);
				sql.setString(2, star_names[0]);
			} else {
				sql_str = "select id from stars where last_name=?;";
				sql = conn.prepareStatement(sql_str);
				sql.setString(1, star_names[0]);
			}
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				star_id = rs.getInt(1);
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return star_id;
	}

	private static int findMovie_id(IMoviePara para) {
		int movie_id = -1;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from movies where title='" + para.getTitle() + "'";
			if (para.getYear() != -1)
				sql_str += " and year=" + para.getYear();
			if (para.getDirctor() != "")
				sql_str += " and dirctor='" + para.getDirctor() + "'";
			if (para.getBanner_url() != "")
				sql_str += " and banner_url='" + para.getBanner_url() + "'";
			if (para.getTrailer() != "")
				sql_str += " and trailer='" + para.getTrailer() + "'";
			if (para.getPrice() != 0.0)
				sql_str += " and price=" + para.getPrice();
			sql_str += ";";

			// System.out.println(sql_str);

			ResultSet rs = conn.createStatement().executeQuery(sql_str);

			if (rs.next()) {
				movie_id = rs.getInt(1);
			}
			rs.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return movie_id;
	}

	private static int insertMovie(IMoviePara para) {
		int insert_id = -1;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "insert into movies (title, year, dirctor, banner_url, trailer, price)"
					+ " values (?,?,?,?,?,?);";
			PreparedStatement sql = conn.prepareStatement(sql_str, Statement.RETURN_GENERATED_KEYS);
			sql.setString(1, para.getTitle());
			sql.setInt(2, para.getYear());
			sql.setString(3, para.getDirctor());
			sql.setString(4, para.getBanner_url());
			sql.setString(5, para.getTrailer());
			sql.setDouble(6, para.getPrice());

			// System.out.println(sql.toString());

			sql.executeUpdate();
			ResultSet rs = sql.getGeneratedKeys();

			if (rs.next())
				insert_id = rs.getInt(1);

			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}

		return insert_id;
	}

	private static boolean findMovie_Genre(int movie_id, int genre_id) {
		boolean linked = false;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from genres_in_movies where movie_id=? and genre_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);
			sql.setInt(2, genre_id);

			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				linked = true;
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return linked;
	}

	private static void insertMovie_Genre(int movie_id, int genre_id) {
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "insert into genres_in_movies (movie_id, genre_id) values (?,?);";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);
			sql.setInt(2, genre_id);

			// System.out.println(sql.toString());

			int lines = sql.executeUpdate();

			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}

	}

	private static boolean findMovie_Star(int movie_id, int stars_id) {
		boolean linked = false;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from stars_in_movies where movie_id=? and stars_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);
			sql.setInt(2, stars_id);

			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				linked = true;
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return linked;
	}

	private static void insertMovie_Star(int movie_id, int stars_id) {
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "insert into stars_in_movies (movie_id, stars_id) values (?,?);";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);
			sql.setInt(2, stars_id);

			// System.out.println(sql.toString());

			int lines = sql.executeUpdate();

			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}

	}
}
