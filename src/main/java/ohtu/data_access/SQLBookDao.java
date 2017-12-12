package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ohtu.domain.Book;

public class SQLBookDao implements InterfaceBookDao {

    private Database database;

    public SQLBookDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Book> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> findByCreator(String creator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public HashMap<String, Book> findByAll(String arg) {
        HashMap<String, Book> books = new HashMap();
        try {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book WHERE isbn LIKE ? OR title LIKE ? OR creator LIKE ? OR description LIKE ?");
        statement.setObject(1, "%" + arg + "%");
        statement.setObject(2, "%" + arg + "%");
        statement.setObject(3, "%" + arg + "%");
        statement.setObject(4, "%" + arg + "%");

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            String isbn = rs.getString("isbn");
            String title = rs.getString("title");
            String creator = rs.getString("creator");
            String description = rs.getString("description");

            books.put(isbn, new Book(title, creator, description, isbn));
        }

        rs.close();
        statement.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> list = new ArrayList<>();

        try {
        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Book WHERE title LIKE ?");
        stmt.setObject(1, "%" + title + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            String author = rs.getString("creator");
            String title1 = rs.getString("title");
            String description = rs.getString("description");
            String ISBN = rs.getString("isbn");

            list.add(new Book(title1, author, description, ISBN));
        }

        rs.close();
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }

    @Override
    public List<Book> findByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book findByISBN(String ISBN) {
        try {
        Connection connection = database.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Book WHERE isbn = ?");
        stmt.setObject(1, ISBN);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        String author = rs.getString("creator");
        String title = rs.getString("title");
        String description = rs.getString("description");

        rs.close();
        stmt.close();
        connection.close();
        
        return new Book(title, author, description, ISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean containsTitleAndCreator(String title, String creator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book findByTitleAndCreator(String title, String creator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void add(Book book) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Book (isbn, title, creator, description) VALUES (?, ?, ?, ?)");

            stmt.setString(1, book.getISBN());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getCreator());
            stmt.setString(4, book.getDescription());

            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Book book) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Book WHERE isbn = ?");
            stmt.setString(1, book.getISBN());
            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE Book SET title = ?, creator = ?, description = ? WHERE isbn = ?");

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getCreator());
            stmt.setString(3, book.getDescription());
            stmt.setString(4, book.getISBN());

            stmt.executeUpdate();

            stmt.close();
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
