/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
import ohtu.domain.Type;
import static ohtu.domain.Type.*;

/**
 *
 * @author hasasami
 */
public class InMemorySuggestionDao implements SuggestionDao{
    private List<Suggestion> suggestions;
    
    public InMemorySuggestionDao() {
        suggestions = new ArrayList();
        suggestions.add(new Suggestion(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert Martin", "Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship", "978-951-98548-9-2")));
        suggestions.add(new Suggestion(new Blog("JRE #002 - MMA Show #2", "Joe Rogan" , "Eddie Bravo needs help" ,"http://podcasts.joerogan.net/podcasts/mma-show-2", "The Joe Rogan Experience")));
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
    public void remove(Suggestion s) {
        suggestions.remove(s);
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

    //huom equals metodit pitää tehdä
    @Override
    public boolean containsSuggestionForSuggestable(Suggestable suggestable) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getSuggestable().equals(suggestable))
                return true;
        }
        return false;
    }
    
    
    
    
    
}
