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
    private int id;
    private String title;
    private String creator;
    private String description;
    private String ISBN;
    private static final Type type = Type.BOOK;
    
    public Book(String title, String creator, String description, String ISBN) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.description = description;
        this.ISBN = ISBN;
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

    public String getDescription() {
        return description;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + creator + "\nDescription: " + description;
    }
    
}
