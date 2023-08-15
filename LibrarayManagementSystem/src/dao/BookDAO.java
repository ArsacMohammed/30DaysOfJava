package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Book;
import dao.DatabaseConnection;
import java.util.*;
import model.User;
import model.Role;

public class BookDAO {
    public static boolean addBook(Book book) {
        String query = "INSERT INTO books (title, author, isbn, quantity) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3, book.getISBN());
            preparedStatement.setInt(4, book.getQuantity());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        book.setId(generatedId);
                    }
                }
                return true; // Return true after setting the generated ID
            } else {
                return false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
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
    public static boolean updateBook(Book book) {
        String query = "UPDATE books SET title=?, author=?, isbn=?, quantity=? WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setLong(3, book.getISBN());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean removeBook(int bookId) {
        String query = "DELETE FROM books WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                long isbn = resultSet.getLong("isbn");
                int quantity = resultSet.getInt("quantity");

                Book book = new Book(title, author, isbn, quantity);
                book.setId(id);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
	

}

