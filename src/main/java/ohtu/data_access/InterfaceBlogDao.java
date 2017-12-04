package ohtu.data_access;

import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Blog;

public interface InterfaceBlogDao {
    
    List<Blog> listAll();
    List<Blog> findByTitle(String title);
    List<Blog> findByCreator(String creator);
    Blog findByUrl(String url) throws SQLException;
    List<Blog> findByBlogName(String blogName);
    List<Blog> findByAll(String arg) throws SQLException;
    //etsimismetodi avaimella
    void add(Blog blog);
    void remove(Blog blog);
    
}
