package project3.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import project4.jdbc.JDBCPool;

public class MoviePrice {
	public static void main(String[] args) {
		JDBCPool pool = JDBCPool.getInstance();
		List<Integer> movie_ids = new ArrayList<Integer>();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select id from movies;";
			PreparedStatement sql = conn.prepareStatement(sql_str);

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				movie_ids.add(rs.getInt(1));
			}
			rs.close();
			sql.close();

			Random r = new Random();
			for (Integer i : movie_ids) {
				sql_str = "update movies set price=? where id=?;";
				PreparedStatement sql_update = conn.prepareStatement(sql_str);
				sql_update.setDouble(1, r.nextDouble() * 5 + 15);
				sql_update.setInt(2, i);

				sql_update.executeUpdate();
				sql_update.close();
			}

			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}

		pool.closePool();
	}
}
