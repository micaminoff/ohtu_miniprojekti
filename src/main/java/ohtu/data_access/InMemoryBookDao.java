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
    private List<Book> books;
    
    public InMemoryBookDao() {
        books = new ArrayList<>();
        books.add(new Book("Robert Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", "Paska kirja", "978-951-98548-9-2"));
    }

    @Override
    public List<Book> listAll() {
        return books;
    }


    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public Book findByCreator(String creator) {
        for (Book book: books) {
            if (book.getCreator().equals(creator)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        for (Book book: books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book findByDescription(String description) {
        for (Book book: books) {
            if (book.getDescription().equals(description)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book findByISBN(String ISBN) {
        for (Book book: books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
    
}
