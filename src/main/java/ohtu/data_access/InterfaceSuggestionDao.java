package ohtu.data_access;

import java.util.List;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;

public interface InterfaceSuggestionDao {
    List<Suggestion> listAll();
    List<Suggestion> findByTitle(String title);
    List<Suggestion> findByAll(String arg);
    void add(Suggestion suggestion);
    void remove(Suggestion suggestion);
    boolean containsSuggestionForSuggestable(Suggestable suggestable);
}
