package LibrarayManagementSystem;

public class Author {
/*This class would represent the Author entity and contain attributes like the author's name, biography, etc. It would have methods for adding new authors and retrieving author information.*/
	private String name;
	private String biography;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getBiography() {
		return biography;
	}
}
