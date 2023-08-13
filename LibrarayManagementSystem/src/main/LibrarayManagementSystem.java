package main;

import java.util.*;
import dao.DatabaseConnection;
import model.Book;
import model.Borrower;
import model.User;
import model.Role;
import dao.BookDAO;
public class LibrarayManagementSystem {
	
	static Scanner in =new Scanner(System.in);
	static ArrayList<Book> books =new ArrayList<Book>();
	static ArrayList<Borrower> borrowers =new ArrayList<Borrower>();
	static boolean choice =false;
	static User currentUser =null;
	static List<User>users =new ArrayList<>();
	
	
	public static void main(String[] args) {
		initialiseUser();
		initialiseBooks();
		menu();
		
	}	
	public static void initialiseUser() {
		users.add(new User("admin","admin",Role.Librarian));
		users.add(new User("user","user",Role.Borrower));
	}
	public static void initialiseBooks() {
		books.add(new Book("succes","arsac",1234,1));		
		books.add(new Book("secondsucces","sharjun",4321,1));
		books.add(new Book("thirdsucces","marzjuk",5678,1));
	}
	
	public static void menu() {
	    authenticate();

	    if (currentUser != null) {
	        System.out.println("Welcome, " + currentUser.getUserName() + "!");
	        
	        if (currentUser.getRole() == Role.Librarian) {
	            librarianMenu(); 
	        } else if (currentUser.getRole() == Role.Borrower) {
	            borrowerMenu(); 
	        }
	    } else {
	        System.out.println("Authentication failed. Exiting...");
	    }
	}
	
	public static void librarianMenu() {
	    System.out.println("Librarian Menu:");
	    System.out.println("1. Add a Book");
	    System.out.println("2. Remove a Book");
	    System.out.println("3. Update a Book");
	    System.out.println("4. View all the Books");
	    System.out.println("5. View complete Books details");
	    // other librarian menu options
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
                doUpdateBook();
                break;
	        case 4:
	        	doPrintBookList();
	        	break;
	        case 5:
	        	doPrintAllBookDetails();
	        case 0:
	            System.out.println("Thanks for using the library!");
	            return;
	        default:
	            System.out.println("Invalid input. Please enter a valid choice.");
	    }

	    if (wantToContinue()) {
	        librarianMenu();
	    } else {
	        System.out.println("Thanks for visiting the library! Come again sometime!");
	    }
	}
	
	public static void borrowerMenu() {
	    System.out.println("Borrower Menu:");
	    System.out.println("1. Borrow a Book");      
        System.out.println("2. Search for a book ");
        System.out.println("3. Print all the Books");
        System.out.println("4. Print all the Books details");
        System.out.println("5. Return a book");
        System.out.println("6. Borrowed Book list ");
        System.out.println("7. Pay fine .");
	    System.out.println("0. Exit");
	    System.out.print("Enter your choice: ");
	    int option = in.nextInt();
	    in.nextLine();

	    switch (option) {
	        case 1:
	            doBookToBorrow(books, borrowers);
	            break;
	        case 2:
            	String nameOfAuthor =in.nextLine();
                doSearchByAuthor(nameOfAuthor);
                break;
            case 3:
                doPrintBookList();
                break;
            case 4:
                doPrintAllBookDetails();
                break;
	        case 0:
	            System.out.println("Thanks for using the library!");
	            return;
	        default:
	            System.out.println("Invalid input. Please enter a valid choice.");
	    }

	    if (wantToContinue()) {
	        borrowerMenu();
	    } else {
	        System.out.println("Thanks for visiting the library! Come again sometime!");
	    }
	}

	public static void authenticate() {
	    System.out.println("Enter your username: ");
	    String username = in.nextLine();
	    System.out.println("Enter your password: ");
	    String password = in.nextLine();

	    for (User user : users) {
	        if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
	            currentUser = user;
	            return; 
	        }
	    }

	    currentUser = null;
	}
	
	
	private static void visitAgain() {
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
		
		System.out.println("To borrow first check for book availability");
		Book checkBook =getBookInfo();
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
	    System.out.println("To update the required book : ");
	    Book bookToUpdate = getBookInfo();
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
		System.out.println("To add the book : ");
	    Book bookToAdd = getBookInfo();
	    boolean isAdded = BookDAO.addBook(bookToAdd);

        if (isAdded) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add book.");
        }
	}

	private static Book getBookInfo() {
		// TODO Auto-generated method stub
		System.out.println("Enter the book details in order as title, author name, ISBN, Quantity:");
	    System.out.println("Enter the title of the book !");
	    String title = "";
	    while (title.isEmpty()) {  	
	    	title=in.nextLine();
	    	if (title.isEmpty()) {
	            System.out.println("Book title cannot be empty.");
	        }
	    }
	    
	    String authorName="";
	    System.out.println("Enter the authorName for the book !");
	    while (authorName.isEmpty() || !isalphabet(authorName)) {  	
	    	authorName = in.nextLine();
	    	if (!isalphabet(authorName)) {
	    		System.out.println("Book authorName must contain only alphabet");
	    	}
	    	else if (authorName.isEmpty()) {
	            System.out.println("Book authorName cannot be empty.");
	        }
	    }
	    
	    System.out.println("Enter Valid ISBN ! Only Numbers..");
	    long ISBN =0;
	    boolean isvalid = false ;
	    while (!isvalid) {
	    	try {
	    		ISBN = in.nextLong();
	    		isvalid =true;
	    	}catch(InputMismatchException e){
	    		System.out.println("Invalid ISBN ! provide valid input");
	    		in.next(); // Consume invalid input to avoid an infinite loop
	    	}
	    }
	    
	    
	    
	    System.out.println("Enter the Quantity of the book..");
	    int quantity=1;
	    boolean isValidQuantity = false;
	    while (!isValidQuantity) {
	    	if (in.hasNextInt()) {
	    		quantity =in.nextInt();
	    		in.nextLine(); 
	    		isValidQuantity=true;
	    	}
	    	else {
	    		System.out.println("Invalid quantity format !! please  provide the valid quantity in numbers only..");
	    		in.next();
	    	}
	    }
	    return new Book (title,authorName,ISBN,quantity);
	}


	public static boolean isalphabet(String input) {
		char [] charArray = input.toCharArray();
		for (char ch:charArray) {
			if (!Character.isLetter(ch)) {
				return false;
			}
		}
		return true;
	}
	
	private static void doRemoveBook() {
	    System.out.println("To remove the book: ");
	    Book bookToRemove = getBookInfo();
	    if (books.contains(bookToRemove)) {
	        books.remove(bookToRemove);
	        System.out.println("Book removed successfully.");
	    } else {
	        System.out.println("Book not found.");
	    }
	   	    
	}
	
}
