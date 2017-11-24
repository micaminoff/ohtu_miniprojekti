/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

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
        
    }
    public List<Suggestion> listAll() {
        return suggestions;
    }
}
