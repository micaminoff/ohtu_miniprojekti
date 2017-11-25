package ohtu.data_access;

import java.util.ArrayList;
import java.util.List;
import ohtu.domain.Podcast;

public class InMemoryPodcastDao implements PodcastDao {
    private List<Podcast> podcasts;
    
    public InMemoryPodcastDao() {
        this.podcasts = new ArrayList();
    }
    
    @Override
    public List<Podcast> listAll() {
        return podcasts;
    }
    
    @Override
    public List<Podcast> findByTitle(String title) {
        ArrayList<Podcast> podcastsReturn = new ArrayList();
        for (Podcast podcast : podcasts) {
            if (podcast.getTitle().toLowerCase().contains(title.toLowerCase())) {
                podcastsReturn.add(podcast);
            }
        }
        return podcastsReturn;
    }
    
    @Override
    public List<Podcast> findByCreator(String creator) {
        ArrayList<Podcast> podcastsReturn = new ArrayList();
        for (Podcast podcast : podcasts) {
            if (podcast.getCreator().toLowerCase().contains(creator.toLowerCase())) {
                podcastsReturn.add(podcast);
            }
        }
        return podcastsReturn;
    }

    @Override
    public Podcast findByUrl(String url) {
       for (Podcast podcast : podcasts) {
            if (podcast.getUrl().toLowerCase().contains(url.toLowerCase())) {
                return podcast;
            }
        }
       return null;
    }
    
    @Override
    public List<Podcast> findByPodcastName(String podcastName) {
        ArrayList<Podcast> podcastsReturn = new ArrayList();
        for (Podcast podcast : podcasts) {
            if (podcast.getPodcastName().toLowerCase().contains(podcastName.toLowerCase())) {
                podcastsReturn.add(podcast);
            }
        }
        return podcastsReturn;
    }
    
    @Override
    public void add(Podcast podcast) {
        podcasts.add(podcast);
    }
    
}
