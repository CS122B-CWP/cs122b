package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.bean.ShoppingCartBean;

public class ShoppingCartDAO {
	private static ShoppingCartBean checkitem(String customer_id, String item_id) {
		ShoppingCartBean item = null;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from shopping_cart_items where customer_id=? and item_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, customer_id);
			sql.setString(2, item_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			if (rs.next()) {
				item = new ShoppingCartBean();
				item.setCustomer_id(customer_id);
				item.setItem_id(item_id);
				item.setTitle((rs.getString(3)));
				item.setPrice((rs.getDouble(4)));
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

	private static double unitprice(String item_id) {
		double price = 0.0;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select current_price from items where item_id= ?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, item_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				price = rs.getDouble(1);
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return price;
	}

	public static void additem(String customer_id, String item_id, String title) {
		ShoppingCartBean item = ShoppingCartDAO.checkitem(customer_id, item_id);
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str;
			PreparedStatement sql;
			if (item == null) {
				sql_str = "insert into shopping_cart_items (customer_id, item_id, "
						+ "title, price) values (?, ?, ?, ?);";
				sql = conn.prepareStatement(sql_str);
				sql.setString(1, customer_id);
				sql.setString(2, item_id);
				sql.setString(3, title);
				sql.setDouble(4, ShoppingCartDAO.unitprice(item_id));
			} else {
				return;
			}

			// System.out.println(sql.toString());

			sql.executeUpdate();
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

			sql.executeUpdate();
			// System.out.println(lines);
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
	}

	public static void removeitem(String customer_id, String item_id) {
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "delete from shopping_cart_items where customer_id = ? and item_id = ?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, customer_id);
			sql.setString(2, item_id);

			// System.out.println(sql.toString());

			sql.executeUpdate();
			// System.out.println(lines);
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
	}

	public static void removeAll(String customer_id) {
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "delete from shopping_cart_items where customer_id = ?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, customer_id);

			// System.out.println(sql.toString());

			sql.executeUpdate();
			// System.out.println(lines);
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
	}

	public static List<ShoppingCartBean> items(String customer_id) {
		List<ShoppingCartBean> items = new ArrayList<ShoppingCartBean>();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from shopping_cart_items where customer_id=?";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setString(1, customer_id);
			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				ShoppingCartBean item = new ShoppingCartBean();
				item.setCustomer_id(customer_id);
				item.setItem_id(rs.getString(2));
				item.setTitle((rs.getString(3)));
				item.setPrice((rs.getDouble(4)));
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
