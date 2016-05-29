package project5.jdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import project5.jdbc.JDBCPool;

public class InsertStarDAO {

	public static int insert(String dob, String first_name, String last_name, String photo_url) {
		int new_star_id = -1;
		Statement insert;
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			insert = conn.createStatement();
			String cols;
			String values;

			cols = "(";
			values = "(";

			cols += "first_name,last_name,dob,photo_url)";

			values += "'" + first_name + "','" + last_name + "',";
			if (dob == null)
				values += "null,'" + photo_url + "')";
			else
				values += "'" + dob + "','" + photo_url + "')";
			String sql_string = "insert into stars " + cols + " values " + values;
			int row = insert.executeUpdate(sql_string, Statement.RETURN_GENERATED_KEYS);
			ResultSet result = insert.getGeneratedKeys();

			if (result.next()) {
				new_star_id = result.getInt(row);
			}
			result.close();
			insert.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062)
				System.out.println("Star already exsited! Please try another ID.\n");
			else
				System.out.println(e.getMessage());
		}

		return new_star_id;
	}
}
