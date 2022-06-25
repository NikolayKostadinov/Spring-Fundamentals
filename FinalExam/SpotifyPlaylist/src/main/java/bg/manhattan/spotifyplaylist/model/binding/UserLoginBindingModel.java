package bg.manhattan.spotifyplaylist.model.binding;

import bg.manhattan.spotifyplaylist.model.validator.UniqueUserName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    /**
     * Username (unique, not null)
     * Username length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @NotNull(message = "Username length must be between 3 and 20 characters!")
    @Size(min=3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    /**
     * Password (not null)
     * Password length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @NotNull(message = "Password length must be between 3 and 20 characters!")
    @Size(min=3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
