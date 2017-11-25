package ohtu;

import ohtu.io.ConsoleIO;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.BookDao;
import ohtu.data_access.InMemoryBookDao;
import ohtu.data_access.InMemorySuggestionDao;
import ohtu.data_access.SuggestionDao;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
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
        String command = io.readLine("What would you like to add? (types: book)");
        if (command.equals("book")) {
            
            String creator = io.readLine("Author: ");
            String title = io.readLine("Title: ");
            String description = io.readLine("Description: ");
            String ISBN = io.readLine("ISBN: ");
            if (sugg.addSuggestionWithBook(title, creator, description, ISBN)) {
                io.print("New suggestion with book added!");
            } else {
                io.print("Adding a new suggestion with book failed!");
            }
        } else {
            io.print("Unknown command!");
        }
    }

    public void list() {
        for (Suggestion s : sugg.listAll()) {
            //toString tähän
        }
    }

    public void find() {

        List<Suggestable> booksFound = new ArrayList();
        String command;

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

        BookDao bookDao = new InMemoryBookDao();
        SuggestionDao suggestionDao = new InMemorySuggestionDao();
        IO io = new ConsoleIO();
        SuggestionService sugg = new SuggestionService(bookDao, suggestionDao);
        new App(io, sugg).run();

        sugg.addBook("Paavon kirja", "Paavo", "Moi oon Paavo", "1337");

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
