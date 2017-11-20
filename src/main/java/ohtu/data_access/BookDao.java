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
    Book findByCreator(String creator);
    Book findByTitle(String title);
    Book findByDescription(String description);
    Book findByISBN(String ISBN);
    void add(Book book);
    
}
