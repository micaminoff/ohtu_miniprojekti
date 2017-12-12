package ohtu.data_access;

import java.util.HashSet;
import java.util.List;
import ohtu.domain.Tag;

public interface InterfaceTagDao {
    public void addTagsForSuggestion(int id, List<Tag> tags);
    public HashSet<Integer> findByAll(String arg);
    public List<Tag> findBySuggestionId(int id);
    public void edit(Tag t, String newContent);
}
