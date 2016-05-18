package fablix;

public class Genres_Info {
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int genre_id) {
		this.g_id = genre_id;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int movie_id) {
		this.m_id = movie_id;
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int star_id) {
		this.s_id = star_id;
	}
	private int g_id;
	private int m_id;
	private int s_id;
}
