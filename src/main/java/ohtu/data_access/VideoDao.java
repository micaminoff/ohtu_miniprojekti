package ohtu.data_access;

import java.sql.SQLException;
import java.util.List;
import ohtu.domain.Video;

public interface VideoDao {
    
    List<Video> listAll();
    List<Video> findByTitle(String title);
    List<Video> findByCreator(String creator);
    List<Video> findByDescription(String description);
    Video findByUrl(String url) throws SQLException;
    //etsimismetodi avaimella
    void add(Video video);
    void remove(Video video);
}
