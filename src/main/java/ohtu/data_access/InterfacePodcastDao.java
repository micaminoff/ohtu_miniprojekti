package ohtu.data_access;

import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Podcast;

public interface InterfacePodcastDao {
    
    List<Podcast> listAll();
    List<Podcast> findByTitle(String podcast);
    List<Podcast> findByCreator(String podcast);
    Podcast findByUrl(String url) throws SQLException;
    List<Podcast> findByPodcastName(String podcastName);
    //etsimismetodi avaimella
    void add(Podcast podcast);
    void remove(Podcast pocast);
    
}
