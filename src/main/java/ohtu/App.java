package ohtu;

import ohtu.io.ConsoleIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import ohtu.data_access.BookDao;
import ohtu.data_access.InMemoryBookDao;
import ohtu.domain.Book;
import ohtu.domain.Suggestable;
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
        
        System.out.println("Tervetuloa!");
        while (true) {
            String command = io.readLine("\nKomento (listaa tai lisää)");
            
            if (command.isEmpty()) {
                break;
            }
            if (command.equals("listaa")) {
                for (Suggestable suggestable: sugg.listAll()) {
                    io.print("Tekijä: " + suggestable.getCreator() + "\nOtsikko: " + suggestable.getTitle() + "\nKuvaus: " + suggestable.getDescription() + "\n");
                }
            } else if (command.equals("lisää")) {
                lisaa();
            }
        }

    }
    
    public void lisaa() {
        String command = io.readLine("Mitä lisätään? (kirja)");
        if (command.equals("kirja")) {
            String creator = io.readLine("Tekijä: ");
            String title = io.readLine("Otsikko: ");
            String description = io.readLine("Kuvaus: ");
            if (sugg.addBook(creator, title, description)) {
                io.print("Kirja lisätty!");
            } else {
                io.print("Kirjan lisääminen ei onnistunut!");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //SQL TESTI
        
        
        BookDao bookDao = new InMemoryBookDao();
        IO io = new ConsoleIO();
        SuggestionService sugg = new SuggestionService(bookDao);
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
