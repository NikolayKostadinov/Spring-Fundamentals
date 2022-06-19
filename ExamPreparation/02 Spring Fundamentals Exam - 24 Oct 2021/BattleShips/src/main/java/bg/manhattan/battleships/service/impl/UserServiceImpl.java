package bg.manhattan.battleships.service.impl;

import bg.manhattan.battleships.model.entity.User;
import bg.manhattan.battleships.model.service.UserServiceLoginModel;
import bg.manhattan.battleships.model.service.UserServiceModel;
import bg.manhattan.battleships.repository.UserRepository;
import bg.manhattan.battleships.security.CurrentUser;
import bg.manhattan.battleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    private final CurrentUser currentUser;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper mapper,
                           CurrentUser currentUser,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserServiceModel userModel) {
        User user = this.mapper.map(userModel, User.class);
        this.userRepository.save(user);
    }

    @Override
    public boolean loginUser(UserServiceLoginModel userModel) {
        Optional<User> user = this.userRepository.findByUsername(userModel.getUsername());
        if (user.isEmpty()) {
            return false;
        }

        boolean matches = this.passwordEncoder.matches(userModel.getPassword(), user.get().getPasswordHash());
        if (!matches) {
            return false;
        }

        this.currentUser
                .setUsername(user.get().getUsername());

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

        return this.userRepository.findByUsername(this.currentUser.getUsername());
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

    private Optional<UserServiceModel> toUserServiceModel(Optional<User> user) {
        return user.isPresent() ?
                Optional.of(this.mapper.map(user.get(), UserServiceModel.class))
                : Optional.empty();
    }
}
