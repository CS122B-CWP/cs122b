package miniEbay.jdbc.bean;

import org.json.JSONObject;

public class ShoppingCartBean {
	private int customer_id;
	private int movie_id;
	private String movie_title;
	private double unit_price;
	private int qty;

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("customer_id", this.customer_id);
		jsonStr.put("movie_id", this.movie_id);
		jsonStr.put("movie_title", this.movie_title);
		jsonStr.put("unit_price", this.unit_price);
		jsonStr.put("qty", this.qty);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
