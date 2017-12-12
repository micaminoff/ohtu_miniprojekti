package ohtu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private Pattern ISBNPattern;
    private Pattern URLPattern;
    
    public Validator() {
        this.ISBNPattern = Pattern.compile("^([0-9]+[-])(([0-9]+[-]+)*([0-9]+)+)+$");
        this.URLPattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }
    
    public boolean ISBNIsValid(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        Matcher m = ISBNPattern.matcher(input);
        return m.find();
    }
    
    public boolean URLIsValid(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        Matcher m = URLPattern.matcher(input);
        return m.find();
    }
    
}
