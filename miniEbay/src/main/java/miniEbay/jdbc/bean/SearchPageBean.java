package miniEbay.jdbc.bean;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import miniEbay.jdbc.dao.MovieStarsDAO;
import miniEbay.object.Movie;

public class SearchPageBean {
	private int curPage = 1;
	private int maxPage;
	private int rowsPerPage = 20;
	private String title = "";
	private String type;
	private int syear = 1800;
	private int eyear = 3000;
	private String director = "";
	private String fname = "";
	private String lname = "";

	private List<Movie> movies;

	public SearchPageBean() {
	}

	public SearchPageBean(int curPage, int perPage) {
		this.curPage = curPage;
		this.rowsPerPage = perPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSyear() {
		return syear;
	}

	public void setSyear(int syear) {
		this.syear = syear;
	}

	public int getEyear() {
		return eyear;
	}

	public void setEyear(int eyear) {
		this.eyear = eyear;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
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

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		JSONObject jsonStr = new JSONObject();
		jsonStr.put("curPage", this.curPage);
		jsonStr.put("maxPage", this.maxPage);
		jsonStr.put("rowsPerPage", this.rowsPerPage);
		jsonStr.put("title", this.title);
		jsonStr.put("type", this.type);
		jsonStr.put("syear", this.syear);
		jsonStr.put("eyear", this.eyear);
		jsonStr.put("director", this.director);
		jsonStr.put("fname", this.fname);
		jsonStr.put("lname", this.lname);
		JSONArray mv_array = new JSONArray();
		if (movies != null)
			for (Movie mv : movies) {
				if (mv.getId() != -1)
					mv.setStars(MovieStarsDAO.movie_stars(mv.getId()));
				mv_array.put(mv.toJson());
			}
		jsonStr.put("movies", mv_array);
		return jsonStr.toString();
	}
}
