/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestion;
import ohtu.domain.Type;
import ohtu.domain.Video;
import ohtu.data_access.InterfaceBlogDao;
import ohtu.data_access.InterfaceBookDao;
import ohtu.data_access.InterfaceVideoDao;
import ohtu.data_access.InterfacePodcastDao;
import ohtu.data_access.InterfaceSuggestionDao;

/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public class SuggestionService {
    private InterfaceSuggestionDao suggestionDao;
    private InterfaceBookDao bookDao;
    private InterfaceBlogDao blogDao;
    private InterfacePodcastDao podcastDao;
    private InterfaceVideoDao videoDao;
    
    public SuggestionService(InterfaceSuggestionDao suggestionDao, InterfaceBookDao bookDao, InterfaceBlogDao blogDao, InterfacePodcastDao podcastDao, InterfaceVideoDao videoDao) {
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
    public Book findBookByISBN(String ISBN) throws SQLException {
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
    
    public Blog findBlogByURL(String url) throws SQLException {
        return blogDao.findByUrl(url);
    }
    
    public Video findVideoByURL(String url) throws SQLException {
        return videoDao.findByUrl(url);
    }
    
    public Podcast findPodcastByURL(String url) throws SQLException {
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
    
    
    //Nää vois yhdistää yhdeksi
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
    
    public List<Suggestion> findByAll(String arg) throws SQLException {
        return suggestionDao.findByAll(arg);
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
