package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Book;
import dao.DatabaseConnection;

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
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Similarly, implement methods for update, remove, and retrieve book information
}
