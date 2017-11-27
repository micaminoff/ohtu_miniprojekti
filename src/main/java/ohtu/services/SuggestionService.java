/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import java.util.List;
import ohtu.data_access.BlogDao;
import ohtu.data_access.BookDao;
import ohtu.data_access.PodcastDao;
import ohtu.data_access.SuggestionDao;
import ohtu.data_access.VideoDao;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Suggestion;

/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public class SuggestionService {
    private SuggestionDao suggestionDao;
    private BookDao bookDao;
    private BlogDao blogDao;
    private PodcastDao podcastDao;
    private VideoDao videoDao;
    
    public SuggestionService(SuggestionDao suggestionDao, BookDao bookDao, BlogDao blogDao, PodcastDao podcastDao, VideoDao videoDao) {
        this.suggestionDao = suggestionDao;
        this.bookDao = bookDao;
        this.blogDao = blogDao;
        this.podcastDao = podcastDao;
        this.videoDao = videoDao;
        
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
    public Book findBookByISBN(String ISBN) {
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
            suggestionDao.add(new Suggestion(book));
            return true;
        }
        return false;
    }
    
    public List<Suggestion> findByTitle(String title) {
        return suggestionDao.findByTitle(title);
    }
    
    public Blog findBlogByURL(String url) {
        return blogDao.findByUrl(url);
    }
    
    public boolean addSuggestionWithBlog(Blog blog) {
       if (blog != null) {
           suggestionDao.add(new Suggestion(blog));
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
