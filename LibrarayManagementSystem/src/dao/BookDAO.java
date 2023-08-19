package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.Book;
import model.Borrower;
import model.Role;
import model.User;

public class BookDAO {
	public static boolean addBook(Book book) {
	    String query = "INSERT INTO books (uuid, title, author, isbn, quantity, status) VALUES (uuid_generate_v4(), ?, ?, ?, ?, ?)";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setString(1, book.getTitle());
	        preparedStatement.setString(2, book.getAuthor());
	        preparedStatement.setLong(3, book.getISBN());
	        preparedStatement.setInt(4, book.getQuantity());
	        preparedStatement.setString(5, "available"); 

	        int rowsAffected = preparedStatement.executeUpdate();
	        return  rowsAffected > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    

       

    
	public static boolean returnBook(UUID bookUuid, UUID borrowerUuid, Date returnDate) {
	    Connection connection = null;
	    PreparedStatement returnStatement = null;

	    try {
	        connection = DatabaseConnection.getConnection();
	        connection.setAutoCommit(false); 

	        
	        String borrowerQuery = "SELECT id, due_date FROM borrower WHERE book_uuid = ? AND uuid = ? AND return_date IS NULL";
	        PreparedStatement borrowerStatement = connection.prepareStatement(borrowerQuery);
	        borrowerStatement.setObject(1, bookUuid, Types.OTHER);
	        borrowerStatement.setObject(2, borrowerUuid, Types.OTHER);
	        ResultSet borrowerResult = borrowerStatement.executeQuery();
	        
	        if (borrowerResult.next()) {
	            int borrowerId = borrowerResult.getInt("id");
	            Date dueDate = borrowerResult.getDate("due_date");

	            // Calculate fines if return date is beyond the due date
	            BigDecimal fineAmount = BigDecimal.ZERO;
	            if (returnDate.after(dueDate)) {
	                long daysLate = ChronoUnit.DAYS.between(dueDate.toLocalDate(), returnDate.toLocalDate());
	                fineAmount = new BigDecimal(daysLate).multiply(new BigDecimal("2"));
	            }

	            // Update the borrower table with return date and fine information
	            String updateBorrowerQuery = "UPDATE borrower SET return_date = ?, fine_payable = ?, fine_paid = ? WHERE id = ?";
	            returnStatement = connection.prepareStatement(updateBorrowerQuery);
	            returnStatement.setDate(1, new java.sql.Date(returnDate.getTime()));
	            returnStatement.setBigDecimal(2, fineAmount);
	            returnStatement.setBoolean(3, false);
	            returnStatement.setInt(4, borrowerId);
	            int rowsAffected = returnStatement.executeUpdate();

	            if (rowsAffected > 0) {
	                
	                String updateBookQuery = "UPDATE books SET status = 'available', quantity = quantity + 1 WHERE uuid = ?";
	                PreparedStatement updateBookStatement = connection.prepareStatement(updateBookQuery);
	                updateBookStatement.setObject(1, bookUuid, Types.OTHER);
	                updateBookStatement.executeUpdate();

	                connection.commit();
	                return true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        if (connection != null) {
	            try {
	                connection.rollback(); 
	            } catch (SQLException rollbackException) {
	                rollbackException.printStackTrace();
	            }
	        }
	    } finally {
	        try {
	            if (returnStatement != null) {
	                returnStatement.close();
	            }
	            if (connection != null) {
	                connection.setAutoCommit(true); 
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}





    public static boolean addUser(User user) {   	
    	String query = "";
    	String checkQuery="";
        if (user.getRole() == Role.Librarian) {
            query = "INSERT INTO admin(username,password, role) VALUES (?, ?, ?)";
            checkQuery = "SELECT COUNT(*) AS count FROM admin WHERE username = ?";
        } else if (user.getRole() == Role.Borrower) {
            query = "INSERT INTO users(username,password, role) VALUES (?, ?, ?)";
            checkQuery = "SELECT COUNT(*) AS count FROM users WHERE username = ?";
        }
        
    	try(Connection connection = DatabaseConnection.getConnection()){
    		PreparedStatement checkStatement =connection.prepareStatement(checkQuery);
    		checkStatement.setString(1, user.getUserName());
            ResultSet checkResult = checkStatement.executeQuery();
            checkResult.next();
            int userCount = checkResult.getInt("count");
            if (userCount > 0) {
                System.out.println("User already exists.");
                return false;
            }
    		PreparedStatement insertStatement = connection.prepareStatement(query);
    		insertStatement.setString(1,user.getUserName());
    		insertStatement.setString(2,user.getPassword());
    		insertStatement.setString(3,user.getRole().toString());
    		int rowsAffected = insertStatement.executeUpdate();
    		return rowsAffected > 0;
    		
    	}catch(SQLException e){
    		e.printStackTrace();
    		System.out.println("Connection Interrupted !!");
    		return false;
    	}
	}
    
    
    
    public static boolean borrowBook(Book book, Borrower borrower) {
        Connection connection = null;
        PreparedStatement borrowStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); 

    
            String checkQuery = "SELECT uuid, quantity FROM books WHERE title = ? AND status = 'available'";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, book.getTitle());
            ResultSet checkResult = checkStatement.executeQuery();

            if (checkResult.next()) {
                UUID bookUuid = checkResult.getObject("uuid", UUID.class);
                int bookQuantity = checkResult.getInt("quantity");

                if (bookQuantity == 1) {
                    String updateStatusQuery = "UPDATE books SET status = 'borrowed' WHERE uuid = ?";
                    PreparedStatement updateStatusStatement = connection.prepareStatement(updateStatusQuery);
                    updateStatusStatement.setObject(1, bookUuid, Types.OTHER);
                    updateStatusStatement.executeUpdate();
                }

                String borrowQuery = "INSERT INTO borrower (uuid, borrower_name, contact_number, book_uuid, issue_date, due_date) VALUES (?, ?, ?, ?, NOW(), NOW() + INTERVAL '10 days')";
                borrowStatement = connection.prepareStatement(borrowQuery, Statement.RETURN_GENERATED_KEYS);
                UUID borrowUuid = UUID.randomUUID();
                borrowStatement.setObject(1, borrowUuid, Types.OTHER);
                borrowStatement.setString(2, borrower.getName());
                borrowStatement.setString(3, borrower.getContactNumber());
                borrowStatement.setObject(4, bookUuid, Types.OTHER);

                int rowsAffected = borrowStatement.executeUpdate();

                if (rowsAffected > 0) {
                    String updateQuantityQuery = "UPDATE books SET quantity = ? WHERE uuid = ?";
                    PreparedStatement updateQuantityStatement = connection.prepareStatement(updateQuantityQuery);
                    updateQuantityStatement.setInt(1, bookQuantity - 1);
                    updateQuantityStatement.setObject(2, bookUuid, Types.OTHER);
                    updateQuantityStatement.executeUpdate();

                    connection.commit(); 
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); 
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        } finally {
            try {
                if (borrowStatement != null) {
                    borrowStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


 

    public static boolean updateBook(Book book) {
        String query = "UPDATE books SET title=?, author=?, isbn=?, quantity=? WHERE uuid=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3, book.getISBN());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setObject(5, book.getUuid(), Types.OTHER);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static boolean removeBook(UUID bookId) {
        String updateQuery = "UPDATE books SET status = 'removed' WHERE uuid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            ) {
        	 PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setObject(1, bookId);
            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE status = 'available' OR (status = 'borrowed' AND quantity = 0) OR status = 'removed' ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                long isbn = resultSet.getLong("isbn");
                int quantity = resultSet.getInt("quantity");
                UUID uuid = (UUID) resultSet.getObject("uuid");
                String status = resultSet.getString("status"); 
                
                Book book = new Book(title, author, isbn, quantity);
                book.setId(id);
                book.setUuid(uuid); 
                book.setStatus(status); 
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public static boolean payFine(Borrower borrower, double amount) {
        String updateQuery = "UPDATE borrower SET fine_paid = true, fine_payable = 0 WHERE uuid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

            updateStatement.setObject(1, borrower.getUuid(), Types.OTHER);
            int rowsAffected = updateStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static double getFineAmount(UUID borrowerUuid) {
        String query = "SELECT fine_payable FROM borrower WHERE uuid = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setObject(1, borrowerUuid, Types.OTHER);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("fine_payable");
            } else {
                return 0.0; 
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0; 
        }
    }
    
    
    public static List<Book> searchByTitle(String title) {
        return searchBooks("title", title);
    }

    public static List<Book> searchByAuthor(String author) {
        return searchBooks("author", author);
    }

    public static List<Book> searchByISBN(long isbn) {
        return searchBooks("isbn", String.valueOf(isbn));
    }

    private static List<Book> searchBooks(String searchType, String searchTerm) {
        List<Book> books = new ArrayList<>();
        String query;

        if (searchType.equals("isbn")) {
            query = "SELECT * FROM books WHERE " + searchType + " = ?::bigint AND status != 'removed'";
        } else {
            query = "SELECT * FROM books WHERE " + searchType + " ILIKE ? AND status != 'removed'";
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (searchType.equals("isbn")) {
                preparedStatement.setString(1, searchTerm);
            } else {
                preparedStatement.setString(1, "%" + searchTerm + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                long isbn = resultSet.getLong("isbn");
                int quantity = resultSet.getInt("quantity");
                UUID uuid = (UUID) resultSet.getObject("uuid");
                String status = resultSet.getString("status");

                Book book = new Book(title, author, isbn, quantity);
                book.setId(id);
                book.setUuid(uuid);
                book.setStatus(status);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }



}

