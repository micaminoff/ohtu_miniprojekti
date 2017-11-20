/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.util.List;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;

/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public interface BookDao {
    List<Suggestable> listAll();
    Suggestable findByCreator(String creator);
    Suggestable findByTitle(String title);
    Suggestable findByDescription(String description);
    void add(Book book);
    
}
