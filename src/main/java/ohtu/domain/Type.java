package ohtu.domain;

public enum Type {
    BOOK("Book"), BLOG("Blog"), PODCAST("Podcast"), VIDEO("Video");
    
    private final String fieldText;
    
    private Type(String text) {
        fieldText = text;
    }
    
    @Override
    public String toString() {
        return fieldText;
    }
    
}
