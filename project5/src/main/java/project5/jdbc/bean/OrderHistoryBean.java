package project5.jdbc.bean;

import org.json.JSONObject;

public class OrderHistoryBean {
	private int id;
	private String sale_date;
	private double total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSale_date() {
		return sale_date;
	}

	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("id", this.id);
		jsonStr.put("sale_date", this.sale_date);
		jsonStr.put("total", this.total);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
