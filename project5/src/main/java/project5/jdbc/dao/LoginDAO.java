package project5.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project5.jdbc.JDBCPool;
import project5.object.LoginInfo;

public class LoginDAO {

	public static LoginInfo validate(String email, String pass) {
		LoginInfo user = null;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			PreparedStatement sql = conn
					.prepareStatement("select last_name, id from customers where email=? and password=?");
			sql.setString(1, email);
			sql.setString(2, pass);

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				user = new LoginInfo();
				user.setLname(rs.getString(1));
				user.setUser_id(rs.getInt(2));
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
