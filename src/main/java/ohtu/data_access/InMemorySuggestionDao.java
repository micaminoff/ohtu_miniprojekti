/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.Blog;
import ohtu.domain.Book;
import ohtu.domain.Podcast;
import ohtu.domain.Suggestable;
import ohtu.domain.Suggestion;
import ohtu.domain.Type;
import static ohtu.domain.Type.*;
import ohtu.domain.Video;

public class InMemorySuggestionDao implements InterfaceSuggestionDao {

    private List<Suggestion> suggestions;
    private InterfaceBookDao bookDao;
    private InterfaceBlogDao blogDao;
    private InterfacePodcastDao podcastDao;
    private InterfaceVideoDao videoDao;

    public InMemorySuggestionDao(InterfaceBookDao bookDao, InterfaceBlogDao blogDao, InterfacePodcastDao podcastDao, InterfaceVideoDao videoDao) {
        suggestions = new ArrayList();
        this.bookDao = bookDao;
        this.blogDao = blogDao;
        this.podcastDao = podcastDao;
        this.videoDao = videoDao;

        // Nämä testidatat on siirrettävä suggestionserviceen.
        suggestions.add(new Suggestion(new Book("Clean Code: A Handbook of Agile Software Craftsmanship", "Robert Martin", "Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship", "978-951-98548-9-2")));
        suggestions.add(new Suggestion(new Blog("JRE #002 - MMA Show #2", "Joe Rogan", "Eddie Bravo needs help", "http://podcasts.joerogan.net/podcasts/mma-show-2", "The Joe Rogan Experience")));

    }

    @Override
    public List<Suggestion> listAll() {
        return suggestions;
    }

    @Override
    public void add(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    @Override
    public void remove(Suggestion s) {
        suggestions.remove(s);
    }

    @Override
    public List<Suggestion> findByTitle(String title) {
        List<Suggestion> listRet = new ArrayList();
        List<Book> books = null;
        List<Blog> blogs;
        List<Podcast> podcasts;
        List<Video> videos;

        try {
            books = bookDao.findByTitle(title);
        } catch (SQLException ex) {
            Logger.getLogger(InMemorySuggestionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        blogs = blogDao.findByTitle(title);
        podcasts = podcastDao.findByTitle(title);
        videos = videoDao.findByTitle(title);

        for (int i = 0; i < books.size(); i++) {
            Suggestable book = (Suggestable) books.get(i);

        }

        for (Suggestion suggestion : suggestions) {
            Suggestable suggestable = suggestion.getSuggestable();
            if (suggestion.getType() == BOOK) {
                for (int i = 0; i < books.size(); i++) {
                    if (suggestable.equals(books.get(i))) {
                        listRet.add(suggestion);
                    }
                }
            } else if (suggestion.getType() == BLOG) {
                for (int i = 0; i < blogs.size(); i++) {
                    if (suggestable.equals(blogs.get(i))) {
                        listRet.add(suggestion);
                    }
                }
            } else if (suggestion.getType() == PODCAST) {
                for (int i = 0; i < podcasts.size(); i++) {
                    if (suggestable.equals(podcasts.get(i))) {
                        listRet.add(suggestion);
                    }
                }
            } else if (suggestion.getType() == VIDEO) {
                for (int i = 0; i < videos.size(); i++) {
                    if (suggestable.equals(videos.get(i))) {
                        listRet.add(suggestion);
                    }
                }
            }
            
        }
        return listRet;
    }

    //huom equals metodit pitää tehdä
    @Override
    public boolean containsSuggestionForSuggestable(Suggestable suggestable) {
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getSuggestable().equals(suggestable)) {
                return true;
            }
        }
        return false;
    }

}
