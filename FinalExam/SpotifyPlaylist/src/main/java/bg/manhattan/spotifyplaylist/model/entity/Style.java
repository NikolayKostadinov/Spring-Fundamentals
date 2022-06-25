package bg.manhattan.spotifyplaylist.model.entity;


import bg.manhattan.spotifyplaylist.model.entity.enums.StyleNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private StyleNameEnum name;

    @Column(columnDefinition = "TEXT")
    private String Description;

    public StyleNameEnum getName() {
        return name;
    }

    public Style setName(StyleNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public Style setDescription(String description) {
        Description = description;
        return this;
    }
}
