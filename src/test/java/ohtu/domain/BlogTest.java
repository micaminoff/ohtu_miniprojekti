package ohtu.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BlogTest {
    Blog b;
    
    public BlogTest() {
    }
  
    @Before
    public void setUp() {
        b = new Blog("How to Increase Velocity", "David Bernstein", "Increase code quality today to increase your velocity tomorrow.", "https://www.agilealliance.org/how-to-increase-velocity/", "Agile Alliance Blog");
    }
    
    @Test
    public void constructorWorks() {
        assertEquals("How to Increase Velocity", b.getTitle());
        assertEquals("David Bernstein", b.getCreator());
        assertEquals("Increase code quality today to increase your velocity tomorrow.", b.getDescription());
        assertEquals("https://www.agilealliance.org/how-to-increase-velocity/", b.getUrl());
        assertEquals("Agile Alliance Blog", b.getBlogName());
    }
    
    @Test
    public void equalsTest() {
        Blog b2 = new Blog("How to Increase Velocity", "David Bernstein", "Increase code quality today to increase your velocity tomorrow.", "https://www.agilealliance.org/how-to-increase-velocity/", "Agile Alliance Blog");
        Blog b3 = new Blog("Everything sucks", "David Bernstein", "please read this", "https://www.fakelink.org/dont-click/", "");
        
        assertTrue(b.equals(b2));
        assertFalse(b.equals(b3));
    }
    
}
