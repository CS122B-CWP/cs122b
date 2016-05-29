package fablix;

import java.sql.*;
import java.util.HashSet;
public class Stars_DAO {
	
	int inner_id;
	private Connection connection = JDBCConnection.connectStart();
	
    public boolean insert(HashSet<Stars> starsList) throws SQLException{
    	boolean flag = false;
    	PreparedStatement ps = null;
    	connection.setAutoCommit(false);
    	if (starsList!=null && !starsList.isEmpty()){
        	for (Stars s: starsList){
        		String sql = "replace into stars (first_name, last_name, dob, photo_url) values(?, ?, ?, ?);";
        		ps = connection.prepareStatement(sql);
        		
        		if (s.getF_Name()==null || s.getL_Name()==null || (s.getF_Name().length()==0 && s.getL_Name().length()==0)){
        			continue;
        		}
        		ps.setString(1, s.getF_Name());
				ps.setString(2, s.getL_Name());
				if (s.getDob()!=null){
					ps.setDate(3, new java.sql.Date(s.getDob().getTime()));
				}else{
					ps.setNull(3, java.sql.Types.INTEGER);
				}		
				ps.setString(4, "");			
				ps.executeUpdate();
        	}
        	
        	flag = true;
    	}else flag = false;
    	connection.commit();
		ps.close();
		return flag;
    }
	
    public void Set_Id(int id){
    	this.inner_id = id;
    }
	 public int get_Id(String firstName, String lastName) throws SQLException{
	    	int id = -1;
	    	boolean flag = true;
	    	String sql = "select id from stars where first_name = ? and last_name = ?";
	    	PreparedStatement ps = connection.prepareStatement(sql);
	    	ps.setString(1, firstName);
	    	ps.setString(2, lastName);
	    	ResultSet rs = ps.executeQuery();
			while(rs.next() != false && flag){
				id = rs.getInt(1);
			}
			ps.close();
			rs.close();
	    	return id;
	    }
}

