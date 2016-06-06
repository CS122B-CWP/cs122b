package project5.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JDBCPool implements JDBCProperty {
	private Vector<Connection> pool;
	private static JDBCPool instance = null;

	private JDBCPool() {
		pool = new Vector<Connection>(SIZE);
		addConnection();
	}

	private void addConnection() {
		try {
			Class.forName(DRIVER);
			Context initialContext = new InitialContext();
			Context environmentContext = (Context) initialContext.lookup("java:comp/env");
			DataSource dataSource = (DataSource) environmentContext.lookup("jdbc/movieDB");
			for (int i = 0; i < SIZE; i++) {
				Connection conn = dataSource.getConnection();
				// System.out.println("JDBC Connection " + i + " create!");
				pool.add(conn);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
