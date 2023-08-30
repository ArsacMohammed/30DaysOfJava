package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import dao.BookDAO;
import dao.DatabaseConnection;
import model.Book;
import model.Borrower;
import model.Role;
import model.User;
public class LibrarayManagementSystem {
	
	static Scanner in =new Scanner(System.in);
	static boolean choice =false;
	static User currentUser =null;
	
	public static void main(String[] args) {
	    boolean exitMenu = false;
	    if (!isAdminExists()) {
	        User defaultAdmin = new User("admin", "admin", Role.Librarian);
	        BookDAO.addUser(defaultAdmin);
	        System.out.println("Default Admin added !");
	    } else {
	        System.out.println("Welcome to Library Management System!!");
	    }

	    do {
	        menu();
	        System.out.println("Enter 1 to continue or 2 to exit");
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
			System.out.println(" successfully added ");
		}else {
			System.out.println("Something went wwrong ! please try it again!");
		}
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
	    
	    System.out.println("0. Exit");
	    System.out.print("Enter your choice: ");
	    int option = in.nextInt();
	    in.nextLine(); 

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
	    System.out.println("2. Search book by Author"); 
	    System.out.println("3. Search book by title"); 
        System.out.println("4. Search book by ISBN ");
        System.out.println("5. Print all the Books");
        System.out.println("6. Print all the Books details");
        System.out.println("7. Return a book");
        System.out.println("8. Pay fine .");
        System.out.println("9. Restart the borrower Menu.");
        System.out.println("10. Return to the Login Page");
	    System.out.println("0. Exit");
	    System.out.print("Enter your choice: ");
	    int option = in.nextInt();
	    in.nextLine();

	    switch (option) {
	        case 1:
	            doBookToBorrow();
	            break;
	        case 2:
                doSearchBookByAuthor();
                break;
	        case 3:
                doSearchBookByTitle();
                break;
	        case 4:
                doSearchBookByISBN();
                break;
            case 5:
                doPrintBooksTitle();
                break;
            case 6:
                doPrintAllBookDetails();
                break;
            case 7:
                doReturnBook();
                break;
            case 8:
                doPayFine();
                break;
            case 9:
            	System.out.println("Restarting the Borrower Menu....");
            	borrowerMenu();
            	break;
            case 10:
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

	    double fineAmount = BookDAO.getFineAmount(borrower.getUuid()); 

	    if (fineAmount > 0) {
	        System.out.println("Fine amount to be paid: $" + fineAmount);

	        double paymentAmount = 0.0; 
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter payment amount: ");
	        paymentAmount = scanner.nextDouble();

	        if (paymentAmount >= fineAmount) {
	            boolean paymentSuccess = BookDAO.payFine(borrower, fineAmount);

	            if (paymentSuccess) {
	                System.out.println("Fine paid successfully.");
	            } else {
	                System.out.println("Failed to pay fine.");
	            }
	        } else {
	            System.out.println("Payment amount is less than the fine amount.");
	        }

	        scanner.close(); 
	    } else {
	        System.out.println("No fine to be paid.");
	    }
	}




	private static void doReturnBook() {
	    System.out.println("To return a book:");
	    System.out.println("Enter the borrower UUID: ");
	    UUID borrowerUuid = UUID.fromString(in.nextLine());

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
	                Role role = Role.valueOf(storedRole); 
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
	        }else if (!isNumeric(contactNumber)) {
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


	private static void doSearchBookByTitle() {
	    System.out.println("Enter the title to search for:");
	    String title = in.nextLine();
	    List<Book> books = BookDAO.searchByTitle(title);
	    displayBooks(books);
	}

	private static void doSearchBookByAuthor() {
	    System.out.println("Enter the author to search for:");
	    String author = in.nextLine();
	    
	    if (!isAlphabet(author)) {
	        System.out.println("Invalid input. Author name should contain only alphabetic characters.");
	        return;
	    }
	    
	    List<Book> books = BookDAO.searchByAuthor(author);
	    displayBooks(books);
	}

	private static void doSearchBookByISBN() {
	    System.out.println("Enter the ISBN to search for:");
	    
	    long isbn = 0;
	    boolean validInput = false;

	    while (!validInput) {
	        try {
	            isbn = Long.parseLong(in.nextLine());
	            in.nextLine();
	            validInput = true;
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid ISBN (a number):");
	        }
	    }
	    
	    List<Book> books = BookDAO.searchByISBN(isbn);
	    displayBooks(books);
	}


	private static void displayBooks(List<Book> books) {
	    if (books.isEmpty()) {
	        System.out.println("No books found.");
	    } else {
	        for (Book book : books) {
	            System.out.println(book);
	        }
	    }
	}



	private static void doUpdateBook() {
	    System.out.println("Enter the new details for the book updation:");
	    Book bookToUpdate = getBookInfo();
	    
	    
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
	    		in.nextLine();
	    		isvalid =true;
	    	}catch(InputMismatchException e){
	    		System.out.println("Invalid ISBN ! provide valid input");
	    		in.next(); 
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


	private static User getUserInfo() {
	
	    System.out.println("Enter the username");
	    String username = "";
	    while (username.isEmpty() || !isAlphabet(username)) {
	        username = in.nextLine();
	        if (username.isEmpty()) {
	            System.out.println("Book username cannot be empty.");
	        } else if (!isAlphabet(username)) {
	            System.out.println("Book username must contain only alphabet");
	        }
	    }
	    System.out.println("Enter the password");
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
	    		}else {
	    		isValidOption=true;
	    		}
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
