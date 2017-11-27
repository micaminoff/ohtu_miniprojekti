package ohtu.domain;

public class Blog implements Suggestable {
    private String title;
    private String creator;
    private String url;
    private String blogName;
    
    public Blog(String title, String creator, String url, String blogName) {
        this.title = title;
        this.creator = creator;
        this.url = url;
        this.blogName = blogName;
    }
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public void setTitle(String title) {
        this.title = title;
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
        return "Title: " + title + "\nAuthor: " + creator + "\nUrl: " + url + "\nType: Blog";
    }
    
}
