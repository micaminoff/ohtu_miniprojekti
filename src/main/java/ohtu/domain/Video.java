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
    private String creator;
    private String title;
    private String description;
    

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    
}
