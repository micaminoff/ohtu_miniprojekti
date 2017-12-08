package ohtu;

import java.sql.SQLException;
import ohtu.io.ConsoleIO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.data_access.*;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
import ohtu.domain.Tag;
import ohtu.domain.Video;
import ohtu.io.IO;
import ohtu.services.SuggestionService;

public class App {

    private IO io;
    private SuggestionService sugg;

    public App(IO io, SuggestionService sugg) {
        this.io = io;
        this.sugg = sugg;
    }

    public void run() throws SQLException {
        io.print("Welcome!");
        while (true) {
            String command = io.readLine("\nCommand (list, find, remove, add or edit empty command exits program):");
            if (command.isEmpty()) {
                break;
            }
           if (command.equals("list")) {
                list(sugg.listAllSuggestions(), false);
            } else if (command.equals("add")) {
                add();
            } else if (command.equals("find")) {
                find();
            } else if (command.equals("remove")) {
                remove();
            } else if (command.equals("edit")) {
                edit();
            } else {
                io.print("Unknown command!");
            }
        }

    }

    public void edit() throws SQLException {
        String input = io.readLine("\nSearch suggestions to edit (type) y");
        List<Suggestion> suggestions = null;
        
        if (input.equals("y")) {
            String arg = io.readLine("Enter keyword:");
            suggestions = sugg.findByAll(arg);
        } else {
            suggestions = sugg.listAllSuggestions();
        }
        
        list(suggestions, true);
        
        io.print("\nChoose suggestion to edit:");
        
        input = io.readLine("");

        if (input.matches("\\d+")) {
            int index = Integer.parseInt(input);

            if (index >= 0 && index < suggestions.size()) {
                io.print("\nEditing following suggestion:");
                io.print(suggestions.get(index).toString());
                
                String attribute = io.readLine("\nChoose attribute to edit:");
                String newContent = io.readLine("\nEnter new content:");
                
                io.print("NOT YET IMPLEMENTED!");
                return;
            }
        }
        io.print("Incorrect index given!");
    }
    
    public void remove() throws SQLException {
        String ans = io.readLine("\nSearch suggestions to remove (type y)?");

        List<Suggestion> suggestions = null;
        
        if (ans.equals("y")) {
            String arg = io.readLine("Enter keyword:");
            suggestions = sugg.findByAll(arg);
        } else {
            suggestions = sugg.listAllSuggestions();
        }
        
        list(suggestions, true);

        io.print("\nChoose suggestion to remove:");
        String input = io.readLine("");

        if (input.matches("\\d+")) {
            int index = Integer.parseInt(input);

            if (index >= 0 && index < suggestions.size()) {
                String confirm = io.readLine("Are you sure? (type y)");
                if (confirm.equals("y")) {
                    sugg.removeSuggestion(suggestions.get(index));
                    io.print("Suggestion removed!");
                }
                return;
            }
        }
        io.print("Incorrect index given!");
    }

    public void add() throws SQLException {
        String command = io.readLine("What would you like to add? (types: book, blog, video, podcast)");
        if (command.equals("book")) {
            addBook();
        } else if (command.equals("blog")) {
            addBlog();
        } else if (command.equals("video")) {
            addVideo();
        } else if (command.equals("podcast")) {
            addPodcast();
        } else {
            io.print("Unknown command!");
        }
    }

    private void addBook() throws SQLException {
        String title = io.readLine("(*)Title:");
        while (title.isEmpty()) {
            title = io.readLine("Title is required\nTitle:");
        }
        String creator = io.readLine("(*)Author:");
        while (creator.isEmpty()) {
            creator = io.readLine("Author is required\nAuthor:");
        }
        String ISBN = io.readLine("(*)ISBN:");
        Pattern p = Pattern.compile("^([0-9]+[-])(([0-9]+[-]+)*([0-9]+)+)+$");
        Matcher m = p.matcher(ISBN);
        while (ISBN.isEmpty() || !m.find()) {
            if (!m.find()) {
                io.print("ISBN must consist of only numbers and dashes and contain at least one of each and cannot end with a dash!");
            }
            ISBN = io.readLine("ISBN is required\nISBN:");
            m = p.matcher(ISBN);
        }
//        Book book = sugg.findBookByTitleAndCreator(title, creator);
        Book book = sugg.findBookByISBN(ISBN);  //en jaksanut toteuttaa yllä olevaa
        
        List<Tag> tags = new ArrayList<>();
        
        if (book == null) {
            String description = io.readLine("Description (optional):");
            book = new Book(title, creator, description, ISBN);
            sugg.addBook(book);
            //Tagien lisääminen
            String inputTags = io.readLine("Tags (seperate with a space):");
            List<String> stringTags = new ArrayList<>();
            stringTags = Arrays.asList(inputTags.toLowerCase().split(" "));
            for (String tag: stringTags) {
                tags.add(new Tag(tag));
            }
            
        } else {
            io.print("\nThere already exist a book with that ISBN: \n");
            io.print(book.toString());
            book = null;
        }

        if (sugg.addSuggestion(book, tags)) {
            io.print("\nNew suggestion with book added!");
        } else {
            io.print("\nAdding a new suggestion with book failed!");
        }
    }

