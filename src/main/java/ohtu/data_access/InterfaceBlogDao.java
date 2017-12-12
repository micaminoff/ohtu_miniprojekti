package ohtu.data_access;

import java.util.HashMap;
import java.util.List;
import ohtu.domain.Blog;

public interface InterfaceBlogDao {
    
    List<Blog> listAll();
    List<Blog> findByTitle(String title);
    List<Blog> findByCreator(String creator);
    Blog findByUrl(String url);
    List<Blog> findByBlogName(String blogName);
    HashMap<String ,Blog> findByAll(String arg);
    void add(Blog blog);
    void remove(Blog blog);
    void update(Blog blog);
    
}
