package ohtu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestable;
import ohtu.domain.Tag;
import ohtu.domain.Type;
import ohtu.domain.Video;
import ohtu.io.IO;

public class UserReader {
    private IO io;
    private Validator validator;
    
    public UserReader(IO io) {
        this.io = io;
        validator = new Validator();
    }
    
    public String readKey(Type t) {
        switch (t) {
            case BOOK:
                return readKeyForBook();
            case BLOG:
                return readKeyForBlog();
            case VIDEO:
                return readKeyForVideo();
            case PODCAST:
                return readKeyForPodcast();
        }
        return null;
    }
    
    public Suggestable readAndCreateSuggestable(Type t, String key) {
        switch (t) {
             case BOOK:
                return readAndCreateBook(key);
            case BLOG:
                return readAndCreateBlog(key);
            case VIDEO:
                return readAndCreateVideo(key);
            case PODCAST:
                 return readAndCreatePodcast(key);
        }
        return null;
    }
    
    public List<Tag> readAndCreateTags() {
        List<Tag> tags = new ArrayList();
        
        String inputTags = io.readLine("Tags (seperate with a space):");
        List<String> stringTags = new ArrayList<>();
        stringTags = Arrays.asList(inputTags.toLowerCase().split(" "));
        for (String tag: stringTags) {
            tags.add(new Tag(tag));
        }
        
        return tags;
    }
    
    private String readKeyForBook() {
       String ISBN = io.readLine("(*)ISBN:");
        while (!validator.ISBNIsValid(ISBN)) {
            io.print("ISBN must consist of only numbers and dashes and contain at least one of each and cannot end with a dash!");
            ISBN = io.readLine("ISBN is required\nISBN:");
        }
        return ISBN;
    }
    
     private Suggestable readAndCreateBook (String key) {
         String title = io.readLine("(*)Title:");
            while (title.isEmpty()) {
                title = io.readLine("Title is required\nTitle:");
            }
            
            String creator = io.readLine("(*)Author:");
            while (creator.isEmpty()) {
                creator = io.readLine("Author is required\nAuthor:");
            }
            
            String description = io.readLine("Description (optional):");
            return new Book(title, creator, description, key);
     }
    
    private String readKeyForBlog() {
       String url = io.readLine("(*)URL:");
        while (!validator.URLIsValid(url)) {
            io.print("Malformed or empty URL!");
            url = io.readLine("URL is required!\nUrl:");
        }
        return url;
    }
    
    private Suggestable readAndCreateBlog(String key) {
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
        
        return new Blog(title, creator, description, key, blogName);
    }
    
    private String readKeyForVideo() {
       String url = io.readLine("(*)URL:");
        while (!validator.URLIsValid(url)) {
            io.print("Malformed or empty URL!");
            url = io.readLine("URL is required!\nUrl:");
        }
        return url;
    }
    
    public Suggestable readAndCreateVideo(String key) {
        String title = io.readLine("(*)Title:");
        
        while (title.isEmpty()) {
            title = io.readLine("Title is required!\nTitle:");
        }
        
        String creator = io.readLine("Creator (optional):");
        String description = io.readLine("Description (optional):");
        return new Video(title, creator, description, key);
    }
    
    private String readKeyForPodcast() {
      String url = io.readLine("(*)URL:");
      while (!validator.URLIsValid(url)) {
          io.print("Malformed or empty URL!");
          url = io.readLine("URL is required!\nUrl:");
      }
      return url;
    }
    
    private Suggestable readAndCreatePodcast(String key) {
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
        
        return new Podcast(title, creator, description, key, podcastName);
    }
    
}
