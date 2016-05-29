package project5.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project5.jdbc.JDBCPool;
import project5.jdbc.bean.OrderHistoryBean;
import project5.jdbc.bean.OrderSingleBean;

public class SalesDAO {
	public static List<OrderHistoryBean> orderhistory(int customer_id) {
		List<OrderHistoryBean> sales = new ArrayList<OrderHistoryBean>();

		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select id, sale_date, total from sales where customer_id=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, customer_id);

			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				OrderHistoryBean sale = new OrderHistoryBean();
				sale.setId(rs.getInt(1));
				sale.setSale_date(rs.getDate(2).toString());
				sale.setTotal(rs.getDouble(3));
				sales.add(sale);
				// System.out.println(star.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return sales;
	}

	public static List<OrderSingleBean> orderSingle(int customer_id, String sale_date) {
		List<OrderSingleBean> sales_day = new ArrayList<OrderSingleBean>();

		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select qty, price, movies.id, title from movies, sales "
					+ "where movies.id = sales.movie_id and customer_id=? and sale_date=?;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			sql.setInt(1, customer_id);
			sql.setString(2, sale_date);

			// System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				OrderSingleBean sale_day = new OrderSingleBean();
				sale_day.setQty(rs.getInt(1));
				sale_day.setUnit_price(rs.getDouble(2));
				sale_day.setMovie_id(rs.getInt(3));
				sale_day.setTitle(rs.getString(4));
				sales_day.add(sale_day);
				// System.out.println(star.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return sales_day;
	}
}
