/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.domain;

/**
 *
 * @author hasasami
 * päivityksiä: mkotola
 */
public class Book implements Suggestable {
    private String creator;
    private String title;
    private String description;
    private String ISBN;
    
    public Book(String creator, String title, String description, String ISBN) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.ISBN = ISBN;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
}
