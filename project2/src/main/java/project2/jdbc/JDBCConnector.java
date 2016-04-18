package project2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector implements JDBCProperty {
	private static Connection connection;

	static {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void connectionClose(Connection connection) {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
			}
	}
}
