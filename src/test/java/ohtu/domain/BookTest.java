/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hasasami
 */
public class BookTest {
    Book b;
    public BookTest() {
    }
    
    @Before
    public void setUp() {
        b = new Book("Jorma", "Lama 101", "Tosi jees");
    }
    
    @Test
    public void bookConstructorWorks() {
        assertEquals("Jorma", b.getCreator());
        assertEquals("Lama 101", b.getTitle());
        assertEquals("Tosi jees", b.getDescription());
    }
    
}
