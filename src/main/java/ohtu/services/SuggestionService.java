/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import java.util.List;
import ohtu.data_access.BookDao;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;

/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public class SuggestionService {
    private BookDao bookDao;
    
    
    public SuggestionService(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    
    public boolean addBook(String creator, String title, String description) {
        if (bookDao.findByTitle(title) != null) {
            return false;
        }
        bookDao.add(new Book(creator, title, description));
        
        return true;
    }
    
//    public BookDao getBookDao() {
//        return bookDao;
//    }
    
    public List<Suggestable> listAll() {
        return this.bookDao.listAll();
    }
    
    
}
