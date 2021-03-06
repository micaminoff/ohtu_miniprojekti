package ohtu.data_access;

import java.util.HashMap;
import java.util.List;
import ohtu.domain.Video;

public interface InterfaceVideoDao {
    
    List<Video> listAll();
    List<Video> findByTitle(String title);
    List<Video> findByCreator(String creator);
    HashMap<String, Video> findByAll(String arg);
    List<Video> findByDescription(String description);
    Video findByUrl(String url);
    void add(Video video);
    void remove(Video video);
    void update(Video video);
}
