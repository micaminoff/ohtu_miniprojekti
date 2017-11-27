/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.domain;

/**
 *
 * @author otsepp
 */
public class Podcast implements Suggestable {
    private String title;
    private String creator; //vapaaeht
    private String url;
    private String podcastName;
    private String description;
    private static final Type type = Type.PODCAST;
    
    public Podcast(String title, String creator, String url, String podcastName, String description) {
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
    
    public String getPodcastName() {
        return podcastName;
    }
    
    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }
    
    @Override
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + creator + "\nurl: " + url + "\nPodcast name: " + podcastName;
    }
    
}
