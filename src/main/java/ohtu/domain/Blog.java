package ohtu.domain;

public class Blog implements Suggestable {
    private String title;
    private String description;
    private String creator;
    private String url;
    private String blogName;
    private static final Type type = Type.BLOG;
    
    public Blog(String title, String creator, String description, String url, String blogName) {
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
    public String getCreator() {
        return creator;
    
    }
    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
   @Override
    public Type getType() {
        return type;
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
        return "Title: " + title + "\nWriter: " + creator + "\nDescription: " + description +"\nUrl: " + url + "\nBlog name: " + blogName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Blog)) {
            return false;
        }
        Blog b = (Blog) o;
        if (this.title.equals(b.title) &&
                this.creator.equals(b.creator) &&
                this.description.equals(b.description) &&
                this.url.equals(b.url) &&
                this.blogName.equals(b.blogName)) {
            
            return true;
        }
        return false;
    }
    
}
