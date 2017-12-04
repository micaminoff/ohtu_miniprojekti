package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
import ohtu.domain.Type;
import ohtu.domain.Video;

public class SQLSuggestionDao implements InterfaceSuggestionDao {
    private Database database;
    private InterfaceBookDao bookDao;
    private InterfaceBlogDao blogDao;
    private InterfacePodcastDao podcastDao;
    private InterfaceVideoDao videoDao;
    
    public SQLSuggestionDao(Database database, InterfaceBookDao bookDao, InterfaceBlogDao blogDao, InterfacePodcastDao podcastDao, InterfaceVideoDao videoDao) {
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

        while (rs.next()) {
            int id = rs.getInt("id");
            String type = rs.getString("type");
            String suggestableKey = rs.getString("suggestableKey");
            if (type.equals(Type.BOOK.toString())) {
                Book book = bookDao.findByISBN(suggestableKey);
                list.add(new Suggestion(id, book));
            } else if (type.equals(Type.BLOG.toString())) {
                Blog blog = blogDao.findByUrl(suggestableKey);
                list.add(new Suggestion(id, blog));
            } else if (type.equals(Type.VIDEO.toString())) {
                Video video = videoDao.findByUrl(suggestableKey);
                list.add(new Suggestion(id, video));
            } else if (type.equals(Type.PODCAST.toString())) {
                Podcast podcast = podcastDao.findByUrl(suggestableKey);
                list.add(new Suggestion(id, podcast));
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
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Suggestion (type, suggestableKey) VALUES (?, ?)");
        
        stmt.setString(1, suggestion.getType().toString());
        stmt.setString(2, suggestion.getSuggestableKey());
        
        stmt.executeUpdate();
        
        stmt.close();
        connection.close();
    }

  @Override
  public void remove(Suggestion s){
      try {
          Connection connection = database.getConnection();
          PreparedStatement stmt = connection.prepareStatement("DELETE FROM Suggestion WHERE id = ?");
          stmt.setInt(1, s.getId());
          stmt.executeUpdate();
          
          stmt.close();
          connection.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
  
  @Override
    public boolean containsSuggestionForSuggestable(Suggestable suggestable) {
        boolean ret = false;
        
       try {
          Connection connection = database.getConnection();
          PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Suggestion WHERE suggestableKey = ? LIMIT 1");
          stmt.setString(1, suggestable.getKey());
          ResultSet rs = stmt.executeQuery();
          
          if (rs.next()) {
              ret = true;
          }
          
          rs.close();
          stmt.close();
          connection.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
       
       return ret;
    }
}
