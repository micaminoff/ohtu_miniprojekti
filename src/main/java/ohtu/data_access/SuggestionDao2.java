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

//    @Override
//    public Suggestion findOne(Integer key) throws SQLException {
//        Connection connection = database.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vinkki WHERE id = ?");
//        stmt.setObject(1, key);
//
//        ResultSet rs = stmt.executeQuery();
//
//        if (!rs.next()) {
//            return null;
//        }
//        
//        //Otetaan talteen vinkin tiedot
//        String type = rs.getString("type");
//        String comment = rs.getString("comment");
//        String tags = rs.getString("tags");
//        String relatedCourses = rs.getString("related_courses");
//        String esitietoKurssit = rs.getString("esitietokurssit");
//        
//        //Nyt kun tyyppi on selvillä, voidaan yhdistää taulut
//        if (type.equals("book")) {
//            stmt = connection.prepareStatement("SELECT * FROM Vinkki v, Book b WHERE v.id = b.id AND v.id = ?");
//            rs = stmt.executeQuery();
//            
//            //Otetaan talteen kirjan ja vinkin tiedot
//            comment = rs.getString("comment");
//            tags = rs.getString("tags");
//            relatedCourses = rs.getString("related_courses");
//            esitietoKurssit = rs.getString("esitietokurssit");
//            
//        }
//
//        rs.close();
//        stmt.close();
//        connection.close();
//        Suggestion s = new Suggestion(new Book("null","null","null","null"));
//        return s;
//    }
//
//    @Override
//    public List<Suggestion> findAll() throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void delete(Integer key) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    /**
     *
     * @return
     * @throws SQLException
     */

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
            if (type.equals("book")) {
                Book book = bookDao.findByISBN(suggestableId);
//                stmt = connection.prepareStatement("SELECT * FROM Book WHERE id = ?");
//                stmt.setObject(1, id);
//                ResultSet rs2 = stmt.executeQuery();
//
//                String author = rs2.getString("author");
//                String title = rs2.getString("title");
//                String description = rs2.getString("description");
//                String ISBN = rs2.getString("ISBN");
//                
                list.add(new Suggestion(book));
//                
//                rs2.close();
            } //Tähän sit else haaroja
            
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
