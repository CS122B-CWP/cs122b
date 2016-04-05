package cs122b.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cs122b.project1.obj.Customer;
import cs122b.project1.obj.Star;

public class JDBC_Console {
	private static Scanner console;
	private static JDBC_Connection conn;
	private static String username;

	private static void create_console() {
		console = new Scanner(System.in);
		console.useDelimiter("\r\n");
	}

	private static void close_console() {
		console.close();
	}

	public static void login_console() {
		create_console();
		String input;
		boolean ifExit = false;
		do {
			System.out.println("\nLogin Menu:\n" + "1. Connect to local MySQL.\n" + "2. Connect to remote MySQL.\n"
					+ "3. Exit Program.\n");
			System.out.print("input your choice:\n>>");
			input = console.next();
			switch (input) {
			case "1":
				ifExit = connectlocal();
				break;
			case "2":
				ifExit = connectremote();
				break;
			case "3":
				System.out.println("Bye.");
				ifExit = true;
				close_console();
				break;
			default:
				System.out.println("no such choice, please retry!\n");
				break;
			}
		} while (!ifExit);

		if (input.equals("1") || input.equals("2"))
			menu_console();
	}

	private static boolean connectlocal() {
		System.out.print("Input DataBase Name:\n>>");
		String dbname = console.next();
		System.out.print("Input User Name:\n>>");
		String user = console.next();
		System.out.print("Input Password:\n>>");
		String pass = console.next();
		conn = new JDBC_Connection("localhost", dbname, user, pass);
		if (!conn.isConnected())
			return false;
		else {
			username = user;
			System.out.println("\nLogin to " + dbname + " as " + username + " at localhost. Welcome!");
			return true;
		}
	}

	private static boolean connectremote() {
		System.out.print("Input ip-address:\n>>");
		String host = console.next();
		System.out.print("Input DataBase Name:\n>>");
		String dbname = console.next();
		System.out.print("Input User Name:\n>>");
		String user = console.next();
		System.out.print("Input Password:\n>>");
		String pass = console.next();
		conn = new JDBC_Connection(host, dbname, user, pass);
		if (!conn.isConnected())
			return false;
		else {
			username = user;
			System.out.println("\nLogin to " + dbname + " as " + username + " at " + host + ". Welcome!");
			return true;
		}
	}

