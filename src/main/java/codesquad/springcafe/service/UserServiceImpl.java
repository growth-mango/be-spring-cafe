package codesquad.springcafe.service;

import codesquad.springcafe.dto.User;
import codesquad.springcafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signup(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
