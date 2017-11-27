package ohtu;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
import ohtu.services.*;

public class Stepdefs {
    App app;
    StubIO io;
    BookDao bdao = new InMemoryBookDao();
    SuggestionDao sdao = new InMemorySuggestionDao();
    SuggestionService sugg = new SuggestionService(bdao, sdao);
    List<String> inputLines = new ArrayList<>();
    
    
    @Given("^command \"([^\"]*)\" is selected$")
    public void g_command_selected(String cmd) throws Throwable {
        inputLines.add(cmd);
    }    
    
    @When("^search term \"([^\"]*)\" is entered")
    public void term_entered(String term) throws Throwable {
        inputLines.add(term);
        io = new StubIO(inputLines); 
        app = new App(io, sugg);
        app.run();
    }
    
    @When("^author \"([^\"]*)\" and title \"([^\"]*)\" and description \"([^\"]*)\" and ISBN \"([^\"]*)\" are entered$")
    public void title_author_descr_are_entered(String author, String title, String description, String ISBN) throws Throwable {
       inputLines.add(author);
       inputLines.add(title);
       inputLines.add(description);
       inputLines.add(ISBN);
       
       io = new StubIO(inputLines); 
       app = new App(io, sugg);
       app.run();
    }
    
    @Then("^message \"([^\"]*)\" is displayed$")
    public void message_is_displayed(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }
    
    @Then("^book is found$")
    public void book_is_found() throws Throwable {
        assertTrue(io.getPrints().contains("Author: Robert Martin\nTitle: Clean Code: A Handbook of Agile Software Craftsmanship\nDescription: Good book\nISBN: 978-951-98548-9-2\n"));
        
    }

}
