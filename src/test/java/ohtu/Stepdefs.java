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
    
    @When("^command \"([^\"]*)\" is entered$")
    public void command_is_entered(String cmd) throws Throwable {
        inputLines.add(cmd);
        runApp();
    }
    
    @When("^search term \"([^\"]*)\" is entered")
    public void term_entered(String term) throws Throwable {
        inputLines.add(term);
        runApp();
    }
    
    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" and ISBN \"([^\"]*)\" are entered$")
    public void title_author_descr_isbn_are_entered(String author, String title, String description, String ISBN) throws Throwable {
       inputLines.add(title);
       inputLines.add(author);
       inputLines.add(description);
       inputLines.add(ISBN);

       runApp();
    }
    
    @Then("^message \"([^\"]*)\" is displayed$")
    public void message_is_displayed(String expectedOutput) throws Throwable {
        assertTrue(io.getPrints().contains(expectedOutput));
    }
    
    @Then("^book is found$")
    public void book_is_found() throws Throwable {
        boolean found = false;
        ArrayList<String> prints = io.getPrints();
        for (int i = 0; i < prints.size(); i++) {
            String print = prints.get(i);
            if (print.contains("ISBN: 978-951-98548-9-2")) {
                found = true;
            }
        }
        assertTrue(found);
    }
    
    private void runApp() {
        io = new StubIO(inputLines); 
        app = new App(io, sugg);
        app.run();
    }

}
