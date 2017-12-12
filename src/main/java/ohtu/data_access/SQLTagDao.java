package ohtu.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import ohtu.domain.Tag;

public class SQLTagDao implements InterfaceTagDao {

    private Database database;

    public SQLTagDao(Database database) {
        this.database = database;
    }

    @Override
    public void addTagsForSuggestion(int id, List<Tag> tags) {
        try {
            Connection connection = database.getConnection();

            for (Tag tag : tags) {

                PreparedStatement check = connection.prepareStatement("SELECT * FROM Tag WHERE name = ?");
                check.setString(1, tag.getName());

                ResultSet rs = check.executeQuery();
                boolean containsStuff = rs.next();
                check.close();

                if (!containsStuff) { //Jos tagi ei ole tietokannassa, lisätään uusi (tätä checkiä ei pakolla tarvita, en tiiä)

                    PreparedStatement tagTableInsert = connection.prepareStatement("INSERT INTO Tag (name) VALUES (?)");

                    tagTableInsert.setString(1, tag.getName());

                    tagTableInsert.executeUpdate();
                    tagTableInsert.close();
                }

                //Lisätään liitostauluun SuggestionTag uusi rivi
                PreparedStatement joinTableInsert = connection.prepareStatement("INSERT INTO SuggestionTag (suggestion_id, tag_name) VALUES (?, ?)");
                joinTableInsert.setInt(1, id);
                joinTableInsert.setString(2, tag.getName());

                joinTableInsert.executeUpdate();

                joinTableInsert.close();

            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashSet<Integer> findByAll(String arg) {
        HashSet<Integer> matched_ids = new HashSet();

        try {
        List<String> keywords = Arrays.asList(arg.toLowerCase().split(" "));
        Connection connection = database.getConnection();

        for (String keyword : keywords) {
            PreparedStatement stmt = connection.prepareStatement("SELECT Suggestion.id FROM Suggestion JOIN SuggestionTag ON Suggestion.id = SuggestionTag.suggestion_id JOIN Tag ON Tag.name = SuggestionTag.tag_name WHERE Tag.name LIKE ?");
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                matched_ids.add(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
        }
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matched_ids;
    }
    
    @Override
    public List<Tag> findBySuggestionId(int id) {
        List<Tag> tags = new ArrayList<>();

        try {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM SuggestionTag WHERE suggestion_id = ?");
        stmt.setObject(1, id);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            tags.add(new Tag(rs.getString("tag_name")));
        }

        rs.close();
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

    @Override
    public void edit(Tag t, String newContent) {
        try {
        Connection connection = database.getConnection();
        
        PreparedStatement stmt = connection.prepareStatement("UPDATE Tag SET name = ? WHERE name = ?");
        stmt.setString(1, newContent);
        stmt.setString(2, t.getName());
        stmt.executeUpdate();
        
        stmt = connection.prepareStatement("UPDATE SuggestionTag SET tag_name = ? WHERE tag_name = ?");
        stmt.setString(1, newContent);
        stmt.setString(2, t.getName());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
