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
import java.util.List;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;

/**
 *
 * @author paavo
 */
public class SuggestionDao2 implements SuggestionDao {
    private Database database;
    
    public SuggestionDao2(Database database) {
        this.database = database;
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

    @Override
    public List<Suggestion> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Suggestion> findByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Suggestion suggestion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Suggestion findSuggestionById(int id) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vinkki WHERE id = ?");
        stmt.setObject(1, id);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        
        
        String type = rs.getString("type");
        
        return null;
    }
    
}
