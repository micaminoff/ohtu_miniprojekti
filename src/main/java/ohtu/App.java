package ohtu;

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

    public void run() {

        System.out.println("Welcome!");
        while (true) {
            String command = io.readLine("\nCommand (list, find or add, empty command exits program)");

            if (command.isEmpty()) {
                break;
            }
            if (command.equals("list")) {
                list();
            } else if (command.equals("add")) {
                add();
            } else if (command.equals("find")) {
                find();
            } else {
                io.print("Unknown command!");
            }
        }

    }

    public void add() {
        String command = io.readLine("What would you like to add? (types: book, blog, video, podcast)");
        // KIRJAN LISÄYS
        if (command.equals("book")) {
            String title = io.readLine("Title: ");
            String creator = io.readLine("Author: ");
            
            if (title.isEmpty() || creator.isEmpty()) {
                io.print("Book must have atleast title and author!");
            }
            
            Book book = sugg.findBookByTitleAndCreator(title, creator);
                
            if (book == null) {
                String description = io.readLine("Description: ");
                String ISBN = io.readLine("ISBN: ");
                book = new Book(title, creator, description, ISBN);
            }
            
            if (sugg.addSuggestionWithBook(book)) {
                io.print("New suggestion with book added!");
            } else {
                io.print("Adding a new suggestion with book failed!");
            }
            
            //vanha kirjan lisäys
//            String creator = io.readLine("Author: ");
//            String title = io.readLine("Title: ");
//            String description = io.readLine("Description: ");
//            String ISBN = io.readLine("ISBN: ");
//            if (sugg.addSuggestionWithBook(title, creator, description, ISBN)) {
//                io.print("New suggestion with book added!");
//            } else {
//                io.print("Adding a new suggestion with book failed!");
//            }
        // BLOGIN LISÄYS
        } else if (command.equals("blog")) {
            String title = io.readLine("Title: ");
            String creator = io.readLine("Writer: ");
            String url = io.readLine("URL: ");
            String blogName = io.readLine("Blogname (optional): ");
            String description = io.readLine("Description (optional): ");
            
            if (title.isEmpty() || creator.isEmpty() || url.isEmpty()) {
                io.print("Blog must have atleast title, writer and url!");
                return;
            }
            
            Blog blog = sugg.findBlogByURL(url);
            if (blog == null) {
                blog = (new Blog(title, creator, url, blogName, description));
            }
            
            if (sugg.addSuggestionWithBlog(blog)) {
                io.print("New suggestion with blog added!");
            } else {
                io.print("Failed to add suggestion with blog!");
            }
        // VIDEON LISÄYS
        } else if (command.equals("video")) {
            String title = io.readLine("Title: ");
            String creator = io.readLine("Creator (optional): ");
            String url = io.readLine("URL: ");
            String description = io.readLine("Description (optional): ");
            
            if (title.isEmpty() || url.isEmpty()) {
                io.print("Video must have atleast title and url!");
                return;
            }
            
            Video video = sugg.findVideoByURL(url);
            if (video == null) {
                video = new Video(title, creator, description, url);
            }
            
            if (sugg.addSuggestionWithVideo(video)) {
                io.print("New suggestion with video added!");
            } else {
                io.print("Failed to add suggestion with video!");
            }
            
        //PODCASTIN LISÄYS
        } else if (command.equals("podcast")) {
            String podcastName = io.readLine("Podcast Name: ");
            String episodeName = io.readLine("Episode name: ");
            String url = io.readLine("URL: ");
            String creator = io.readLine("Writer (optional): ");
            String description = io.readLine("Description (optional): ");
            
            if (podcastName.isEmpty() || episodeName.isEmpty() || url.isEmpty()) {
                io.print("Podcast must have atleast podcast name, episode name and url!");
                return;
            }
            
            Podcast podcast = sugg.findPodcastByURL(url);
            
            if (podcast == null) {
                podcast = new Podcast(podcastName, creator, url, episodeName, description);
            }
            
            if (sugg.addSuggestionWithPodcast(podcast)) {
                io.print("New suggestion with podcast added!");
            } else {
                io.print("Failed to add suggestion with podcast!");
            }
            
        } else {
            io.print("Unknown command!");
        }
    }

    public void list() {
        for (Suggestion s : sugg.listAll()) {
            System.out.println("\n" + s);
        }
    }

    public void find() {

        List<Suggestable> booksFound = new ArrayList();
        String command;
        //Tänne tarvitaan nyt kysymys, what would you like to find? (book, blog, podcast, all)(Choose one)
        //Sit tehdään omat metodit tänne, eli findBook(), findBlog() jne.
        //Ja toi alempi while true -lause laitetaan sit sinne metodiin
        //findAll() :ssa on vain Vinkin omia attribuutteja, eli tags, related courses ...HUOM! EI tyyppi, koska 
        //silloin toi aikasempi kysely olis turha ja esim listaus olis turha metodi silloin. (koska sinne tulee
        //kuitenkin tulostus kaikista tyypin mukaan)
        //Toteutetaan siten, että toi Dao rajapinta otetaan vittuun, se on turha. Tehdään vaan esim. luokka
        //BookDao1 joka toeuttaa rajapinnan BookDao ja muille sama homma. Ja tonne BookDao1 luokkaan laitetaan
        //kaikki sql-paska. Näin tän ohjelman rakenne ei muutu kauheasti. 
        //Eli appissa kutsutaan SuggestionServiceä, jossa on sit oltava myös metodit findBlogByTitle() esim.
        //Eli SuggestionServiceen tulee miljoona metodia. 
        //Edelleen SuggestionService kutsuu omia dao-luokkiaan ja niiden metodeja. Esim. SuggestionServicen metodi
        //findBookByTitle() kutsuu BookDao1:n metodia findByTitle() jossa tapahtuu kaikki sql hakeminen.
   

        while (true) {
            command = io.readLine("Find by (title, description, creator, isbn, q = back): ");
            if (command.equals("title")) {
                String command_title = io.readLine("Give title: ");
                List<Book> booksByTitle = sugg.findBookByTitle(command_title);
                if (!booksByTitle.isEmpty()) {
                    booksFound.addAll(booksByTitle);
                }
                break;

            } else if (command.equals("description")) {
                String command_description = io.readLine("Give description: ");
                List<Book> booksByDesc = sugg.findBookByDescription(command_description);
                if (!booksByDesc.isEmpty()) {
                    booksFound.addAll(booksByDesc);
                }
                break;

            } else if (command.equals("creator")) {
                String command_creator = io.readLine("Give creator: ");
                List<Book> booksByCreator = sugg.findBookByCreator(command_creator);
                if (!booksByCreator.isEmpty()) {
                    booksFound.addAll(booksByCreator);
                }
                break;

            } else if (command.equals("isbn")) {
                String command_isbn = io.readLine("Give isbn: ");

                if (sugg.findBookByISBN(command_isbn) != null) {
                    booksFound.add(sugg.findBookByISBN(command_isbn));
                }
                break;
                
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println("Unknown command!");
            }
        }
        if (!command.equals("q")) {
            if (!booksFound.isEmpty()) {
                for (Suggestable suggestable : booksFound) {
                    Book book = (Book) suggestable;
                    io.print("Author: " + book.getCreator() + "\nTitle: " + book.getTitle() + "\nDescription: " + book.getDescription() + "\nISBN: " + book.getISBN() + "\n");
                }
            } else {
                io.print("No books found.");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        //SQL TESTI

        SuggestionDao suggestionDao = new InMemorySuggestionDao();
        BookDao bookDao = new InMemoryBookDao();
        BlogDao blogDao = new InMemoryBlogDao();
        PodcastDao podcastDao = new InMemoryPodcastDao();
        VideoDao videoDao = new InMemoryVideoDao();
        IO io = new ConsoleIO();
        SuggestionService sugg = new SuggestionService(suggestionDao, bookDao, blogDao, podcastDao, videoDao);
        new App(io, sugg).run();

//        Class.forName("org.sqlite.JDBC");
//        Connection c = DriverManager.getConnection("jdbc:sqlite:sql/database.db");
//        Statement s = c.createStatement();
//        
//        ResultSet rs = s.executeQuery("SELECT * FROM Book");
//
//        System.out.println("\nTietokannassa olevat kirjat:\n");
//        
//        while (rs.next()) {
//            System.out.println(
//                    "id: " + rs.getString("id") + "\n" +
//                    "title: " + rs.getString("title") + "\n" + 
//                    "author: " + rs.getString("author") + "\n" + 
//                    "ISBN: " + rs.getString("isbn") + "\n");
//        }
//        UserDao dao = new InMemoryUserDao();
//        IO io = new ConsoleIO();
//        AuthenticationService auth = new AuthenticationService(dao);
//        new App(io, auth).run();
    }

}
