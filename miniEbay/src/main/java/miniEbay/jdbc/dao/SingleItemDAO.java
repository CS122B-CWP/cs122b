package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import miniEbay.jdbc.JDBCPool;
import miniEbay.object.DetailItem;

public class SingleItemDAO {

	public static DetailItem itemDetail(String item_id) {
		DetailItem item = new DetailItem();
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select * from items, categories where "
					+ "items.category_id = categories.category_id and item_id = ?;";
			PreparedStatement stmn = conn.prepareStatement(sql_str);
			stmn.setString(1, item_id);
			// System.out.println(stmn.toString());

			ResultSet rs = stmn.executeQuery();

			if (rs.next()) {
				item.setItem_id(item_id);
				item.setTitle(rs.getString(2));
				item.setSeller_id(rs.getString(3));
				item.setStart_time(rs.getString(4));
				item.setEnd_time(rs.getString(5));
				item.setCurrent_price(rs.getDouble(6));
				item.setCategory_id(rs.getString(7));
				item.setGallery_url(rs.getString(8));
				item.setDes(rs.getString(9));
				item.setCategory_name(rs.getString(11));
			}
			rs.close();
			stmn.close();

			sql_str = "select distinct photo_url from photos where item_id = ?;";
			stmn = conn.prepareStatement(sql_str);
			stmn.setString(1, item_id);
			// System.out.println(stmn.toString());

			rs = stmn.executeQuery();

			while (rs.next()) {
				item.getPhotos().add(rs.getString(1));
			}

			// System.out.println(item.toString());
			rs.close();
			stmn.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return item;
	}
}
