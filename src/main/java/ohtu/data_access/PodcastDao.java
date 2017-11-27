package ohtu.data_access;

import java.util.List;
import ohtu.domain.Podcast;

public interface PodcastDao {
    
    List<Podcast> listAll();
    List<Podcast> findByTitle(String podcast);
    List<Podcast> findByCreator(String podcast);
    Podcast findByUrl(String url);
    List<Podcast> findByPodcastName(String podcastName);
    //etsimismetodi avaimella
    void add(Podcast podcast);
    //void delete(Podcast pocast);
    
}
