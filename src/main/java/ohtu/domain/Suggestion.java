/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hasasami
 */
public class Suggestion {
    
    // private List<Tag> tags;
    //private List<Comment> comments;
    //private List<Courses> rcourses;
    //private List<Courses> esitietoKurssit;
    private int id;
    private Suggestable suggestable;
//    private String type;
    
//    public Suggestion(Suggestable suggestable, String type) {
//        //this.tags.addAll(Arrays.asList(tags));
//        this.suggestable = suggestable;
//        
//    }
    
    public Suggestion(Suggestable suggestable) {
        this.suggestable = suggestable;
    }
    
    public Suggestion(int id, Suggestable suggestable) {
        this.id = id;
        this.suggestable = suggestable;
    }
    
    //merkki
//    public void addTags(Tag... tags) {
//        this.tags.addAll(Arrays.asList(tags));
//    }
//
//    public List<Tag> getTags() {
//        return tags;
//    }
    //merkki
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
    
    @Override
    public String toString() {
        return suggestable.toString() + "\nType: " + getType();
    }
    
}
