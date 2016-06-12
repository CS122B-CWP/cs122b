package miniEbay.jdbc.bean;

import org.json.JSONObject;

public class ShoppingCartBean {
	private String customer_id;
	private String item_id;
	private String title;
	private double price;

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("customer_id", this.customer_id);
		jsonStr.put("item_id", this.item_id);
		jsonStr.put("title", this.title);
		jsonStr.put("price", this.price);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
