package bg.manhattan.spotifyplaylist.model.service;

import java.time.LocalDate;

public class UserServiceSongModel {
    private Long id;
    private String performer;
    private String title;
    private Integer duration;
    private LocalDate releaseDate;

    public Long getId() {
        return id;
    }

    public UserServiceSongModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public UserServiceSongModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UserServiceSongModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public UserServiceSongModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public UserServiceSongModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }
}
