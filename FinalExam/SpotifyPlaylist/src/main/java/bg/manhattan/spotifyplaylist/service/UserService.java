package bg.manhattan.spotifyplaylist.service;

import bg.manhattan.spotifyplaylist.model.entity.User;
import bg.manhattan.spotifyplaylist.model.service.UserServiceSongModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceLoginModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(UserServiceModel userModel);

    boolean loginUser(UserServiceLoginModel userModel);

    void logout();

    boolean isLoggedIn();

    Optional<User> getCurrentUser();

    String getCurrentUserName();

    Optional<UserServiceModel> getUserByUsername(String userName);

    Optional<UserServiceModel> getUserByEmail(String email);

    List<UserServiceSongModel> getPlaylist();

    void addToPlaylist(Long id);
    void clearPlaylist();
}
