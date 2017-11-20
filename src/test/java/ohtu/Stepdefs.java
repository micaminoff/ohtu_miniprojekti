package ohtu;

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
    SuggestionService sugg = new SuggestionService(bdao);
    List<String> inputLines = new ArrayList<>();
    
    @Given("^command add is selected$")
    public void command_add_selected() throws Throwable {
        inputLines.add("add");
    }
    
    @Given("^command list is selected$")
    public void command_list_selected() throws Throwable {
        inputLines.add("list");
    }
    
    @When("^command book is selected$")
    public void command_book_selected() throws Throwable {
        inputLines.add("book");
    }
    
    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void title_author_descr_are_entered(String title, String author, String description) throws Throwable {
       inputLines.add(title);
       inputLines.add(author);
       inputLines.add(description);
       
       io = new StubIO(inputLines); 
       app = new App(io, sugg);
       app.run();
    }
    
    @Then("^message \"([^\"]*)\" is displayed$")
    public void message_is_displayed(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }

}
