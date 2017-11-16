/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.domain;

/**
 *
 * @author hasasami
 */
public interface Suggestable {
    String getTitle();
    String getCreator();
    String getDescription();
    
    void setTitle(String title);
    void setCreator(String creator);
    void setDescription(String description);
}
