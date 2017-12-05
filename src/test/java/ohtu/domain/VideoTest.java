package ohtu.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class VideoTest {
    Video v;
    
    public VideoTest() {
    }
    
    @Before
    public void setUp() {
        v = new Video("Turing Machine - Introduction (Part 1)", "Neso Academy", "TOC: Introduction to Turing Machine", "https://www.youtube.com/watch?v=PvLaPKPzq2I");
    }
    
    @Test
    public void constructorWorks() {
        assertEquals("Turing Machine - Introduction (Part 1)", v.getTitle());
        assertEquals("Neso Academy", v.getCreator());
        assertEquals("TOC: Introduction to Turing Machine", v.getDescription());
        assertEquals("https://www.youtube.com/watch?v=PvLaPKPzq2I", v.getUrl());
    }
    
    @Test
    public void equalsTest() {
        Video v2 = new Video("Turing Machine - Introduction (Part 1)", "Neso Academy", "TOC: Introduction to Turing Machine", "https://www.youtube.com/watch?v=PvLaPKPzq2I");
        Video v3 = new Video("My video", "leetgamer", "like and scribe", "https://www.youtube.com/fake");
        
        assertTrue(v.equals(v2));
        assertFalse(v.equals(v3));
    }
    
}
