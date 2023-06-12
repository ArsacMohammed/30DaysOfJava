package LibrarayManagementSystem;

public class Transaction {
/*This class would represent the Transaction entity and include attributes such as the book being borrowed, the borrower, issue date, due date, and return date. It would have methods for checking out books, returning books, and managing fines or penalties.*/
	 public Borrower borrower;
	 public Book book ;
	 int issueDate;
	 int dueDate;
	 int returnDate;
}