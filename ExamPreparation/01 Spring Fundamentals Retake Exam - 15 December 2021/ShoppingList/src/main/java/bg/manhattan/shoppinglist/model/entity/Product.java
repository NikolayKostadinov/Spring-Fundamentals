package bg.manhattan.shoppinglist.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {
    /**
     * Has an Id – UUID-string or Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Has a Name (unique)
     *     Name length must be between 3 and 20 characters (inclusive 3 and 20).
     */
    @Column(nullable = false, unique = true, length = 21)
    private String name;

    /**
     * Has a Description
     * Description min length must be minimum 5(inclusive) characters
     */
    @Column(nullable = false)
    private String description;

    /**
     * Has a Price
     * The price must be a positive number
     */
    @Column(nullable = false)
    private BigDecimal Price;

    /**
     * Date and Time, that cannot be in the past
     */
    @Column(name="needed_before",nullable = false)
    private LocalDateTime neededBefore;

    /**
     * Category cannot be null
     */
    @ManyToOne(optional = false)
    private Category category;
}
