package miniEbay.object;

import java.sql.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Star {
	private int id;
	private String fname;
	private String lname;
	private Date dob;
	private String photo_url;
	private List<StarMovie> movies;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public List<StarMovie> getMovies() {
		return movies;
	}

	public void setMovies(List<StarMovie> movies) {
		this.movies = movies;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("id", this.id);
		jsonStr.put("fname", this.fname);
		jsonStr.put("lname", this.lname);
		jsonStr.put("dob", this.dob);
		jsonStr.put("photo_url", this.photo_url);
		JSONArray mv_array = new JSONArray();
		if (this.movies != null) {
			for (StarMovie mv : movies)
				mv_array.put(mv.toJson());
		}
		jsonStr.put("movies", mv_array);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
