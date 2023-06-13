package LibrarayManagementSystem;
import java.util.ArrayList;
import java.util.*;
public class LibrarayManagementSystem {
//  getting input for a book and storing it inside an arraylist.
	
	static Scanner in =new Scanner(System.in);
	static ArrayList<Book> books =new ArrayList<Book>();
	
	public static void main(String[] args) {

		System.out.println("enter the add option  as 1");
		int  addOption= in.nextInt();
		if (addOption == 1) {
			doAddBook();
		}
		System.out.println(books.size());
		System.out.println("do you want to remove the book");
		int removeOption =in.nextInt();
		if (removeOption==1) {
			doRemoveBook();
		}
		
		System.out.println(books.size());
	
	}

	private static void doAddBook() {
	    System.out.println("Enter the book details in order as title, author name, ISBN, Quantity:");
	    String title = in.next();
	    String authorName = in.next();
	    long ISBN = in.nextLong();
	    int quantity = in.nextInt();
	    Book book1 = new Book(title, authorName, ISBN, quantity);
		books.add(book1);		
	}
	
	private static void doRemoveBook() {
	    System.out.println("Enter the book details in order as title, author name, ISBN, Quantity:");
	    String title = in.next();
	    String authorName = in.next();
	    long ISBN = in.nextLong();
	    int quantity = in.nextInt();
	    
	    // Create a temporary book object with the given details for searching
	    Book bookToRemove = new Book(title, authorName, ISBN, quantity);
	    
	    if (books.contains(bookToRemove)) {
	        books.remove(bookToRemove);
	        System.out.println("Book removed successfully.");
	    } else {
	        System.out.println("Book not found.");
	    }
	}

		
		
		
}
