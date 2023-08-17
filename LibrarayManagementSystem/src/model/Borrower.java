package model;

import java.util.UUID;

public class Borrower {
/*Borrower Class:
This class would represent the Borrower entity and contain attributes like name, contact details, membership ID, and borrowing history. It would include methods for adding a new borrower, updating borrower details, and removing borrowers.*/
	public String name;
	public String contactNumber;
	private int id;
	public UUID uuid;
	int issueDate;
	int dueDate;
	int returnDate;
	int finePayable;
	boolean finePaid;
	public Borrower(String name,String contactNumber){
		this.name=name;
		this.contactNumber=contactNumber;
	}
	public String getName() {
		return name;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public UUID getUuid() {
		return uuid;
	}

}
