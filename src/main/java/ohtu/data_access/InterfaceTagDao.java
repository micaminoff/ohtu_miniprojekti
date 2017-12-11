/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Tag;

/**
 *
 * @author hcpaavo
 */
public interface InterfaceTagDao {
    public void addTagsForSuggestion(int id, List<Tag> tags) throws SQLException;
    public List<Tag> findBySuggestionId(int id) throws SQLException;
}
