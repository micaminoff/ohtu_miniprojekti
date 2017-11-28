/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Suggestion;

/**
 *
 * @author hasasami
 */
public interface SuggestionDao {
    List<Suggestion> listAll() throws SQLException;
    List<Suggestion> findByTitle(String title);
    void add(Suggestion suggestion);
    Suggestion findSuggestionById(int id) throws SQLException;
}
