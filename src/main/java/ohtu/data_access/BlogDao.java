package ohtu.data_access;

import java.util.List;
import ohtu.domain.Blog;

public interface BlogDao {
    
    List<Blog> listAll();
    List<Blog> findByTitle(String title);
    List<Blog> findByCreator(String creator);
    Blog findByUrl(String url);
    List<Blog> findByBlogName(String blogName);

    
}