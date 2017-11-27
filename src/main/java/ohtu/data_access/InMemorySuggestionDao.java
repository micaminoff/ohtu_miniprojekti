/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Book;
import ohtu.domain.Suggestion;

/**
 *
 * @author hasasami
 */
public class InMemorySuggestionDao implements SuggestionDao{
    private List<Suggestion> suggestions;
    
    public InMemorySuggestionDao() {
        suggestions = new ArrayList();
        suggestions.add(new Suggestion(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert Martin", "Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship", "978-951-98548-9-2")));
    }
    
    @Override
    public List<Suggestion> listAll() {
        return suggestions;
    }
    
    @Override
    public void add(Suggestion suggestion) {
        suggestions.add(suggestion);
    }
    
}
