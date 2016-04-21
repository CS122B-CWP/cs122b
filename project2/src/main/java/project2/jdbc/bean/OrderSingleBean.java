package project2.jdbc.bean;

import org.json.JSONObject;

public class OrderSingleBean {
	private int qty;
	private double unit_price;
	private int movie_id;
	private String title;

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("qty", this.qty);
		jsonStr.put("unit_price", this.unit_price);
		jsonStr.put("movie_id", this.movie_id);
		jsonStr.put("title", this.title);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
