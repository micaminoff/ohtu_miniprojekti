package ohtu.domain;

public interface Suggestable {
    String getTitle();
    void setTitle(String title);
    Type getType();
    String getCreator();
    void setCreator(String creator);
    String getDescription();
    void setDescription(String description);
    String getKey();
}
