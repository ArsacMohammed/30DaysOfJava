package LibrarayManagementSystem;
import java.util.ArrayList;
import java.util.*;
public class LibrarayManagementSystem {
//  getting input for a book and storing it inside an arraylist.
	
	static Scanner in =new Scanner(System.in);
	static ArrayList<Book> books =new ArrayList<Book>();
	static ArrayList<Borrower> borrowers =new ArrayList<Borrower>();
 
	
	public static void main(String[] args) {
		Book book1 =new Book("succes","arsac",1234,1);		
		Book book2 =new Book("secondsucces","sharjun",4321,1);
		Book book3 =new Book("thirdsucces","marzjuk",5678,1);
		books.add(book1);
		books.add(book2);
		books.add(book3);

		
		bookToBorrow(books,borrowers);
			

		
//		System.out.println("enter the add option  as 1");
//		int  addOption= in.nextInt();
//		if (addOption == 1) {
//			doAddBook();
//		}
//		System.out.println(books.size());
//		System.out.println("do you want to remove the book");
//		int removeOption =in.nextInt();
//		if (removeOption==1) {
//			doRemoveBook();
//		}
//		
//		
//		System.out.println("do you want to update the book if yes enter 1");
//		int updateOption=in.nextInt();
//		if (updateOption==1) {
//			doUpdateBook();
//		}
//		System.out.println(books.size());
//		
//		System.out.println("do you want to search book by author ");
//		int searchOption=in.nextInt();
//		if (searchOption==1) {
//			System.out.println(doSearchByAuthor("arsac"));
//		}
//		
//		
	}	
		
		
		
		
		
		
		
		
	









	private static void bookToBorrow(ArrayList<Book>books,ArrayList<Borrower>borrowers) {
		// TODO Auto-generated method stub
		Borrower borrower1 =new Borrower();
		System.out.println("enter the book details to check for availibity");
		String title =in.next();
		String authorName = in.next();	
		Long ISBN =in.nextLong();
		int quantity =in.nextInt();
		Book checkBook =new Book(title,authorName,ISBN,quantity);
		for (Book book:books) {
			if (book.equals(checkBook)) {
				System.out.println("book found");
				System.out.println("tell me ur name");
				borrower1.name=in.next();		
	            System.out.println("Do you want to borrow? (Enter 'true' or 'false')");
	            boolean borrowOption = in.nextBoolean();
	
				if (borrowOption) {
					borrowers.add(borrower1);
					book.quantity=(book.quantity)-1;
					System.out.println("nice borrowed");

				}else {
					System.out.println("some error please retype the book details");
				}
			}
		}
	}	

	private static int doSearchByAuthor(String authorName) {
		// TODO Auto-generated method stub
		ArrayList<Book> searchResult = new ArrayList<Book>();
		
		for (Book book:books) {
			if(book.getAuthor().equals(authorName)) {
				searchResult.add(book);
			}			
		}
		for (Book book :searchResult) {
			System.out.println(book.getTitle()+" "+book.getAuthor());
		}
		return searchResult.size();
		
		
	}



	private static void doUpdateBook() {
		// TODO Auto-generated method stub
	    System.out.println("Enter the book details for update in order as title, author name, ISBN, Quantity:");
	    String title = in.next();
	    String authorName = in.next();
	    long ISBN = in.nextLong();
	    int quantity = in.nextInt();
	    Book bookToUpdate = new Book(title, authorName, ISBN, quantity);
	    
	    
	    for (int index1=0;index1<books.size();index1++) {
	    	if(books.get(index1).equals(bookToUpdate)) {
	    		books.remove(index1);
	    		System.out.println("enter the details for the book");
	    	    String newtitle = in.next();
	    	    String newauthorName = in.next();
	    	    long newISBN = in.nextLong();
	    	    int newquantity = in.nextInt();
	    		Book updatedBook=new Book(newtitle,newauthorName,newISBN,newquantity);
	    		books.add(updatedBook);

	    	}
	    }	
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
