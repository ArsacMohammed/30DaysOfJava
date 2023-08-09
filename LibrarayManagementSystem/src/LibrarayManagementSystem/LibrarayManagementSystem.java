package LibrarayManagementSystem;
import java.util.ArrayList;
import java.util.*;
public class LibrarayManagementSystem {
//  getting input for a book and storing it inside an arraylist.
	
	static Scanner in =new Scanner(System.in);
	static ArrayList<Book> books =new ArrayList<Book>();
	static ArrayList<Borrower> borrowers =new ArrayList<Borrower>();
	static boolean choice =false;
	
	public static void main(String[] args) {
		Book book1 =new Book("succes","arsac",1234,1);		
		Book book2 =new Book("secondsucces","sharjun",4321,1);
		Book book3 =new Book("thirdsucces","marzjuk",5678,1);
		books.add(book1);
		books.add(book2);
		books.add(book3);	
		menu();
		
	}	
	
	public static void menu() {
        System.out.println("1. Add a Book");
        System.out.println("2. Remove a Book");
        System.out.println("3. Borrow a Book");
        System.out.println("4. Update a Book");
        System.out.println("5. Search By Author");
        System.out.println("6. Print all the Books");
        System.out.println("7. Print all the Books details");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        int option = in.nextInt();
        in.nextLine(); // Consume newline

        switch (option) {
            case 1:
                doAddBook();
                break;
            case 2:
                doRemoveBook();
                break;
            case 3:
                doBookToBorrow(books,borrowers);
                break;
            case 4:
                doUpdateBook();
                break;
            case 5:
            	String nameOfAuthor =in.nextLine();
                doSearchByAuthor(nameOfAuthor);
                break;
            case 6:
                doPrintBookList();
                break;
            case 7:
                doPrintAllBookDetails();
                break;
            case 0:
                System.out.println("Thanks for using the library!");
                return;
            default:
                System.out.println("Invalid input. Please enter a valid choice.");
        }

        if (wantToContinue()) {
            menu();
        } else {
            System.out.println("Thanks for visiting the library! Come again sometime!");
        }
    }


	private static void visitAgain() {
		// TODO Auto-generated method stub
		System.out.println("Thanks for coming to the library ,Come Again Sometime!!");
		
	}

    private static boolean wantToContinue() {
        System.out.println("Do you want to continue using the library? Enter 'yes' or 'no': ");
        String input = in.nextLine().toLowerCase();

        while (!input.equals("yes") && !input.equals("no")) {
            System.out.println("Invalid input. Please enter 'yes' or 'no': ");
            input = in.nextLine().toLowerCase();
        }

        return input.equals("yes");
    }

	private static void doPrintAllBookDetails() {
		// TODO Auto-generated method stub
		for (Book book :books) {
			System.out.println(book.title+" "+book.authorName+" "+book.ISBN+" "+book.quantity);
		}
		
	}
	
    public static void doPrintBookList() {
        System.out.println("Book List:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("Index " + i + ": " + books.get(i).getTitle());
        }
    }
	private static void doBookToBorrow(ArrayList<Book>books,ArrayList<Borrower>borrowers) {
		// TODO Auto-generated method stub
		
		System.out.println("enter the book details to check for availibity");
		String title =in.next();
		String authorName = in.next();	
		Long ISBN =in.nextLong();
		int quantity =in.nextInt();
		Book checkBook =new Book(title,authorName,ISBN,quantity);
		for (Book book:books) {
			if (book.equals(checkBook)) {
				System.out.println("book found");
				System.out.println("tell your(borrowwer) details");
				String name =in.next();
				int contactDetails =in.nextInt();
				int id =in.nextInt();
				int borrowingHistory = in.nextInt();
				Borrower borrower1 =new Borrower(name,contactDetails,id,borrowingHistory);	
	            System.out.println("Do you want to borrow? (Enter 'true' or 'false')");
	            boolean borrowOption = in.nextBoolean();
	
				if (borrowOption) {
					borrowers.add(borrower1);
					book.quantity=(book.quantity)-1;
					System.out.println("nice borrowed");

				}else {
					System.out.println("some error please retype the book details");
					doBookToBorrow(books,borrowers);
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
	    in.nextLine(); 
	    Book book1 = new Book(title, authorName, ISBN, quantity);
		books.add(book1);	
		System.out.println("Book added sucessfully");
	}
	
	private static void doRemoveBook() {
	    System.out.println("Enter the book details in order as title, author name, ISBN, Quantity:");
	    String title = in.next();
	    String authorName = in.next();
	    long ISBN = in.nextLong();
	    int quantity = in.nextInt();
	    in.nextLine();
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
