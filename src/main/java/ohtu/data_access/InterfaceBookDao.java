package ohtu.data_access;

import java.util.HashMap;
import java.util.List;
import ohtu.domain.Book;

public interface InterfaceBookDao {
    List<Book> listAll();
    List<Book> findByCreator(String creator);
    List<Book> findByTitle(String title);
    List<Book> findByDescription(String description);
    HashMap<String, Book> findByAll(String arg);
    Book findByISBN(String ISBN);
    boolean containsTitleAndCreator(String title, String creator);
    Book findByTitleAndCreator(String title, String creator);
    void add(Book book);
    void remove(Book book);
    void update(Book book);
}
