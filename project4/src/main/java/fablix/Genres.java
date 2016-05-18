package fablix;

public class Genres {
	private int id;
	private String name;
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if (!(obj instanceof Genres)) return false;
		Genres i = (Genres)obj;
		if( i.getName().trim().equals(this.getName().trim())) return true;
			return false;

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

	
}
