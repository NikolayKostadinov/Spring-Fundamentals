package bg.manhattan.musicdb.model.entity;

import bg.manhattan.musicdb.model.entity.enums.Genre;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="albums")
public class Album extends BaseEntity{
    /**
     *  Name length must be between 3 and 20 characters (inclusive 3 and 20).
     */
    @Column(nullable = false)
    private String name;

    /**
     * Image Url length must be minimum 5(inclusive) characters.
     */
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    /**
     * Description min length must be minimum 5(inclusive) characters
     * The description is a long text field.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * All sold copies of the album
     * Must be a more than 10(inclusive).
     */
    @Column(nullable = false)
    private Integer copies;

    /**
     * Has a Price
     * Price must be a positive number
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * Date that cannot be in the future
     */
    @Column(name="released_date", nullable = false)
    private LocalDate releasedDate;

    /**
     * Name of producer
     * Can be null.
     */
    @Column(nullable = true)
    private String producer;

    /**
     * This is just an enumeration, not entity.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Genre genre;

    /**
     * This is the relation with artists
     */
    @ManyToOne(optional = false)
    private Artist artist;

    @ManyToOne(optional = false)
    private User addedFrom;
}
