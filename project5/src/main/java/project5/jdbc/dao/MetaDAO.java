package project5.jdbc.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project5.jdbc.JDBCPool;

public class MetaDAO {
	public static String showmetadata() {
		Connection conn = JDBCPool.getInstance().getConnection();
		List<String> table_names = new ArrayList<String>();
		DatabaseMetaData dbmd;
		StringBuilder str = new StringBuilder();
		try {
			dbmd = conn.getMetaData();
			ResultSet tables = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			while (tables.next()) {
				table_names.add(tables.getString("TABLE_NAME"));
			}
			tables.close();
			str.append("Total " + table_names.size() + " tables.\r\n");
			str.append("---------------------------------------------------------------------------\r\n");
			// System.out.println("Total " + table_names.size() + " tables.\n");
			// System.out.println("---------------------------------------------------------------------------");

			for (String table_name : table_names) {
				str.append("Table_Name=" + table_name + "\r\n");
				// System.out.println("Table_Name=" + table_name);
				ResultSet columns = dbmd.getColumns(null, null, table_name, null);
				while (columns.next())
					str.append("Column" + columns.getRow()
							+ String.format(":\t%-40s", "Column_Name=" + columns.getString("COLUMN_NAME"))
							+ String.format("%-40s", "Column_Type=" + columns.getString("TYPE_NAME")) + "Column_Size="
							+ columns.getInt("COLUMN_SIZE") + "\r\n");
				str.append("---------------------------------------------------------------------------\r\n");
				// System.out.println("---------------------------------------------------------------------------");
				columns.close();
			}
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return str.toString();
	}
}
