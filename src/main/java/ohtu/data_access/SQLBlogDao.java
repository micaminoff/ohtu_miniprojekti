package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import ohtu.domain.Blog;

public class SQLBlogDao implements InterfaceBlogDao {
    private Database database;
    
    public SQLBlogDao(Database database) {
        this.database = database;
    }

    @Override
    public List<Blog> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public HashMap<String,Blog> findByAll(String arg) {
        HashMap<String, Blog> blogs = new HashMap();

        try {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Blog WHERE url LIKE ? OR title LIKE ? OR creator LIKE ? OR blogName LIKE ? OR description LIKE ?");
        statement.setObject(1, "%" + arg + "%");
        statement.setObject(2, "%" + arg + "%");
        statement.setObject(3, "%" + arg + "%");
        statement.setObject(4, "%" + arg + "%");
        statement.setObject(5, "%" + arg + "%");
        
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String url = rs.getString("url");
            String title = rs.getString("title");
            String creator = rs.getString("creator");
            String blogName = rs.getString("blogName");
            String description = rs.getString("description");
            
            blogs.put(url, new Blog(title, creator, description, url, blogName));
        }
        
        rs.close();
        statement.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public List<Blog> findByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Blog> findByCreator(String creator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blog findByUrl(String url) {
        try {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Blog WHERE url = ?");
        stmt.setObject(1, url);

        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }
        
        String title = rs.getString("title");
        String writer = rs.getString("creator");
        String description = rs.getString("description");
        String blogName = rs.getString("blogName");
        
        rs.close();
        stmt.close();
        connection.close();
        
        return new Blog(title, writer, description, url, blogName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Blog> findByBlogName(String blogName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Blog blog) {
        try {
          Connection connection = database.getConnection();
          PreparedStatement stmt = connection.prepareStatement("INSERT INTO Blog (url, title, creator, blogName, description) VALUES (?, ?, ?, ?, ?)");
          
          stmt.setString(1, blog.getUrl());
          stmt.setString(2, blog.getTitle());
          stmt.setString(3, blog.getCreator());
          stmt.setString(4, blog.getBlogName());
          stmt.setString(5, blog.getDescription());
          
          stmt.executeUpdate();
          
          stmt.close();
          connection.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }

    @Override
    public void remove(Blog blog) {
        try {
          Connection connection = database.getConnection();
          PreparedStatement stmt = connection.prepareStatement("DELETE FROM Blog WHERE url = ?");
          stmt.setString(1, blog.getUrl());
          stmt.executeUpdate();
          
          stmt.close();
          connection.close();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }
    
}
