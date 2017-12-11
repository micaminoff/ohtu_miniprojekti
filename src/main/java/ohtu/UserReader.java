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
        for (String tag : stringTags) {
            tags.add(new Tag(tag));
        }

        return tags;
    }

    private String readKeyForBook() {
        String ISBN = io.readLine("(*)ISBN:");
        while (!validator.ISBNIsValid(ISBN)) {
            io.print("ISBN must consist of only numbers and dashes and contain at least one of each and cannot end with a dash!");
            ISBN = io.readLine("ISBN is required (max length 100).\nISBN:");
        }
        return ISBN;
    }

    private Suggestable readAndCreateBook(String key) {
        String title = io.readLine("(*)Title:");
        while (!validator.lengthIsValid(title, 60, true)) {
            title = io.readLine("Title must be 1-60 characters long.\nTitle:");
        }
        String creator = io.readLine("(*)Author:");
        while (!validator.lengthIsValid(creator, 40, true)) {
            creator = io.readLine("Author's name must be 1-40 characters long.\nAuthor:");
        }

        String description = io.readLine("Description (optional):");
        while (!validator.lengthIsValid(description, 200, false)) {
            description = io.readLine("Description too long (over 200 characters).\nDescription:");
        }
        return new Book(title, creator, description, key);
    }

    private String readKeyForBlog() {
        String url = io.readLine("(*)URL:");
        while (!validator.URLIsValid(url)) {
            io.print("Malformed or empty URL! (Max length 100)");
            url = io.readLine("URL is required!\nUrl:");
        }
        return url;
    }

    private Suggestable readAndCreateBlog(String key) {
        String title = io.readLine("(*)Title:");
        while (!validator.lengthIsValid(title, 60, true)) {
            title = io.readLine("Title must be 1-60 characters long!\nTitle:");
        }
        String creator = io.readLine("(*)Author:");
        while (!validator.lengthIsValid(creator, 40, true)) {
            creator = io.readLine("Author's name must be 1-40 characters long.\nAuthor:");
        }

        String blogName = io.readLine("Blogname (optional):");
        while (!validator.lengthIsValid(blogName, 40, false)) {
            blogName = io.readLine("Name too long (over 40 characters).\nBlogname:");
        }
        String description = io.readLine("Description (optional):");
        while (!validator.lengthIsValid(description, 200, false)) {
            description = io.readLine("Description too long (over 200 characters).\nDescription:");
        }

        return new Blog(title, creator, description, key, blogName);
    }

    private String readKeyForVideo() {
        String url = io.readLine("(*)URL:");
        while (!validator.URLIsValid(url)) {
            io.print("Malformed or empty URL! (Max length 100)");
            url = io.readLine("URL is required!\nUrl:");
        }
        return url;
    }

    public Suggestable readAndCreateVideo(String key) {
        String title = io.readLine("(*)Title:");

        while (!validator.lengthIsValid(title, 60, true)) {
            title = io.readLine("Title must be 1-60 characters long!\nTitle:");
        }

        String creator = io.readLine("Creator (optional):");
        while (!validator.lengthIsValid(creator, 40, false)) {
            creator = io.readLine("Name too long (over 40 characters).\nCreator:");
        }
        String description = io.readLine("Description (optional):");
        while (!validator.lengthIsValid(description, 200, false)) {
            description = io.readLine("Description too long (over 200 characters).\nDescription:");
        }
        return new Video(title, creator, description, key);
    }

    private String readKeyForPodcast() {
        String url = io.readLine("(*)URL:");
        while (!validator.URLIsValid(url)) {
            io.print("Malformed or empty URL! (Max length 100)");
            url = io.readLine("URL is required!\nUrl:");
        }
        return url;
    }

    private Suggestable readAndCreatePodcast(String key) {
        String title = io.readLine("(*)Title:");

        while (!validator.lengthIsValid(title, 60, true)) {
            title = io.readLine("Title must be 1-60 characters long.\nTitle:");
        }
        String podcastName = io.readLine("(*)Podcast name:");
        while (!validator.lengthIsValid(podcastName, 60, true)) {
            podcastName = io.readLine("Name must be 1-60 characters long.!\nPodcast name:");
        }

        String creator = io.readLine("Creator (optional):");
        while (!validator.lengthIsValid(creator, 40, false)) {
            creator = io.readLine("Name too long (over 40 characters).\nName:");
        }
        String description = io.readLine("Description (optional):");
        while (!validator.lengthIsValid(description, 200, false)) {
            description = io.readLine("Description too long (over 200 characters).\nDescription:");
        }

        return new Podcast(title, creator, description, key, podcastName);
    }

}
