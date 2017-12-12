package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import ohtu.domain.Video;

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
    public Video findByUrl(String url) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public HashMap<String, Video> findByAll(String arg) {
        HashMap<String, Video> videos = new HashMap();

        try {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Video WHERE url LIKE ? OR title LIKE ? OR creator LIKE ? OR description LIKE ?");
        statement.setObject(1, "%" + arg + "%");
        statement.setObject(2, "%" + arg + "%");
        statement.setObject(3, "%" + arg + "%");
        statement.setObject(4, "%" + arg + "%");

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            String url = rs.getString("url");
            String title = rs.getString("title");
            String creator = rs.getString("creator");
            String description = rs.getString("description");

            videos.put(url, new Video(title, creator, description, url));
        }

        rs.close();
        statement.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videos;
    }

    @Override
    public void update(Video video) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE Video SET title = ?, creator = ?, description = ? WHERE url = ?");

            stmt.setString(1, video.getTitle());
            stmt.setString(2, video.getCreator());
            stmt.setString(3, video.getDescription());
            stmt.setString(4, video.getUrl());

            stmt.executeUpdate();

            stmt.close();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
