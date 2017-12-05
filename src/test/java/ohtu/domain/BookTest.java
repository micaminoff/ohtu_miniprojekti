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
 * päivityksiä: mkotola
 */
public class BookTest {
    Book b;
    public BookTest() {
    }
    
    @Before
    public void setUp() {
        b = new Book("Lama 101", "Jorma", "Tosi jees", "978-951-98548-9-2");
    }
    
    @Test
    public void bookConstructorWorks() {
        assertEquals("Lama 101", b.getTitle());
        assertEquals("Jorma", b.getCreator());
        assertEquals("Tosi jees", b.getDescription());
        assertEquals("978-951-98548-9-2", b.getISBN());
    }
    
    @Test
    public void equalsTest() {
        Book b2 = new Book("Lama 101", "Jorma", "Tosi jees", "978-951-98548-9-2");
        Book b3 = new Book("Puurokirja", "Pekka Puuro", "opi tekemään puuroa", "78-951-98548-9-3");
        
        assertTrue(b.equals(b2));
        assertFalse(b.equals(b3));
        
    }
    
}
