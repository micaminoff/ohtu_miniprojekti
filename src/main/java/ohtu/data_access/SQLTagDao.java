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
public class SQLTagDao implements InterfaceTagDao {
    private Database database;
    
    public SQLTagDao(Database database) {
        this.database = database;
    }

    @Override
    public void addTagsForSuggestion(int id, List<Tag> tags) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
