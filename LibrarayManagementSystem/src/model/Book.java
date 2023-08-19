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
	


	    @Override
	    public String toString() {
	        return "Book{" +
	                "id=" + id +
	                ", uuid=" + uuid +
	                ", title='" + title + '\'' +
	                ", author='" + authorName + '\'' +
	                ", isbn=" + ISBN +
	                ", quantity=" + quantity +
	                ", status='" + status + '\'' +
	                '}';
	    }
	


}







	
	


