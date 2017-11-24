/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.util.List;
import ohtu.domain.Book;


/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public interface BookDao {
    List<Book> listAll();
    List<Book> findByCreator(String creator);
    List<Book> findByTitle(String title);
    List<Book> findByDescription(String description);
    Book findByISBN(String ISBN);
    boolean containsTitleAndCreator(String creator, String title);
    Book findByTitleAndCreator(String creator, String title);
    void add(Book book);
    
}
