package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.Before;
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
    Database test_data = create_db();

    InterfaceBookDao bookDao = new SQLBookDao(test_data);
    InterfaceBlogDao blogDao = new SQLBlogDao(test_data);
    InterfacePodcastDao podcastDao = new SQLPodcastDao(test_data);
    InterfaceVideoDao videoDao = new SQLVideoDao(test_data);
    InterfaceTagDao tagDao = new SQLTagDao(test_data);
    InterfaceSuggestionDao suggestionDao = new SQLSuggestionDao(test_data, bookDao, blogDao, podcastDao, videoDao, tagDao);
    SuggestionService sugg = new SuggestionService(suggestionDao, bookDao, blogDao, podcastDao, videoDao);
    List<String> inputLines = new ArrayList<>();
    
    private Database create_db() {
        Database db;
        try {
            db = new Database("jdbc:sqlite:src/test/resources/sql/testi.db");
            return db;
        } catch (Exception ex) {
            System.out.println("Couldn't create database. " + ex.getMessage());
        }
        return null;
    }
    
    @Before
    public void populate() {
        sugg.fillWithExampleData();
    }

    @After
    public void tear_down_db() throws SQLException {
        this.test_data.getConnection().createStatement().executeUpdate("delete from Suggestion");
        this.test_data.getConnection().createStatement().executeUpdate("delete from Video");
        this.test_data.getConnection().createStatement().executeUpdate("delete from Podcast");
        this.test_data.getConnection().createStatement().executeUpdate("delete from Book");
        this.test_data.getConnection().createStatement().executeUpdate("delete from Blog");
    }

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
    
    @When("^author \"([^\"]*)\" is entered")
    public void author_is_entered(String author) {
        inputLines.add(author);
    }
    
    @When("^ISBN \"([^\"]*)\" is entered")
    public void ISBN_is_entered(String isbn) {
        inputLines.add(isbn);
    }
    
    @When("^url \"([^\"]*)\" is entered")
    public void url_is_entered(String url) {
        inputLines.add(url);
    }
    
    @When("^name \"([^\"]*)\" is entered")
    public void name_is_entered(String name) {
        inputLines.add(name);
    }
    
    @When("^wrong description \"([^\"]*)\" is entered")
    public void wrong_description_is_entered(String description) {
        inputLines.add(description);
    }
    
    @When("^description \"([^\"]*)\" is entered")
    public void description_is_entered(String description) {
        inputLines.add(description);
        runApp();
    }

    @When("^ISBN \"([^\"]*)\" and title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void isbn_and_title_and_author_and_description_are_entered(String ISBN, String title, String author, String description) throws Throwable {
        inputLines.add(ISBN);
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(description);
        runApp();
    }
    
    @When("title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void title_and_author_and_description_are_entered(String title, String author, String description) throws Throwable {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(description);
        runApp();
    }
    
    @When("^url \"([^\"]*)\" and title \"([^\"]*)\" and author \"([^\"]*)\" and name \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void url_and_title_and_author_and_name_and_description_are_entered(String url, String title, String author, String name, String description) throws Throwable {
        inputLines.add(url);
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(name);
        inputLines.add(description);
        runApp();
    }
    
    @When("^title \"([^\"]*)\" and author \"([^\"]*)\" and name \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void title_and_author_and_name_and_description_are_entered(String title, String author, String name, String description) throws Throwable {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(name);
        inputLines.add(description);
        runApp();
    }
    
    @When("^author \"([^\"]*)\" and name \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void author_and_name_and_description_are_entered(String author, String name, String description) throws Throwable {
        inputLines.add(author);
        inputLines.add(name);
        inputLines.add(description);
        runApp();
    }
    
    @When("^name \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void name_and_author_and_description_are_entered(String name, String author, String description) throws Throwable {
        inputLines.add(name);
        inputLines.add(author);
        inputLines.add(description);
        runApp();
    }
    
    @When("^name \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void name_and_description_are_entered(String name, String description) throws Throwable {
        inputLines.add(name);
        runApp();
    }
    
    @When("^url \"([^\"]*)\" and title \"([^\"]*)\" and author \"([^\"]*)\" and blogname \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void url_and_title_and_author_and_blogname_and_description_are_entered(String url, String title, String author, String blogName, String description) throws Throwable {
        inputLines.add(url);
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(blogName);
        inputLines.add(description);
        runApp();
    }

    
    @When("^url \"([^\"]*)\" and title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void url_and_title_and_author_and_description_are_entered(String url, String title, String author, String description) throws Throwable {
        inputLines.add(url);
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(description);
        runApp();
    }

    
    @When("^url \"([^\"]*)\" and title \"([^\"]*)\" and podcast name \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void url_and_title_and_podcast_name_and_author_and_description(String url, String title, String podcastName, String author, String description) throws Throwable {
        inputLines.add(url);
        inputLines.add(title);
        inputLines.add(podcastName);
        inputLines.add(author);
        inputLines.add(description);
        runApp();
    }
    
    @When("^title \"([^\"]*)\" and podcast name \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void title_and_podcast_name_and_author_and_description(String title, String podcastName, String author, String description) throws Throwable {
        inputLines.add(title);
        inputLines.add(podcastName);
        inputLines.add(author);
        inputLines.add(description);
        runApp();
    }


    @Then("^message \"([^\"]*)\" is displayed$")
    public void message_is_displayed(String expectedOutput) throws Throwable {
        // Cucumber on outo olento
        expectedOutput = expectedOutput.replace("\\n", "\n");
        boolean found = false;
        for (String print : io.getPrints()) {
            if (print.contains(expectedOutput)) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @Then("^book is found$")
    public void book_is_found() throws Throwable {
        message_is_displayed("Title: Clean Code: A Handbook of Agile Software Craftsmanship");
    }

    private void runApp() {
        io = new StubIO(inputLines);
        app = new App(io, sugg);
        app.run();
    }

}
