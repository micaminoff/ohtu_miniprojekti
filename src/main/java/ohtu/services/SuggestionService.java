/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.services;

import java.sql.SQLException;
import java.util.ArrayList;
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
import ohtu.data_access.InterfaceTagDao;
import ohtu.domain.Tag;

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
    
    private InterfaceTagDao tagDao;
    
    public SuggestionService(InterfaceSuggestionDao suggestionDao, InterfaceBookDao bookDao, InterfaceBlogDao blogDao, InterfacePodcastDao podcastDao, InterfaceVideoDao videoDao) {
        this.suggestionDao = suggestionDao;
        this.bookDao = bookDao;
        this.blogDao = blogDao;
        this.podcastDao = podcastDao;
        this.videoDao = videoDao;
    }
    
    public SuggestionService(InterfaceSuggestionDao suggestionDao, InterfaceBookDao bookDao, InterfaceBlogDao blogDao, InterfacePodcastDao podcastDao, InterfaceVideoDao videoDao, InterfaceTagDao tagDao) {
        this.suggestionDao = suggestionDao;
        this.bookDao = bookDao;
        this.blogDao = blogDao;
        this.podcastDao = podcastDao;
        this.videoDao = videoDao;
        this.tagDao = tagDao;
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
    public boolean addSuggestion(Book book, List<Tag> tags) throws SQLException {
        if (book != null) {
            suggestionDao.add(new Suggestion(book, tags));
            return true;
        }
        return false;
    }
    public boolean addSuggestion(Blog blog, List<Tag> tags) throws SQLException {
       if (blog != null) {
           suggestionDao.add(new Suggestion(blog, tags));
           return true;
       }
       return false;
    }
    public boolean addSuggestion(Video video, List<Tag> tags) throws SQLException {
        if (video != null) {
            suggestionDao.add(new Suggestion(video, tags));
            return true;
        }
        return false;
    }
    public boolean addSuggestion(Podcast podcast, List<Tag> tags) throws SQLException {
        if (podcast != null) {
            suggestionDao.add(new Suggestion(podcast, tags));
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
    
    public void editTag(Tag t, String newContent) throws SQLException {
        tagDao.edit(t, newContent);
    }
    
    public void fillWithExampleData() throws SQLException {
        List<Tag> tags = new ArrayList<>();
        Blog blog = new Blog("How to Increase Velocity",
                "David Bernstein", 
                "Increase code quality today to increase your velocity tomorrow.", 
                "https://www.agilealliance.org/how-to-increase-velocity/",
                "Agile Alliance Blog");
        addBlog(blog);
        addSuggestion(blog, tags);
        blog = new Blog("Joulun käyttölahjoja",
                "Johanna", 
                "Johanna kirjoittaa elämästään norjalaisen kalastajan vaimona ja kahden söpöliinin äitinä. Blogissa pohditaan, miten arki voi olla kaunista, esitellään päivän asuja ja ihania sisustusasioita – sekä välillä pohditaan syvällisiä.", 
                "https://www.menaiset.fi/blogit/kalastajan-vaimo/joulun-kayttolahjoja",
                "Kalastajan vaimo");
        addBlog(blog);
        addSuggestion(blog, tags);
        
        Book book = new Book("Clean Code: A Handbook of Agile Software Craftsmanship", 
                "Robert Martin", "Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship", 
                "978-951-98548-9-2");
        addBook(book);
        addSuggestion(book, tags);
        book = new Book("Introduction to Algorithms", 
                "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "The bible of algorithms. Widely used as a coursebook in CS curriculums.", 
                "978-026-20338-4-8");
        addBook(book);
        addSuggestion(book, tags);
        
        
        Podcast podcast = new Podcast("JRE #002 - MMA Show #2", 
                "Joe Rogan" , 
                "Eddie Bravo needs help",
                "http://podcasts.joerogan.net/podcasts/mma-show-2", 
                "The Joe Rogan Experience");
        addPodcast(podcast);
        addSuggestion(podcast, tags);
        
        podcast = new Podcast("#038 Revolution In The Age Of Anger (with Pankaj Mishra)", 
                "Russell Brand" , 
                "Historian and novelist Pankaj Mishra joins me to talk about what he calls the ‘age of anger’ - a global pandemic of rage - and how the pursuit of progress and individualism has created a demoralised world.",
                "https://art19.com/shows/under-the-skin/episodes/ef646cc4-c1dd-486e-ab33-6da8d88a8c13", 
                "Under The Skin");
        addPodcast(podcast);
        addSuggestion(podcast, tags);
        
        Video video = new Video("Turing Machine - Introduction (Part 1)",
                "Neso Academy", 
                "TOC: Introduction to Turing Machine",
                "https://www.youtube.com/watch?v=PvLaPKPzq2I");
        addVideo(video);
        addSuggestion(video, tags);
              
        video = new Video("Mitä tietojenkäsittely on?",
                "Helsingin yliopisto", 
                "Tietojenkäsittelytieteen laitoksen opettajat kertovat mitä tietojenkäsittely on, ja mihin olemme menossa.",
                "https://www.youtube.com/watch?v=q44xFlrKCTE");
        addVideo(video);
        addSuggestion(video, tags);
        
    }
    
}
