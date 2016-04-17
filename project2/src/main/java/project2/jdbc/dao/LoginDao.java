package project2.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project2.jdbc.JDBCConnector;
import project2.jdbc.bean.LoginBean;

public class LoginDao {
	private static Connection conn = null;

	public LoginDao() {
		conn = JDBCConnector.getConnection();
	}

	public boolean validate(LoginBean bean) {
		boolean status = false;
		try {
			PreparedStatement sql = conn.prepareStatement("select * from customer where email=? and password=?");
			sql.setString(1, bean.getEmail());
			sql.setString(2, bean.getPass());

			ResultSet rs = sql.executeQuery();
			status = rs.next();
			rs.close();
			sql.close();
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return status;
	}

	public void close() {
		JDBCConnector.connectionClose(conn);
	}
}
