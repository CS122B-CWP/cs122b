package fablix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


public class Genres_DAO {
	private Connection connection = JDBCConnection.connectStart();
	public boolean insert(HashSet<Genres> genresList) throws SQLException{

    	String str = "insert into genres (name) select * from (select ?) as tmp "
			     + "where not exists(select name from genres where name = ?) limit 1" ;
    	boolean flag = false;
    	PreparedStatement ps = null;
    	connection.setAutoCommit(false);
    	if (genresList!=null && !genresList.isEmpty()){
        	for (Genres g: genresList){
        		ps = connection.prepareStatement(str);
        		ps.setString(1, g.getName());
        		ps.setString(2, g.getName());
        	}
        	
        	flag = true;
    	}else{
    		flag = false;
    	}
    	connection.commit();
		ps.close();
		
		return flag;
	}
    public int getId(String name) throws SQLException{
    	connection.setAutoCommit(false);
    	int id = -1;
    	String sql = "select id from genres where name = ?";
    	PreparedStatement ps = connection.prepareStatement(sql);
    	ps.setString(1, name);
		ResultSet result = ps.executeQuery();
		
		while(result.next() == true){
			id = result.getInt(1);
		}
		connection.commit();
		ps.close();
		result.close();
    	return id;
    }

}







