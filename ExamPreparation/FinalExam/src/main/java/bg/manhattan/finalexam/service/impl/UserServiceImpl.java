package bg.manhattan.finalexam.service.impl;

import bg.manhattan.finalexam.model.entity.User;
import bg.manhattan.finalexam.model.service.UserServiceLoginModel;
import bg.manhattan.finalexam.model.service.UserServiceModel;
import bg.manhattan.finalexam.repository.UserRepository;
import bg.manhattan.finalexam.security.CurrentUser;
import bg.manhattan.finalexam.service.UserService;
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
    public Optional<UserServiceModel> getCurrentUser() {
        Optional<User> user = this.userRepository.findById(this.currentUser.getId());
        return toUserServiceModel(user);
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
