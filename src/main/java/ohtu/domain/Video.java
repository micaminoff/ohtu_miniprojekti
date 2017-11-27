/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.domain;

/**
 *
 * @author hcpaavo
 */
public class Video implements Suggestable {
    private String title;
    private String creator; //vapaaeht
    private String description;
    private String url;
    
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
    
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
}
