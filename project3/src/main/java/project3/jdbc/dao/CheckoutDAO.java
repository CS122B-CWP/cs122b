package project3.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import project3.jdbc.JDBCPool;
import project3.jdbc.bean.ShoppingCartBean;

public class CheckoutDAO {

	public static boolean validate(String credit_card_id, String first_name, String last_name, String exp) {
		boolean ifValid = false;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			PreparedStatement sql = conn.prepareStatement(
					"select * from creditcards where id=? and first_name=? and last_name=? and expiration=?");
			sql.setString(1, credit_card_id);
			sql.setString(2, first_name);
			sql.setString(3, last_name);
			sql.setString(4, exp);

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				ifValid = true;
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return ifValid;
	}

	public static boolean addsales(int customer_id) {
		boolean ifOrderComplete = false;
		List<ShoppingCartBean> items = ShoppingCartDAO.items(customer_id);
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(date);
			for (ShoppingCartBean item : items) {
				String sql_str = "insert into sales (customer_id, movie_id, sale_date, qty, total) "
						+ "values (?, ?, ?, ?, ?);";
				PreparedStatement sql = conn.prepareStatement(sql_str);

				sql.setInt(1, customer_id);
				sql.setInt(2, item.getMovie_id());
				sql.setString(3, time);
				sql.setInt(4, item.getQty());
				sql.setDouble(5, item.getQty() * item.getUnit_price());

				// System.out.println(sql.toString());

				int lines = sql.executeUpdate();
				ShoppingCartDAO.removeitem(customer_id, item.getMovie_id());
				// System.out.println(lines);
				sql.close();
			}
			ifOrderComplete = true;
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return ifOrderComplete;
	}
}
