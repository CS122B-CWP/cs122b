package miniEbay.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import miniEbay.jdbc.JDBCPool;
import miniEbay.jdbc.bean.SearchPageBean;
import miniEbay.object.BriefItem;

public class SearchDAO {
	public static List<BriefItem> searchContent(SearchPageBean pagebean) {
		List<BriefItem> brief_items = new ArrayList<BriefItem>();
		int start = (pagebean.getCurPage() - 1) * pagebean.getRowsPerPage();
		Object[] para = new Object[] { "%" + pagebean.getSearch_title() + "%",
				"%" + pagebean.getSearch_category_id() + "%", pagebean.getLowPrice(), pagebean.getHighPrice(), start,
				pagebean.getRowsPerPage() };
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select item_id, title, items.category_id, category_name, current_price, gallery_url"
					+ " from items, categories where items.category_id = categories.category_id and "
					+ "title like ? and items.category_id like ? and current_price > ? and current_price < ? "
					+ "group by item_id limit ?, ?;";
			PreparedStatement stmn = conn.prepareStatement(sql_str);
			stmn.setObject(1, para[0]);
			stmn.setObject(2, para[1]);
			stmn.setObject(3, para[2]);
			stmn.setObject(4, para[3]);
			stmn.setObject(5, para[4]);
			stmn.setObject(6, para[5]);
			// System.out.println(stmn.toString());

			ResultSet rs = stmn.executeQuery();

			while (rs.next()) {
				BriefItem item = new BriefItem();
				item.setItem_id(rs.getString(1));
				item.setTitle(rs.getString(2));
				item.setCategory_id(rs.getString(3));
				item.setCategory_name(rs.getString(4));
				item.setCurrent_price(rs.getDouble(5));
				item.setGallery_url(rs.getString(6));
				brief_items.add(item);
				// System.out.println(mv.toString());
			}
			rs.close();
			stmn.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		return brief_items;
	}

	public static int searchPages(SearchPageBean pagebean) {
		int total_pages = 0;
		Object[] para = new Object[] { "%" + pagebean.getSearch_title() + "%",
				"%" + pagebean.getSearch_category_id() + "%", pagebean.getLowPrice(), pagebean.getHighPrice() };
		try {
			Connection conn = JDBCPool.getInstance().getConnection();
			String sql_str = "select count(item_id) from items, categories"
					+ " where items.category_id = categories.category_id and "
					+ "title like ? and items.category_id like ? and current_price > ? and current_price < ?;";
			PreparedStatement stmn = conn.prepareStatement(sql_str);
			stmn.setObject(1, para[0]);
			stmn.setObject(2, para[1]);
			stmn.setObject(3, para[2]);
			stmn.setObject(4, para[3]);

			// System.out.println(stmn.toString());

			ResultSet rs = stmn.executeQuery();
			if (rs.next()) {
				total_pages = rs.getInt(1);
				// System.out.println(total_pages);
			}
			rs.close();
			stmn.close();
			JDBCPool.getInstance().release(conn);
		} catch (SQLException e) {
			System.out.println("JDBC Error:\t" + e.getMessage() + "\nError Code:\t" + e.getErrorCode());
		}
		if (total_pages % pagebean.getRowsPerPage() != 0)
			total_pages /= pagebean.getRowsPerPage() + 1;
		else
			total_pages /= pagebean.getRowsPerPage();

		if (total_pages == 0)
			total_pages = 1;
		// System.out.println(total_pages);
		return total_pages;
	}
}
