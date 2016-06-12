package miniEbay.object;

import org.json.JSONObject;

public class BriefItem {
	private String item_id;
	private String title;
	private String category_id;
	private String category_name;
	private double current_price;
	private String gallery_url;

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("ItemID", item_id);
		jsonStr.put("Title", title);
		jsonStr.put("CategoryId", category_id);
		jsonStr.put("CategoryName", category_name);
		jsonStr.put("CurrentPrice", current_price);
		jsonStr.put("GalleryURL", gallery_url);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public double getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(double current_price) {
		this.current_price = current_price;
	}

	public String getGallery_url() {
		return gallery_url;
	}

	public void setGallery_url(String gallery_url) {
		this.gallery_url = gallery_url;
	}
}
