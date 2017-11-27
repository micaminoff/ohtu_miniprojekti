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

/**
 *
 * @author paavo
 */
public class SuggestionDao2 implements Dao<Suggestable, Integer> {
    private Database database;
    
    public SuggestionDao2(Database database) {
        this.database = database;
    }

    @Override
    public Suggestable findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Vinkki WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        
        //Otetaan talteen vinkin tiedot
        String type = rs.getString("type");
        String comment = rs.getString("comment");
        String tags = rs.getString("tags");
        String relatedCourses = rs.getString("related_courses");
        String esitietoKurssit = rs.getString("esitietokurssit");

        //Nyt kun tyyppi on selvillä, voidaan yhdistää taulut
        if (type.equals("book")) {
            stmt = connection.prepareStatement("SELECT * FROM Vinkki v, Book b WHERE v.id = b.id AND v.id = ?");
            rs = stmt.executeQuery();
            
            //Otetaan talteen kirjan ja vinkin tiedot
            String comment = rs.getString("comment");
            String tags = rs.getString("tags");
            String relatedCourses = rs.getString("related_courses");
            String esitietoKurssit = rs.getString("esitietokurssit");
            
            return new Book()
        }

        rs.close();
        stmt.close();
        connection.close();
        
        return new Suggestion(title, creator, description, ISBN);
    }

    @Override
    public List<Suggestable> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
