package ohtu.domain;

public class Podcast implements Suggestable {
    private String title;
    private String creator;
    private String description;
    private String url;
    private String podcastName;
    private static final Type type = Type.PODCAST;
    
    public Podcast(String title, String creator, String description, String url, String podcastName) {
        this.title = title;
        this.creator = creator;
        this.url = url;
        this.podcastName = podcastName; 
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
        return this.description;
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
    
    public String getPodcastName() {
        return podcastName;
    }
    
    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + creator + "\nDescription: " + description + "\nurl: " + url + "\nPodcast name: " + podcastName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Podcast)) {
            return false;
        }
        Podcast p = (Podcast) o;
        if (this.title.equals(p.title) &&
                this.creator.equals(p.creator) &&
                this.description.equals(p.description) &&
                this.url.equals(p.url) &&
                this.podcastName.equals(p.podcastName)) {
            
            return true;
        }
        return false;
    }
    
}
