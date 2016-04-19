package project2.object;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Movie {
	private int id = -1;
	private String title;
	private int year;
	private String dirctor;
	private String banner_url;
	private String trailer;
	private String genre;
	private List<MovieStar> stars;
	private double price;

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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<MovieStar> getStars() {
		return stars;
	}

	public void setStars(List<MovieStar> stars) {
		this.stars = stars;
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
		JSONArray star_array = new JSONArray();
		if (this.stars != null) {
			for (MovieStar star : stars)
				star_array.put(star.toJson());
		}
		jsonStr.put("stars", star_array);
		jsonStr.put("price", this.price);
		return jsonStr;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
