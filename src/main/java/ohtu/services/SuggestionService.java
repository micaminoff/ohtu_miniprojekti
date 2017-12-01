/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import java.sql.SQLException;
import java.util.List;
import ohtu.data_access.BlogDao;
import ohtu.data_access.BookDao;
import ohtu.data_access.PodcastDao;
import ohtu.data_access.SuggestionDao;
import ohtu.data_access.VideoDao;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestion;
import ohtu.domain.Type;
import ohtu.domain.Video;

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
    
    public List<Suggestion> listAllSuggestions() throws SQLException {
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
    public List<Book> findBookByTitle(String title) throws SQLException {
        return bookDao.findByTitle(title);
    }
    public Book findBookByTitleAndCreator(String title, String creator) {
        return bookDao.findByTitleAndCreator(title, creator);
    }
    
    public Blog findBlogByURL(String url) {
        return blogDao.findByUrl(url);
    }
    
    public Video findVideoByURL(String url) {
        return videoDao.findByUrl(url);
    }
    
    public Podcast findPodcastByURL(String url) {
        return podcastDao.findByUrl(url);
    }
    
    public List<Suggestion> findByTitle(String title) {
        return suggestionDao.findByTitle(title);
    }
    
    public void addBook(Book b) {
        bookDao.add(b);
    }
    public void addBlog(Blog b) {
        blogDao.add(b);
    }
    public void addVideo(Video v) {
        videoDao.add(v);
    }
    public void addPodcast(Podcast p) {
        podcastDao.add(p);
    }
    
    public boolean addSuggestion(Book book) throws SQLException {
        if (book != null) {
            suggestionDao.add(new Suggestion(book));
            return true;
        }
        return false;
    }
    public boolean addSuggestion(Blog blog) throws SQLException {
       if (blog != null) {
           suggestionDao.add(new Suggestion(blog));
           return true;
       }
       return false;
    }
    public boolean addSuggestion(Video video) throws SQLException {
        if (video != null) {
            suggestionDao.add(new Suggestion(video));
            return true;
        }
        return false;
    }
    public boolean addSuggestion(Podcast podcast) throws SQLException {
        if (podcast != null) {
            suggestionDao.add(new Suggestion(podcast));
            return true;
        }
        return false;
    }
    
    public boolean findSuggestionBy(String title) {
        return false;
    }
    
    public void removeSuggestion(Suggestion s) {
        suggestionDao.remove(s);

        if (!suggestionDao.containsSuggestionForSuggestable(s.getSuggestable())) {
            Type t = s .getType();
            switch (t) {
                case BOOK:
                    bookDao.remove((Book) s.getSuggestable());
                    break;
                case BLOG:
                    blogDao.remove((Blog) s.getSuggestable());
                    break;
                case PODCAST:
                    podcastDao.remove((Podcast) s.getSuggestable());
                    break;
                case VIDEO:
                    videoDao.remove((Video) s.getSuggestable());
                    break;
            }
        }
        
    }
    
}
