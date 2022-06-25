package bg.manhattan.spotifyplaylist.service;

import bg.manhattan.spotifyplaylist.model.entity.Song;
import bg.manhattan.spotifyplaylist.model.service.SongServiceModel;

import java.util.List;
import java.util.Optional;

public interface SongService {
    Optional<SongServiceModel> getSongByPerformer(String performer);

    void addSong(SongServiceModel songModel);

    List<SongServiceModel> getAllSongs();

    Song getById(Long id);
}
