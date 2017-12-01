/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;

/**
 *
 * @author paavo
 */
public class SuggestionDao2 implements SuggestionDao {
    private Database database;
    private BookDao bookDao;
    private BlogDao blogDao;
    private PodcastDao podcastDao;
    private VideoDao videoDao;
    
    public SuggestionDao2(Database database, BookDao bookDao, BlogDao blogDao, PodcastDao podcastDao, VideoDao videoDao) {
        this.database = database;
        this.bookDao = bookDao;
        this.blogDao = blogDao;
        this.podcastDao = podcastDao;
        this.videoDao = videoDao;
    }
    @Override
    public List<Suggestion> listAll() throws SQLException {
        List<Suggestion> list = new ArrayList<>();
        
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Suggestion");

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String type = rs.getString("type");
            String suggestableId = rs.getString("suggestable_id");
            //Kommnetit pitää kerätä listalle
            //Tagit pitää kerätä listalle
            //Samoin kurssit listalle
            //bookin osalta listaus PITÄISI TOIMIA jA SAMI ON HOMOSEKSUAALI
            if (type.equals("book")) {
                //Voidaan tehdä Dao-rajapintaan metodi findById(String string)
                //Muuutetaan siis findByISBN() ja findByURL()
                Book book = bookDao.findByISBN(suggestableId);
                //tulevaisuudessa lisätään parametreina myös kommentti olio
                list.add(new Suggestion(book));
            } else if (type.equals("blog")) {
                Blog blog = blogDao.findByUrl(suggestableId);
                list.add(new Suggestion(blog));
            } else if (type.equals("video")) {
                
            }
            
        }
        rs.close();
        stmt.close();
        connection.close();
        
        return list;
    }

    @Override
    public List<Suggestion> findByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Suggestion suggestion) throws SQLException {
        //tyyppi = suggestion.getType()
        //INSERT INTO Vinkki(type) VALUES (?)
        //
        
//        String type = suggestion.getType();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Vinkki(type) VALUES (?)");
//        stmt.setObject(1, type);

        ResultSet rs = stmt.executeQuery();
        
        //id:n selvittäminen bookille
//        stmt = connection.prepareStatement("")
        
        Suggestable suggestable = null;
//        if (type.equals("book")) {
//            Book book = (Book) suggestion.getSuggestable();
//            
//            String title = book.getTitle();
//            String creator = book.getCreator();
//            String description = book.getDescription();
//            String ISBN = book.getISBN();
//        } else if (type.equals("blog")) {
//            suggestable = (Blog) suggestion.getSuggestable();
//        } else {
//            
//        }
//        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  @Override
  public void remove(Suggestion s) {
      
  }
  
  @Override
    public boolean containsSuggestionForSuggestable(Suggestable suggestable) {
        return false;
    }
    
}
