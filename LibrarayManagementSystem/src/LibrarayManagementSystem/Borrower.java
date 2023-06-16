package LibrarayManagementSystem;

public class Borrower {
/*Borrower Class:
This class would represent the Borrower entity and contain attributes like name, contact details, membership ID, and borrowing history. It would include methods for adding a new borrower, updating borrower details, and removing borrowers.*/
	public String name;
	public int contactNumber;
	public int id;
	public int borrowingHistory;
	
	public Borrower(String name,int contactNumber,int id, int borrowingHistory){
		this.name=name;
		this.contactNumber=contactNumber;
		this.id=id;
		this.borrowingHistory=borrowingHistory;
	}
}
