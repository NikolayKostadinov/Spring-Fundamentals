package bg.manhattan.spotifyplaylist.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="songs")
public class Song extends BaseEntity{
    /**
     * Performer (unique, not null)
     * Performer name length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @Column(nullable = false, unique = true)
    private String performer;

    /**
     * Title (not null)
     * Title length must be between 2 and 20 characters (inclusive of 2 and 20).
     */
    @Column(nullable = false, length = 21)
    private String title;

    /**
     * Duration (not null)
     * The duration must be a positive number
     */
    @Column(nullable = false)
    private Integer duration;

    /**
     * Has a Release date (optional)
     * The Date that cannot be in the future
     */
    @Column(name="release_date")
    private LocalDate releaseDate;

    /**
     * Has a Style (not null)
     * One song has one style and one style can have many songs.
     */
    @ManyToOne(optional = false)
    private Style style;

    public String getPerformer() {
        return performer;
    }

    public Song setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Song setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Song setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Song setStyle(Style style) {
        this.style = style;
        return this;
    }
}
