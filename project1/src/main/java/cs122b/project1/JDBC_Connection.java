package cs122b.project1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs122b.project1.obj.Customer;
import cs122b.project1.obj.Star;

public class JDBC_Connection {
	private Connection connection;

	public JDBC_Connection(String dbname, String user, String pass) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, user, pass);
		} catch (SQLException e) {
			e.getErrorCode();
			System.out.println("Login Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode()
					+ "\nReturn to Login Menu!");
		}
	}

	public boolean isConnected() {
		if (connection == null)
			return false;
		return true;
	}

	public void search_movie_by_star_id(String id) {
		Statement select;
		try {
			select = connection.createStatement();
			String sql_string = "select * from movies where id in (select movie_id from stars_in_movies where stars_id="
					+ id + ")";
			ResultSet result = select.executeQuery(sql_string);
			System.out.println("\nThe results of the query:");
			// print table's contents, field by field
			while (result.next()) {
				System.out.println("Id:\t\t" + result.getInt(1));
				System.out.println("Title:\t\t" + result.getString(2));
				System.out.println("Year:\t\t" + result.getInt(3));
				System.out.println("Dirctor:\t" + result.getString(4));
				System.out.println("Banner_URL:\t" + result.getString(5));
				System.out.println("Trailer:\t" + result.getString(6));
				System.out.println();
			}

			result.close();
			select.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void search_movie_by_star_name(String fname, String lname) {
		Statement select;
		if (fname.equals(""))
			fname = "%%";
		if (lname.equals(""))
			lname = "%%";
		try {
			select = connection.createStatement();
			String sql_string = "select * from movies where id in "
					+ "(select movie_id from stars_in_movies where stars_id in "
					+ "(select id from stars where first_name like '" + fname + "' and last_name like '" + lname
					+ "'))";
			ResultSet result = select.executeQuery(sql_string);
			System.out.println("\nThe results of the query:");
			// print table's contents, field by field
			while (result.next()) {
				System.out.println("Id:\t\t" + result.getInt(1));
				System.out.println("Title:\t\t" + result.getString(2));
				System.out.println("Year:\t\t" + result.getInt(3));
				System.out.println("Dirctor:\t" + result.getString(4));
				System.out.println("Banner_URL:\t" + result.getString(5));
				System.out.println("Trailer:\t" + result.getString(6));
				System.out.println();
			}

			result.close();
			select.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void insert_star(Star star) {
		Statement insert;
		try {
			insert = connection.createStatement();
			String cols;
			String values;
			if (star.getId() != -1) {
				cols = "(id,";
				values = "(" + star.getId() + ",";
			} else {
				cols = "(";
				values = "(";
			}

			cols += "first_name,last_name,dob,photo_url)";

			values += "'" + star.getFirst_name() + "','" + star.getLast_name() + "',";
			if (star.getDob() == null)
				values += "null,'" + star.getPhoto_url() + "')";
			else
				values += "'" + star.getDob() + "','" + star.getPhoto_url() + "')";
			String sql_string = "insert into stars " + cols + " values " + values;
			int row = insert.executeUpdate(sql_string, Statement.RETURN_GENERATED_KEYS);
			ResultSet result = insert.getGeneratedKeys();

			if (result.next())
				System.out.println("\nInsert Star Success with ID:\t" + result.getInt(row) + "\n");
			result.close();
			insert.close();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062)
				System.out.println("ID " + star.getId() + " already exsited! Please try another ID.\n");
			else
				System.out.println(e.getMessage());
		}
	}

	public String valid_customer_by_creditcard(String fname, String lname) {
		String cc_id = "";
		Statement select;
		if (fname.equals(""))
			fname = "%%";
		try {
			select = connection.createStatement();
			String sql_string = "select id from creditcards where first_name like '" + fname + "' and last_name like '"
					+ lname + "'";
			ResultSet result = select.executeQuery(sql_string);
			result.last();
			if (result.getRow() == 0) {
				System.out.println("This customer did not validate in credit card list!");
				return "";
			}
			if (result.getRow() > 1) {
				System.out.println("Duplicate records found! Please enter more accurate name info!");
				return "";
			}
			result.beforeFirst();
			if (result.next()) {
				cc_id = result.getString("id");
			}
			result.close();
			select.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return cc_id;
	}

	public void insert_customer(Customer customer) {
		Statement insert;
		try {
			insert = connection.createStatement();
			String sql_string = "insert into customers " + "(first_name,last_name,cc_id,address,email,password)"
					+ " values " + "('" + customer.getFirst_name() + "','" + customer.getLast_name() + "','"
					+ customer.getCc_id() + "','" + customer.getAddress() + "','" + customer.getEmail() + "','"
					+ customer.getPassword() + "')";

			int row = insert.executeUpdate(sql_string, Statement.RETURN_GENERATED_KEYS);
			ResultSet result = insert.getGeneratedKeys();

			if (result.next())
				System.out.println("\nInsert Custormer Success with ID:\t" + result.getInt(row) + "\n");
			result.close();
			insert.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Map<Integer, String> customer_per_delete(String cc_id) {
		Map<Integer, String> pre_delete = new HashMap<Integer, String>();
		Statement select;
		try {
			select = connection.createStatement();
			String sql_string = "select * from customers where cc_id = '" + cc_id + "'";
			ResultSet result = select.executeQuery(sql_string);
			ResultSetMetaData metadata = result.getMetaData();
			int columns = metadata.getColumnCount();
			String column = "";
			for (int i = 1; i <= columns; i++)
				column += String.format("%-40s", metadata.getColumnLabel(i));
			pre_delete.put(-1, column);

			while (result.next()) {
				int id = result.getInt("id");
				String info = "";
				for (int i = 1; i <= columns; i++)
					info += String.format("%-40s", result.getString(i));
				pre_delete.put(id, info);
			}
			result.close();
			select.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return pre_delete;
	}

	public void delete_customer_by_id(int id) {
		Statement delete;
		try {
			delete = connection.createStatement();
			String sql_string = "delete from customers where id=" + id;
			int row = delete.executeUpdate(sql_string);

			if (row == 1)
				System.out.println("\nDelete Custormer Success with ID:\t" + id + "\n");
			else {
				System.out.println("\nError Code 1\n");
			}

			delete.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void print_database_tables() {
		List<String> table_names = new ArrayList<String>();
		DatabaseMetaData dbmd;
		try {
			dbmd = connection.getMetaData();
			ResultSet tables = dbmd.getTables(null, null, null, new String[] { "TABLE" });
			while (tables.next()) {
				table_names.add(tables.getString("TABLE_NAME"));
			}
			tables.close();

			System.out.println("Total " + table_names.size() + " tables.\n");
			System.out.println("---------------------------------------------------------------------------");

			for (String table_name : table_names) {
				System.out.println("Table_Name=" + table_name);
				ResultSet columns = dbmd.getColumns(null, null, table_name, null);
				while (columns.next())
					System.out.println("Column" + columns.getRow()
							+ String.format(":\t%-40s", "Column_Name=" + columns.getString("COLUMN_NAME"))
							+ String.format("%-40s", "Column_Type=" + columns.getString("TYPE_NAME")) + "Column_Size="
							+ columns.getInt("COLUMN_SIZE"));
				System.out.println("---------------------------------------------------------------------------");
				columns.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void execute_sql(String sql) {
		if (sql.length() < 6) {
			System.out.println("Not a valid sql statement!");
			return;
		}
		String head = sql.substring(0, 6).toUpperCase();
		if (head.equals("SELECT")) {
			this.execute_query(sql);
		} else {
			this.execute_update(sql);
		}
	}

	private void execute_query(String sql) {
		Statement query;
		try {
			query = connection.createStatement();
			ResultSet result = query.executeQuery(sql);
			System.out.println("\nThe results of the query:");
			System.out.println("---------------------------------------------------------------------------");

			ResultSetMetaData metadata = result.getMetaData();
			int columns = metadata.getColumnCount();
			for (int i = 1; i <= columns; i++)
				System.out.print(String.format("%-40s", metadata.getColumnLabel(i)));
			System.out.println();
			while (result.next()) {
				for (int i = 1; i <= columns; i++)
					System.out.print(String.format("%-40s", result.getString(i)));
				System.out.println();
			}
			result.close();
			query.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void execute_update(String sql) {
		Statement update;
		String execute_type = sql.substring(0, 6).toLowerCase();
		try {
			update = connection.createStatement();
			int row = update.executeUpdate(sql);
			if (row > 1)
				System.out.println(execute_type + " " + row + " rows successfully.");
			else
				System.out.println(execute_type + " " + row + " rows successfully.");
			update.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void connectionClose() {
		if (isConnected())
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
