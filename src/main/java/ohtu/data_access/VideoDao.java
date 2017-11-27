package ohtu.data_access;

import java.util.List;
import ohtu.domain.Video;

public interface VideoDao {
    
    List<Video> listAll();
    List<Video> findByTitle(String title);
    List<Video> findByCreator(String creator);
    List<Video> findByDescription(String description);
    Video findByUrl(String url);
    //etsimismetodi avaimella
    void add(Video video);
    //void delete(Video video);
}
