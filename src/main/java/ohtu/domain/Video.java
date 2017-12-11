package ohtu.domain;

public class Video implements Suggestable {
    private String title;
    private String creator; //vapaaeht
    private String description;
    private String url;
    private static final Type type = Type.VIDEO;

    public Video(String title, String creator, String description, String url) {
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.url = url;
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
    
    @Override
    public String getKey() {
        return url;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + creator + "\nDescription: " + description + "\nurl: " + url;
    }
 
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Video)) {
            return false;
        }
        Video v = (Video) o;
        if (this.title.equals(v.title) &&
                this.creator.equals(v.creator) &&
                this.description.equals(v.description) &&
                this.url.equals(v.url)) {
            
            return true;
        }
        return false;
    }
    
}
