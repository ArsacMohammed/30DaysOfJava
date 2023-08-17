package model;
import java.util.*;
public class Book {
	public String title;
	public String authorName;
	public long ISBN ;
	public int quantity;
	public  UUID uuid;
	int id;
	String status;
	public Book(String title,String authorName,long ISBN ,int quantity) {
		this.title=title;
		this.authorName=authorName;
		this.ISBN=ISBN;
		this.quantity=quantity;
		this.id =id;
	}
	public String  getTitle() {
		return title;
	}
	public String getAuthor() {
		return authorName;
	}
	public long getISBN() {
		return ISBN;
	}
	public int getQuantity() {
		return quantity;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    
    public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id=id;
    }
    
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status=status;
	}
	public String getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

}







	
	


