package project5.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project5.jdbc.JDBCPool;

public class FullTSearchAjaxDAO {
	public static List<String> searchContent(String prefixes_str) {
		List<String> titles = new ArrayList<String>();

		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "SELECT title FROM movies WHERE MATCH (title) AGAINST (? IN BOOLEAN MODE) limit 5;";
			PreparedStatement sql = conn.prepareStatement(sql_str);
			String[] prefixes = prefixes_str.split(" ");
			String query_str = "";
			for (int i = 0; i < prefixes.length; i++) {
				if (i == prefixes.length - 1)
					query_str += "+" + prefixes[i] + "*";
				else
					query_str += "+" + prefixes[i] + "* ";
			}

			sql.setString(1, query_str);

			//System.out.println(sql.toString());

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {

				titles.add(rs.getString(1));
				// System.out.println(mv.toString());
			}
			rs.close();
			sql.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return titles;
	}
}
