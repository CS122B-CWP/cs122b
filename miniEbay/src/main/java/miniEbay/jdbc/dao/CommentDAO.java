package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import miniEbay.jdbc.JDBCPool;
import miniEbay.object.DetailItem;

public class CommentDAO {

	public static String post_comment(String customer_id, String item_id, String comment) {
		DetailItem item;
		JSONObject msg = new JSONObject();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now_date = new Date();

			// insert comment
			String sql_str = "insert into comments (customer_id, item_id, comment, post_time) values (?, ?, ?, ?);";

			PreparedStatement stmn = conn.prepareStatement(sql_str);
			stmn.setString(1, customer_id);
			stmn.setString(2, item_id);
			stmn.setString(3, comment);
			stmn.setString(4, df.format(now_date));
			// System.out.println(stmn.toString());

			stmn.executeUpdate();
			stmn.close();
			JDBCPool.getInstance().release(conn);

			// Get updated info
			item = SingleItemDAO.itemDetail(item_id);

			msg.put("comment_result", "success");
			msg.put("item_info", item.toJson());
			return msg.toString();
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		msg.put("comment_result", "fail");
		msg.put("info", "Server Busy, Please retry!");
		return msg.toString();
	}
}
