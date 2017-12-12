package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import ohtu.domain.Podcast;

public class SQLPodcastDao implements InterfacePodcastDao {

    private Database database;

    public SQLPodcastDao(Database database) {
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
    public HashMap<String, Podcast> findByAll(String arg) {
        HashMap<String, Podcast> podcasts = new HashMap();
        try {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Podcast WHERE url LIKE ? OR title LIKE ? OR podcastName LIKE ? OR creator LIKE ? OR description LIKE ?");
        statement.setObject(1, "%" + arg + "%");
        statement.setObject(2, "%" + arg + "%");
        statement.setObject(3, "%" + arg + "%");
        statement.setObject(4, "%" + arg + "%");
        statement.setObject(5, "%" + arg + "%");

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            String url = rs.getString("url");
            String title = rs.getString("title");
            String podcastName = rs.getString("podcastName");
            String creator = rs.getString("creator");
            String description = rs.getString("description");

            podcasts.put(url, new Podcast(title, creator, description, url, podcastName));
        }

        rs.close();
        statement.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podcasts;
    }

    @Override
    public Podcast findByUrl(String url) {
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Podcast> findByPodcastName(String podcastName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Podcast podcast) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Podcast (url, title, creator, podcastName, description) VALUES (?, ?, ?, ?, ?)");

            stmt.setString(1, podcast.getUrl());
            stmt.setString(2, podcast.getTitle());
            stmt.setString(3, podcast.getCreator());
            stmt.setString(4, podcast.getPodcastName());
            stmt.setString(5, podcast.getDescription());

            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Podcast podcast) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Podcast WHERE url = ?");
            stmt.setString(1, podcast.getUrl());
            stmt.executeUpdate();

            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Podcast podcast) {
        try {
            Connection connection = database.getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE Podcast SET title = ?, creator = ?, podcastName = ?, description = ? WHERE url = ?");

            stmt.setString(1, podcast.getTitle());
            stmt.setString(2, podcast.getCreator());
            stmt.setString(3, podcast.getPodcastName());
            stmt.setString(4, podcast.getDescription());
            stmt.setString(5, podcast.getUrl());

            stmt.executeUpdate();

            stmt.close();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
