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
                           ModelMapper mapper, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
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
}
