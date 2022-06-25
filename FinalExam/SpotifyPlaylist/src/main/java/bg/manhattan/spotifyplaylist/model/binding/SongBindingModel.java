package bg.manhattan.spotifyplaylist.model.binding;

import bg.manhattan.spotifyplaylist.model.entity.enums.StyleNameEnum;
import bg.manhattan.spotifyplaylist.model.validator.UniquePerformer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class SongBindingModel {
    /**
     * Performer (unique, not null)
     * Performer name length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @NotBlank(message = "Performer is required")
    @Size(min=2, max = 30, message = "Performer name length must be between 3 and 20 characters (inclusive of 3 and 20).")
    @UniquePerformer
    private String performer;

    /**
     * Title (not null)
     * Title length must be between 2 and 20 characters (inclusive of 2 and 20).
     */
    @NotBlank(message = "Title is required")
    @Size(min=2, max = 30, message = "Title length must be between 2 and 20 characters (inclusive of 2 and 20).")
    private String title;

    /**
     * Duration (not null)
     * The duration must be a positive number
     */
    @NotNull(message = "Duration is required")
    @Positive(message = "The duration must be positive!")
    private Integer duration;

    /**
     * Has a Release date (optional)
     * The Date that cannot be in the future
     */
    @PastOrPresent(message = "The Date that cannot be in future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    /**
     * Has a Style (not null)
     * One song has one style and one style can have many songs.
     */
    @NotNull(message = "You must select a style!")
    private StyleNameEnum style;

    public String getPerformer() {
        return performer;
    }

    public SongBindingModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongBindingModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleNameEnum getStyle() {
        return style;
    }

    public SongBindingModel setStyle(StyleNameEnum style) {
        this.style = style;
        return this;
    }
}
