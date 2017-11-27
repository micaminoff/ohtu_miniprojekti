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
    private Suggestable suggestable;
    
    public Suggestion(Suggestable suggestable) {
        //this.tags.addAll(Arrays.asList(tags));
        this.suggestable = suggestable;
        
    }
    
//    public void addTags(Tag... tags) {
//        this.tags.addAll(Arrays.asList(tags));
//    }
//
//    public List<Tag> getTags() {
//        return tags;
//    }
    
    public Suggestable getSuggestable() {
        return this.suggestable;
    }
    
    @Override
    public String toString() {
        return suggestable.toString() + "\nType: " + suggestable.getType();
    }
    
}
