package miniEbay.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;

public class JDBCPoolTest {
	public static void main(String[] args) throws SQLException {
		String sql = "select last_name from customers where email='a@email.com' and password='a2'";
		JDBCPool pool = null;

		for (int i = 0; i < 5; i++) {
			pool = JDBCPool.getInstance();
			Connection conn = pool.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
			stmt.close();
			pool.release(conn);
		}
		pool.closePool();
	}
}
