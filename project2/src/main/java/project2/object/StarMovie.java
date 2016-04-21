package project2.object;

import org.json.JSONObject;

public class StarMovie {
	private int id;
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("id", this.id);
		jsonStr.put("title", this.title);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
