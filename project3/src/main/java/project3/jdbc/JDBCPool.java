package project3.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class JDBCPool implements JDBCProperty {
	private Vector<Connection> pool;
	private static JDBCPool instance = null;

	private JDBCPool() {
		pool = new Vector<Connection>(SIZE);
		addConnection();
	}

	private void addConnection() {
		Connection coon = null;
		for (int i = 0; i < SIZE; i++) {
			try {
				Class.forName(DRIVER);
				coon = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
				// System.out.println("JDBC Connection " + i + " create!");
				pool.add(coon);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
			}
		}
	}

	public static JDBCPool getInstance() {
		if (instance == null) {
			instance = new JDBCPool();
		}
		return instance;
	}

	public synchronized void release(Connection conn) {
		pool.add(conn);
	}

	public synchronized void closePool() {
		for (int i = 0; i < pool.size(); i++) {
			try {
				((Connection) pool.get(i)).close();
				// System.out.println("JDBC Connection " + i + " close!");
			} catch (SQLException e) {
				System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
			}
			pool.remove(i);
		}
	}

	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}

}
