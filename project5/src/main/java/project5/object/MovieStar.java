package project5.object;

import org.json.JSONObject;

public class MovieStar {
	private int id;
	private String fname;
	private String lname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("id", this.id);
		jsonStr.put("fname", this.fname);
		jsonStr.put("lname", this.lname);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
