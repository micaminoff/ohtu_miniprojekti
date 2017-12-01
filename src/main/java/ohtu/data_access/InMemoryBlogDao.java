package ohtu.data_access;

import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Blog;

public class InMemoryBlogDao implements BlogDao {
    private List<Blog> blogs;
    
    public InMemoryBlogDao() {
        blogs = new ArrayList();
        blogs.add(new Blog("How to Increase Velocity", "David Bernstein", "Increase code quality today to increase your velocity tomorrow.", "https://www.agilealliance.org/how-to-increase-velocity/", "Agile Alliance Blog"));
    }
    
    @Override
    public List<Blog> listAll() {
        return blogs;
    }
    
    @Override 
    public List<Blog> findByTitle(String title) {
        ArrayList<Blog> blogsReturn = new ArrayList();
        for (Blog blog : blogs) {
            if (blog.getTitle().toLowerCase().contains(title.toLowerCase())) {
                blogsReturn.add(blog);
            }
        }
        return blogsReturn;
    }
    
    @Override 
    public List<Blog> findByCreator(String creator) {
        ArrayList<Blog> blogsReturn = new ArrayList();
        for (Blog blog : blogs) {
            if (blog.getCreator().toLowerCase().contains(creator.toLowerCase())) {
                blogsReturn.add(blog);
            }
        }
        return blogsReturn;
    }
    
    @Override
    public Blog findByUrl(String url) {
        for (Blog blog : blogs) {
            if (blog.getUrl().toLowerCase().contains(url.toLowerCase())) {
                return blog;
            }
        }
        return null;
    }
    
    @Override
    public List<Blog> findByBlogName(String blogName) {
        ArrayList<Blog> blogsReturn = new ArrayList();
        for (Blog blog : blogs) {
            if (blog.getBlogName().toLowerCase().contains(blogName.toLowerCase())) {
                blogsReturn.add(blog);
            }
        }
        return blogsReturn;
    }
    
    @Override
    public void add(Blog blog) {
        blogs.add(blog);
    }
    
    @Override
    public void remove(Blog blog) {
        blogs.remove(blog);
    }
    
}
