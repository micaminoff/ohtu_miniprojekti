/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.data_access;

import java.util.List;
import ohtu.domain.Podcast;

/**
 *
 * @author otsepp
 */
public interface PodcastDao {
    
    List<Podcast> listAll();
    List<Podcast> findByTitle(String podcast);
    List<Podcast> findByCreator(String podcast);
    Podcast findByUrl(String url);
    List<Podcast> findByPodcastName(String podcastName);
    //etsimismetodi avaimella
    void add(Podcast podcast);
    //delete
    
}
