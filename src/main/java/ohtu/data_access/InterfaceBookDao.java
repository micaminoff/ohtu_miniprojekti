/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Book;


/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public interface InterfaceBookDao {
    List<Book> listAll();
    List<Book> findByCreator(String creator);
    List<Book> findByTitle(String title) throws SQLException;
    List<Book> findByDescription(String description);
    List<Book> findByAll(String arg) throws SQLException;
    Book findByISBN(String ISBN) throws SQLException;
    boolean containsTitleAndCreator(String title, String creator);
    Book findByTitleAndCreator(String title, String creator);
    void add(Book book);
    void remove(Book book);
}
