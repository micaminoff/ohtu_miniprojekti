package ohtu.data_access;

import java.util.HashMap;
import java.util.List;
import ohtu.domain.Podcast;

public interface InterfacePodcastDao {
    
    List<Podcast> listAll();
    List<Podcast> findByTitle(String podcast);
    List<Podcast> findByCreator(String podcast);
    HashMap<String, Podcast> findByAll(String arg);
    Podcast findByUrl(String url);
    List<Podcast> findByPodcastName(String podcastName);
    void add(Podcast podcast);
    void remove(Podcast pocast);
    void update(Podcast podcast);
    
}
