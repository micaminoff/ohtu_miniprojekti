package ohtu.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PodcastTest {
    Podcast p;
    
    public PodcastTest() {
    }
    
    @Before
    public void setUp() {
        p = new Podcast("JRE #002 - MMA Show #2", "Joe Rogan" , "Eddie Bravo needs help" ,"http://podcasts.joerogan.net/podcasts/mma-show-2", "The Joe Rogan Experience");
    }
    
    @Test
    public void constructorWorks() {
        assertEquals("JRE #002 - MMA Show #2", p.getTitle());
        assertEquals("Joe Rogan", p.getCreator());
        assertEquals("Eddie Bravo needs help", p.getDescription());
        assertEquals("http://podcasts.joerogan.net/podcasts/mma-show-2", p.getUrl());
        assertEquals("The Joe Rogan Experience", p.getPodcastName());
    }
    
    @Test
    public void equalsTest() {
        Podcast p2 = new Podcast("JRE #002 - MMA Show #2", "Joe Rogan" , "Eddie Bravo needs help" ,"http://podcasts.joerogan.net/podcasts/mma-show-2", "The Joe Rogan Experience");
        Podcast p3 = new Podcast("podcast #001", "Aapo Virtanen", "Agile basics", "http://fakelink.net/podcasts/", "Virtanen show");
        
        assertTrue(p.equals(p2));
        assertFalse(p.equals(p3));
    }
    
}
