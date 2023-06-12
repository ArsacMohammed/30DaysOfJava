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
	}

	private static void doAddBook() {
		// TODO Auto-generated method stub
		System.out.println("enter the book details in order as title,authorname,ISBN,Quantity");
		Book book1 =new Book (in.next(),in.next(),in.nextInt() ,in.nextInt());
		books.add(book1);
		
	}
}
