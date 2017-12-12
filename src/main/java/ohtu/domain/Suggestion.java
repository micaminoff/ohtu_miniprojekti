package ohtu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Suggestion {
    
     private List<Tag> tags;
    private int id;
    private Suggestable suggestable;
    private String suggestableKey;
    
    
    public Suggestion(Suggestable suggestable) {
        this.suggestable = suggestable;
    }
    
    public Suggestion(int id, Suggestable suggestable) {
        this.id = id;
        this.suggestable = suggestable;
    }
    
    public Suggestion(Suggestable suggestable, List<Tag> tags) {
        this.suggestable = suggestable;
        this.tags = tags;
    }
    
    public Suggestion(int id, Suggestable suggestable, List<Tag> tags) {
        this.id = id;
        this.suggestable = suggestable;
        this.tags = tags;
    }

    public Suggestable getSuggestable() {
        return suggestable;
    }
    
    public int getId() {
        return id;
    }
    
    public Type getType() {
        return suggestable.getType();
    }
    
    public String getSuggestableKey() {
        return suggestable.getKey();
    }
    
    public List<Tag> getTags() {
        
        return this.tags;
    }
    
    @Override
    public String toString() {
        return suggestable.toString() + "\nType: " + getType() + "\nTags: " + tagsAsString();
    }
    
    private String tagsAsString() {
        String tagsAsString = "";
        if (this.tags == null) {
            return "";
        }
        for (Tag tag: this.tags) {
            tagsAsString = tagsAsString + tag.getName() + " ";
        }
        return tagsAsString;
    }
    
}