package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public static void main(String[] args) {
	    boolean exitMenu = false;
	    if (!isAdminExists()) {
	        User defaultAdmin = new User("admin", "admin", Role.Librarian);
	        BookDAO.addUser(defaultAdmin);
	        System.out.println("Default Admin added !");
	    } else {
	        System.out.println("Admin already exists!!");
	    }

	    do {
	        menu();
	        System.out.println("If you want to continue using the menu, enter 1; otherwise, enter 2");
	        int menuChoice = 0;
	        while (menuChoice != 1 && menuChoice != 2) {
	            menuChoice = in.nextInt();
	            in.nextLine();
	        }

	        if (menuChoice == 1) {
	            System.out.println("Returning to the menu...");
	        } else if (menuChoice == 2) {
	            exitMenu = true;
	            System.out.println("Exiting the menu...");
	        }
	    } while (!exitMenu);
	}

	
	public static boolean isAdminExists() {
	    String adminUsername = "admin";

	    String checkQuery = "SELECT COUNT(*) AS count FROM admin WHERE username = ?";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {

	        checkStatement.setString(1, adminUsername);
	        ResultSet checkResult = checkStatement.executeQuery();
	        checkResult.next();
	        int adminCount = checkResult.getInt("count");

	        return adminCount > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Connection Interrupted !!");
	        return false;
	    }
	}
	private static void doAddAdmin() {
		System.out.println("Enter the Admin credentials");
		User adminToAdd = getUserInfo();
		boolean isAdminAdded = BookDAO.addUser(adminToAdd);
		if (isAdminAdded) {
			System.out.println("Admin successfully added ");
		}else {
			System.out.println("Something went wwrong ! please try it again!");
		}
	}
	
	private static User getUserInfo() {
		// TODO Auto-generated method stub
		System.out.println("Enter the ");
		String username="";
		while(username.isEmpty() || !isAlphabet(username)) {
			username = in.nextLine();
			if (!isAlphabet(username)) {
	    		System.out.println("Book username must contain only alphabet");
	    	}
	    	else if (username.isEmpty()) {
	            System.out.println("Book username cannot be empty.");
	        }
		}
		String password ="";
		while(password.isEmpty()) {
			password = in.nextLine();
			if (username.isEmpty()) {
	            System.out.println("Book password cannot be empty.");
	        }
		}
		
		System.out.println("Are you a User or Admin");
		System.out.println("1.Librarian");
		System.out.println("2.Borrower");
		int option =0;
	    boolean isValidOption = false;
	    while (!isValidOption) {
	    	if (in.hasNextInt()) {
	    		option =in.nextInt();
	    		in.nextLine(); 
	    		if (option != 1 && option !=2) {
	    			isValidOption=false;
	    		}
	    		isValidOption=true;
	    	}
	    	else {
	    		System.out.println("Invalid option!! please  provide the valid option in numbers only..");
	    		in.next();
	    	}
	    }
		if (option==1) {
			return new User(username,password,Role.Librarian);
		}
		return new User(username,password,Role.Borrower);
	}
	


	public static void menu() {
		System.out.println("1.Login as Librarian");
		System.out.println("2.Login as User");
		int option = in.nextInt();
		in.nextLine();
		String loginChoice ="";
		switch(option) {
			case 1:
				loginChoice = "Librarian";
				authenticate(loginChoice);
				break;
			case 2:
				loginChoice="Borrower";
				authenticate(loginChoice);
				break;
			case 0:
	        	visitAgain();
	            return;
			default:
				System.out.println("invalid input !! please try again...");
		}
		
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
	        	break;
	        case 0:
	        	visitAgain();
	            return;
	        default:
	            System.out.println("Invalid input. Please enter a valid choice.");
	    }

	    if (wantToContinue()) {
	        librarianMenu();
	    } else {
	    	visitAgain();
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
	        	visitAgain();
	            return;
	        default:
	            System.out.println("Invalid input. Please enter a valid choice.");
	    }

	    if (wantToContinue()) {
	        borrowerMenu();
	    } else {
	        visitAgain();
	    }
	}

	public static void authenticate(String  input) {
	    System.out.println("Enter your username: ");
	    String username = in.nextLine();
	    System.out.println("Enter your password: ");
	    String password = in.nextLine();
	    String query="";
	    if (input.equals("borrower")) {
	    	query = "SELECT * FROM users WHERE username=? AND password=?";
	    }else if (input.equals("Librarian")){
	    	query = "SELECT * FROM admin WHERE username=? AND password=?";
	    }
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, username);
	        preparedStatement.setString(2, password);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                String storedRole = resultSet.getString("role");
	                Role role = Role.valueOf(storedRole); // Convert the stored role to Enum
	                currentUser = new User(username, password, role);
	                System.out.println("Authentication successful. Welcome, " + currentUser.getUserName() + "!");
	            } else {
	                System.out.println("Authentication failed. Exiting...");
	                currentUser = null;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        currentUser = null;
	    }
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
	    System.out.println("To update the required book .Enter the book Id : ");
	    int bookId = in.nextInt();
	    in.nextLine();
	    System.out.println("Enter the details to update book!");
	    Book bookToUpdate = getBookInfo();
	    bookToUpdate.setId(bookId);
	    boolean isUpdated = BookDAO.updateBook(bookToUpdate);
	    if (isUpdated) {
            System.out.println("Book updated  successfully.");
        } else {
            System.out.println("Failed to update book.");
        }
	}

	private static void doAddBook() { 
		System.out.println("To add the book : ");
	    Book bookToAdd = getBookInfo();
	    boolean isAdded = BookDAO.addBook(bookToAdd);
	    System.out.println(isAdded);
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
	    while (authorName.isEmpty() || !isAlphabet(authorName)) {  	
	    	authorName = in.nextLine();
	    	if (!isAlphabet(authorName)) {
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


	public static boolean isAlphabet(String input) {
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
	    int bookId =  in.nextInt();
	    in.nextLine();
	    boolean isremoved = BookDAO.removeBook(bookId);
	    if (isremoved) {
	        System.out.println("Book removed successfully.");
	    } else {
	        System.out.println("Book not found.");
	    }
	   	    
	}
	
}
