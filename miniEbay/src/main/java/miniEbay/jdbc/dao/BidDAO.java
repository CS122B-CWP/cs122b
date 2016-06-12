package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import miniEbay.jdbc.JDBCPool;
import miniEbay.object.DetailItem;

public class BidDAO {

	public static String bid(String customer_id, String item_id, double price) {
		DetailItem item = new DetailItem();
		JSONObject msg = new JSONObject();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();

			// Valid user
			String sql_str = "select seller_id from items where item_id=?;";
			PreparedStatement stmn = conn.prepareStatement(sql_str);
			stmn.setString(1, item_id);
			// System.out.println(stmn.toString());

			ResultSet rs = stmn.executeQuery();

			String seller = null;
			if (rs.next()) {
				seller = rs.getString(1);
			}
			rs.close();
			stmn.close();

			if (customer_id.equals(seller)) {
				msg.put("bid_result", "fail");
				msg.put("info", "Your are the seller!");
				return msg.toString();
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now_date = new Date();

			// Valid price
			sql_str = "select current_price, end_time from items where item_id=?;";
			stmn = conn.prepareStatement(sql_str);
			stmn.setString(1, item_id);
			// System.out.println(stmn.toString());

			rs = stmn.executeQuery();

			double current_price = 0;
			String end_date = null;
			if (rs.next()) {
				current_price = rs.getDouble(1);
				end_date = rs.getString(2);
			}
			rs.close();
			stmn.close();

			if (price <= current_price) {
				msg.put("bid_result", "fail");
				msg.put("info", "price changed, please refresh!");
				return msg.toString();
			}

			if (now_date.compareTo(df.parse(end_date)) > 0) {
				msg.put("bid_result", "fail");
				msg.put("info", "Auction is finished!");
				return msg.toString();
			}

			// insert bid and update item_info
			sql_str = "insert into bids (customer_id, item_id, bid_price, bid_time) values (?, ?, ?, ?);";

			stmn = conn.prepareStatement(sql_str);
			stmn.setString(1, customer_id);
			stmn.setString(2, item_id);
			stmn.setDouble(3, price);
			stmn.setString(4, df.format(now_date));
			// System.out.println(stmn.toString());

			stmn.executeUpdate();
			stmn.close();

			sql_str = "update items set current_price = ? where item_id = ?;";

			stmn = conn.prepareStatement(sql_str);
			stmn.setDouble(1, price);
			stmn.setString(2, item_id);
			// System.out.println(stmn.toString());

			stmn.executeUpdate();
			stmn.close();
			JDBCPool.getInstance().release(conn);

			// Get updated info
			item = SingleItemDAO.itemDetail(item_id);

			msg.put("bid_result", "success");
			msg.put("item_info", item.toJson());
			return msg.toString();
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		msg.put("bid_result", "fail");
		msg.put("info", "Server Busy, Please retry!");
		return msg.toString();
	}
}
