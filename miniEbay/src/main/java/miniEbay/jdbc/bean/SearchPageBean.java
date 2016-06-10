package miniEbay.jdbc.bean;

import org.json.JSONObject;

public class SearchPageBean extends PageBean {
	private double lowPrice = 0.0;
	private double highPrice = Double.MAX_VALUE;

	public SearchPageBean() {
		super();
	}

	public SearchPageBean(int curPage, int perPage) {
		super(curPage, perPage);
	}

	@Override
	public JSONObject toJson() {
		JSONObject jsonStr = super.toJson();
		jsonStr.put("lowPrice", this.lowPrice);
		jsonStr.put("highPrice", this.highPrice);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}
}
