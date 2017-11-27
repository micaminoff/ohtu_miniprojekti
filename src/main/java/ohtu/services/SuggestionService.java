/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import java.util.List;
import ohtu.data_access.BookDao;
import ohtu.data_access.SuggestionDao;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;

/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public class SuggestionService {
    private BookDao bookDao;
    private SuggestionDao suggestionDao;
    
    public SuggestionService(BookDao bookDao, SuggestionDao suggestionDao) {
        this.bookDao = bookDao;
        this.suggestionDao = suggestionDao;
    }
    
    //vanha kirjan lisäys
//    public Book addBook(String title, String creator, String description, String ISBN) {
//        Book newBook = null;
//        if (bookDao.containsTitleAndCreator(title, creator)) {
//            newBook = bookDao.findByTitleAndCreator(title, creator);
//            return newBook;
//        }
//        newBook = new Book(title, creator, description, ISBN);
//        bookDao.add(newBook);
//        return newBook;
//    }
    
    public List<Suggestion> listAll() {
        return suggestionDao.listAll();
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
    
    public Book findBookByTitleAndCreator(String title, String creator) {
        return bookDao.findByTitleAndCreator(title, creator);
    }
    
    public boolean addSuggestionWithBook(Book book) {
        if (book != null) {
            //suggestionDao.add(book);
            return true;
        }
        return false;
    }
    
    //vanha kirjan lisäys
//    public boolean addSuggestionWithBook(String title, String creator, String description, String ISBN) {
//        Book suggestionBook = addBook(title, creator, description, ISBN);
//        if (suggestionBook != null) {
//            //suggestionDao.add(suggestionBook);
//            return true;
//        }
//        return false;
//    }
}
