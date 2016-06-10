package miniEbay.jdbc.bean;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import miniEbay.object.BriefItem;

public class PageBean {
	private int curPage = 1;
	private int maxPage;
	private int rowsPerPage = 50;
	private String search_title = "";
	private String search_category_id = "";
	private List<BriefItem> brief_items;

	public PageBean() {
	}

	public PageBean(int curPage, int perPage) {
		this.curPage = curPage;
		this.rowsPerPage = perPage;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("curPage", this.curPage);
		jsonStr.put("maxPage", this.maxPage);
		jsonStr.put("rowsPerPage", this.rowsPerPage);
		jsonStr.put("search_title", this.search_title);
		jsonStr.put("search_category_id", this.search_category_id);
		JSONArray item_array = new JSONArray();
		if (brief_items != null)
			for (BriefItem item : brief_items) {
				item_array.put(item.toJson());
			}
		jsonStr.put("brief_items", brief_items);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getSearch_title() {
		return search_title;
	}

	public void setSearch_title(String search_title) {
		this.search_title = search_title;
	}

	public String getSearch_category_id() {
		return search_category_id;
	}

	public void setSearch_category_id(String search_category_id) {
		this.search_category_id = search_category_id;
	}

	public List<BriefItem> getBrief_items() {
		return brief_items;
	}

	public void setBrief_items(List<BriefItem> brief_items) {
		this.brief_items = brief_items;
	}
}
