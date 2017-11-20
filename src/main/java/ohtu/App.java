package ohtu;

//import ohtu.data_access.InMemoryUserDao;
//import ohtu.data_access.UserDao;
import ohtu.io.ConsoleIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import ohtu.data_access.BookDao;
import ohtu.data_access.InMemoryBookDao;
import ohtu.domain.Book;
import ohtu.io.IO;
import ohtu.services.AuthenticationService;
import ohtu.services.SuggestionService;


public class App {

    private IO io;
//    private AuthenticationService auth;
    private SuggestionService sugg;

    public App(IO io, SuggestionService sugg) {
        this.io = io;
        this.sugg = sugg;
    }

    public String[] ask() {
        String[] userPwd = new String[2];
        userPwd[0] = io.readLine("username:");
        userPwd[1] = io.readLine("password:");
        return userPwd;
    }

    public void run() {
        
        System.out.println("Tervetuloa!");
        while (true) {
            String command = io.readLine("komento (listaa tai lisää)");
            
            if (command.isEmpty()) {
                break;
            }
            if (command.equals("listaa")) {
                for (Book book: sugg.getBookDao().listAll()) {
                    io.print("Tekijä: " + book.getCreator() + "\nOtsikko: " + book.getTitle() + "\nKuvaus: " + book.getDescription() + "\n");
                }
            } else if (command.equals("lisää")) {
                lisaa();
            }
        }
//        while (true) {
//            String command = io.readLine("komento (new tai login):");
//
//            if (command.isEmpty()) {
//                break;
//            }
//
//            if (command.equals("new")) {
//                String[] usernameAndPasword = ask();
//                if (auth.createUser(usernameAndPasword[0], usernameAndPasword[1])) {
//                    io.print("new user registered");
//                } else {
//                    io.print("new user not registered");
//                }
//
//            } else if (command.equals("login")) {
//                String[] usernameAndPasword = ask();
//                if (auth.logIn(usernameAndPasword[0], usernameAndPasword[1])) {
//                    io.print("logged in");
//                } else {
//                    io.print("wrong username or password");
//                }
//            }
//
//        }
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
    
    // testejä debugatessa saattaa olla hyödyllistä testata ohjelman ajamista
    // samoin kuin testi tekee, eli injektoimalla käyttäjän syötteen StubIO:n avulla
    //
    // UserDao dao = new InMemoryUserDao();  
    // StubIO io = new StubIO("new", "eero", "sala1nen" );   
    //  AuthenticationService auth = new AuthenticationService(dao);
    // new App(io, auth).run();
    // System.out.println(io.getPrints());
}
