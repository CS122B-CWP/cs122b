package fablix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

public class Genres_Info_DAO {
	public void insert(HashMap<Movies,HashSet<Genres>> genreInMovie) throws SQLException{
        connection.setAutoCommit(false);
		String sql = "insert into genres_in_movies (genre_id, movie_id) values(?,?);";
		PreparedStatement ps = connection.prepareStatement(sql);
		if (genreInMovie.isEmpty() == false){
			for (Movies m : genreInMovie.keySet()){
				int m_id = movieDAO.getId(m.getTitle());
				for (Genres g : genreInMovie.get(m)){
					int g_id = genreDAO.getId(g.getName());
					
					if (m_id != -1 && g_id != -1){
						ps.setInt(1, g_id);
						ps.setInt(2, m_id);
						ps.executeUpdate();
					}
				}
			}
		}
		connection.commit();
		ps.close();	
	}
	private Connection connection = JDBCConnection.connectStart();
	private Movies_DAO movieDAO = new Movies_DAO(); 
	private Genres_DAO genreDAO = new Genres_DAO();
	
	
}
