package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.bean.UserInfoBean;

public class UserDAO {

	public static UserInfoBean userinfo(int customer_id) {
		UserInfoBean user = new UserInfoBean();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			PreparedStatement sql = conn
					.prepareStatement("select first_name, last_name, address, email from customers where id=?");
			sql.setInt(1, customer_id);

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				user.setId(customer_id);
				user.setFirst_name(rs.getString(1));
				user.setLast_name(rs.getString(2));
				user.setAddress(rs.getString(3));
				user.setEmail(rs.getString(4));
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return user;
	}

}
