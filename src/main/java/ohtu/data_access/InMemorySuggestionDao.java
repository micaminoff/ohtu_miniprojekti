/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
import ohtu.domain.Type;

/**
 *
 * @author hasasami
 */
public class InMemorySuggestionDao implements SuggestionDao{
    private List<Suggestion> suggestions;
    
    public InMemorySuggestionDao() {
        suggestions = new ArrayList();
        suggestions.add(new Suggestion(new Book(1, "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert Martin", "Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship", "978-951-98548-9-2"),1, "book"));
    }
    
    @Override
    public List<Suggestion> listAll() {
        return suggestions;
    }
    
    @Override
    public void add(Suggestion suggestion) {
        suggestions.add(suggestion);
    }
    
    @Override
    public List<Suggestion> findByTitle(String title) {
        List<Suggestion> listRet = new ArrayList();
        
        for (Suggestion suggestion : suggestions) {
            Suggestable suggestable = suggestion.getSuggestable();
            if (suggestable.getTitle().toLowerCase().contains(title.toLowerCase()));
                listRet.add(suggestion);
        }
        
        return listRet;
    }

    @Override
    public Suggestion findSuggestionById(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
