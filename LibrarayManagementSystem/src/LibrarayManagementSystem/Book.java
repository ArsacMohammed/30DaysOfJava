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
	
	public void  removeBook() {
		
	}
	public void updateBook() {
		
	}

}
