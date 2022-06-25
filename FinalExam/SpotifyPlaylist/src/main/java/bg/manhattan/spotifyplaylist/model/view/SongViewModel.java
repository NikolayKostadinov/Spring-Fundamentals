package bg.manhattan.spotifyplaylist.model.view;

public class SongViewModel {
    private Long id;
    private String performer;
    private String title;
    private String duration;

    public Long getId() {
        return id;
    }

    public SongViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongViewModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public SongViewModel setDuration(String duration) {
        this.duration = duration;
        return this;
    }
    
}
