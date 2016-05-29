package fablix;

import java.sql.*;

public class JDBCConnection {
	public static Connection connectStart() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			if (connection == null) {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			}
			return connection;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public static void rsClose(Statement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void connectClose() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Close Connection failed!");
			}
		}
	}

	private static final String URL = "jdbc:mysql://54.183.102.98:3306/moviedb";
	private static final String USER = "caoyh";
	private static final String PASSWORD = "cyhwwq";
	private static Connection connection = null;

}
