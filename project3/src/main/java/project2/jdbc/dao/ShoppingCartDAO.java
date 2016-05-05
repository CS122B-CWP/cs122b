package project2.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project2.jdbc.JDBCPool;
import project2.jdbc.bean.ShoppingCartBean;

public class ShoppingCartDAO {
	private static ShoppingCartBean checkitem(int customer_id, int movie_id) {
		ShoppingCartBean item = null;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from shopping_cart_items where customer_id=? and movie_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, customer_id);
			sql.setInt(2, movie_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				item = new ShoppingCartBean();
				item.setCustomer_id(rs.getInt(1));
				item.setMovie_id(rs.getInt(2));
				item.setMovie_title(rs.getString(3));
				item.setUnit_price(rs.getDouble(4));
				item.setQty(rs.getInt(5));
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return item;
	}

	private static double unitprice(int movie_id) {
		double price = 0.0;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select price from movies where id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, movie_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				price = rs.getDouble(1);
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return price;
	}

	public static void additem(int customer_id, int movie_id, String movie_title) {
		ShoppingCartBean item = ShoppingCartDAO.checkitem(customer_id, movie_id);
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str;
			PreparedStatement sql;
			if (item == null) {
				sql_str = "insert into shopping_cart_items (customer_id, movie_id, "
						+ "movie_title, unit_price, qty) values (?, ?, ?, ?, 1);";
				sql = conn.prepareStatement(sql_str);
				sql.setInt(1, customer_id);
				sql.setInt(2, movie_id);
				sql.setString(3, movie_title);
				sql.setDouble(4, ShoppingCartDAO.unitprice(movie_id));
			} else {
				sql_str = "update shopping_cart_items set qty=qty+1 where customer_id=? and movie_id=?;";
				sql = conn.prepareStatement(sql_str);
				sql.setInt(1, customer_id);
				sql.setInt(2, movie_id);
			}

			// System.out.println(sql.toString());

			int lines = sql.executeUpdate();
			// System.out.println(lines);
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
	}

	public static void updateitem(int customer_id, int movie_id, int qty) {
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "update shopping_cart_items set qty=? where customer_id=? and movie_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, qty);
			sql.setInt(2, customer_id);
			sql.setInt(3, movie_id);

			// System.out.println(sql.toString());

			int lines = sql.executeUpdate();
			// System.out.println(lines);
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
	}

	public static void removeitem(int customer_id, int movie_id) {
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "delete from shopping_cart_items where customer_id=? and movie_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, customer_id);
			sql.setInt(2, movie_id);

			// System.out.println(sql.toString());

			int lines = sql.executeUpdate();
			// System.out.println(lines);
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
	}

	public static void removeAll(int customer_id) {
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "delete from shopping_cart_items where customer_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, customer_id);

			// System.out.println(sql.toString());

			int lines = sql.executeUpdate();
			// System.out.println(lines);
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
	}

	public static List<ShoppingCartBean> items(int customer_id) {
		List<ShoppingCartBean> items = new ArrayList<ShoppingCartBean>();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from shopping_cart_items where customer_id=?";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, customer_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				ShoppingCartBean item = new ShoppingCartBean();
				item.setCustomer_id(rs.getInt(1));
				item.setMovie_id(rs.getInt(2));
				item.setMovie_title(rs.getString(3));
				item.setUnit_price(rs.getDouble(4));
				item.setQty(rs.getInt(5));
				items.add(item);
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return items;

	}
}