    private void addBlog() throws SQLException {
        String url = io.readLine("(*)URL:");
        Pattern p = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher m = p.matcher(url);
        while (url.isEmpty() || !m.find()) {
            if (!m.find()) {
                io.print("Malformed URL!");
            }
            url = io.readLine("URL is required!\nUrl:");
            m = p.matcher(url);
        }

        Blog blog = sugg.findBlogByURL(url);

        if (blog == null) {
            String title = io.readLine("(*)Title:");
            while (title.isEmpty()) {
                title = io.readLine("Title is required!\nTitle:");
            }
            String creator = io.readLine("(*)Author:");
            while (creator.isEmpty()) {
                creator = io.readLine("Author is required!\nAuthor:");
            }

            String blogName = io.readLine("Blogname (optional):");
            String description = io.readLine("Description (optional):");
            blog = new Blog(title, creator, description, url, blogName);
            sugg.addBlog(blog);
        } else {
            io.print("\n");
            io.print("Found the following blog:");
            io.print(blog.toString());
        }

        if (sugg.addSuggestion(blog)) {
            io.print("New suggestion with blog added!");
        } else {
            io.print("Failed to add suggestion with blog!");
        }
    }

    private void addVideo() throws SQLException {
        String url = io.readLine("(*)URL:");

        Pattern p = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher m = p.matcher(url);
        while (url.isEmpty() || !m.find()) {
            if (!m.find()) {
                io.print("Malformed URL!");
            }
            url = io.readLine("URL is required!\nUrl:");
            m = p.matcher(url);
        }

        Video video = sugg.findVideoByURL(url);

        if (video == null) {
            String title = io.readLine("(*)Title:");

            while (title.isEmpty()) {
                title = io.readLine("Title is required!\nTitle:");
            }

            String creator = io.readLine("Creator (optional):");
            String description = io.readLine("Description (optional):");
            video = new Video(title, creator, description, url);
            sugg.addVideo(video);
        } else {
            io.print("\n");
            io.print("Found the following video:");
            io.print(video.toString());
        }

        if (sugg.addSuggestion(video)) {
            io.print("New suggestion with video added!");
        } else {
            io.print("Failed to add suggestion with video!");
        }
    }

    private void addPodcast() throws SQLException {
        String url = io.readLine("(*)URL:");

        Pattern p = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher m = p.matcher(url);
        while (url.isEmpty() || !m.find()) {
            if (!m.find()) {
                io.print("Malformed URL!");
            }
            url = io.readLine("URL is required!\nUrl:");
            m = p.matcher(url);
        }

        Podcast podcast = sugg.findPodcastByURL(url);

        if (podcast == null) {
            String title = io.readLine("(*)Title:");
            while (title.isEmpty()) {
                title = io.readLine("Title is required!\nTitle:");
            }
            String podcastName = io.readLine("(*)Podcast name:");
            while (podcastName.isEmpty()) {
                podcastName = io.readLine("Podcast name is required!\nPodcast name:");
            }

            String creator = io.readLine("Creator (optional):");
            String description = io.readLine("Description (optional):");

            podcast = new Podcast(title, creator, description, url, podcastName);
            sugg.addPodcast(podcast);
        } else {
            io.print("\n");
            io.print("Found the following podcast:");
            io.print(podcast.toString());
        }

        if (sugg.addSuggestion(podcast)) {
            io.print("New suggestion with podcast added!");
        } else {
            io.print("Failed to add suggestion with podcast!");
        }

    }

    public void list(List<Suggestion> suggestions, boolean showIndexes) throws SQLException {
        if (suggestions.isEmpty()) {
            io.print("\nNo suggestions found.");
        } else {
            for (int i = 0; i < suggestions.size(); i++) {
                if (showIndexes)
                    io.print("\n" + i + ".:\n" + suggestions.get(i));
                else 
                    io.print("\n" + suggestions.get(i));
            }
        }
    }
    
    public void find() throws SQLException {
        List<Suggestion> suggestions_found = new ArrayList();
        String command;
        while (true) {
            command = io.readLine("Find by (any, q = back): ");
            if (command.equals("any")) {
                String command_any = io.readLine("Keyword: ");
                List<Suggestion> suggestionsByAll = sugg.findByAll(command_any);
                if (!suggestionsByAll.isEmpty()) {
                    suggestions_found.addAll(suggestionsByAll);
                }
                break;
            } else if (command.equals("q")) {
                break;
            } else {
                io.print("Unknown command!");
            }
        }
        
        if (!command.equals("q")) {
            if (!suggestions_found.isEmpty()) {
                list(suggestions_found, false);
                    
            } else {
                io.print("No suggestions found.");
            }
        }
    }

    public static void main(String[] args) throws Exception {

//        BookDao bookDao = new InMemoryBookDao();
//        BlogDao blogDao = new InMemoryBlogDao();
//        PodcastDao podcastDao = new InMemoryPodcastDao();
//        VideoDao videoDao = new InMemoryVideoDao();
//        SuggestionDao suggestionDao = new InMemorySuggestionDao(bookDao, blogDao, podcastDao, videoDao);
//        IO io = new ConsoleIO();
//        SuggestionService sugg = new SuggestionService(suggestionDao, bookDao, blogDao, podcastDao, videoDao);
//        new App(io, sugg).run();
        //Tästä kommentti pois niin toimii tietokannalla
        Database database = new Database("jdbc:sqlite:database.db");

        InterfaceBookDao bookDao = new SQLBookDao(database);
        InterfaceBlogDao blogDao = new SQLBlogDao(database);
        InterfaceVideoDao videoDao = new SQLVideoDao(database);
        InterfacePodcastDao podcastDao = new SQLPodcastDao(database);
        InterfaceTagDao tagDao = new SQLTagDao(database);
        InterfaceSuggestionDao suggestionDao = new SQLSuggestionDao(database, bookDao, blogDao, podcastDao, videoDao, tagDao);
        SuggestionService sugg = new SuggestionService(suggestionDao, bookDao, blogDao, podcastDao, videoDao);
        
        // Tässä kommentoituna mahdollisuus kutsua esimerkkidatan lisäämistä.
        if (sugg.listAllSuggestions().isEmpty()) {
            sugg.fillWithExampleData();
        }

        IO io = new ConsoleIO();
        new App(io, sugg).run();
    }

}
