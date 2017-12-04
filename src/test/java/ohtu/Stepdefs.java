package ohtu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {

    App app;
    StubIO io;

    InterfaceBookDao bookDao = new InMemoryBookDao();
    InterfaceBlogDao blogDao = new InMemoryBlogDao();
    InterfacePodcastDao podcastDao = new InMemoryPodcastDao();
    InterfaceVideoDao videoDao = new InMemoryVideoDao();
    InterfaceSuggestionDao suggestionDao = new InMemorySuggestionDao(bookDao, blogDao, podcastDao, videoDao);
    SuggestionService sugg = new SuggestionService(suggestionDao, bookDao, blogDao, podcastDao, videoDao);
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
    
    @When("^title \"([^\"]*)\" is entered")
    public void title_is_entered(String title) {
        inputLines.add(title);
    }
    
    @When("^creator \"([^\"]*)\" is entered")
    public void creator_is_entered(String creator) {
        inputLines.add(creator);
    }
    
    @When("^ISBN \"([^\"]*)\" is entered")
    public void ISBN_is_entered(String isbn) {
        inputLines.add(isbn);
    }
    
    @When("^url \"([^\"]*)\" is entered")
    public void url_is_entered(String url) {
        inputLines.add(url);
    }
    
    @When("^podcast name \"([^\"]*)\" is entered")
    public void pod_name_is_entered(String name) {
        inputLines.add(name);
    }

    @When("^title \"([^\"]*)\" and creator \"([^\"]*)\" and ISBN \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void title_and_creator_and_ISBN_and_description_are_entered(String title, String creator, String ISBN, String description) throws Throwable {
        inputLines.add(title);
        inputLines.add(creator);
        inputLines.add(ISBN);
        inputLines.add(description);
        runApp();
    }


    
    @When("^url \"([^\"]*)\" and title \"([^\"]*)\" and creator \"([^\"]*)\" and blogname \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void url_and_title_and_creator_and_blogname_and_description_are_entered(String url, String title, String creator, String blogName, String description) throws Throwable {
        inputLines.add(url);
        inputLines.add(title);
        inputLines.add(creator);
        inputLines.add(blogName);
        inputLines.add(description);
        runApp();
    }

    
    @When("^url \"([^\"]*)\" and title \"([^\"]*)\" and creator \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void url_and_title_and_creator_and_description_are_entered(String url, String title, String creator, String description) throws Throwable {
        inputLines.add(url);
        inputLines.add(title);
        inputLines.add(creator);
        inputLines.add(description);
        runApp();
    }

    
     @When("^url \"([^\"]*)\" and title \"([^\"]*)\" and podcast name \"([^\"]*)\" and creator \"([^\"]*)\" and description \"([^\"]*)\"$")
    public void url_and_title_and_podcast_name_and_creator_and_description(String url, String title, String podcastName, String creator, String description) throws Throwable {
        inputLines.add(url);
        inputLines.add(title);
        inputLines.add(podcastName);
        inputLines.add(creator);
        inputLines.add(description);
        runApp();
    }


    @Then("^message \"([^\"]*)\" is displayed$")
    public void message_is_displayed(String expectedOutput) throws Throwable {
        // Cucumber on outo olento
        expectedOutput = expectedOutput.replace("\\n", "\n");
        assertTrue(io.getPrints().contains(expectedOutput));
    }

    @Then("^book is found$")
    public void book_is_found() throws Throwable {
        boolean found = false;
        ArrayList<String> prints = io.getPrints();
        for (int i = 0; i < prints.size(); i++) {
            String print = prints.get(i);
            if (print.contains("Title: Clean Code: A Handbook of Agile Software Craftsmanship")) {
                found = true;
            }
        }
        assertTrue(found);
    }

    private void runApp() {
        io = new StubIO(inputLines);
        app = new App(io, sugg);
        try {
            app.run();
        } catch (SQLException ex) {
            System.out.println("Tietokantaongelma: " + ex.getMessage());
        }
    }

}
