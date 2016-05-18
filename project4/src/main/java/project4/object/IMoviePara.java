package project4.object;

import org.json.JSONObject;

public class IMoviePara {
	private int id = -1;
	private String title = "";
	private int year = -1;
	private String dirctor = "";
	private String banner_url = "";
	private String trailer = "";
	private String star_name = "";
	private String genre = "";
	private double price = 0.0;

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirctor() {
		return dirctor;
	}

	public void setDirctor(String dirctor) {
		this.dirctor = dirctor;
	}

	public String getBanner_url() {
		return banner_url;
	}

	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getStar_name() {
		return star_name;
	}

	public void setStar_name(String star_name) {
		this.star_name = star_name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public JSONObject toJson() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("id", this.id);
		jsonStr.put("title", this.title);
		jsonStr.put("genre", this.genre);
		jsonStr.put("dirctor", this.dirctor);
		jsonStr.put("year", this.year);
		jsonStr.put("banner_url", this.banner_url);
		jsonStr.put("trailer", this.trailer);
		jsonStr.put("star_name", star_name);
		jsonStr.put("price", this.price);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
