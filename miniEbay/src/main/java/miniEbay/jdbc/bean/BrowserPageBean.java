package miniEbay.jdbc.bean;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import miniEbay.jdbc.dao.MovieStarsDAO;
import miniEbay.object.Movie;

public class BrowserPageBean {
	private int curPage = 1;
	private int maxPage;
	private int rowsPerPage = 20;
	private String genre = "";
	private String year = "";
	private List<Movie> movies;

	public BrowserPageBean() {
	}

	public BrowserPageBean(int curPage, int perPage) {
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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
		jsonStr.put("genre", this.genre);
		jsonStr.put("year", this.year);
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
