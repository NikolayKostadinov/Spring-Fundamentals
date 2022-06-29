package bg.manhattan.spotifyplaylist.service.impl;

import bg.manhattan.spotifyplaylist.model.entity.Song;
import bg.manhattan.spotifyplaylist.model.entity.User;
import bg.manhattan.spotifyplaylist.model.service.UserServiceLoginModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceModel;
import bg.manhattan.spotifyplaylist.model.service.UserServiceSongModel;
import bg.manhattan.spotifyplaylist.repository.UserRepository;
import bg.manhattan.spotifyplaylist.security.CurrentUser;
import bg.manhattan.spotifyplaylist.service.SongService;
import bg.manhattan.spotifyplaylist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;
    private final SongService songService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper mapper,
                           CurrentUser currentUser,
                           SongService songService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
        this.songService = songService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserServiceModel userModel) {
        User user = this.mapper.map(userModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public boolean loginUser(UserServiceLoginModel userModel) {
        Optional<User> dbUser = this.userRepository.findByUsername(userModel.getUsername());
        if (dbUser.isEmpty()) {
            return false;
        }

        boolean matches = this.passwordEncoder.matches(userModel.getPassword(), dbUser.get().getPasswordHash());
        if (!matches) {
            return false;
        }

        User user = dbUser.get();
        this.currentUser
                .setId(user.getId())
                .setUsername(user.getUsername());
        return true;
    }

    @Override
    public void logout() {
        this.currentUser.clear();
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser.isLoggedIn();
    }

    @Override
    public Optional<User> getCurrentUser() {
        return this.userRepository.findById(this.currentUser.getId());
    }

    @Override
    public String getCurrentUserName() {
        return this.currentUser.getUsername();
    }

    @Override
    public Optional<UserServiceModel> getUserByUsername(String userName) {
        Optional<User> user = this.userRepository.findByUsername(userName);
        return toUserServiceModel(user);
    }

    @Override
    public Optional<UserServiceModel> getUserByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        return toUserServiceModel(user);
    }

    @Override
    public List<UserServiceSongModel> getPlaylist() {
        User user = this.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("No user logged in"));
        return user.getPlaylist()
                .stream()
                .map(song -> this.mapper.map(song, UserServiceSongModel.class))
                .toList();
    }

    @Override
    public void addToPlaylist(Long id) {
        Song song = this.songService.getById(id);
        User user = this.getCurrentUser().orElse(null);
        if (song != null && user != null){
            user.getPlaylist().add(song);
            this.userRepository.save(user);
        }
    }

    @Override
    public void clearPlaylist() {
        User user = this.getCurrentUser().orElse(null);
        if (user != null){
            user.getPlaylist().clear();
            this.userRepository.save(user);
        }
    }

    private Optional<UserServiceModel> toUserServiceModel(Optional<User> user) {
        return user.map(value -> this.mapper.map(value, UserServiceModel.class));
    }
}
