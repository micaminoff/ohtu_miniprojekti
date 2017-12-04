package ohtu.data_access;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import ohtu.domain.Podcast;

public interface InterfacePodcastDao {
    
    List<Podcast> listAll();
    List<Podcast> findByTitle(String podcast);
    List<Podcast> findByCreator(String podcast);
    HashMap<String, Podcast> findByAll(String arg) throws SQLException;
    Podcast findByUrl(String url) throws SQLException;
    List<Podcast> findByPodcastName(String podcastName);
    //etsimismetodi avaimella
    void add(Podcast podcast);
    void remove(Podcast pocast);
    
}
