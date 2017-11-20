/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import java.util.ArrayList;
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
    
    public boolean addBook(String creator, String title, String description, String ISBN) {
        if (bookDao.findByTitle(title) != null) {
            return false;
        }
        bookDao.add(new Book(creator, title, description, ISBN));
        
        return true;
    }
    
    public List<Suggestable> listAll() {
        List<Suggestable> teokset = new ArrayList<>();
        List<Book> books = this.bookDao.listAll();
        teokset.addAll(books);
        return teokset;
    }
    
    public List<Book> findBookByCreator(String creator) {
        return bookDao.findByCreator(creator);
    }
    public Suggestable findBookByISBN(String ISBN) {
        return bookDao.findByISBN(ISBN);
    }
    public List<Book> findBookByDescription(String description) {
        return bookDao.findByDescription(description);
    }
    public Book findBookByTitle(String title) {
        return bookDao.findByTitle(title);
    }
}
