package fablix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

public class Stars_Info_DAO {
	private Connection connection = JDBCConnection.connectStart();
	private Stars_DAO sDAO = new Stars_DAO();
	private Movies_DAO mDAO = new Movies_DAO();
	public void insert(HashMap<Movies,HashSet<Stars>> starInMovie) throws SQLException{
		int s_id,m_id;
		connection.setAutoCommit(false);
		String sql = "insert into stars_in_movies (star_id, movie_id) values(?,?);";
		PreparedStatement prepstmt = connection.prepareStatement(sql);
		if (!starInMovie.isEmpty()){
			for (Movies movie : starInMovie.keySet()){
				m_id = mDAO.getId(movie.getTitle());
				for (Stars s : starInMovie.get(movie)){
					s_id = sDAO.get_Id(s.getF_Name(), s.getL_Name());
					if (m_id != -1 && s_id != -1){
						prepstmt.setInt(1, s_id);
						prepstmt.setInt(2, m_id);
						prepstmt.executeUpdate();
					}
				
				}
			}
		}
		connection.commit();
		prepstmt.close();
		
	}
}
