package ohtu.domain;

public class Blog implements Suggestable {
    private String title;
    private String creator;
    private String url;
    private String blogName;
    private String description;
    private static final Type type = Type.BLOG;
    
    public Blog(String title, String creator, String url, String blogName, String description) {
        this.title = title;
        this.creator = creator;
        this.url = url;
        this.blogName = blogName;
        this.description = description;
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public Type getType() {
        return type;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getBlogName() {
        return blogName;
    }
    
    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + creator + "\nUrl: " + url;
    }
    
}
