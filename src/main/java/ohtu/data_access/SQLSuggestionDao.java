package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
import ohtu.domain.Tag;
import ohtu.domain.Type;
import ohtu.domain.Video;

public class SQLSuggestionDao implements InterfaceSuggestionDao {

    private Database database;
    private InterfaceBookDao bookDao;
    private InterfaceBlogDao blogDao;
    private InterfacePodcastDao podcastDao;
    private InterfaceVideoDao videoDao;
    private InterfaceTagDao tagDao;

    public SQLSuggestionDao(Database database, InterfaceBookDao bookDao, InterfaceBlogDao blogDao, InterfacePodcastDao podcastDao, InterfaceVideoDao videoDao, InterfaceTagDao tagDao) {
        this.database = database;
        this.bookDao = bookDao;
        this.blogDao = blogDao;
        this.podcastDao = podcastDao;
        this.videoDao = videoDao;
        this.tagDao = tagDao;
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
                List<Tag> tags = tagDao.findBySuggestionId(id);
                list.add(new Suggestion(id, book, tags));
            } else if (type.equals(Type.BLOG.toString())) {
                Blog blog = blogDao.findByUrl(suggestableKey);
                List<Tag> tags = tagDao.findBySuggestionId(id);
                list.add(new Suggestion(id, blog, tags));
            } else if (type.equals(Type.VIDEO.toString())) {
                Video video = videoDao.findByUrl(suggestableKey);
                List<Tag> tags = tagDao.findBySuggestionId(id);
                list.add(new Suggestion(id, video, tags));
            } else if (type.equals(Type.PODCAST.toString())) {
                Podcast podcast = podcastDao.findByUrl(suggestableKey);
                List<Tag> tags = tagDao.findBySuggestionId(id);
                list.add(new Suggestion(id, podcast, tags));
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

        String type = suggestion.getType().toString();
        String suggestableKey = suggestion.getSuggestableKey();

        stmt.setString(1, type);
        stmt.setString(2, suggestableKey);

        stmt.executeUpdate();

        //selvitetään suggestion_id ja kutsutaan metodia addTagsForSuggestion(int id, List<Tag> tags)
        stmt = connection.prepareStatement("SELECT MAX(id) AS max FROM Suggestion");
        ResultSet rs = stmt.executeQuery();
        int id = rs.getInt("max");

        rs.close();
        stmt.close();
        connection.close();

        tagDao.addTagsForSuggestion(id, suggestion.getTags());
    }

    @Override
    public void remove(Suggestion s) {
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

    public List<Suggestion> findByAll(String arg) throws SQLException {

        HashMap<String, Book> books = bookDao.findByAll(arg);
        HashMap<String, Blog> blogs = blogDao.findByAll(arg);
        HashMap<String, Podcast> podcasts = podcastDao.findByAll(arg);
        HashMap<String, Video> videos = videoDao.findByAll(arg);
        
        //suggestioneiden id:t joiden tageissa esiintyy arg-stringin sanoja
        HashSet<Integer> suggestionsMatchedByTags = tagDao.findByAll(arg);
        //suggestioneiden id:t joiden suggestableissa esiintyy arg-stringin substringejä
        HashSet<Integer> suggestionsMatchedBySuggestables = new HashSet();

        List<Suggestion> matching_suggestions = new ArrayList();

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Suggestion");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String suggestable_id = rs.getString("suggestablekey");
            String type = rs.getString("type");

            if (type.equals(Type.BOOK.toString())) {
                if (books.get(suggestable_id) != null) {
                    matching_suggestions.add(new Suggestion(id, books.get(suggestable_id)));
                    suggestionsMatchedBySuggestables.add(id);
                }

            } else if (type.equals(Type.BLOG.toString())) {
                if (blogs.get(suggestable_id) != null) {
                    matching_suggestions.add(new Suggestion(id, blogs.get(suggestable_id)));
                    suggestionsMatchedBySuggestables.add(id);
                }

            } else if (type.equals(Type.PODCAST.toString())) {
                if (podcasts.get(suggestable_id) != null) {
                    matching_suggestions.add(new Suggestion(id, podcasts.get(suggestable_id)));
                    suggestionsMatchedBySuggestables.add(id);
                }

            } else if (type.equals(Type.VIDEO.toString())) {
                if (videos.get(suggestable_id) != null) {
                    matching_suggestions.add(new Suggestion(id, videos.get(suggestable_id)));
                    suggestionsMatchedBySuggestables.add(id);
                }
            }
        }

        for (Integer id : suggestionsMatchedByTags) {
            if (!suggestionsMatchedBySuggestables.contains(id)) {
                Suggestable s = getSuggestionsSuggestable(id);
                if (s != null) {
                    matching_suggestions.add(new Suggestion(id, s));
                }
            }
        }

        rs.close();
        stmt.close();
        connection.close();
        return matching_suggestions;
    }

    public Suggestable getSuggestionsSuggestable(Integer id) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Suggestion WHERE id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        String type = null;
        if (rs.next()) {
            type = rs.getString("type");
        }
        
        if (type != null) {
           
            PreparedStatement findSuggestable;
            ResultSet fields;
            if (type.equals(Type.BOOK.toString())) {
                findSuggestable = connection.prepareStatement("SELECT * FROM Book JOIN Suggestion ON Book.isbn = Suggestion.suggestablekey WHERE Suggestion.id = ?");
                findSuggestable.setInt(1, id);
                fields = findSuggestable.executeQuery();
                if (fields.next()) {
                    return new Book(fields.getString("title"), fields.getString("creator"), fields.getString("description"), fields.getString("isbn"));
                }

            } else if (type.equals(Type.BLOG.toString())) {
                findSuggestable = connection.prepareStatement("SELECT * FROM Blog JOIN Suggestion ON Blog.url = Suggestion.suggestablekey WHERE Suggestion.id = ?");
                findSuggestable.setInt(1, id);
                fields = findSuggestable.executeQuery();
                if (fields.next()) {
                    return new Blog(fields.getString("title"), fields.getString("creator"), fields.getString("description"), fields.getString("url"),  fields.getString("blogName"));
                }

            } else if (type.equals(Type.VIDEO.toString())) {
                findSuggestable = connection.prepareStatement("SELECT * FROM Video JOIN Suggestion ON Video.url = Suggestion.suggestablekey WHERE Suggestion.id = ?");
                findSuggestable.setInt(1, id);
                fields = findSuggestable.executeQuery();
                if (fields.next()) {
                    return new Video(fields.getString("title"), fields.getString("creator"), fields.getString("description"), fields.getString("url"));
                }

            } else if (type.equals(Type.PODCAST.toString())) {
                findSuggestable = connection.prepareStatement("SELECT * FROM Podcast JOIN Suggestion ON Podcast.url = Suggestion.suggestablekey WHERE Suggestion.id = ?");
                findSuggestable.setInt(1, id);
                fields = findSuggestable.executeQuery();
                if (fields.next()) {
                    return new Podcast(fields.getString("title"), fields.getString("creator"), fields.getString("description"), fields.getString("url"), fields.getString("podcastName"));
                }
            }
        }

        return null;
    }
}
