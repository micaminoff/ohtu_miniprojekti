/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;

/**
 *
 * @author hasasami
 */
public interface InterfaceSuggestionDao {
    List<Suggestion> listAll() throws SQLException;
    List<Suggestion> findByTitle(String title);
    List<Suggestion> findByAll(String arg) throws SQLException;
    void add(Suggestion suggestion) throws SQLException;
    void remove(Suggestion suggestion);
    boolean containsSuggestionForSuggestable(Suggestable suggestable);
}
