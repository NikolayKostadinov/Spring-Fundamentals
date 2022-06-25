package bg.manhattan.spotifyplaylist.service.impl;

import bg.manhattan.spotifyplaylist.model.entity.Song;
import bg.manhattan.spotifyplaylist.model.entity.Style;
import bg.manhattan.spotifyplaylist.model.service.SongServiceModel;
import bg.manhattan.spotifyplaylist.repository.SongRepository;
import bg.manhattan.spotifyplaylist.service.SongService;
import bg.manhattan.spotifyplaylist.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository repository;
    private final StyleService styleService;
    private final ModelMapper mapper;

    public SongServiceImpl(SongRepository repository,
                           StyleService styleService,
                           ModelMapper mapper) {
        this.repository = repository;
        this.styleService = styleService;
        this.mapper = mapper;
    }

    @Override
    public Optional<SongServiceModel> getSongByPerformer(String performer) {
        Optional<Song> dbSong = this.repository.findByPerformer(performer);
        return dbSong.map(song -> this.mapper.map(song, SongServiceModel.class));
    }

    @Override
    public void addSong(SongServiceModel songModel) {
        Style style = this.styleService.findByName(songModel.getStyle())
                .orElseThrow(()->new IllegalArgumentException(
                        String.format("Style [%s] not found!", songModel.getStyle())));
        Song song = this.mapper.map(songModel, Song.class)
                .setStyle(style);
        this.repository.save(song);
    }

    @Override
    public List<SongServiceModel> getAllSongs() {
        return this.repository.findAll()
                .stream()
                .map(song -> this.mapper.map(song, SongServiceModel.class))
                .toList();
    }

    @Override
    public Song getById(Long id) {
       return this.repository.findById(id).orElse(null);
    }
}
