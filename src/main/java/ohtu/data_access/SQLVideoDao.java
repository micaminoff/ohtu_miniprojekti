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
import java.util.HashMap;
import java.util.List;
import ohtu.domain.Video;

/**
 *
 * @author hcpaavo
 */
public class SQLVideoDao implements InterfaceVideoDao {
    private Database database;
    
    public SQLVideoDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Video> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Video> findByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Video> findByCreator(String creator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Video> findByDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Video findByUrl(String url) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Video WHERE url = ?");
        stmt.setObject(1, url);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        
        String title = rs.getString("title");
        String creator = rs.getString("creator");
        String description = rs.getString("description");
        
        rs.close();
        stmt.close();
        connection.close();
        
        return new Video(title, creator, description, url);
    }

    @Override
    public void add(Video video) {
          try {
          Connection connection = database.getConnection();
          PreparedStatement stmt = connection.prepareStatement("INSERT INTO Video (url, title, creator, description) VALUES (?, ?, ?, ?)");
          
          stmt.setString(1, video.getUrl());
          stmt.setString(2, video.getTitle());
          stmt.setString(3, video.getCreator());
          stmt.setString(4, video.getDescription());
          
          stmt.executeUpdate();
          
          stmt.close();
          connection.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    @Override
    public void remove(Video video) {
        try {
          Connection connection = database.getConnection();
          PreparedStatement stmt = connection.prepareStatement("DELETE FROM Video WHERE url = ?");
          stmt.setString(1, video.getUrl());
          stmt.executeUpdate();
          
          stmt.close();
          connection.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }
    
    @Override
    public HashMap<String, Video> findByAll(String arg) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Video WHERE url LIKE ? OR title LIKE ? OR creator LIKE ? OR description LIKE ?");
        statement.setObject(1, "%" + arg + "%");
        statement.setObject(2, "%" + arg + "%");
        statement.setObject(3, "%" + arg + "%");
        statement.setObject(4, "%" + arg + "%");

        ResultSet rs = statement.executeQuery();

        HashMap<String, Video> videos = new HashMap();
        while (rs.next()) {
            String url = rs.getString("url");
            String title = rs.getString("title");
            String creator = rs.getString("creator");
            String description = rs.getString("description");

            videos.put(url ,new Video(title, creator, description, url));
        }
        
        rs.close();
        statement.close();
        connection.close();

        return videos;
    }
    
}