	private static void menu_console() {
		// create_console();
		// conn = new JDBC_Connection("movieDB", "root", "cyhwwq");
		String input;
		boolean ifExit = false;
		do {
			System.out.println("\nMain Menu:\n" + "1. Search a movie by star.\n" + "2. Insert a new star.\n"
					+ "3. Insert a new customer.\n" + "4. Delete a customer.\n" + "5. Print database table metadata.\n"
					+ "6. Execute a sql.\n" + "7. Exit Menu.\n" + "8. Exit Program.\n");
			System.out.print("input your choice:\n>>");
			input = console.next();
			switch (input) {
			case "1":
				ifExit = true;
				break;
			case "2":
				System.out.print("Input Name (Last, First Format, Seperate By Comma):\n>>");
				String[] star_nameStr = console.next().split(",");

				while (star_nameStr.length == 0 || star_nameStr.length > 2 || star_nameStr[0].equals("")) {
					System.out.print("please as least input last name, please retry!\n>>");
					star_nameStr = console.next().split(",");
				}

				String star_lname = star_nameStr[0];
				String star_fname;
				if (star_nameStr.length == 1)
					star_fname = "";
				else
					star_fname = star_nameStr[1];

				// System.out.println(star_fname + "," + star_lname);

				Star star = new Star(star_fname, star_lname);
				System.out.print("Input ID:\n>>");
				String id = console.next();
				while (!(id.matches("[0-9]*") || id.equals(""))) {
					System.out.print("please input a valid ID or leave blank, please retry!\n>>");
					id = console.next();
				}

				if (!id.equals(""))
					star.setId(Integer.parseInt(id));
				else
					star.setId(-1);

				// System.out.println(star.getId());

				String dateReg = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
				System.out.print("Input Data of Birth (YYYY-MM-DD Format):\n>>");
				String dob = console.next();
				while (!(dob.matches(dateReg) || dob.equals(""))) {
					System.out.print("please input a valid date or leave blank, please retry!\n>>");
					dob = console.next();
				}

				if (!dob.equals("")) {
					star.setDob(dob);
				}

				// System.out.println(star.getDob());

				System.out.print("Input PhotoURL:\n>>");
				String photo_url = console.next();
				star.setPhoto_url(photo_url);

				conn.insert_star(star);
				System.out.println("press enter to continue...");
				console.next();
				break;
			case "3":
				System.out.print("Input Name (Last, First Format, Seperate By Comma):\n>>");
				String[] customer_nameStr = console.next().split(",");

				while (customer_nameStr.length == 0 || customer_nameStr.length > 2 || customer_nameStr[0].equals("")) {
					System.out.print("please as least input last name, please retry!\n>>");
					customer_nameStr = console.next().split(",");
				}

				String customer_lname = customer_nameStr[0];
				String customer_fname;
				if (customer_nameStr.length == 1)
					customer_fname = "";
				else
					customer_fname = customer_nameStr[1];

				// System.out.println(customer_fname + "," + customer_lname);

				String cc_id = conn.valid_customer_by_creditcard(customer_fname, customer_lname);
				if (cc_id != "") {
					Customer customer = new Customer(customer_fname, customer_lname, cc_id);
					System.out.print("Input Address:\n>>");
					customer.setAddress(console.next());
					System.out.print("Input E-Mail:\n>>");
					customer.setEmail(console.next());
					System.out.print("Input Password:\n>>");
					customer.setPassword(console.next());
					conn.insert_customer(customer);
				}

				System.out.println("press enter to continue...");
				console.next();
				break;
			case "4":
				System.out.print("Input credit card ID for delete:\n>>");
				String cc_number = console.next();
				while (cc_number.equals("")) {
					System.out.print("you need to input credit card id, please retry!\n>>");
					cc_number = console.next();
				}

				Map<Integer, String> matched_users = conn.customer_per_delete(cc_number);
				if (matched_users.size() > 1) {
					List<Integer> ids = new ArrayList<Integer>();
					boolean ifValid_Choice = false;
					int choice_id = -1;
					int counter = 1;
					System.out.println(matched_users.get(-1));
					matched_users.remove(-1);
					for (Map.Entry<Integer, String> entry : matched_users.entrySet()) {
						System.out.println(counter + ". " + entry.getValue());
						ids.add(entry.getKey());
						counter++;
					}
					System.out.println(counter + ". Cancle.");
					System.out.print("Input your choice:\n>>");
					String choice = console.next();
					while (!ifValid_Choice) {
						while (!choice.matches("[0-9][0-9]*")) {
							System.out.print("please input a valid choice!\n>>");
							choice = console.next();
						}
						choice_id = Integer.parseInt(choice);
						if (choice_id > 0 && choice_id <= ids.size() + 1)
							ifValid_Choice = true;
						else {
							System.out.print("please input a valid choice!\n>>");
							choice = console.next();
						}
					}

					choice_id = Integer.parseInt(choice);
					if (choice_id != counter) {
						int delete_id = ids.get(choice_id - 1);
						conn.delete_customer_by_id(delete_id);
					}
				} else {
					System.out.println("No matched customer record with credit card id:" + cc_number + "!");
				}

				System.out.println("press enter to continue...");
				console.next();
				break;
			case "5":
				conn.print_database_tables();
				System.out.println("press enter to continue...");
				console.next();
				break;
			case "6":
				String sql = "";
				do {
					System.out.print("sql>>");
					sql += console.next();
				} while (!sql.endsWith(";"));

				conn.execute_sql(sql);
				System.out.println("press enter to continue...");
				console.next();
				break;
			case "7":
				conn.connectionClose();
				ifExit = true;
				break;
			case "8":
				System.out.println("Bye " + username + ".");
				conn.connectionClose();
				close_console();
				ifExit = true;
				break;
			default:
				System.out.println("no such choice, please retry!\n");
				break;
			}
		} while (!ifExit);

		switch (input) {
		case "1":
			searchmovie_console();
			break;
		case "7":
			login_console();
		default:
			break;
		}
	}

	private static void searchmovie_console() {
		String input;
		boolean ifExit = false;
		do {
			System.out.println("\nMovie Search By Star:\n" + "1. By Name.\n" + "2. By ID.\n"
					+ "3. Return to Main Menu.\n" + "4. Exit Menu.\n" + "5. Exit Program.\n");
			System.out.print("input your choice:\n>>");
			input = console.next();
			switch (input) {
			case "1":
				System.out.print("Input Name (Last, First Format, Seperate By Comma):\n>>");
				String[] nameStr = console.next().split(",");

				while (nameStr.length == 0 || nameStr.length > 2 || (nameStr.length == 1 && nameStr[0].equals(""))) {
					System.out.print("please as least input last name or first name, please retry!\n>>");
					nameStr = console.next().split(",");
				}

				String lname = nameStr[0];
				String fname;
				if (nameStr.length == 1)
					fname = "";
				else
					fname = nameStr[1];
				conn.search_movie_by_star_name(fname, lname);
				System.out.println("press enter to continue...");
				console.next();
				break;
			case "2":
				System.out.print("Input ID:\n>>");
				String id = console.next();
				while (!id.matches("[0-9][0-9]*")) {
					System.out.print("please input a valid ID, please retry!\n>>");
					id = console.next();
				}
				conn.search_movie_by_star_id(id);
				System.out.println("press enter to continue...");
				console.next();
				break;
			case "3":
				ifExit = true;
				break;
			case "4":
				conn.connectionClose();
				ifExit = true;
				break;
			case "5":
				conn.connectionClose();
				close_console();
				ifExit = true;
				System.out.println("Bye " + username + ".");
				break;
			default:
				System.out.println("no such choice, please retry!\n");
				break;
			}
		} while (!ifExit);
		switch (input) {
		case "3":
			menu_console();
			break;
		case "4":
			login_console();
			break;
		default:
			break;
		}

	}

	public static void main(String[] arg) {
		System.out.println("Welcome to JDBC Text Console!\n");
		// JDBC_Console.menu_console();
		JDBC_Console.login_console();
	}
}
