package bg.manhattan.spotifyplaylist.model.view;

import bg.manhattan.spotifyplaylist.model.entity.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeViewModel {
    private final List<SongViewModel> popSongs;
    private final List<SongViewModel> rockSongs;
    private final List<SongViewModel> jazzSongs;
    private final List<SongViewModel> playlist;

    private String totalDurationOfPlaylist;

    public HomeViewModel() {
        this(new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),0);
    }

    public HomeViewModel(List<SongViewModel> popSongs,
                         List<SongViewModel> rockSongs,
                         List<SongViewModel> jazzSongs,
                         List<SongViewModel> playlist,
                        Integer toTotalDuration) {
        this.popSongs = popSongs;
        this.rockSongs = rockSongs;
        this.jazzSongs = jazzSongs;
        this.playlist = playlist;
        this.totalDurationOfPlaylist = String.format("%d:%02d", toTotalDuration / 60, toTotalDuration % 60);

    }

    public List<SongViewModel> getPopSongs() {
        return Collections.unmodifiableList(popSongs);
    }

    public List<SongViewModel> getRockSongs() {
        return Collections.unmodifiableList(rockSongs);
    }

    public List<SongViewModel> getJazzSongs() {
        return Collections.unmodifiableList(jazzSongs);
    }

    public List<SongViewModel> getPlaylist() {
        return Collections.unmodifiableList(playlist);
    }

    public String getTotalDurationOfPlaylist() {
        return totalDurationOfPlaylist;
    }
}
