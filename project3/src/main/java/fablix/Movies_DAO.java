package fablix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


public class Movies_DAO {
	
	private Connection connection = JDBCConnection.connectStart();	
    
    public boolean insert(HashSet<Movies> movieList) throws SQLException{
    	boolean flag = false;
    	PreparedStatement ps = null;
    	connection.setAutoCommit(false);
    	if (movieList!=null && !movieList.isEmpty()){
        	for (Movies m: movieList){
        		flag = true;
        		String sql = "replace into movies (title, year, director, banner_url, trailer_url) values(?, ?, ?, ?, ?);";

        		ps = connection.prepareStatement(sql);
        		
        		if (m.getYear()== -1) continue;
        		ps.setString(1, m.getTitle());
        		
				ps.setInt(2, m.getYear());
				ps.setString(3, m.getDirector());
				ps.setString(4, "");
				ps.setString(5, "");
				ps.executeUpdate();
				
        	}
    	
    	}else{
    		flag = false;
    	}
    	connection.commit();
		ps.close();
		
		return flag;
    }
    
    public int getId(String title) throws SQLException{
    	//Movies m = new Movies();
    	connection.setAutoCommit(false);
    	int id = -1;
    	String sql = "select id from movies where title = ?";
    	PreparedStatement ps = connection.prepareStatement(sql);
    	ps.setString(1, title);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			id = rs.getInt(1);
		}
		
		connection.commit();
		ps.close();
		rs.close();
    	return id;
    }
}





