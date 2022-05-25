package bg.softuni.intro.cats.model.entity;

import javax.persistence.*;

@Entity
@Table(name="cats")
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String catName;

    @ManyToOne
    private OwnerEntity owner;

    public Long getId() {
        return id;
    }

    public void setId(Long catId) {
        this.id = catId;
    }

    public String getCatName() {
        return catName;
    }

    public CatEntity setCatName(String catName) {
        this.catName = catName;
        return this;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public CatEntity setOwner(OwnerEntity owner) {
        this.owner = owner;
        return this;
    }
}
