package bg.manhattan.spotifyplaylist.model.binding;



import bg.manhattan.spotifyplaylist.model.validator.FieldMatch;
import bg.manhattan.spotifyplaylist.model.validator.UniqueEmail;
import bg.manhattan.spotifyplaylist.model.validator.UniqueUserName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "confirmPassword", message = "Password and Confirm password must be the same!")
public class UserRegisterBindingModel {

    /**
     * Username (unique, not null)
     * Username length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @NotNull(message = "Username length must be between 3 and 20 characters!")
    @Size(min=3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @UniqueUserName(message = "Username must be unique.")
    private String username;

     /**
     * Email
     * Must contains '@'.  Cannot be null.
     */
    @NotNull( message = "Email cannot be empty")
    @Email(regexp = "^(\\w+@\\w+)(.\\w+){2,}$", message = "Enter valid email address")
    @UniqueEmail(message="Email must be unique.")
    private String email;

    /**
     * Password (not null)
     * Password length must be between 3 and 20 characters (inclusive of 3 and 20).
     */
    @NotNull(message = "Password length must be between 3 and 20 characters!")
    @Size(min=3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;

    @NotNull(message = "Confirm password length must be between 3 and 20 characters!")
    @Size(min=3, max = 20, message = "Confirm password length must be between 3 and 20 characters!")
    private String confirmPassword;



    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
