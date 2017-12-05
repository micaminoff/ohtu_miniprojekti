package ohtu;

import java.sql.SQLException;
import ohtu.io.ConsoleIO;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.*;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
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
            String command = io.readLine("\nCommand (list, find, remove or add, empty command exits program):");
            if (command.isEmpty()) {
                break;
            }
            if (command.equals("list")) {
                list();
            } else if (command.equals("add")) {
                add();
            } else if (command.equals("find")) {
                find();
            } else if (command.equals("remove")) {
                remove();
            } else {
                io.print("Unknown command!");
            }
        }

    }

    //ei toimi vielä suggestionservicessä
    public void remove() throws SQLException {
        List<Suggestion> suggestions = sugg.listAllSuggestions();
        for (int i = 0; i < suggestions.size(); i++) {
            io.print("\n" + i + ".:\n" + suggestions.get(i));
        }
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
        while (ISBN.isEmpty()) {
            ISBN = io.readLine("ISBN is required\nISBN:");
        }
//        Book book = sugg.findBookByTitleAndCreator(title, creator);
        Book book = sugg.findBookByISBN(ISBN);  //en jaksanut toteuttaa yllä olevaa

        if (book == null) {
            String description = io.readLine("Description (optional):");
            book = new Book(title, creator, description, ISBN);
            sugg.addBook(book);
        } else {
            io.print("\nFound the following book:");
            io.print(book.toString());
        }

        if (sugg.addSuggestion(book)) {
            io.print("New suggestion with book added!");
        } else {
            io.print("Adding a new suggestion with book failed!");
        }
    }

    private void addBlog() throws SQLException {
        String url = io.readLine("(*)URL:");

        while (url.isEmpty()) {
            url = io.readLine("URL is required!\nUrl:");
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

        while (url.isEmpty()) {
            url = io.readLine("URL is required!\nURL:");
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

        while (url.isEmpty()) {
            url = io.readLine("URL is required!\nURL:");
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

    public void list() throws SQLException {
        List<Suggestion> suggestions = sugg.listAllSuggestions();
        if (suggestions.isEmpty()) {
            io.print("\nThere are no suggestions yet! Type 'add' to add a new one");
        } else {
            for (Suggestion s : suggestions) {
                io.print("\n" + s);
            }
        }
    }
//*************VANHA FIND PELKILLE KIRJOILLE*******************
//    public void find() throws SQLException {
//        List<Suggestable> booksFound = new ArrayList();
//        String command;
//        while (true) {
//            command = io.readLine("Find by (any ,title, description, creator, isbn, q = back): ");
//            if (command.equals("title")) {
//                String command_title = io.readLine("Give title: ");
//                List<Book> booksByTitle = sugg.findBookByTitle(command_title);
//                if (!booksByTitle.isEmpty()) {
//                    booksFound.addAll(booksByTitle);
//                }
//                break;
//
//            } else if (command.equals("description")) {
//                String command_description = io.readLine("Give description: ");
//                List<Book> booksByDesc = sugg.findBookByDescription(command_description);
//                if (!booksByDesc.isEmpty()) {
//                    booksFound.addAll(booksByDesc);
//                }
//                break;
//
//            } else if (command.equals("creator")) {
//                String command_creator = io.readLine("Give creator: ");
//                List<Book> booksByCreator = sugg.findBookByCreator(command_creator);
//                if (!booksByCreator.isEmpty()) {
//                    booksFound.addAll(booksByCreator);
//                }
//                break;
//
//            } else if (command.equals("isbn")) {
//                String command_isbn = io.readLine("Give isbn: ");
//
//                if (sugg.findBookByISBN(command_isbn) != null) {
//                    booksFound.add(sugg.findBookByISBN(command_isbn));
//                }
//                break;
//
//            } else if (command.equals("q")) {
//                break;
//            } else {
//                io.print("Unknown command!");
//                break;
//            }
//        }
//        if (!command.equals("q")) {
//            if (!booksFound.isEmpty()) {
//                for (Suggestable suggestable : booksFound) {
//                    //Nyt tarvitaan vielä suggestionille metodi findSuggestionById()
//                    Book book = (Book) suggestable;
//                    io.print("Author: " + book.getCreator() + "\nTitle: " + book.getTitle() + "\nDescription: " + book.getDescription() + "\nISBN: " + book.getISBN() + "\n");
//                }
//            } else {
//                io.print("No books found.");
//            }
//        }
//
//    }

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
                for (Suggestion suggestion : suggestions_found) {
                    io.print(suggestion.getSuggestable().toString());
                }
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
        Database database = new Database("jdbc:sqlite:src/main/resources/sql/database.db");

        InterfaceBookDao bookDao = new SQLBookDao(database);
        InterfaceBlogDao blogDao = new SQLBlogDao(database);
        InterfaceVideoDao videoDao = new SQLVideoDao(database);
        InterfacePodcastDao podcastDao = new SQLPodcastDao(database);
        InterfaceSuggestionDao suggestionDao = new SQLSuggestionDao(database, bookDao, blogDao, podcastDao, videoDao);
        SuggestionService sugg = new SuggestionService(suggestionDao, bookDao, blogDao, podcastDao, videoDao);
        
        // Tässä kommentoituna mahdollisuus kutsua esimerkkidatan lisäämistä.
//        if (sugg.listAllSuggestions().isEmpty()) {
//            sugg.fillWithExampleData();
//        }
        IO io = new ConsoleIO();
        new App(io, sugg).run();
    }

}
