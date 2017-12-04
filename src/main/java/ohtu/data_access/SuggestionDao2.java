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

        while (rs.next()) {
            String type = rs.getString("type");
            String suggestableKey = rs.getString("suggestableKey");
            if (type.equals(Type.BOOK.toString())) {
                Book book = bookDao.findByISBN(suggestableKey);
                list.add(new Suggestion(book));
            } else if (type.equals(Type.BLOG.toString())) {
                Blog blog = blogDao.findByUrl(suggestableKey);
                list.add(new Suggestion(blog));
            } else if (type.equals(Type.VIDEO.toString())) {
                Video video = videoDao.findByUrl(suggestableKey);
                list.add(new Suggestion(video));
            } else if (type.equals(Type.PODCAST.toString())) {
                Podcast podcast = podcastDao.findByUrl(suggestableKey);
                list.add(new Suggestion(podcast));
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
        PreparedStatement stmt = connection.prepareStatement("");
        
    }

  @Override
  public void remove(Suggestion s) {
      
  }
  
  @Override
    public boolean containsSuggestionForSuggestable(Suggestable suggestable) {
        return false;
    }
    
}
