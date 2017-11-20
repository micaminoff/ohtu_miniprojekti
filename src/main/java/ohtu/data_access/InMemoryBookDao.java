/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;

/**
 *
 * @author paavo
 * päivityksiä: mkotola
 */
public class InMemoryBookDao implements BookDao {
    private List<Suggestable> books;
    
    public InMemoryBookDao() {
        books = new ArrayList<Suggestable>();
        books.add(new Book("Robert Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", "Paska kirja"));
    }

    @Override
    public List<Suggestable> listAll() {
        return books;
    }


    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public Suggestable findByCreator(String creator) {
        for (Suggestable book: books) {
            if (book.getCreator().equals(creator)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Suggestable findByTitle(String title) {
        for (Suggestable book: books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Suggestable findByDescription(String description) {
        for (Suggestable book: books) {
            if (book.getDescription().equals(description)) {
                return book;
            }
        }
        return null;
    }
    
}
