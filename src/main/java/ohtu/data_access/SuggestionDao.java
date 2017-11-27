/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.util.List;
import ohtu.domain.Suggestion;

/**
 *
 * @author hasasami
 */
public interface SuggestionDao {
    List<Suggestion> listAll();
    void add(Suggestion suggestion);
}
