package project2.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project2.jdbc.JDBCPool;

public class LoginDao {

	public static String validate(String email, String pass) {
		String name = "";
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			PreparedStatement sql = conn
					.prepareStatement("select last_name from customers where email=? and password=?");
			sql.setString(1, email);
			sql.setString(2, pass);

			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return name;
	}

}
