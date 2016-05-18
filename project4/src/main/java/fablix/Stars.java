package fablix;

import java.util.Date;

public class Stars {
	private int id;
	private String first_name;
	private String last_name;
	private Date dob;
	private String photo_url; 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getF_Name() {
		return first_name;
	}
	public void setF_Name(String first_name) {
		this.first_name = first_name;
	}
	public String getL_Name() {
		return last_name;
	}
	public void setL_Name(String last_name) {
		this.last_name = last_name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (this.getF_Name()+this.getL_Name()).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj){
			return true;
		}
		
		if(obj instanceof Stars){
			Stars s = (Stars)obj;
			if( this.getF_Name().equals(s.getF_Name()) && this.getL_Name().equals(s.getL_Name()) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
