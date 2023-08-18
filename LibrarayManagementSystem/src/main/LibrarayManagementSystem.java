package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.*;
import dao.DatabaseConnection;
import model.Book;
import model.Borrower;
import model.User;
import model.Role;
import java.util.UUID;
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
	private static void doAddUser() {
		System.out.println("Add new users ! Enter the details..");
		User userToAdd = getUserInfo();
		boolean isAdminAdded = BookDAO.addUser(userToAdd);
		if (isAdminAdded) {
			System.out.println("Admin successfully added ");
		}else {
			System.out.println("Something went wwrong ! please try it again!");
		}
	}
	
	private static User getUserInfo() {
		// TODO Auto-generated method stub
		System.out.println("Enter the username");
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
		System.out.println("2.User");
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
	    System.out.println("1. Add a User");
	    System.out.println("2. Add a Book");
	    System.out.println("3. Remove a Book");
	    System.out.println("4. Update a Book");
	    System.out.println("5. View all the Books");
	    System.out.println("6. View complete Books details");
	    System.out.println("7. To restart the Librarian Menu");
	    System.out.println("8. Return to Login page.");
	    // other librarian menu options
	    System.out.println("0. Exit");
	    System.out.print("Enter your choice: ");
	    int option = in.nextInt();
	    in.nextLine(); // Consume newline

	    switch (option) {
	        case 1:
	        	doAddUser();
	            break;
	        case 2:
	        	doAddBook();
	        	break;
	        case 3:
	            doRemoveBook();
	            break;
	        case 4:
                doUpdateBook();
                break;
	        case 5:
	        	doPrintBooksTitle();
	        	break;
	        case 6:
	        	doPrintAllBookDetails();
	        	break;
	        case 7:
	        	System.out.println("Restarting the librarian menu.....");
	        	librarianMenu();
	        	
	        case 8:
	        	System.out.println("Returing to the login credentials");
	        	menu();
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
        System.out.println("6. Pay fine .");
        System.out.println("7. Restart the borrower Menu.");
        System.out.println("8. Return to the Login Page");
	    System.out.println("0. Exit");
	    System.out.print("Enter your choice: ");
	    int option = in.nextInt();
	    in.nextLine();

	    switch (option) {
	        case 1:
	            doBookToBorrow();
	            break;
	        case 2:
            	String nameOfAuthor =in.nextLine();
                doSearchByAuthor(nameOfAuthor);
                break;
            case 3:
                doPrintBooksTitle();
                break;
            case 4:
                doPrintAllBookDetails();
                break;
            case 5:
                doReturnBook();
                break;
            case 6:
                doPayFine();
                break;
            case 7:
            	System.out.println("Restarting the Borrower Menu....");
            	borrowerMenu();
            case 8:
            	System.out.println("Returning to the Login Page");
            	menu();
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



	private static void doPayFine() {
	    System.out.println("To pay fine:");
	    Borrower borrower = getBorrowerInfo();
	    
	    double fineAmount = BookDAO.getFineAmount(borrower.getUuid()); // Get the fine amount from the database

	    if (fineAmount > 0) {
	        System.out.println("Fine amount to be paid: $" + fineAmount);
	        
	        // Here you can implement the logic for payment
	        // For example, ask the user to enter the payment amount and update the fine_payable and fine_paid fields
	        // in the borrower table
	        
	        double paymentAmount = 0.0; // Assume the user enters the payment amount
	        
	        if (paymentAmount >= fineAmount) {
	            // Update the fine_payable and fine_paid fields in the database
	            boolean paymentSuccess = BookDAO.payFine(borrower, fineAmount);
	            
	            if (paymentSuccess) {
	                System.out.println("Fine paid successfully.");
	            } else {
	                System.out.println("Failed to pay fine.");
	            }
	        } else {
	            System.out.println("Payment amount is less than the fine amount.");
	        }
	    } else {
	        System.out.println("No fine to be paid.");
	    }
	}



	private static void doReturnBook() {
	    System.out.println("To return a book:");
	    System.out.println("Enter the borrower UUID: ");
	    UUID borrowerUuid = UUID.fromString(in.nextLine());

	    // Assuming you have a method to get book UUID and borrower ID
	    System.out.println("Enter the book UUID: ");
	    UUID bookUuid = UUID.fromString(in.nextLine());


	    Date returnDate = new Date(System.currentTimeMillis());
	    
	    boolean isReturned = BookDAO.returnBook(bookUuid, borrowerUuid, returnDate);

	    if (isReturned) {
	        System.out.println("Book returned successfully.");
	    } else {
	        System.out.println("Failed to return book.");
	    }
	}

	public static void authenticate(String  input) {
	    System.out.println("Enter your username: ");
	    String username = in.nextLine();
	    System.out.println("Enter your password: ");
	    String password = in.nextLine();
	    String query="";
	    if (input.equals("Borrower")) {
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
		List<Book> books = BookDAO.getAllBooks();
		
		for (Book book :books) {
			System.out.println(book.getId()+" "+book.getUuid()+" "+book.getTitle()+" "+book.getAuthor()+" "+book.getISBN()+" "+book.getQuantity()+" "+book.getStatus());
		}
		
	}
	
    public static void doPrintBooksTitle() {
        List<Book> books = BookDAO.getAllBooks();
        for (Book book:books) {
        	System.out.println(book.getId()+" "+book.getTitle());
        }
    }
    private static void doBookToBorrow() {
        System.out.println("To borrow, first check for book availability:");
        Book checkBook = getBookInfo();
        Borrower borrowerToAdd = getBorrowerInfo();
        boolean isBorrowed = BookDAO.borrowBook(checkBook, borrowerToAdd);

        if (isBorrowed) {
            System.out.println("Successfully borrowed!");
        } else {
            System.out.println("Book not found, could not be borrowed, or quantity is zero.");
        }
    }

    
	private static Borrower getBorrowerInfo() {
		// TODO Auto-generated method stubSystem.out.println("Enter the book details in order as title, author name, ISBN, Quantity:");
	    System.out.println("Enter the borrower name !");
 	    String borrowerName="";
	    System.out.println("Enter the Borrower Name for the book !");
	    while (borrowerName.isEmpty() || !isAlphabet(borrowerName)) {  	
	    	borrowerName = in.nextLine();
	    	if (!isAlphabet(borrowerName)) {
	    		System.out.println("Book borrowername must contain only alphabet");
	    	}
	    	else if (borrowerName.isEmpty()) {
	            System.out.println("Book borrowername cannot be empty.");
	        }
	    }
	    
	    System.out.println("Enter the contact Number of the borrower (10 digits)!");
	    String contactNumber = "";
	    while (contactNumber.isEmpty() || !isNumeric(contactNumber)) {  	
	    	contactNumber=in.nextLine();
	    	if (contactNumber.isEmpty()) {
	            System.out.println("contact number can not be empty.");
	        }else {
	        	System.out.println("contact Number can only be numbers ");
	        }
	    }
	    System.out.println("book returned");
		return new Borrower(borrowerName,contactNumber);
	}


	public static boolean isNumeric(String str) {
		if (str.length()>10) {
			System.out.println("Contact Number must be within 10 digits!");
			return false;
		}
	    for (char c : str.toCharArray()) {
	        if (!Character.isDigit(c)) {
	            return false;
	        }
	    }
	    return true;
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
	    System.out.println("Enter the new details for the book updation:");
	    Book bookToUpdate = getBookInfo();
	    
	    // Retrieve the UUID of the book to update
	    System.out.println("Enter the UUID of the book to update:");
	    String uuidString = in.nextLine();
	    
	    try {
	        UUID uuid = UUID.fromString(uuidString);
	        bookToUpdate.setUuid(uuid);
	    } catch (IllegalArgumentException e) {
	        System.out.println("Invalid UUID format.");
	        return;
	    }
	    
	    boolean isUpdated = BookDAO.updateBook(bookToUpdate);
	    if (isUpdated) {
	        System.out.println("Book updated successfully.");
	    } else {
	        System.out.println("Failed to update book.");
	    }
	}


	private static void doAddBook() { 
        System.out.println("To add the book:");
        Book bookToAdd = getBookInfo();
        
        // Generate a UUID for the new book
        UUID bookId = UUID.randomUUID();
        bookToAdd.setUuid(bookId);

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
	    System.out.println("To remove the book, enter the book UUID: ");
	    UUID bookId = UUID.fromString(in.nextLine());

	    boolean isRemoved = BookDAO.removeBook(bookId);
	    if (isRemoved) {
	        System.out.println("Book removed successfully.");
	    } else {
	        System.out.println("Book not found or could not be removed.");
	    }
	}

	
}
