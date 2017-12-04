package ohtu.data_access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ohtu.domain.Video;

public class InMemoryVideoDao implements InterfaceVideoDao {
    private List<Video> videos;
    
    public InMemoryVideoDao() {
        videos = new ArrayList();
        videos.add(new Video("Turing Machine - Introduction (Part 1)",
                "Neso Academy", "TOC: Introduction to Turing Machine",
                "https://www.youtube.com/watch?v=PvLaPKPzq2I"));
    }
    
    @Override
    public List<Video> listAll() {
        return videos;
    }
    
    @Override
    public HashMap<String, Video> findByAll(String arg) {
        return null;
    }
    
    @Override
    public List<Video> findByTitle(String title) {
        ArrayList<Video> videosReturn = new ArrayList();
        for (Video video : videos) {
            if (video.getTitle().toLowerCase().contains(title.toLowerCase())) {
                videosReturn.add(video);
            }
        }
        return videosReturn;
    }
    
    @Override
    public List<Video> findByCreator(String creator) {
        ArrayList<Video> videosReturn = new ArrayList();
        for (Video video : videos) {
            if (video.getCreator().toLowerCase().contains(creator.toLowerCase())) {
                videosReturn.add(video);
            }
        }
        return videosReturn;
    }
    
    @Override
    public List<Video> findByDescription(String description) {
        ArrayList<Video> videosReturn = new ArrayList();
        for (Video video : videos) {
            if (video.getDescription().toLowerCase().contains(description.toLowerCase())) {
                videosReturn.add(video);
            }
        }
        return videosReturn;
    }
    
    @Override
    public Video findByUrl(String url) {
        for (Video video : videos) {
            if (video.getUrl().toLowerCase().contains(url.toLowerCase())) {
                return video;
            }
        }
        return null;
    }
    
    @Override
    public void add(Video video) {
        videos.add(video);
    }
    
    @Override
    public void remove(Video video) {
        videos.remove(video);
    }
    
}
