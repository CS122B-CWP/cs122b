package project4.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import project5.jdbc.JDBCPool;
import project5.jdbc.dao.SingleMovieDAO;

public class SaleQtyTotal {
	public static void main(String[] args) {
		JDBCPool pool = JDBCPool.getInstance();
		List<Integer> sale_ids = new ArrayList<Integer>();
		List<Integer> movie_ids = new ArrayList<Integer>();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select id, movie_id from sales;";
			PreparedStatement sql = conn.prepareStatement(sql_str);

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				sale_ids.add(rs.getInt(1));
				movie_ids.add(rs.getInt(2));
			}
			rs.close();
			sql.close();

			Random r = new Random();
			for (int i = 0; i < sale_ids.size(); i++) {
				sql_str = "update sales set qty=?, total=? where id=?;";
				PreparedStatement sql_update = conn.prepareStatement(sql_str);
				int qty = r.nextInt(5) + 1;
				sql_update.setInt(1, qty);
				sql_update.setDouble(2, qty * SingleMovieDAO.moviecontent(movie_ids.get(i)).getPrice());
				sql_update.setInt(3, sale_ids.get(i));
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
