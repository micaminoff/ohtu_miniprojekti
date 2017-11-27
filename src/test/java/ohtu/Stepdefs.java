package ohtu;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {

    App app;
    StubIO io;

    SuggestionDao suggestionDao = new InMemorySuggestionDao();
    BookDao bookDao = new InMemoryBookDao();
    BlogDao blogDao = new InMemoryBlogDao();
    PodcastDao podcastDao = new InMemoryPodcastDao();
    VideoDao videoDao = new InMemoryVideoDao();
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

    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" and ISBN \"([^\"]*)\" are entered$")
    public void title_author_descr_isbn_are_entered(String author, String title, String description, String ISBN) throws Throwable {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(description);
        inputLines.add(ISBN);

        runApp();
    }
    
    @When("^title \"([^\"]*)\" and creator \"([^\"]*)\" and url \"([^\"]*)\" and blogname \"([^\"]*)\" and description \"([^\"]*)\" are entered")
    public void blog_info_is_entered(String title, String creator, String url, String blogname, String description) {
        inputLines.add(title);
        inputLines.add(creator);
        inputLines.add(url);
        inputLines.add(blogname);
        inputLines.add(description);
        
        runApp();
    }
    
    @When("^title \"([^\"]*)\" and creator \"([^\"]*)\" and url \"([^\"]*)\" and description \"([^\"]*)\" are entered")
    public void video_info_is_entered(String title, String creator, String url, String description) {
        inputLines.add(title);
        inputLines.add(creator);
        inputLines.add(url);
        inputLines.add(description);
        
        runApp();
    }
    
    @When("^podcastName \"([^\"]*)\" and episodeName \"([^\"]*)\" and url \"([^\"]*)\" and creator \"([^\"]*)\" and description \"([^\"]*)\" are entered")
    public void pod_info_is_entered(String podcastName, String episodeName, String url, String creator, String description) {
        inputLines.add(podcastName);
        inputLines.add(episodeName);
        inputLines.add(url);
        inputLines.add(creator);
        inputLines.add(description);
        
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
            if (print.contains("Title: Clean Code: A Handbook of Agile Software Craftsmanship")) {
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
