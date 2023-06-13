package LibrarayManagementSystem;
import java.util.*;
public class Book {
	String title;
	String authorName;
	long ISBN ;
	int quantity;
	public Book(String title,String authorName,long ISBN ,int quantity) {
		this.title=title;
		this.authorName=authorName;
		this.ISBN=ISBN;
		this.quantity=quantity;
	}
	public String  getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(title,authorName,ISBN,quantity);
	
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (ISBN != other.ISBN)
			return false;
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
			return false;
		if (quantity != other.quantity)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}
