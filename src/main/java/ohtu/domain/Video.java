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
    private String creator;
    private String description;
    
    public Video(String title, String creator, String description) {
        this.title = title;
        this.creator = creator;
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
    
    public String getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
