package miniEbay.object;

import org.json.JSONObject;

public class ItemComment {
	private String customer_id;
	private String post_date;
	private String comment;

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("customer_id", this.customer_id);
		jsonStr.put("post_date", this.post_date);
		jsonStr.put("comment", this.comment);
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

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
