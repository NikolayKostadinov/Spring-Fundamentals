package bg.manhattan.spotifyplaylist.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    /**
     * Username (unique, not null)
     * Username length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @Column(nullable = false, length = 21, unique = true)
    private String username;

    /**
     * Password (not null)
     * Password length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @Column(name = "password", nullable = false)
    private String passwordHash;

    /**
     * Email (unique, not null)
     * The values should contain a '@' symbol
     */
    @Column(nullable = false, unique = true)
    private String email;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Song> playlist;

    public String getPasswordHash() {
        return passwordHash;
    }

    public User setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Song> getPlaylist() {
        return playlist;
    }

    public User setPlaylist(Set<Song> playlist) {
        this.playlist = playlist;
        return this;
    }
}
