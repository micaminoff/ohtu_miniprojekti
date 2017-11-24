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
    
    public Book addBook(String creator, String title, String description, String ISBN) {
        if (bookDao.containsTitleAndCreator(creator, title)) {
            return bookDao.findByTitleAndCreator(creator, title);
        }
        Book newBook = new Book(creator, title, description, ISBN);
        bookDao.add(newBook);
        return newBook;
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
    public List<Book> findBookByTitle(String title) {
        return bookDao.findByTitle(title);
    }
    
    public boolean addSuggestionWithBook(String creator, String title, String description, String ISBN) {
        Book suggestionBook = addBook(creator, title, description, ISBN);
        if (suggestionBook != null) {
            //suggestionDao.add(suggestionBook);
            return true;
        }
        return false;
    }
}
