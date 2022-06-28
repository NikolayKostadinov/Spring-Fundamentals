package bg.manhattan.spotifyplaylist.repository;

import bg.manhattan.spotifyplaylist.model.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    public Optional<Song> findByPerformer(String performer);
}
