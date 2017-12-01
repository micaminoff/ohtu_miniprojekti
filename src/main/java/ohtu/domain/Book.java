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
    private String title;
    private String creator;
    private String description;
    private String ISBN;
    private static final Type type = Type.BOOK;
    
    public Book(String title, String creator, String description, String ISBN) {
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
    
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + creator + "\nDescription: " + description + "\nISBN: " + ISBN;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) {
            return false;
        }
        Book b = (Book) o;
        if (this.title.equals(b.title) &&
                this.creator.equals(b.creator) &&
                this.description.equals(b.description) &&
                this.ISBN.equals(b.ISBN)) {
            
            return true;
        }
        return false;
    }
    
}
