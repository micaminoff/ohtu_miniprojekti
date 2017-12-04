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
import ohtu.domain.Podcast;

/**
 *
 * @author paavo
 */
public class PodcastDao2 implements PodcastDao {
    private Database database;
    
    public PodcastDao2(Database database) {
        this.database = database;
    }

    @Override
    public List<Podcast> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Podcast> findByTitle(String podcast) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Podcast> findByCreator(String podcast) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Podcast findByUrl(String url) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Podcast WHERE url = ?");
        stmt.setObject(1, url);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        
        String title = rs.getString("title");
        String creator = rs.getString("creator");
        String description = rs.getString("description");
        String podcastName = rs.getString("podcastName");
        
        rs.close();
        stmt.close();
        connection.close();
        
        return new Podcast(title, creator, description, url, podcastName);
    }

    @Override
    public List<Podcast> findByPodcastName(String podcastName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Podcast podcast) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Podcast pocast) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
